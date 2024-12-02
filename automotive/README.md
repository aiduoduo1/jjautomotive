# Android Automotive 开发环境

这是一个针对Android Automotive OS开发的基础项目环境配置。

## 环境配置

### 构建工具版本
- Gradle: 8.2
- Android Gradle Plugin (AGP): 8.1.2
- Kotlin: 1.8.20

### SDK 配置
- compileSdk: 33 (Android 13)
- minSdk: 29 (Android 10/Q)
- targetSdk: 33 (Android 13)

### Java 环境
- Java版本: 17
- Kotlin JVM Target: 17

## 主要依赖库版本 
oml
- androidx-core-ktx: 1.12.0
- androidx-appcompat: 1.6.1
- android-material: 1.8.0
- androidx-car-ui: 2.5.1

## 项目结构
automotive/
├── build.gradle # 根项目构建配置
├── build.gradle.kts # 主模块构建配置
├── gradle/
│ └── libs.versions.toml # 依赖版本管理
└── gradle.properties # Gradle属性配置


## 版本选择说明

1. **Gradle 8.2**:
    - 稳定性好
    - 与AGP 8.1.2完美配合
    - 构建速度优秀

2. **AGP 8.1.2**:
    - 经过充分验证的稳定版本
    - 支持最新的Android车载功能
    - 与Gradle 8.2兼容性最佳

3. **Kotlin 1.8.20**:
    - 与AGP 8.1.2匹配良好
    - 提供稳定的协程支持
    - 支持最新的Kotlin特性

4. **SDK 版本**:
    - minSdk 29确保与大多数车载设备兼容
    - targetSdk 33提供最新的API支持
    - compileSdk 33支持最新的编译特性

## 注意事项

1. 确保本地安装了Java 17
2. Android Studio版本建议使用最新的稳定版
3. 首次导入项目后，建议执行一次Gradle sync
4. 如需支持更老的车载设备，可以考虑调整minSdk版本

## 更新日志

### 2024-03
- 初始化项目环境
- 配置基础构建脚本
- 添加车载UI相关依赖