<script setup>
import { useRouter } from 'vue-router';
import { reactive } from 'vue';
import axios from 'axios';

const router = useRouter();
const data = reactive({
  result: null,
  confidence: null,
  emailContent: '',
  uploadStatus: '', // 存储上传状态的信息
});

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

async function uploadFile(event) {
  const formData = new FormData();
  formData.append('file', event.target.files[0]);

  try {
    const response = await axios.post('/api/file/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    if (response.status === 200 && response.data.success) {
      data.uploadStatus = '文件上传成功';
      data.result = response.data.result;
      data.confidence = response.data.confidence;
    } else {
      data.uploadStatus = '文件上传失败';
      console.error('文件上传失败，服务器返回:', response.data.message || response.status);
    }
  } catch (error) {
    data.uploadStatus = '文件上传失败';
    console.error('文件上传失败', error.response ? error.response.data : error.message);
  }
}

async function detectEmail() {
  try {
    const response = await axios.post('/api/email/detect', {
      content: data.emailContent,
    });

    if (response.data.success) {
      data.result = response.data.result;
      data.confidence = response.data.confidence;
    } else {
      console.error('检测失败', response.data.message);
    }
  } catch (error) {
    console.error('检测失败', error);
  }
}

function triggerFileUpload() {
  const fileInput = document.getElementById('fileInput');
  if (fileInput) {
    fileInput.click();
  } else {
    console.error('文件输入元素未找到');
  }
}
</script>

<template>
  <div class="flex-col page">
    <div class="flex-row items-center group">
      <div class="flex-col justify-start shrink-0 text-wrapper">
        <span class="text">智邮卫盾</span>
      </div>
      <span class="font text_2" @click="onClick">主页</span>
      <div class="flex-row shrink-0 group_2">
        <span class="font" @click="onClick_1">上传检测</span>
        <div class="flex-row shrink-0 ml-95">
          <span class="font text_3 " @click="onClick_2">防护提示</span>
          <span class="ml-84 font text_4" @click="onClick_3">黑名单展示</span>
        </div>
      </div>
    
      <span class="font text_2" @click="onClick">主页</span>
      <div class="flex-row shrink-0 group_2">
        <span class="font" @click="onClick_1">上传检测</span>
        <div class="flex-row shrink-0 ml-95">
          <span class="font text_3" @click="onClick_2">防护提示</span>
          <span class="ml-84 font text_4" @click="onClick_3">黑名单展示</span>
        </div>
      </div>
    </div>
    <div class="flex-col self-stretch section">
      <br><br>
      <img class="shrink-0 self-center image" src="@/image/topbackground.png" />
      <div class="flex-col items-center self-center group_3">
        <span class="font_2 text_5">上传检测</span><br><br>
        <span class="mt-18 text_6">上传邮件或输入邮件信息，系统将给出钓鱼邮件的检测结果和置信度。</span>
      </div>
      <div class="flex-row justify-between items-center self-stretch section_2">
        <textarea v-model="data.emailContent" class="font_3 section_3  text_7 textarea" placeholder="请输入邮件正文内容"></textarea>
      </div>
      <div class="flex-row self-center group_4">
        <div class="flex-col justify-start items-center flex-1 text-wrapper_2" @click="triggerFileUpload">
          <span class="font text_8">上传</span>
        </div>
        <input type="file" id="fileInput" @change="uploadFile" style="display: none;" accept=".eml" />
        <div class="flex-col justify-start items-center shrink-0 text-wrapper_3 ml-3-5" @click="detectEmail">
          <span class="font text_9">检测</span>
        </div>
      </div>

      <span class="self-center font_2 text_10">检测结果</span>
      <div class="flex-col self-stretch section_4">
        <span v-if="data.uploadStatus" class="self-start font_3 text_11">{{ data.uploadStatus }}</span>
        <span v-if="data.result !== null && data.confidence !== null" class="self-start font_3 text_11">
          检测结果: {{ data.result }} <br />
          置信度: {{ data.confidence }}
        </span>
        <div class="shrink-0 self-end relative section_3 view"></div>
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
  font-size: 1.5rem;
  font-family: "Noto Serif SC", serif; /* 使用黑体字体 */
  font-weight: 700;
  line-height: 1.31rem;
}
.font {
  font-size: 1.2rem; /* 增大字体大小 */
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
.section {
  padding-bottom: 4.78rem;
  background-color: #121212;
  overflow: hidden;
}
.image {
  width: 100vw;
  height: 8vw;
}
.group_3 {
  margin-top: 5.13rem;
}
.font_2 {
  font-size: 2rem;
  font-family: "Noto Serif SC", serif;
  line-height: 1.39rem;
  font-weight: 700;
  color: #ffffff;
}
.text_5 {
  line-height: 1.41rem;
}
.text_6 {
  color: #ffffff;
  font-size: 1.2rem;
  font-family: "Noto Serif SC", serif;
  line-height: 0.57rem;
}
.section_2 {
  margin: 2.06rem 1.38rem 0;

  background-color: #ffffff1a;
  border-radius: 0.38rem;
}
.font_3 {
  font-size: 1.2rem;
  font-family: "Noto Serif SC", serif;
  line-height: 0.47rem;
  color: #505050;
}
.text_7 {
  line-height: 0.47rem;
}
.section_3 {
  background-color: #00000000;
  width: 100%;
  height: 5rem;
  border: 1px solid #fff;
  border-radius: 0.38rem;
  color: #ffffff;
  padding: 0.5rem;
}
.textarea {
  resize: none;
  width: 100%;
  height: 10rem;
  background-color: #00000000;
  border: 1px solid #ffffff;
  color: #ffffff;
  border-radius: 0.38rem;
  padding: 0.5rem;
}
.group_4 {
  margin-top: 3.03rem;
}
.text-wrapper_2 {
  padding: 0.5rem 0;
  background-color: #1f4788;
  border-radius: 1.25rem;
  width: 5rem;
  height: 2rem;
  text-align: center;
  cursor: pointer;
}
.text_8 {
  color: #ffffff;
}
.text-wrapper_3 {
  padding: 0.5rem 0;
  background-color: #ffffff14;
  border-radius: 1.25rem;
  width: 5rem;
  height: 2rem;
  border-left: solid 0.031rem #ffffff29;
  border-right: solid 0.031rem #ffffff29;
  border-top: solid 0.031rem #ffffff29;
  border-bottom: solid 0.031rem #ffffff29;
  text-align: center;
  cursor: pointer;
}
.text_9 {
  line-height: 0.46rem;
}
.image_2 {
  margin-top: 2.66rem;
}
.text_10 {
  margin-top: 2.63rem;
}
.section_4 {
  margin: 2.75rem 1.69rem 0;
  padding: 0.38rem 0 4.06rem 0.38rem;
  background-color: #ffffff1a;
  border-radius: 0.38rem;
  height: 5rem;
}
.text_11 {
  margin-top: 0.13rem;
  line-height: 0.46rem;
}
.view {
  margin-top: -0.56rem;
}
</style>
