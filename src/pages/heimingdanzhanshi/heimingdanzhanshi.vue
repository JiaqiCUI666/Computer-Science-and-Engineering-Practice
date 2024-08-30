<script setup>
  import { useRouter } from 'vue-router';
  import { reactive, onMounted } from 'vue';

  const props = defineProps({});

  const data = reactive({});

  const router = useRouter();

  function onClick() {
    router.push({ name: 'zhuye' });
  }

  function onClick_1() {
    router.push({ name: 'shangchuanjiance' });
  }

  function onClick_2() {
    router.push({ name: 'xuanchuanzhanshi' });
  }

  function onClick_3() {
    router.push({ name: 'heimingdanzhanshi' });
  }
</script>

<template>
  <div class="flex-col page">
    <div class="flex-row items-center  group">
      <div class="flex-col justify-start shrink-0 text-wrapper"><span class="text">智邮卫盾</span></div>
      <span class="font text_2"  @click="onClick">主页</span>
      <div class="flex-row shrink-0 group_2">
        <span class="font" @click="onClick_1">上传检测</span>
        <div class="flex-row shrink-0 ml-95">
          <span class="font text_3 " @click="onClick_2">防护提示</span>
          <span class="ml-84 font text_4" @click="onClick_3">黑名单展示</span>
        </div>
      </div>
    </div>
    <img
      class="self-center image"
      src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=9278f58e64e3252024c504853b1a59a3.png"
    />
    <div class="flex-col items-center self-center group_3">
      <span class="text_5">黑名单展示</span>
      <span class="text_6 mt-21">通过上传邮件或输入邮件信息，系统将给出钓鱼邮件的检测结果和置信度。</span>
    </div>
    <div class="flex-col self-stretch group_4">
      <div class="flex-row justify-between group_5">
        <span class="font_2">标号</span>
        <div class="flex-row group_6">
          <span class="font_2 text_7">邮箱号</span>
          <span class="font_2 text_8 ml-341">IP地址</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-center relative group_7">
        <img
          class="self-center image_2 pos"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=59d1abcd57576838d85ba1fd96da5591.png"
        />
        <span class="self-start font_4">2365975900@qq.com</span>
        <span class="self-center font_3 pos_2">操作步骤描述</span>
      </div>
      <div class="mt-30 flex-row justify-center relative group_8">
        <img
          class="self-center image_3 pos_3"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=815a75d4f3a6978e6f37481a24b291f4.png"
        />
        <span class="self-start font_4">2365975900@qq.com</span>
        <span class="self-center font_3 pos_4">操作步骤描述</span>
      </div>
      <div class="mt-30 flex-row justify-between group_9">
        <img
          class="self-center image_4"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=3557b0761f24d1037d5ff42ce09cb394.png"
        />
        <div class="flex-row self-start group_10">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-between group_11">
        <img
          class="self-center image_5"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=0fcc30838c5a4d2449b0e7329598cd3e.png"
        />
        <div class="flex-row self-start group_12">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-between group_13">
        <img
          class="self-center image_3"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=952fa1423274dcb98154438e0eda4c67.png"
        />
        <div class="flex-row self-start group_14">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-between group_15">
        <img
          class="self-center image_6"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=4fee2abaad8d06d571fb8660b0def90b.png"
        />
        <div class="flex-row self-start group_16">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-between group_13">
        <img
          class="self-center image_3"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=648392788f35851076700e3e3ab33915.png"
        />
        <div class="flex-row self-start group_14">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-between group_15">
        <img
          class="self-center image_7"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=6b184e1ccaefbf33183d99e6caca68bb.png"
        />
        <div class="flex-row self-start group_17">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
      <div class="mt-30 flex-row justify-between group_18">
        <img
          class="self-center image_8"
          src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=1b355bc8a6d5c0fef3d86f93d862b7bf.png"
        />
        <div class="flex-row self-start group_19">
          <span class="self-center font_4">2365975900@qq.com</span>
          <span class="self-start font_3 ml-264">操作步骤描述</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="css">
  .ml-95 {
    margin-left: 5.94rem;
  }
  .mt-21 {
    margin-top: 1.31rem;
  }
  .ml-341 {
    margin-left: 21.31rem;
  }
  .ml-264 {
    margin-left: 16.5rem;
  }
  .page {
    padding: 1.06rem 0 9.69rem;
    background-color: #000000;
    width: 100%;
    overflow-y: auto;
    overflow-x: hidden;
    height: 100%;
  }
  .group {
    margin-left: 5rem;
  }
  .text-wrapper {
    padding: 0.38rem 0 0.75rem;
    border-radius: 0.38rem;
    height: 2.38rem;
  }
  .text {
    margin-left: 0.13rem;
    color: #ffffff;
    font-size: 1.38rem;
    font-family: "Noto Serif SC", serif;
    font-weight: 700;
    line-height: 1.31rem;
  }
  .font {
    font-size: 1rem;
    font-family: "Noto Serif SC", serif;
    line-height: 0.94rem;
    color: #ffffff;
  }
  .text_2 {
    margin-left: 16.38rem;
  }
  .group_2 {
    margin-left: 7.38rem;
  }
  .text_3 {
    line-height: 0.93rem;
  }
  .text_4 {
    line-height: 0.94rem;
  }
  .image {
    margin-top: 3rem;
    width: 100vw;
    height: 22.2222vw;
  }
  .group_3 {
    margin-top: 6.5rem;
  }
  .text_5 {
    color: #ffffff;
    font-size: 3.75rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2.7rem;
  }
  .text_6 {
    color: #ffffff;
    font-size: 1.13rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.14rem;
  }
  .group_4 {
    margin-top: 7.25rem;
  }
  .group_5 {
    padding: 2rem 10.63rem 1.88rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .font_2 {
    font-size: 2.25rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2.06rem;
    color: #ffffff;
  }
  .group_6 {
    margin-right: 3.63rem;
  }
  .text_7 {
    line-height: 2.07rem;
  }
  .text_8 {
    line-height: 1.98rem;
  }
  .group_7 {
    padding: 2.75rem 11rem 1.75rem 11.75rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_2 {
    width: 0.94rem;
    height: 2.63rem;
  }
  .pos {
    position: absolute;
    left: 11.79rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .font_4 {
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.51rem;
    color: #ffffff;
  }
  .font_3 {
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.38rem;
    color: #ffffff;
  }
  .pos_2 {
    position: absolute;
    right: 11.1rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .group_8 {
    padding: 2.75rem 11.13rem 1.75rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_3 {
    width: 1.94rem;
    height: 2.69rem;
  }
  .pos_3 {
    position: absolute;
    left: 11.66rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .pos_4 {
    position: absolute;
    right: 11.23rem;
    top: 50%;
    transform: translateY(-50%);
  }
  .group_9 {
    padding: 1.75rem 10.88rem 1.75rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_4 {
    width: 1.94rem;
    height: 2.63rem;
  }
  .group_10 {
    margin-top: 0.75rem;
  }
  .group_11 {
    padding: 1.75rem 10.63rem 1.75rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_5 {
    width: 2.19rem;
    height: 2.63rem;
  }
  .group_12 {
    margin-top: 0.75rem;
  }
  .group_13 {
    padding: 1.63rem 10.88rem 1.63rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .group_14 {
    margin-top: 0.75rem;
  }
  .group_15 {
    padding: 1.63rem 10.75rem 1.63rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_6 {
    width: 2.13rem;
    height: 2.75rem;
  }
  .group_16 {
    margin-top: 0.75rem;
  }
  .image_7 {
    width: 2.13rem;
    height: 2.81rem;
  }
  .group_17 {
    margin-top: 0.81rem;
  }
  .group_18 {
    padding: 1.63rem 10.63rem 1.63rem 11.63rem;
    border-radius: 0.31rem;
    border-left: solid 0.063rem #e0e0e0;
    border-right: solid 0.063rem #e0e0e0;
    border-top: solid 0.063rem #e0e0e0;
    border-bottom: solid 0.063rem #e0e0e0;
  }
  .image_8 {
    width: 2.19rem;
    height: 2.75rem;
  }
  .group_19 {
    margin-top: 0.75rem;
  }
</style>