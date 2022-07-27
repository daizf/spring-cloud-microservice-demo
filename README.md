# spring-cloud-microservice-demo
基于Spring Cloud微服务框架Demo，采用Erueka作为注册中心，服务直接采用Feign进行调用。同时集成了actuator、hystrix。

## Service list
- UserService
- ProductService
- OrderService

## Call chain
```
UserService -> OrderService -> ProductService
```

## Docker Image
- eureka server
```
cd microservice-discovery-eureka
docker build -t eureka-server:0.0.1 .
```
- 以 microservice-user-service 为例：
```
cd microservice-user-service
docker build -t user-service:0.0.1 . 
```

## Docker Run
环境变量```EUREKA_SERVICE_URL```为eureka注册中心地址 
```
docker run -p 8761:8761 --name eureka-server -d eureka:0.0.1
docker run -e EUREKA_SERVICE_URL=http://30.43.72.102:8761  -p 8081:8080 --name user-service -d user-service:0.0.1
docker run -e EUREKA_SERVICE_URL=http://30.43.72.102:8761  -p 8081:8080 --name product-service -d product-service:0.0.1
```

## K8S Run
```
kubectl create configmap micro-config --from-literal=EUREKA_SERVICE_URL=http://eureka-server:8761
kubectl create -f microservice-gateway-zuul/yaml/*.yaml
kubectl create -f microservice-user-service/yaml/*.yaml
```

## 验证
```shell script
curl http://localhost:8080/user/1
```
```json
{
    "id": 1,
    "username": "account1",
    "name": "张三",
    "age": 20,
    "balance": 100.00,
    "orders":
    [
        {
            "id": 1,
            "uid": 1,
            "num": 10,
            "fee": 39990.00,
            "product":
            {
                "id": 1,
                "name": "vivo X21",
                "price": 3999.00,
                "remark": "vivo X21 全面屏 双摄拍照游戏手机"
            }
        },
        {
            "id": 4,
            "uid": 1,
            "num": 1,
            "fee": 4999.00,
            "product":
            {
                "id": 2,
                "name": "vivo Nex One",
                "price": 4999.00,
                "remark": "vivo NEX 双屏版 AI三摄 游戏手机 10GB+128GB 冰原蓝"
            }
        }
    ]
}
```
