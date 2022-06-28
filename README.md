# 小蜜蜂医疗

## 简介

小蜜蜂医疗是一款移动医疗 APP ，前台 APP 使用 Vue + Vant 组件库开发，后台管理系统使用 Vue + Element UI 组件库开发，后台采用Spring Boot + Spring Cloud 的微服务的架构模式进行开发，整个项目的部署采用 Docker 进行部署。

实现了在线预约挂号、在线支付(支付宝支付)、在线咨询、医院导航、健康指导等功能。

- [前台 APP 的地址](https://github.com/lostblackknight/mobile-health-app)
- [后台管理系统的地址](https://github.com/lostblackknight/mobile-health-admin)
- [后台地址](https://github.com/lostblackknight/mobile-health)

## 整体架构图

![image-20220628131011563](https://s2.loli.net/2022/06/28/UOhBw7a3I2yZCJY.png)

## 功能模块图

![功能模块](https://s2.loli.net/2022/06/28/5nMSLwgJHy9z2UT.png)

## 目录结构

```shell
bee-health   
├── bee-health-common                     # 公共部分
│       └── constant                      # 常量
│       └── model                         # 数据模型
│       └── properties                    # 属性
│       └── util                          # 工具类
├── bee-health-admin                      # 后台服务
├── bee-health-gateway                    # 网关服务
├── bee-health-hospital                   # 医院服务
├── bee-health-member                     # 会员服务
├── bee-health-message                    # 消息服务
├── bee-health-mock                       # 医院客户端模拟
├── bee-health-order                      # 订单服务
├── bee-health-search                     # 检索服务
├── bee-health-third-party                # 第三方服务
├── bee-health-topic                      # 主题服务
```

## Docker 部署

### 环境要求

1. 虚拟机或服务器的内存务必保证有 6 G 及以上，磁盘保证有 40 G 及以上。
2. docker、docker-compose、git 环境。

### 后台部署

1. 安装项目。

   ```bash
   git clone https://github.com/lostblackknight/mobile-health.git
   ```

2. 部署组件。

   > 提示：如果 Nacos 服务无法访问，可以使用 `docker restart <container_id>` 命令重启服务

   ```bash
   cd mobile-health
   ```

   ```shell
   docker-compose -f docker-compose-e.yml up -d
   ```

3. 部署服务。

   > 注意：务必保证部署的组件全部启动成功后再部署

   ```bash
   docker-compose -f docker-compose-app.yml up -d
   ```

### 后台管理系统部署

1. 安装项目。

   ```bash
   git clone https://github.com/lostblackknight/mobile-health-admin.git
   ```

2. 修改后台 API 的地址。

   ```bash
   cd mobile-health-admin
   ```

   >  修改 `BASE_API_HOST` 环境变量

   ```bash
   vim docker-compose.yml
   ```

3. 部署项目。

   ```bash
   docker-compose up -d
   ```

### 前台APP部署

1. 安装项目。

   ```bash
   git clone https://github.com/lostblackknight/mobile-health-app.git
   ```

2. 修改后台 API 的地址。

   ```bash
   cd mobile-health-app
   ```

   > 修改 `BASE_API_HOST` 环境变量

   ```bash
   vim docker-compose.yml
   ```

3. 部署项目。

   ```bash
   docker-compose up -d
   ```

## 上传测试数据

### 上传医院数据到ES

1. 打开 `bee-health-mock` 项目中的 `BeeHealthTemplate` 类，修改`HOSPITAL_API` `TOKEN_API`中的 host 和 port 为网关的 host 和 port。

   ![image-20220628181931980](https://s2.loli.net/2022/06/28/CqF6MiU2cvTwzOf.png)

2. 启动项目 `bee-health-mock` ，发送 GET 请求。(推荐使用 Postman 发送)

   ```tex
   http://localhost:7011/hospitals/upload?clientId=1a903469ecdb481ea05651be04312ad0&secret=d6b382c1-2524-49d5-b1dc-2c01790bbbd8&hospitalName=北京天使儿童医院
   ```
   
   ```
   http://localhost:7011/hospitals/upload?clientId=cd50350b4fa84d2b87d5cb6f4342b7b2&secret=a07ada8f-4f82-4af1-aafc-7d7948198a9e&hospitalName=太原肤康中医皮肤病医院
   ```

### 上传科室数据到ES

1. 启动项目 `bee-health-mock` ，发送 GET 请求。

   ```tex
   http://localhost:7011/depts/upload?clientId=1a903469ecdb481ea05651be04312ad0&secret=d6b382c1-2524-49d5-b1dc-2c01790bbbd8&hospitalName=北京天使儿童医院
   ```
   
   ```
   http://localhost:7011/depts/upload?clientId=cd50350b4fa84d2b87d5cb6f4342b7b2&secret=a07ada8f-4f82-4af1-aafc-7d7948198a9e&hospitalName=太原肤康中医皮肤病医院
   ```

### 上传排班数据到ES

1. 启动项目 `bee-health-mock` ，发送 GET 请求。

   ```tex
   http://localhost:7011/schedules/upload?clientId=1a903469ecdb481ea05651be04312ad0&secret=d6b382c1-2524-49d5-b1dc-2c01790bbbd8&hospitalName=北京天使儿童医院
   ```
   
   ```
   http://localhost:7011/schedules/upload?clientId=cd50350b4fa84d2b87d5cb6f4342b7b2&secret=a07ada8f-4f82-4af1-aafc-7d7948198a9e&hospitalName=太原肤康中医皮肤病医院
   ```

### 上传文章数据到ES

1. 文章数据已经存在在数据库中，一共有十条数据。进入后台管理系统文章列表功能页面，点击修改按钮，再点击确定，将文章上传到 ES 中。

## 短信服务接入

1. 在阿里云平台申请相关的服务。

2. 在 nacos 中的  `bee-health-third-party-prod.yaml` 中进行配置。

3. 默认使用测试短信，可以在  `bee-health-third-party` 模块中的`SmsCodeTemplate`中修改，然后编译打包放到 `docker/app/third-party` 中重新部署。

   ```yaml
   aliyun:
     sms:
       access-key-id: xxx
       access-key-secret: xxx
       endpoint: xxx
   ```

## OSS 服务接入

1. 在阿里云平台申请相关服务。

2. 在 nacos 中的  `bee-health-third-party-prod.yaml` 中进行配置。

   ```yaml
   aliyun:
     oss:
       endpoint: xxx
       access-key-id: xxx
       access-key-secret: xxx
       bucket-name: xxx
   ```

## 支付服务接入

1. 在支付宝开放平台创建相关的应用。

2. 在 nacos 中的  `bee-health-order-prod.yaml` 中进行配置。

   ```yaml
   alipay:
     app-id: xxx
     gateway-url: xxx
     alipay-public-key: xxx
     app-private-key: xxx
     notify-url: http://39.106.85.222:7001/api/order/payment/notify
     return-url: http://localhost:9527/payment
   ```

3. `notify-url`需要公网可以访问，可以使用内网穿透技术实现。

   - 有服务器，可以使用frp。
   - 没有服务器，可以使用花生壳。