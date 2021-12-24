package com.highcom.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Component
public class KaptchaConfig {

	
	@Bean(name = "captchaProducer")
	public DefaultKaptcha getDefaultKaptcha(){
		com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.border", "no");
		properties.setProperty("kaptcha.border.color", "white");
		properties.setProperty("kaptcha.textproducer.font.color", "black");
		properties.setProperty("kaptcha.image.width", "110");
		properties.setProperty("kaptcha.image.height", "46");
		properties.setProperty("kaptcha.textproducer.char.string","23456789abcdefghkmnpqrstuvwxyzABCDEFGHKMNPRSTUVWXYZ");
		properties.setProperty("kaptcha.textproducer.font.size", "45");
		properties.setProperty("kaptcha.textproducer.char.space","2");
		properties.setProperty("kaptcha.session.key", "code");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.background.clear.from", "white");
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		// 这里 是去掉 噪点颜色
		properties.setProperty("kaptcha.noise.color", "black");
		// 这是 是图片样式配置 原生的有三种 水纹 、 鱼眼 、 阴影
		// 这里是 我们自己实现的一个 也就是 样式自定义
		//properties.setProperty("kaptcha.obscurificator.impl","com.highcom.utils.NoWaterRipple");
		// 配置使用原生的 无噪 实现类 NoNoise
		properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);

		return defaultKaptcha;
	}
}
/*
  <!-- google kaptcha的相关配置-->
  <bean id="captchaProducer" class="com.google.code.kaptcha.impl.DefaultKaptcha">  
      <property name="config">  
          <bean class="com.google.code.kaptcha.util.Config">  
              <constructor-arg>  
                  <props> 
                      <!-- 是否有边框 可选yes 或者 no --> 
                      <prop key="kaptcha.border">no</prop>  
                      <!-- 边框颜色 -->
                      <prop key="kaptcha.border.color">white</prop>  
                      <!-- 验证码文本字符颜色 -->
                      <prop key="kaptcha.textproducer.font.color">black</prop>  
                      <!-- 验证码文本字符大小 -->
                      <prop key="kaptcha.textproducer.font.size">45</prop>  
                      <!-- 验证码图片的宽度 默认200 -->
                      <prop key="kaptcha.image.width">100</prop>  
                      <!-- 验证码图片的高度 默认50 -->
                      <prop key="kaptcha.image.height">46</prop>  
                      <!-- 验证码文本字符长度  默认为5 -->
                      <prop key="kaptcha.textproducer.char.length">4</prop>  
                      <!-- 验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)  -->
                      <prop key="kaptcha.textproducer.font.names">宋体</prop>  
                      <prop key="kaptcha.background.clear.from">white</prop>  
                      <prop key="kaptcha.noise.color">255,96,0</prop>
                      <prop key="kaptcha.obscurificator.impl">com.highcom.utils.NoWaterRipple</prop>  
                  </props>  
              </constructor-arg>  
          </bean>  
      </property>
      <!-- 
      	Constant				描述					默认值
		kaptcha.border	图片边框，合法值：yes , no	yes
		kaptcha.border.color	边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.	black
		kaptcha.border.thickness	边框厚度，合法值：>0	1
		kaptcha.image.width	图片宽	200
		kaptcha.image.height	图片高	50
		kaptcha.producer.impl	图片实现类	com.google.code.kaptcha.impl.DefaultKaptcha
		kaptcha.textproducer.impl	文本实现类	com.google.code.kaptcha.text.impl.DefaultTextCreator
		kaptcha.textproducer.char.string	文本集合，验证码值从此集合中获取	abcde2345678gfynmnpwx
		kaptcha.textproducer.char.length	验证码长度	5
		kaptcha.textproducer.font.names	字体	Arial, Courier
		kaptcha.textproducer.font.size	字体大小	40px
		kaptcha.textproducer.font.color	字体颜色，合法值： r,g,b  或者 white,black,blue.	black
		kaptcha.textproducer.char.space	文字间隔	2
		kaptcha.noise.impl	干扰实现类	com.google.code.kaptcha.impl.DefaultNoise
		kaptcha.noise.color	干扰颜色，合法值： r,g,b 或者 white,black,blue.	black
		kaptcha.obscurificator.impl	图片样式：
											水纹com.google.code.kaptcha.impl.WaterRipple
											鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
											阴影com.google.code.kaptcha.impl.ShadowGimpy	com.google.code.kaptcha.impl.WaterRipple
		kaptcha.background.impl	背景实现类	com.google.code.kaptcha.impl.DefaultBackground
		kaptcha.background.clear.from	背景颜色渐变，开始颜色	light grey
		kaptcha.background.clear.to	背景颜色渐变，结束颜色	white
		kaptcha.word.impl	文字渲染器	com.google.code.kaptcha.text.impl.DefaultWordRenderer
       -->  
  </bean>
 */
