<script setup>
import { reactive, ref } from 'vue';
import axios from 'axios'; // 引入axios
import { useRouter } from 'vue-router';

const props = defineProps({});
const data = reactive({
  emailContent: '', // 用于存储输入的邮件文本
  detectionResult: '', // 存储检测结果
  file: null, // 用于存储选择的文件
  parsedResult: '', // 存储解析后的中文结果
});

// 解析并展示返回结果的函数
const parseResult = (result) => {
  const { sender_email_address, sender_ip_address, is_pfish_email, urlList } = result;
  let parsed = `发件人邮箱: ${sender_email_address || '无数据'}\n`;
  parsed += `发件人IP地址: ${sender_ip_address || '无数据'}\n`;
  parsed += `钓鱼邮件检测结果: ${is_pfish_email._pfish ? '是钓鱼邮件' : '不是钓鱼邮件'}，置信度: ${is_pfish_email.confidence}\n`;

  if (urlList && urlList.length > 0) {
    parsed += `相关链接:\n`;
    urlList.forEach((url, index) => {
      parsed += `  链接${index + 1}: ${url.url} (置信度: ${url.confidence}, ${url._bad ? '恶意链接' : '安全'})\n`;
    });
  } else {
    parsed += '无相关链接信息\n';
  }

  return parsed;
};

// 文件上传方法
const handleFileUpload = async (event) => {
  data.file = event.target.files[0]; // 获取选择的文件
  const formData = new FormData();
  formData.append('file', data.file);

  try {
    const response = await axios.post('/api/file/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    if (response.data.success) {
      data.parsedResult = parseResult(response.data.data); // 解析并展示文件上传结果
    } else {
      console.error('文件上传失败', response.data.message);
    }
  } catch (error) {
    console.error('文件上传失败', error);
  }
};

// 文本检测方法
const handleTextDetection = async () => {
  try {
    const response = await axios.post(
      '/api/file/upload_text',
      null,
      { params: { text: data.emailContent } }
    );

    if (response.data.success) {
      data.parsedResult = parseResult(response.data.data); // 解析并展示文本检测结果
    } else {
      console.error('文本检测失败', response.data.message);
    }
  } catch (error) {
    console.error('文本检测失败', error);
  }
};

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
        <textarea v-model="data.emailContent" class="font_3 section_3  text_7 textarea"
          placeholder="在此输入邮件内容..."></textarea>
      </div>
      <div class="flex-row self-center group_4">
        <div class="flex-col justify-start items-center flex-1 text-wrapper_2">
          <input type="file" @change="handleFileUpload" style="display: none" id="fileUpload" />
          <label for="fileUpload" class="font_1 text_8">上传邮件文件</label>
        </div>
        <div class="flex-col justify-start items-center shrink-0 text-wrapper_3 ml-3-5" @click="handleTextDetection">
          <span class="font_1 text_8">上传邮件正文</span>
        </div>
      </div>

      <span class="self-center font_2 text_10">检测结果</span>
      <div class="flex-col self-stretch section_4 result-box">
        <pre class="self-center font_3 text_11">{{ data.parsedResult || '此处展示解析后的检测或文件上传结果' }}</pre>
        <div class="shrink-0 self-end relative  view"></div>
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
  font-family: "Noto Serif SC", serif;
  font-weight: 700;
  line-height: 1.31rem;
}

.font {
  font-size: 1.2rem;
  /* 增大字体大小 */
  font-family: "Noto Serif SC", serif;
  /* 使用黑体字体 */
  line-height: 0.94rem;
  color: #ffffff;
}

.font_1 {
  font-size: 1.2rem;
  /* 增大字体大小 */
  font-family: "Noto Serif SC", serif;
  /* 使用黑体字体 */
  line-height: 2rem;
  color: #ffffff;
}

.font:hover {
  color: #7c9bcd;
  font-family: "Noto Serif SC", serif;
  transform: scale(1.1);
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
  line-height: 2rem;
}

.section_2 {
  margin: 2.06rem 1.38rem 0;
  padding: 0.38rem 0.38rem 0.38rem 0.38rem;
  background-color: #ffffff1a;
  border-radius: 0.38rem;
}

.font_3 {
  
  font-size: 1.2rem;
  font-family: "Noto Serif SC", serif;
  line-height: 10rem;
  color: #ffffff;
}

.text_7 {
  line-height: 2rem;
}

.section_3 {
  background-color: #00000000;
  width: 100%;
  height: 10rem;
  border: 1px solid #fff;
  border-radius: 0.38rem;
  color: #ffffff;
  padding: 0.5rem;
}

.group_4 {
  margin-top: 3.03rem;
}

.text-wrapper_2 {
  padding: 0.5rem 0 1rem 0;
  background-color: #1f4788;
  border-radius: 2rem;
  width: 10rem;
  height: 3rem;
  text-align: center;
}

.text_8 {
  color: #ffffff;
}

.text-wrapper_3 {
  padding: 0.5rem 0 1rem 0;
  background-color: #ffffff14;
  border-radius: 2rem;
  width: 10rem;
  height: 3rem;
  text-align: center;
  border-left: solid 0.031rem #ffffff29;
  border-right: solid 0.031rem #ffffff29;
  border-top: solid 0.031rem #ffffff29;
  border-bottom: solid 0.031rem #ffffff29;
}

.text_9 {
  line-height: 2rem;
}

.image_2 {
  margin-top: 2.66rem;
}

.text_10 {
  margin-top: 2.63rem;
}

.section_4 {
  margin: 2.75rem 1.69rem 0;
  padding: 0.38rem 3rem 15rem 4rem;
  background-color: #ffffff1a;
  border-radius: 0.38rem;
}

.result-box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 15rem;
  /* 确保内容居中 */
}

.text_11 {
  margin-top: 15rem;
  line-height: 2rem;
  white-space: pre-line;
  text-align: center;
}

.view {
  margin-top: -0.56rem;
}
</style>
