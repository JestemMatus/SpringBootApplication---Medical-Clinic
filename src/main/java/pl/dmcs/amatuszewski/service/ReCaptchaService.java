package pl.dmcs.amatuszewski.service;

public interface ReCaptchaService {
    boolean verify(String captcha);
}
