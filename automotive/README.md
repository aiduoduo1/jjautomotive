# Android Automotive 开发环境

这是一个针对Android Automotive OS开发的基础项目环境配置。

## 环境配置

### 构建工具版本
- Gradle: 8.4
- Android Gradle Plugin (AGP): 8.1.4
- Kotlin: 1.9.10
- Build Tools: 34.0.0

### SDK 配置
- compileSdk: 34 (Android 14)
- minSdk: 29 (Android 10/Q)
- targetSdk: 34 (Android 14)

### Java 环境
- Java版本: 17
- Kotlin JVM Target: 17

## 主要依赖库版本
- androidx-core-ktx: 1.12.0
- androidx-appcompat: 1.6.1
- android-material: 1.8.0
- compose-bom: 2023.08.00
- compose-compiler: 1.5.3
- compose-plugin: 1.5.3
- activity-compose: 1.7.2
- lifecycle-runtime-ktx: 2.6.2
- material3: 1.1.2
- material-icons: 1.5.4
- activity: 1.8.2

## 项目结构
automotive/
├── build.gradle.kts # 主模块构建配置
├── gradle/
│ └── libs.versions.toml # 依赖版本管理
└── gradle.properties # Gradle属性配置


## 版本选择说明

1. **Gradle 8.4**:
    - 稳定性好
    - 与AGP 8.1.4完美配合
    - 构建速度优秀

2. **AGP 8.1.4**:
    - 经过充分验证的稳定版本
    - 支持最的Android车载功能
    - 与Gradle 8.4兼容性最佳

3. **Kotlin 1.9.10**:
    - 与Compose Compiler 1.5.3完全兼容
    - 提供稳定的协程支持
    - 支持最新的Kotlin特性

4. **Compose相关**:
    - compose-bom: 2023.08.00 统一管理Compose依赖版本
    - compose-plugin: 1.5.3 提供Compose编译支持
    - compose-compiler: 1.5.3 与Kotlin版本匹配

5. **SDK 版本**:
    - minSdk 29确保与大多数车载设备兼容
    - targetSdk 34提供最新的API支持
    - compileSdk 34支持最新的编译特性

## 功能特性

1. **Jetpack Compose UI**:
    - 现代化的声明式UI开发
    - Material3设计支持
    - 完整的Compose工具链
    - 边缘到边缘显示支持

2. **图片加载**:
    - 集成Coil图片加载库
    - Compose专用的图片加载支持
    - 高效的缓存机制

3. **车载专用组件**:
    - 适配驾驶模式的按钮
    - 支持图标的交互组件
    - Material图标支持

## 注意事项

1. 确保本地安装了Java 17
2. Android Studio版本建议使用最新的稳定版
3. 首次导入项目后，建议执行一次Gradle sync
4. 如需支持更老的车载设备，可以考虑调整minSdk版本
5. 地图使用高德地图，生成SHA1时要注意MAC、项目、gradle 版本要适配，本项目适配的是java17

## 更新日志

### 2024-03
- 初始化项目环境
- 配置基础构建脚本
- 添加车载UI相关依赖
- 集成Jetpack Compose
- 更新Gradle到8.4版本
- 更新AGP到8.1.4版本
- 配置Java 17支持 
- 实现车载专用按钮组件

