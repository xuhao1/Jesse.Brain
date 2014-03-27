#Medulla底层控制与通信协议
Medulla为底层模块，主要负责驱动，位置修正，获得GPS数据等。

##Host2Device

开机自检:

    $HELLO,WORLD;

设置电机i的大小为f,i从0-3，f为0-1

    $SET:MOTOR_i:%f

修正坐标:

    $SETVAL:POSI:(%f,%f,%f);

速度修正:

    $SETVAL:VELO:(%f,%f,%f);

设置舵机:

    $SETVAL:SERV:%f;

##Device2Host

完成自检:

    $HELLO,WORLD;

###直接测量量    

气压计

    $VAL:BARO:%f;

温度

    $VAL:TEMP:%f;
    
加速度

    $VAL:ACCE:(%f,%f,%f);

旋转

    $VAL:ROTA:(%f,%f,%f);

磁场

    $VAL:MAGN:(%f,%f,%f);

超声波
    
    $VAL:SUPE:%d,%f;

###积分量

角度

    $VAL:ANGL:((%f,%f),(%f,%f),(%f,%f));
速度

    $VAL:VELO:((%f,%f),(%f,%f),(%f,%f));
位置

    $VAL:POSI:((%f,%f),(%f,%f),(%f,%f));

##电机分配算法

    AP_MotorsMatrix 
    //call raw motor set-up method
    add_motor_raw(
        motor_num,
        cosf(radians(angle_degrees + 90)),               // roll factor
        cosf(radians(angle_degrees)),                    // pitch factor
        yaw_factor,                                      // yaw factor
        testing_order);
X mode
    
    #define AP_MOTORS_MATRIX_YAW_FACTOR_CW   -1
    #define AP_MOTORS_MATRIX_YAW_FACTOR_CCW   1 
    
    add_motor(AP_MOTORS_MOT_1,   45, AP_MOTORS_MATRIX_YAW_FACTOR_CCW, 1);
    add_motor(AP_MOTORS_MOT_2, -135, AP_MOTORS_MATRIX_YAW_FACTOR_CCW, 3);
    add_motor(AP_MOTORS_MOT_3,  -45, AP_MOTORS_MATRIX_YAW_FACTOR_CW,  4);
    add_motor(AP_MOTORS_MOT_4,  135, AP_MOTORS_MATRIX_YAW_FACTOR_CW,  2);

\+ mode
    
    add_motor(AP_MOTORS_MOT_1,  90, AP_MOTORS_MATRIX_YAW_FACTOR_CCW, 2);
    add_motor(AP_MOTORS_MOT_2, -90, AP_MOTORS_MATRIX_YAW_FACTOR_CCW, 4);
    add_motor(AP_MOTORS_MOT_3,   0, AP_MOTORS_MATRIX_YAW_FACTOR_CW,  1);
    add_motor(AP_MOTORS_MOT_4, 180, AP_MOTORS_MATRIX_YAW_FACTOR_CW,  3);

APM中是线性调整的电机，映射为四个通道，更容易理解的操作方式，但是因为引入了一级近似，并没有对每个螺旋桨进行单独建模，虽然其通用性更加，但是损失了很大的精确控制可能性。

在作为前期定位手段的应用中，这套简单的自动控制可以使用。

首要目标是，在前期飞行中获得更加精确的飞行器数学模型。