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
        <span class="text_5">钓鱼邮件防范宣传</span><br><br>
        <span class="text_6 mt-21"><em>警惕钓鱼邮件，保护您的网络安全!</em></span>
      </div>
      <div class="mt-38 flex-col self-stretch section">
        <div class="flex-col section_2">
          <span class="self-start font_2 text_7">一、认识钓鱼邮件</span>
          <div class="mt-44 flex-col self-stretch">
            <div class="flex-row">
              <div class="flex-col section_3">
                <span class="self-start font_3 text_8">钓鱼邮件概述</span><br>
                <span class="self-stretch font_4 mt-21">
                  钓鱼邮件（Phishing Email）是一种常见且危害极大的网络欺诈行为，攻击者通过伪装成可信赖的实体，
                  如银行、社交媒体平台、政府机构或公司，向目标收件人发送虚假的电子邮件。
                  这些邮件的主要目的是诱骗受害者提供敏感信息，如用户名、密码、信用卡信息，
                  或引导收件人点击恶意链接或下载附件，从而感染计算机或移动设备，安装恶意软件或进一步窃取信息。<br><br>

                  钓鱼邮件通常具有极强的伪装性，表面上看起来与合法的电子邮件几乎无异。为了增加可信度，
                  攻击者往往在邮件中使用合法公司或机构的标识、颜色方案、格式，甚至是伪造的签名和联系信息。
                  此外，这类邮件可能还会利用社会工程学手段，例如制造紧迫感或提供虚假奖励，
                  以增加受害者的紧张感或诱惑力，从而诱导他们迅速采取行动。<br><br>

                  钓鱼邮件的影响不仅限于个人财产损失，还可能导致更大范围的网络安全事件。
                  企业如果不慎泄露员工或客户的敏感信息，可能会面临严重的法律责任和信誉损失。
                  因此，识别和防范钓鱼邮件是现代网络安全防护中至关重要的一环。<br><br>
                </span>
              </div>
            </div>
          </div>
        </div>


        <div class="flex-col section_4">
          <span class="self-start font_2 text_10">二、常见钓鱼手法</span>
          <div class="mt-44 flex-row self-stretch">
            <div class="flex-col section_5">
              <span class="self-start font_3 text_11">1.仿冒知名品牌或机构</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  攻击者经常伪装成知名品牌或可信赖的机构，如银行、社交媒体平台、
                  电商网站或政府部门。这些邮件使用官方的语气和格式，
                  甚至包含公司标识和正式的签名。
                  常见策略包括要用户“验证账户”、“更新信息”或“解决安全问题”，通过这些伪装的请求，
                  诱导用户提供敏感信息，如登录凭据、信用卡号码或社会保障号码。
                </span>
                
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3">2.紧急或恐吓性语言</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  钓鱼邮件经常采用紧急或恐吓性的语言，
                  例如，邮件可能声称用户的账户即将被锁定、检测到异常活动、账户涉嫌欺诈，或存在未支付的欠款，
                  并且设置一个“最后时限”，加剧紧迫感，逼迫受害者在短时间内做出决定。
                  这种手法通过引发恐慌和压力，使受害者在情急之下忽略对邮件真实性的怀疑，从而更容易上当受骗。


                </span>
                
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3 text_12">3.伪造的链接</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  钓鱼邮件中的链接可能会看起来像合法网站，但实际上是指向钓鱼网站的。
                  通过使用相似的域名或混淆的URL，攻击者试图蒙蔽受害者，诱导他们输入敏感信息。
                </span>
        
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3 text_13">4.恶意附件</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  邮件附件可能包含恶意软件，如病毒、木马或勒索软件。
                  一旦受害者打开附件，恶意软件就会在其设备上安装，从而窃取信息或破坏系统。</span>
              </div>
            </div>
          </div>
        </div>

        <div class="flex-col section_4">
          <div class="mt-44 flex-row self-stretch">
            <div class="flex-col section_5">
              <span class="self-start font_3 text_11">5.社交工程攻击</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  这是一种高度针对性的钓鱼手法，攻击者利用受害者的社交信息，精心伪装成他们熟悉和信任的人，
                  例如同事、朋友、家人或上级。该手法依赖于攻击者对受害者社交关系的了解，
                  通常通过社交媒体、公开信息或网络攻击获取这些信息。
                  这些邮件可能会请求受害者提供敏感信息、点击链接、下载附件等，
                  例如转账或分享内部文件。
                </span>
                
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3">6.伪造发件人地址</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  攻击者可以伪造发件人的电子邮件地址，使得邮件看起来像是来自可信赖的来源，增加邮件的可信度。
                </span>
                
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3 text_12">7.免费或促销诱饵</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  钓鱼邮件可能会以诱人的免费礼物、巨额折扣或限时促销活动为诱饵，吸引受害者点击链接或提供个人信息。<br>
                  这种手法利用了人们对优惠的强烈兴趣，尤其是在节假日、购物季或特殊促销期间。
                  当受害者收到一封声称他们赢得了大奖或有机会获得独家折扣的邮件时，往往会因为激动而忽略对邮件真实性的怀疑。
                
                </span>
        
              </div>
            </div>
            <div class="ml-24 flex-col section_5">
              <span class="self-start font_3 text_13">8.伪造登录页面</span>
              <div class="flex-col self-stretch mt-21">
                <span class="font_5"><br>
                  邮件中的链接可能会指向一个伪造的登录页面，看起来和受害者常用的合法网站几乎一模一样，
                  目的是获取受害者的登录凭据</span>
              </div>
            </div>
          </div>
        </div>




        
        <div class="flex-col group_3">
          <span class="self-start font_2 text_22">三、真实案例</span>
          <div class="mt-44 flex-row justify-between self-stretch section_8">
            <img
              class="self-center image_3"
              src="@/image/fish.jpg"
            />
            <div class="flex-col self-start group_4">
              <span class="self-start text_23">案例一：假扮官方实施欺诈</span>
              <span class="mt-22 self-stretch font_4 text_24">
                2021年，我国某涉密军工企业工作人员收到了一封伪装成邮件服务商警告信息的“钓鱼”邮件，
                受诱导点击后导致工作邮箱账户密码泄露。境外间谍情报机关通过该密码登录其电子邮箱，
                窃取了大量敏感工作资料。<br><br>

                境外间谍情报机关会预先搭建一个与目标电子邮箱高度相似的邮箱登录界面，并伪装成邮件服务商，
                向指定用户发送虚假的“高风险账户警告信息”邮件。待目标对象点击后，“高仿”登录界面便会弹出，
                一旦输入账号密码，便会被境外间谍掌握。
                
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
  .text_5 {
    color: #ffffff;
    font-size: 3rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2.7rem;
  }
  .text_6 {
    color: #ffffff;
    font-size: 1.3rem;
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
    font-size: 1.75rem;
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
    height: 28rem;
  }
  .font_3 {
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.21rem;
    font-weight: 800;
    color: #000000;
  }
  .text_8 {
    line-height: 1.21rem;
  }
  .font_4 {
    font-size: 1.2rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2rem;
    color: #374151;
  }
  .text_9 {
    line-height: 1.2rem;
  }
  .section_4 {
    margin-top: 0rem;
    padding: 2.5rem 6rem 2.5cm;
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
    height: 27.5rem;
  }
  .text_11 {
    line-height: 1.2rem;
  }
  .font_5 {
    font-size: 1.2rem;
    font-family: "Noto Serif SC", serif;
    line-height: 2rem;
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
    border-radius: 2rem;
    width: 36rem;
    height: 24rem;
  }
  .group_4 {
    margin-top: 0.38rem;
    width: 35.94rem;
  }
  .text_23 {
    color: #ffffff;
    font-size: 1.5rem;
    font-family: "Noto Serif SC", serif;
    line-height: 1.04rem;
  }
  .text_24 {
    color: #ffffff;
  }
  em {
    font-style: italic; /* 设置斜体样式 */
  }
</style>