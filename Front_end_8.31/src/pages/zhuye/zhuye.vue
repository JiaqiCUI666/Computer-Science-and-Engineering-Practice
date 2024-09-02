<script setup>
  import { useRouter } from 'vue-router';
  import { reactive } from 'vue';

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
  <div class="flex-col items-start page">
    <div class="flex-row items-center group">
      <div class="flex-col justify-start shrink-0 text-wrapper"><span class="text">智邮卫盾</span></div>
      <span class="font text_2" @click="onClick">主页</span>
      <div class="flex-row shrink-0 group_2">
        <span class="font" @click="onClick_1">上传检测</span>
        <div class="flex-row shrink-0 ml-95">
          <span class="font text_3 " @click="onClick_2">防护提示</span>
          <span class="ml-84 font text_4" @click="onClick_3">黑名单展示</span>
        </div>
      </div>
    </div>
    <div class="image-container">
      <img
        class="mt-18 image"
        src="@/image/zhuye.jpg"
      />
      <div class="text-overlay">
        <h1 class="title">智邮卫盾</h1>
        <p class="description">
          <em>
          智能守护，安全无忧</em><br><br>
          在信息时代，钓鱼邮件如影随形，威胁着我们的网络安全<br>
        智邮卫盾，凭借先进的算法和深度学习技术，为您构筑一道坚实的安全屏<br>
        以高精度识别技术，实时检测可疑邮件，过滤潜在风险<br>
        为您守护每一封邮件，保障每一份安心<br><br>
          <em>智邮卫盾，您的智能安全卫士</em></p>
        
      </div>
    </div>
  </div>
</template>

<style scoped lang="css">
  .ml-95 {
    margin-left: 5.94rem;
  }
  .page {
    padding: 1.06rem 0 1.69rem;
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
    font-size: 1.5rem; /* 增加字体大小 */
    font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
    font-weight: 700;
    line-height: 1.31rem;
  }
  .font {
    font-size: 1.2rem;
    font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
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
    width: 100vw;
    height: 49.3056vw;
  }
  .image-container {
    position: relative;
    width: 100vw;
    height: 49.3056vw;
  }
  .text-overlay {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: #ffffff;
    text-align: center;
    padding: 1rem;
    max-width: 90%;
  }
  .title {
    font-size: 6rem; /* 增加标题字体大小 */
    font-weight: bold;
    font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
  }
  .description {
    font-size: 1.2rem;
    line-height: 2; /* 增大行间距 */
    font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
  }
  em {
    font-style: italic; /* 设置斜体样式 */
  }
</style>
