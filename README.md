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

## UI

[로그인 및 회원가입]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f854c4fa-eef9-4c0c-b61a-8516cf4ae103/Screenshot_20210531-223944_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f854c4fa-eef9-4c0c-b61a-8516cf4ae103/Screenshot_20210531-223944_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1de2a4da-d752-4a14-a9c9-4c0c6576951a/Screenshot_20210531-223928_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1de2a4da-d752-4a14-a9c9-4c0c6576951a/Screenshot_20210531-223928_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ef0201ce-9690-443b-b153-68b12f513c45/Screenshot_20210531-223806_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ef0201ce-9690-443b-b153-68b12f513c45/Screenshot_20210531-223806_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6b6dc0ef-326a-45ae-a75d-3ff01eb1ba77/Screenshot_20210531-223826_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6b6dc0ef-326a-45ae-a75d-3ff01eb1ba77/Screenshot_20210531-223826_Catchi_Nichi.jpg)

[메인화면]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/822a9a42-8482-43a0-b358-8a71824d01d0/Screenshot_20210531-224003_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/822a9a42-8482-43a0-b358-8a71824d01d0/Screenshot_20210531-224003_Catchi_Nichi.jpg)

[검색기능]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c8377456-416e-4f55-94ad-e846844573fa/Screenshot_20210531-224014_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c8377456-416e-4f55-94ad-e846844573fa/Screenshot_20210531-224014_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1777a8e6-1e4c-421e-a8e8-d0c8b85cbc12/Screenshot_20210531-224038_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1777a8e6-1e4c-421e-a8e8-d0c8b85cbc12/Screenshot_20210531-224038_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3893e438-7304-4692-a5d4-0803e3ec3ad8/Screenshot_20210531-224056_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3893e438-7304-4692-a5d4-0803e3ec3ad8/Screenshot_20210531-224056_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d017acd8-fb89-4da1-93df-9fc0ac68b197/Screenshot_20210531-224109_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d017acd8-fb89-4da1-93df-9fc0ac68b197/Screenshot_20210531-224109_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/745f0895-3ae1-4ceb-8a7d-bb8893e12c76/Screenshot_20210531-224119_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/745f0895-3ae1-4ceb-8a7d-bb8893e12c76/Screenshot_20210531-224119_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e96d31f-7f86-49cb-867f-7405c83c9e86/Screenshot_20210531-224127_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e96d31f-7f86-49cb-867f-7405c83c9e86/Screenshot_20210531-224127_Catchi_Nichi.jpg)

[추천 기능]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c85bc24c-e55b-477c-8867-3c2598443b57/Screenshot_20210531-224146_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c85bc24c-e55b-477c-8867-3c2598443b57/Screenshot_20210531-224146_Catchi_Nichi.jpg)

1) 이미지별 향수추천

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5c53fbc4-25e1-4fe1-873b-5a23043283be/Screenshot_20210531-224154_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5c53fbc4-25e1-4fe1-873b-5a23043283be/Screenshot_20210531-224154_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0ceae958-aa39-47e3-a8f0-4ab3083e28f3/Screenshot_20210531-224201_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0ceae958-aa39-47e3-a8f0-4ab3083e28f3/Screenshot_20210531-224201_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/60f6f375-06a0-4f30-bf76-4780c01af482/Screenshot_20210531-224327_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/60f6f375-06a0-4f30-bf76-4780c01af482/Screenshot_20210531-224327_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/da05978f-cd0b-4908-b8a0-f6a9b81d0b2c/Screenshot_20210531-224209_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/da05978f-cd0b-4908-b8a0-f6a9b81d0b2c/Screenshot_20210531-224209_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff2cb084-17f6-4fb0-b501-15f083f8fcd8/Screenshot_20210531-224221_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff2cb084-17f6-4fb0-b501-15f083f8fcd8/Screenshot_20210531-224221_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7a7c30d2-8340-4310-9ec3-9bed1276faf2/Screenshot_20210531-224234_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7a7c30d2-8340-4310-9ec3-9bed1276faf2/Screenshot_20210531-224234_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/97668ff5-3a21-451f-be54-ee707a6187b0/Screenshot_20210531-224249_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/97668ff5-3a21-451f-be54-ee707a6187b0/Screenshot_20210531-224249_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2f754480-0d15-4b00-b20f-4d875f2ea4f3/Screenshot_20210531-224303_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2f754480-0d15-4b00-b20f-4d875f2ea4f3/Screenshot_20210531-224303_Catchi_Nichi.jpg)

2) 노트별 향수추천

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/71c5f3d4-2b08-40c6-8ade-b921a6d1ca53/Screenshot_20210601-190855_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/71c5f3d4-2b08-40c6-8ade-b921a6d1ca53/Screenshot_20210601-190855_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/457666d8-7cee-4e52-b11c-3e2febd1c55e/Screenshot_20210601-190918_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/457666d8-7cee-4e52-b11c-3e2febd1c55e/Screenshot_20210601-190918_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/90e3d394-13a6-43cd-a996-065c6c42c7f4/Screenshot_20210601-190927_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/90e3d394-13a6-43cd-a996-065c6c42c7f4/Screenshot_20210601-190927_Catchi_Nichi.jpg)

[시향노트]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/01c9677d-7788-4000-aad3-33f37e1d8553/Screenshot_20210531-224335_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/01c9677d-7788-4000-aad3-33f37e1d8553/Screenshot_20210531-224335_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f061afe1-182f-4bc0-a002-609328cf78ea/Screenshot_20210531-224352_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f061afe1-182f-4bc0-a002-609328cf78ea/Screenshot_20210531-224352_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f203fd56-a904-4e44-a9af-130055cafbd6/Screenshot_20210531-224401_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f203fd56-a904-4e44-a9af-130055cafbd6/Screenshot_20210531-224401_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e5c390f1-be77-4c2e-a0c9-ec83d168dfb5/Screenshot_20210531-224413_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e5c390f1-be77-4c2e-a0c9-ec83d168dfb5/Screenshot_20210531-224413_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e08a5c83-4564-478e-baa8-e7fb6a09abe4/Screenshot_20210531-224421_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e08a5c83-4564-478e-baa8-e7fb6a09abe4/Screenshot_20210531-224421_Catchi_Nichi.jpg)

[사진검색]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/652beb7a-ed51-42f7-b393-b6e6bb0104b2/Screenshot_20210531-224431_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/652beb7a-ed51-42f7-b393-b6e6bb0104b2/Screenshot_20210531-224431_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ed20bcd7-0702-4c51-94f0-de05d0d94411/Screenshot_20210531-224443_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ed20bcd7-0702-4c51-94f0-de05d0d94411/Screenshot_20210531-224443_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6dea36df-ec3d-42d9-9813-56745e527850/Screenshot_20210531-224454_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6dea36df-ec3d-42d9-9813-56745e527850/Screenshot_20210531-224454_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b12f0f82-b872-45f4-b9c2-f3c1e4b66b46/Screenshot_20210531-224532_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b12f0f82-b872-45f4-b9c2-f3c1e4b66b46/Screenshot_20210531-224532_Catchi_Nichi.jpg)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/23e1e3e4-ff2d-4bcd-bf1f-db1fcf38ecc6/Screenshot_20210531-224542_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/23e1e3e4-ff2d-4bcd-bf1f-db1fcf38ecc6/Screenshot_20210531-224542_Catchi_Nichi.jpg)

[마이페이지]

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a476db73-0ca5-4b5d-a154-41018aec7dde/Screenshot_20210531-224600_Catchi_Nichi.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a476db73-0ca5-4b5d-a154-41018aec7dde/Screenshot_20210531-224600_Catchi_Nichi.jpg)

## 데모영상

[Android App / Catchi_nichi](https://youtu.be/hrS-3LWO8Pc)
