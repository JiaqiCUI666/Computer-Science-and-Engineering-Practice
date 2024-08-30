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
    <div class="mt-90 flex-col self-stretch">
      <div class="flex-col items-center self-center">
        <span class="text_5">钓鱼邮件防范宣传</span>
        <span class="text_6 mt-21">通过上传邮件或输入邮件信息，系统将给出钓鱼邮件的检测结果和置信度。</span>
      </div>
      <div class="mt-38 flex-col self-stretch section">
        <div class="flex-col section_2">
          <span class="self-start font_2 text_7">内容模块</span>
          <div class="mt-44 flex-col self-stretch">
            <div class="flex-row">
              <div class="flex-col section_3">
                <span class="self-start font_3 text_8">钓鱼邮件的定义</span>
                <span class="self-stretch font_4 mt-21">
                  钓鱼邮件是指通过伪装成合法机构或个人发出的电子邮件，用于获取用户的敏感信息，如用户名、密码、信用卡信息等。
                </span>
              </div>
              <div class="ml-40 flex-col section_3">
                <span class="self-start font_3 text_9">常见的钓鱼手法</span>
                <span class="self-stretch font_4 mt-21">
                  常见的钓鱼手法包括假冒官方邮件、诱导点击恶意链接、伪装成熟悉的人发信等，以获取用户的信任并诱使其提供个人信息。
                </span>
              </div>
            </div>
            <div class="mt-40 flex-row">
              <div class="flex-col section_3">
                <span class="self-start font_3">如何辨别钓鱼邮件</span>
                <span class="self-stretch font_4 mt-21">
                  辨别钓鱼邮件可以通过注意发件人地址、查看链接真实性、注意邮件语言风格等方式，警惕不明邮件、避免提供个人信息。
                </span>
              </div>
              <div class="ml-40 flex-col section_3">
                <span class="self-start font_3">如何防范钓鱼邮件</span>
                <span class="self-stretch font_4 mt-21">
                  防范钓鱼邮件可以通过保持警惕、定期更新密码、安装反钓鱼软件等方式，确保个人信息的安全。
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="flex-col section_4">
          <span class="self-start font_2 text_10">主要内容</span>
          <div class="mt-44 flex-row self-stretch">
            <div class="flex-col section_5">
              <span class="self-start font_3 text_11">钓鱼邮件防范</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5">
                  钓鱼邮件是指冒充他人身份进行诈骗的电子邮件，本模块介绍如何防范钓鱼邮件的攻击。
                </span>
                <img
                  class="mt-14 image"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=e5d003ef6077d015f741c306419639a0.png"
                />
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3">如何识别钓鱼邮件</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5">
                  识别钓鱼邮件是保护个人信息安全的重要一步，本模块提供了一些识别钓鱼邮件的方法和技巧。
                </span>
                <img
                  class="mt-14 image"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=5f9b2829f684687954aaaa3e78956d14.png"
                />
              </div>
            </div>
            <div class="ml-24 flex-col section_6">
              <span class="self-start font_3 text_12">防范措施</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5">
                  除了识别钓鱼邮件，还需要采取一些防范措施来保护个人信息的安全，本模块提供了一些有效的防范方法。
                </span>
                <img
                  class="mt-14 image"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=c48d94ceffc58fb684a3044af38a6f95.png"
                />
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3 text_13">实例分析</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5">通过实际案例分析，了解钓鱼邮件攻击的手段和后果，提高防范意识。</span>
                <img
                  class="mt-14 image"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=fbdeb35eabe03e5705a8119dc39413ca.png"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="flex-col section_4">
          <span class="self-start font_2 text_14">常见钓鱼手法</span>
          <div class="mt-44 flex-row self-stretch">
            <div class="flex-col section_7">
              <div class="flex-col justify-start items-start self-stretch image-wrapper">
                <img
                  class="image_2"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=eef43983fddd561651f72cbace30ad3d.png"
                />
              </div>
              <span class="self-start font_3 text_15 mt-15">假冒网站</span>
              <span class="self-start font_6 text_18 mt-15">描述假冒网站的手法和特点</span>
            </div>
            <div class="ml-16 flex-col section_7">
              <div class="flex-col justify-start items-start self-stretch image-wrapper">
                <img
                  class="image_2"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=eef43983fddd561651f72cbace30ad3d.png"
                />
              </div>
              <span class="self-start font_3 text_16 mt-15">钓鱼邮件</span>
              <span class="self-start font_6 text_19 mt-15">描述钓鱼邮件的手法和特点</span>
            </div>
            <div class="ml-16 flex-col section_7">
              <div class="flex-col justify-start items-start self-stretch image-wrapper">
                <img
                  class="image_2"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=eef43983fddd561651f72cbace30ad3d.png"
                />
              </div>
              <span class="self-start font_3 mt-15">社交工程</span>
              <span class="self-start font_6 text_20 mt-15">描述社交工程的手法和特点</span>
            </div>
            <div class="ml-16 flex-col section_7">
              <div class="flex-col justify-start items-start self-stretch image-wrapper">
                <img
                  class="image_2"
                  src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=eef43983fddd561651f72cbace30ad3d.png"
                />
              </div>
              <span class="self-start font_3 text_17 mt-15">恶意附件</span>
              <span class="self-start font_6 text_21 mt-15">描述恶意附件的手法和特点</span>
            </div>
          </div>
        </div>
        <div class="flex-col group_3">
          <span class="self-start font_2 text_22">案例模块</span>
          <div class="mt-44 flex-row justify-between self-stretch section_8">
            <img
              class="self-center image_3"
              src="https://ide.code.fun/api/image?token=66d15b8763765d0012a2d3da&name=57c7783c0f5234b15419a59bd7f6cfab.png"
            />
            <div class="flex-col self-start group_4">
              <span class="self-start text_23">真实案例一</span>
              <span class="mt-22 self-stretch font_4 text_24">
                内容：某用户收到了一封看似来自银行的电子邮件，要求用户提供个人银行账户信息。用户相信邮件的真实性，提供了敏感信息，结果遭受财务损失。
              </span>
            </div>
          </div>
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
  .mt-15 {
    margin-top: 0.94rem;
  }
  .page {
    padding-top: 1.06rem;
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
  .section {
    padding-bottom: 0.88rem;
    background-color: #121212;
    overflow: hidden;
  }
  .section_2 {
    padding: 2.88rem 6rem 2.5rem;
    background-color: #1f4788;
  }
  .font_2 {
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.5rem;
    font-weight: 800;
    color: #ffffff;
  }
  .text_7 {
    line-height: 1.45rem;
  }
  .section_3 {
    flex: 1 1 37.79rem;
    padding: 2.38rem 2rem 1.88rem;
    background-color: #ffffff;
    border-radius: 0.5rem;
    height: 9.75rem;
  }
  .font_3 {
    font-size: 1.25rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.21rem;
    font-weight: 800;
    color: #000000;
  }
  .text_8 {
    line-height: 1.21rem;
  }
  .font_4 {
    font-size: 1rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.5rem;
    color: #374151;
  }
  .text_9 {
    line-height: 1.2rem;
  }
  .section_4 {
    margin-top: 2.06rem;
    padding: 2.88rem 6rem 2.5rem;
    background-color: #000000;
  }
  .text_10 {
    line-height: 1.45rem;
  }
  .section_5 {
    flex: 1 1 18.39rem;
    padding: 1.88rem 1.5rem 3rem;
    background-color: #ffffff;
    border-radius: 0.5rem;
    height: 24.31rem;
  }
  .text_11 {
    line-height: 1.2rem;
  }
  .font_5 {
    font-size: 1rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.5rem;
    color: #1f2937;
  }
  .image {
    border-radius: 0.5rem;
    width: 15.38rem;
    height: 11.56rem;
  }
  .section_6 {
    padding: 1.88rem 1.5rem 1.5rem;
    flex: 1 1 18.39rem;
    background-color: #ffffff;
    border-radius: 0.5rem;
    height: 24.31rem;
  }
  .text_12 {
    line-height: 1.2rem;
  }
  .text_13 {
    line-height: 1.21rem;
  }
  .text_14 {
    line-height: 1.44rem;
  }
  .section_7 {
    flex: 1 1 18.77rem;
    padding: 1rem 1rem 1.25rem;
    background-color: #ffffff;
    border-radius: 0.5rem;
    height: 7.25rem;
  }
  .image-wrapper {
    border-radius: 0.5rem;
  }
  .image_2 {
    width: 1rem;
    height: 1rem;
  }
  .text_15 {
    line-height: 1.19rem;
  }
  .font_6 {
    font-size: 1rem;
    font-family: "Noto Serif SC", serif;
    line-height: 0.94rem;
    color: #4b5563;
  }
  .text_18 {
    line-height: 0.93rem;
  }
  .text_16 {
    line-height: 1.2rem;
  }
  .text_19 {
    line-height: 0.92rem;
  }
  .text_20 {
    line-height: 0.93rem;
  }
  .text_17 {
    line-height: 1.21rem;
  }
  .text_21 {
    line-height: 0.93rem;
  }
  .group_3 {
    padding: 4.88rem 6rem;
  }
  .text_22 {
    line-height: 1.44rem;
  }
  .section_8 {
    padding: 2rem;
    background-color: #1f4788;
    border-radius: 0.5rem;
  }
  .image_3 {
    border-radius: 0.5rem;
    width: 16rem;
    height: 12rem;
  }
  .group_4 {
    margin-top: 0.38rem;
    width: 35.94rem;
  }
  .text_23 {
    color: #ffffff;
    font-size: 1.13rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.04rem;
  }
  .text_24 {
    color: #ffffff;
  }
</style>