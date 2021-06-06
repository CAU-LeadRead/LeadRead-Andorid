# Catchinichi-Android

## CAU capstone design (2)

### 역할분담

|    담당    | 이름       |
| :--------: | ---------- |
| **안드로이드** | **이지현**     |
|  서버  | 신지호 |
|   AI     | 송유지     |

## 개발환경

Android Studio Version 4.1.3 

## Clone Repository

```bash
git clone https://github.com/Catchi-Nichi/Catchinichi-Andorid.git
```

## 디렉토리 구조

```bash

# Catchi_Nichi-ANDROID
    ├─.gradle
    │  ├─6.5
    │  │  ├─executionHistory
    │  │  ├─fileChanges
    │  │  ├─fileContent
    │  │  ├─fileHashes
    │  │  ├─javaCompile
    │  │  └─vcsMetadata-1
    │  ├─buildOutputCleanup
    │  ├─checksums
    │  └─vcs-1
    ├─.idea
    │  ├─caches
    │  ├─libraries
    │  └─modules
    │      └─app
    ├─app
    │  ├─build
    │  │  ├─generated
    │  │  │  ├─ap_generated_sources
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─res
    │  │  │  │  ├─pngs
    │  │  │  │  │  └─debug
    │  │  │  │  │      ├─drawable-anydpi-v21
    │  │  │  │  │      ├─drawable-anydpi-v24
    │  │  │  │  │      ├─drawable-hdpi
    │  │  │  │  │      ├─drawable-ldpi
    │  │  │  │  │      ├─drawable-mdpi
    │  │  │  │  │      ├─drawable-xhdpi
    │  │  │  │  │      ├─drawable-xxhdpi
    │  │  │  │  │      └─drawable-xxxhdpi
    │  │  │  │  └─resValues
    │  │  │  │      └─debug
    │  │  │  │          └─xml
    │  │  │  └─source
    │  │  │      └─buildConfig
    │  │  │          └─debug
    │  │  │              └─com
    │  │  │                  └─example
    │  │  │                      └─catchi_nichi
    │  │  ├─intermediates
    │  │  │  ├─aar_metadata_check
    │  │  │  │  └─debug
    │  │  │  ├─annotation_processor_list
    │  │  │  │  └─debug
    │  │  │  ├─compatible_screen_manifest
    │  │  │  │  └─debug
    │  │  │  ├─compile_and_runtime_not_namespaced_r_class_jar
    │  │  │  │  └─debug
    │  │  │  ├─compressed_assets
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─data_binding_layout_info_type_merge
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─dex
    │  │  │  │  └─debug
    │  │  │  │      └─mergeDexDebug
    │  │  │  ├─dex_archive_input_jar_hashes
    │  │  │  │  └─debug
    │  │  │  ├─dex_number_of_buckets_file
    │  │  │  │  └─debug
    │  │  │  ├─duplicate_classes_check
    │  │  │  │  └─debug
    │  │  │  ├─external_file_lib_dex_archives
    │  │  │  │  └─debug
    │  │  │  ├─external_libs_dex
    │  │  │  │  └─debug
    │  │  │  │      └─mergeExtDexDebug
    │  │  │  ├─external_libs_dex_archive
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─external_libs_dex_archive_with_artifact_transforms
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─incremental
    │  │  │  │  ├─debug-mergeJavaRes
    │  │  │  │  │  └─zip-cache
    │  │  │  │  ├─debug-mergeNativeLibs
    │  │  │  │  │  └─zip-cache
    │  │  │  │  ├─mergeDebugAssets
    │  │  │  │  ├─mergeDebugJniLibFolders
    │  │  │  │  ├─mergeDebugResources
    │  │  │  │  │  ├─merged.dir
    │  │  │  │  │  └─stripped.dir
    │  │  │  │  ├─mergeDebugShaders
    │  │  │  │  ├─packageDebug
    │  │  │  │  │  └─tmp
    │  │  │  │  │      └─debug
    │  │  │  │  │          └─zip-cache
    │  │  │  │  └─processDebugResources
    │  │  │  ├─javac
    │  │  │  │  └─debug
    │  │  │  │      └─classes
    │  │  │  │          └─com
    │  │  │  │              └─example
    │  │  │  │                  └─catchi_nichi
    │  │  │  ├─manifest_merge_blame_file
    │  │  │  │  └─debug
    │  │  │  ├─merged_assets
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─merged_java_res
    │  │  │  │  └─debug
    │  │  │  ├─merged_jni_libs
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─merged_manifest
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─merged_manifests
    │  │  │  │  └─debug
    │  │  │  ├─merged_native_libs
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─merged_res_blame_folder
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  │          ├─multi-v2
    │  │  │  │          └─single
    │  │  │  ├─merged_shaders
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─mixed_scope_dex_archive
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─navigation_json
    │  │  │  │  └─debug
    │  │  │  ├─packaged_manifests
    │  │  │  │  └─debug
    │  │  │  ├─processed_res
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─project_dex_archive
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  │          └─com
    │  │  │  │              └─example
    │  │  │  │                  └─catchi_nichi
    │  │  │  ├─res
    │  │  │  │  └─merged
    │  │  │  │      └─debug
    │  │  │  ├─runtime_symbol_list
    │  │  │  │  └─debug
    │  │  │  ├─sub_project_dex_archive
    │  │  │  │  └─debug
    │  │  │  │      └─out
    │  │  │  ├─symbol_list_with_package_name
    │  │  │  │  └─debug
    │  │  │  └─validate_signing_config
    │  │  │      └─debug
    │  │  ├─outputs
    │  │  │  ├─apk
    │  │  │  │  └─debug
    │  │  │  └─logs
    │  │  └─tmp
    │  │      └─compileDebugJavaWithJavac
    │  ├─libs
    │  ├─sampledata
    │  └─src
    │      ├─androidTest
    │      │  └─java
    │      │      └─com
    │      │          └─example
    │      │              └─catchi_nichi
    │      ├─main
    │      │  ├─java
    │      │  │  └─com
    │      │  │      └─example
    │      │  │          └─catchi_nichi
    │      │  └─res
    │      │      ├─drawable
    │      │      ├─drawable-v24
    │      │      ├─font
    │      │      ├─layout
    │      │      ├─mipmap-anydpi-v26
    │      │      ├─mipmap-hdpi
    │      │      ├─mipmap-mdpi
    │      │      ├─mipmap-xhdpi
    │      │      ├─mipmap-xxhdpi
    │      │      ├─mipmap-xxxhdpi
    │      │      ├─values
    │      │      ├─values-night
    │      │      └─xml
    │      └─test
    │          └─java
    │              └─com
    │                  └─example
    │                      └─catchi_nichi
    └─gradle
        └─wrapper
```

## 데모영상

[Android App / Catchi_nichi](https://youtu.be/hrS-3LWO8Pc)
