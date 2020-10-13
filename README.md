# check-code
Based on Spring Boot framework and Kaptcha module, a verification code function for the sign-in page is developed. When users click to confirm the code is difficult to recognize, the page will display a simpler picture through my own function.

## 环境配置

- VS Code
- Maven + Thymeleaf + Tomcat
- [配置细节参考](https://blog.csdn.net/qq_43145926/article/details/105016919?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduend~default-1-105016919.nonecase&utm_term=vscode%E5%A6%82%E4%BD%95%E9%83%A8%E7%BD%B2tomcat&spm=1000.2123.3001.4430)

## 项目目录

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂cn
 ┃ ┃ ┃ ┗ 📂zxltech
 ┃ ┃ ┃ ┃ ┗ 📂checkcode
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckcodeApplication.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜KaptchaConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜KaptchaController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MyGetImage.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📜index.html
 ┃ ┃ ┃ ┗ 📜login.html
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
```

- 此目录通过 VS Code 插件 `file-tree-generator` 生成

## 功能介绍

1. 用户访问服务器时，会被重定向到登录页面——`LoginController.java`

   <img src="https://i.loli.net/2020/10/13/ZihbIfgKNuJ9VlF.png" height="200" />

2. 提示用户输入账号、密码与验证码。提交表单时，服务器端将检测三个输入框是否为空，并检测验证码是否正确。任何一项不满足时，网页都将以红字作相应提示。当验证码输入正确时，页面将跳转至“验证成功”页面——`KaptchaConfig.java` `KaptchaController.java`

   <table> 
       <tr>
           <td><center><img src = "https://i.loli.net/2020/10/13/PiTd72OF3lAqIU8.png" height="280" ></center><br>
               <center>图1 错误提示</center>
           </td>
           <td><center><img src = "https://i.loli.net/2020/10/13/POJ36YmKoTelDAj.png",height="300"></center><br>
           	<center>图2 验证成功</center>
           </td>
       </tr>
   </table>

   

3. 用户可以点击 <u>看不清？点击图片刷新一下</u> 来重新获得一个辨认难度更低的验证码图片—— `MyGetImage.java`

   <img src="https://i.loli.net/2020/10/13/N1skMeVgEPjm7uB.png" height="280" />

## 实现细节

1. 基于 Spring Boot 创建 Maven 项目，默认打包为 `.jar`
2. 使用 `Thymeleaf` 作为模板来创建视图。`Thymelead` 相较于 `JSP` 的优势是：模板即原型，前后端分离，劣势是效率较低。
3. 使用 `Bootstrap` 来渲染输入框、提交按钮等组件。`Bootstrap` 是 Twitter 推出的一个前端开发框架。
4. 使用 `Servlet` 作为HTTP 客户端和 HTTP 服务器上之间的中间层。
5. `kaptach` 是一个可配置的实用验证码生成、验证工具。使用 `kaptcha` 的产生验证码的基本环节包括：
   1. 配置 `kaptcha` 
   2. 生成验证码
   3. 将验证码文本写入 `session`
   4. 验证码图片以字节流的形式写入 `response` 的缓冲区
6. 为了防止作弊，避免浏览器缓存验证码信息，利用 `servle` 在 HTTP 响应报头进行设置，如 `response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");`。
7. 使用 `Servlet.ModelAndView` 来处理用户提交的表单
8. 关于刷新验证码：当用户点击 <u>看不清？点击图片刷新一下</u>时，通过 `new Date()*1` 作为参数发送到服务器。在调用 `kaptcha` 生成验证码之前，服务器程序会首先检测此参数，若不为空，则将调用 `MyGetImage.outputImage()` 来生成一个更易辨识的验证码。
9. `MyGetImage.outputImage()` 主要利用 `java.awt.Graphics2D` 实现了一个仿射变换。 

## 相关问题与解决 

- [maven无法在中央仓库找到 `com.google.code.kaptcha:kaptcha:jar:2.3`](https://blog.csdn.net/lancelet223/article/details/78941489)

