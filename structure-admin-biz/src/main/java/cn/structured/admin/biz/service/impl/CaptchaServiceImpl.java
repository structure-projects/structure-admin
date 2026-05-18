package cn.structured.admin.biz.service.impl;

import cn.structured.admin.api.dto.CaptchaDTO;
import cn.structured.admin.biz.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String CAPTCHA_PREFIX = "captcha:";
    private static final int CAPTCHA_EXPIRE_SECONDS = 120;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int CODE_LENGTH = 4;
    private static final char[] CODE_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    @Override
    public CaptchaDTO generateCaptcha() {
        String captchaId = UUID.randomUUID().toString();
        String code = generateRandomCode();
        
        stringRedisTemplate.opsForValue()
                .set(CAPTCHA_PREFIX + captchaId, code, CAPTCHA_EXPIRE_SECONDS, TimeUnit.SECONDS);
        
        String imageBase64 = generateCaptchaImage(code);
        
        return CaptchaDTO.builder()
                .captchaId(captchaId)
                .imageBase64(imageBase64)
                .expireSeconds((long) CAPTCHA_EXPIRE_SECONDS)
                .build();
    }

    @Override
    public boolean validateCaptcha(String captchaId, String code) {
        if (captchaId == null || code == null) {
            return false;
        }
        
        String key = CAPTCHA_PREFIX + captchaId;
        String storedCode = stringRedisTemplate.opsForValue().get(key);
        
        if (storedCode == null) {
            return false;
        }
        
        boolean isValid = storedCode.equalsIgnoreCase(code.trim());
        
        if (isValid) {
            removeCaptcha(captchaId);
        }
        
        return isValid;
    }

    @Override
    public void removeCaptcha(String captchaId) {
        stringRedisTemplate.delete(CAPTCHA_PREFIX + captchaId);
    }

    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CODE_CHARS[random.nextInt(CODE_CHARS.length)]);
        }
        return sb.toString();
    }

    private String generateCaptchaImage(String code) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(getRandomColor(100, 200));
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int w = random.nextInt(10);
            int h = random.nextInt(10);
            g.drawRect(x, y, w, h);
        }
        
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            image.setRGB(x, y, getRandomColor(0, 255).getRGB());
        }
        
        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            g.setColor(getRandomColor(50, 150));
            int x = 15 + i * 20;
            int y = 28 + new Random().nextInt(4) - 2;
            g.drawString(String.valueOf(c), x, y);
        }
        
        g.dispose();
        
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", outputStream);
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            log.error("生成验证码图片失败", e);
            return null;
        }
    }

    private Color getRandomColor(int min, int max) {
        Random random = new Random();
        int r = min + random.nextInt(max - min);
        int g = min + random.nextInt(max - min);
        int b = min + random.nextInt(max - min);
        return new Color(r, g, b);
    }
}