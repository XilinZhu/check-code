# check-code
Based on the spring boot framework and kaptcha module, a verification code function in the sign-in page is developed. When users click to confirm the code is difficult to recognize, the page will display a simpler picture through my own function.

## 环境配置

- VS Code
- Maven + Tomcat
- [参考链接](https://blog.csdn.net/qq_43145926/article/details/105016919?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduend~default-1-105016919.nonecase&utm_term=vscode%E5%A6%82%E4%BD%95%E9%83%A8%E7%BD%B2tomcat&spm=1000.2123.3001.4430)

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

   <img src="https://i.loli.net/2020/10/13/ZihbIfgKNuJ9VlF.png" style="zoom:50%;" />

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

   <img src="https://i.loli.net/2020/10/13/N1skMeVgEPjm7uB.png" style="zoom:50%;" />

## 相关问题与解决

- [maven无法在中央仓库找到 `com.google.code.kaptcha:kaptcha:jar:2.3`](https://blog.csdn.net/lancelet223/article/details/78941489)

