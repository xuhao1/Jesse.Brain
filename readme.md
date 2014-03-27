# Jesse无人机的控制核心
## 控制层次：
 - 与地面通信
 - 飞行器
 - 底层通信

## 与地面通信
获得飞行路径点，设定飞行目标

飞行目标分为：
 - 起飞
 - 降落
 - 巡航飞行
 - 飞行至某点
 - 悬停

并且回传数据

## 飞行器高层
人工智能算法（调度所有部分）

参考多传感器信息决定飞行方式,并将飞行规划作业交给底层

（作业规则：抢占，队列，初始化）

## 飞行器底层（要求实时性）
根据姿态调整飞行
详见(Cerebellum)[./doc/Cerebellum.md]
## 底层通信
将飞行姿态发送给单片机进行执行
获得差分GPS以及光流数据
详见(Medulla)[./doc/Medulla.md]