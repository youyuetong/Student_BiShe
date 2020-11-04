<template>
<div>
  <div class="indexContain">
<div class="cardBox">
 <el-carousel trigger="click" height="400px" style="position: sticky;">
      <el-carousel-item v-for="(item, key) in crouselImg" :key="key">
        <img :src="item.img" class="boxImg" >
      </el-carousel-item>
    </el-carousel>
</div>
  </div>
  <div class="division">
    <h3>热门企业</h3>
    <h3 style="color: #888;font-weight: 400">--- Hot ---</h3></div>
  <div class="cardContain">
<div class="wrapper-card" style="height: 500px; min-height: 500px;">
  <div class="card" style="height: 130px; background: #4786ee" v-for="(item, key) in companyList" :key="key">
    <div  @click="getCompanyDetail(item.id)" style="text-align: left; padding-left: 20px; color: #fff">
      <h3 style="padding-top: 10px">企业名称：{{item.name}}</h3>
      <p>企业电话：{{item.mobile}}</p>
      <p>企业简介：{{item.introduction}}</p>
      <p>企业地址：{{item.address}}</p>
      <p>建企时间：{{item.birthDate}}</p>
    </div>
    <!-- <img :src="item.avatar" class="image"> -->
</div>
</div>
  </div>


<div class="division">
    <h3>热门宣讲会</h3>
    <h3 style="color: #888;font-weight: 400">--- Hot ---</h3></div>
  <div class="cardContain">
<div class="wrapper-card" style="height: 500px; min-height: 500px;">
  <div class="card" style="height: 130px; background: #4786ee" v-for="(item, key) in explainMeetingList" :key="key">
    <div  @click="getExplainMeeting(item.id)" style="text-align: left; padding-left: 20px; color: #fff">
      <h3 style="padding-top: 10px">宣讲会主题：{{item.title}}</h3>
      <p>举行时间：{{item.startDate}}~{{item.endDate}}</p>
      <p>发布时间：{{item.publishDate}}</p>
      <p>企业名称：{{item.enterpriseName}}</p>
    </div>
    <!-- <img :src="item.avatar" class="image"> -->
</div>
</div>
  </div>



  <!--推荐-->
  <div class="division" v-if="isLogin && isHr">
    <h3>推荐候选人</h3>
    <h3 style="color: #888;font-weight: 400">---- Hot ----</h3>
  </div>
  <!--推荐候选人--->
  <div class="recommand" v-if="isLogin && isHr">
    <!---简历弹窗-->
   <el-dialog :title="getResumeList.name+'的简历'" :visible.sync="isShow">
        <table border="1" cellspacing="0" style="border-color:#ededed" class="mytable">
          <tr>
            <td>姓名：</td>
            <td>{{getResumeList.name}}</td>
          </tr>
          <tr>
            <td>年龄：</td>
            <td>{{getResumeList.age}}</td>
          </tr>
          <tr>
            <td>性别：</td>
            <td>{{getResumeList.sex}}</td>
          </tr>
          <tr>
            <td>电话：</td>
            <td>{{getResumeList.phone}}</td>
          </tr>
          <tr>
            <td>邮箱：</td>
            <td>{{getResumeList.email}}</td>
          </tr>
          <tr>
            <td>地址：</td>
            <td>{{getResumeList.address}}</td>
          </tr>
          <tr>
            <td>学校：</td>
            <td>{{getResumeList.school}}</td>
          </tr>
          <tr>
            <td>毕业时间：</td>
            <td>{{getResumeList.endTime}}</td>
          </tr>
          <tr>
            <td>技术栈：</td>
            <td>
          <tr v-for="(item, key) in getResumeList.skills" :key="key">
            <td class="progress2">{{item.name}}</td>
            <td class="progress2">熟悉程度：
              <el-progress :text-inside="true" :stroke-width="15" :percentage="item.level*25"></el-progress>
            </td>
          </tr>
          </td>
          </tr>
          <tr>
            <td>实习（工作）经历：</td>
            <td>{{getResumeList.experience}}</td>
          </tr>
          <tr>
            <td>自我介绍：</td>
            <td>{{getResumeList.introduce}}</td>
          </tr>
          <tr>
            <td>获奖经历：</td>
            <td>{{getResumeList.awards}}</td>
          </tr>
        </table>
      </el-dialog>

  <el-carousel height="180px" >
    <el-carousel-item v-for="(recommand, key) in recommandList" :key="key" class="el-carousel-item">
        <div v-for="(candidate, key) in recommand.candidateList" :key="key" class="recommandList" @click="getTableList(candidate.userId)">
        <el-progress type="circle" :percentage="candidate.rate" :width=100 :stroke-width="8"></el-progress>
        <div class="recommandInfo">
          <p>{{recommand.title}}</p>
          <p>{{candidate.name}}</p>
          <p>{{candidate.school}}</p>
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>

  <!--推荐职位-->
    <!-- <el-carousel height="180px" v-else>
    <el-carousel-item  class="el-carousel-item">
        <div class="recommandJob" v-for="(recommand, key) in recommandList" :key="key" @click="jobDetail(recommand.recruitId)">
          <p>{{recommand.companyName}}</p>
          <p>{{recommand.title}}</p>
        </div>
    </el-carousel-item> -->
  </el-carousel>
  </div>

  <!--热门职位-->
  <div class="division">
    <h3>热门职位</h3>
    <h3 style="color: #888;font-weight: 400">--- JOBS ---</h3>
  </div>
  <div class="newsContain">
    <div class="temp">
      <div class="newsItem"  v-for = "(item, key) in jobList" :key = "key" @click="jobDetail(item.id)">
        <!-- <span>{{item.name}}</span> -->
        <div class="picContain" ontouchstart="this.classList.toggle('hover');" style="background: #4555ee; width: 200px;">
          <div class="flipper" style="width: 200px">
            <span class="itemPic" style="background: #4786ee; width: 200px;"><b>{{item.name}}</b></span>
          </div>
        </div>
        <div>
          <h4><u><b>职位名称：{{item.name}}</b></u></h4>
            <p >职位简介：{{item.introduction}}</p>
            <p >职位诱惑：{{item.temptation}}</p>
            <p >职位标签：{{item.tag}}</p>
          </div>
        </div> 
    </div>
  </div>

  <div class="aboutus">
    <div id="aboutusInfo">
    <h2>关于我们</h2>
    <p>面向互联网求职招聘，提供更多的实习和工作机会</p>
    <p>即刻起，点赞你的生活，从这一份工作开始。</p>
    </div>
  </div>
  <div class="division"><h3>联系我们</h3>
    <h3 style="color: #888;font-weight: 400">--- CONTACT ---</h3></div>
  <div class="footer" style="font-size: 20px; color: #fff; text-weight: wide">
    <span>学校：xxxx</span>
    <span>姓名：xxxx</span>
    <span>学号：xxxx</span>
    <span>导师：xxxx</span>
  </div>
</div>
</template>
<style>
@import "../css/index.css";
@import "../assets/Animate/animate.min.css";
body {
  background: #ededed;
}

.myMenu {
  position: sticky;
  top: 0;
  z-index: 100;
}

.indexContain {
  width: 100%;
  height: 100%;
  border:1px solid #ededed;
  background: #fff;
}

.cardContain {
  width: 100%;
  height: 100%;
  background: #fff;
}

.newsContain {
  padding-top: 1px;
  width: 100%;
  height: 100%;
  background: #fff;
}

.picContain {
  margin-right: 10px;
  perspective: 1000px;
}

.picContain:hover .flipper, .picContain.hover .flipper{
  transform: rotateY(180deg);
}

.picContain, .itemPic, .back{
  width: 80px;
  height: 80px;
}

.flipper {
  transition: 0.6s;
  transform-style:preserve-3d;
  position: relative;
}

.newsItem {
  display: flex;
  justify-content: flex-start;
  width: 1200px;
  margin: auto;
  height: 114px;
  text-align: left;
  color: #5a5a5a;
  font-weight: 500;
  padding-top: 15px;
  border-bottom: 1px solid #ededed;
}

.itemPic, .back {
  position: absolute;
  top: 0;
  left: 0;
  backface-visibility: hidden;
  background: #cc0000;
  text-align: center;
  color: white;
  font-weight: 500;
  line-height: 80px;
  white-space: nowrap;
}

.itemPic {
  z-index: 2;
}

.back {
  transform: rotateY(180deg);
}

.footer {
  width: 100%;
  height: 100px;
  background: black;
  padding-top:20px
}

.footer a {
  color: white;
  text-decoration: none;
}

.aboutus  {
  width: 100%;
  height: 500px;
  background: url("../assets/loge2.jpg") no-repeat;
  background-size: 100% 100%;
  filter: grayscale(70%);
  opacity: 0.7;
  color: white;
  font-weight: 600;
  padding-top: 60px;
}

.aboutus p{
  margin-top: 30px;
  font-size: 18px;
}

#aboutusInfo {
  margin-top: 80px;
  animation-delay: 1s
}

.cardBox {
  position: relative;
  width: 1200px;
  margin: 20px auto 30px auto;
  box-shadow: 0 10px 15px #888;
  border-radius: 6px;
}

.wrapper-card {
  width: 1200px;
  height:1000px;
  margin: 30px auto auto auto;
  padding-top: 30px;
}

.wrapper-card .card {
  color: #07111B;
  font-size: 16px;
  width: 336px;
  height: 243px;
  float: left;
  margin: 30px;
  border-radius: 6px;
}

.wrapper-card .card:hover {
  transform: translateY(-5px);
  transition: 3ms;
  box-shadow: 5px 5px 10px #888;
}

.wrapper-card .image {
  border-radius: 6px 6px 0 0;
  width: 100%;
  height: 100%;
  margin-bottom: 20px;
  border-radius: 6px;
}

.boxImg {
  width: 100%;
  height: 100%;
  border-radius: 6px;
}

.division {
  width: 100%;
  margin: 30px  auto;
  text-align: center;
  padding-left: 10px;
   color: #5a5a5a;
}

.footer img{
  width:25px;
  height: 25px;
  margin-right: 10px
}

.footer span {
  margin-right: 20px;
}

.recommand {
  width: 100%;
  height: 100%;
  background: #fff;
  padding: 36px 0;
  font-size: 18px;
}

.recommandList {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  color: #5a5a5a;
  font-weight: 500;
}

.recommandInfo {
  margin-left: 16px;
}

.recommandInfo p {
  margin-bottom: 6px;
}

.el-progress__text {
  font-size: 16px !important;
  text-align: center !important;
}

.el-carousel-item {
  display: flex;
  justify-content: space-around;
}

.el-carousel {
  width: 1200px;
  margin: 0 auto;
}

.recommandJob {
  border: 1px solid #ebebeb;
  text-align: left;
  color: #fff;
  line-height: 30px;
  background: linear-gradient(#948E99, #2E1437);
  border-radius: 4px;
  width: 150px;
  height: 180px;
  padding: 20px 10px 10px;
  font-weight: bold;
}

.mytable {
  width: 100%;
  height: 700px;
}

.progress2 {
  width: 182px;
  border: 0;
}

</style>
<script>
import fetch from '../api/fetch'
export default {
  data () {
    return {
      crouselImg: [
        {img: 'https://sxsimg.xiaoyuanzhao.com/adservice/image/20200317175914mfoz9OJGb0AhWry3Jn.jpeg'},
        {img: 'https://sxsimg.xiaoyuanzhao.com/adservice/image/20200316172132nhgfnuTQb4Jbxg5oeJ.png'},
        {img: 'https://sxsimg.xiaoyuanzhao.com/adservice/image/20200229103631FNJqsxDDp2Llc3FjgX.png'},
        {img: 'https://sxsimg.xiaoyuanzhao.com/adservice/image/20200319185438KYbETjb3qb8Ca2xNNe.png'}

      ],
      activeIndex2: '1',
      currentDate: '完美',
      company: '',
      companyList: [],
      jobList: [],
      explainMeetingList: [],
      recommandList: [],
      isHr: localStorage.getItem('role') === '1',
      isLogin: !!localStorage.getItem('token'),
      isShow: false,
      getResumeList: {
        name: '',
        sex: '',
        age: '',
        skills: [{
          id: 1,
          name: '',
          level: '',
          resumeId: 1
        }],
        school: '',
        address: '',
        endTime: 2019,
        phone: '',
        email: '',
        introduce: '',
        experience: '',
        awards: '',
        avatar: '',
        projectExList: [],
        educationExList: [],
        workExList: []
      }
    }
  },

  mounted () {
    window.addEventListener('scroll', this.handler)
    this.getCompany()
    this.getJob()
    // this.getRecommand()
    this.getExplainMeetingList();
  },
  methods: {
    handler () {
      let info = document.getElementById('aboutusInfo') || null
      let card = document.getElementsByClassName('temp')[0] || null
      if (info === null || card === null) {

      } else if (document.documentElement.scrollTop > 1000) {
        card.classList.add('animated')
        card.classList.add('bounceInLeft')
        info.classList.add('animated')
        info.classList.add('bounceInLeft')
      } else {
        info.classList.remove('animated')
        info.classList.remove('bounceInLeft')
        card.classList.remove('animated')
        card.classList.remove('bounceInLeft')
      }
    },

    jobDetail (id) {
      localStorage.setItem('jobId', id)
      this.$router.push({name: 'jobInfo'})
    },

    getJob () {
      let _this = this;
      console.log("job");
      let param = {

      }
      fetch.findJob(param).then(res => {
        let data = JSON.parse(res.data);
        console.log(data);
        if (res.status === 200) {
          console.log("important");
          console.log(data.queryresult);
          _this.jobList = data.queryresult;
          console.log(_this.jobList);
        }
      }).catch(e => {
        console.log(e)
      })
    },

    getExplainMeetingList() {
      let _this = this;
      let param = {

      }
      fetch.doqueryExplainMeeting(param).then(res => {
        let data = JSON.parse(res.data);
        if (res.status === 200) {
          _this.explainMeetingList = data.queryresult;
        }
      }).catch(e => {
        console.log(e)
      })
    },
    getCompany () {
      let param = {status: "审核通过"};


      fetch.getCompany(param).then(res => {
        console.log(res);
        let data = JSON.parse(res.data);
        if (res.status === 200) {
          this.companyList = data.queryresult
        }
      })
    },

    getCompanyDetail (id) {
      localStorage.setItem('companyId', id)
      this.$router.push({name: 'companyDetail'})
    },

    getExplainMeeting (id) {
      localStorage.setItem('explainMeetingId', id)
      this.$router.push({name: 'explainMeetingInfo'})
    },
    // 获取推荐列表
    getRecommand () {
      if (this.isLogin) {
        if (this.isHr) {
          fetch.recommendCandidate().then(res => {
            if (res.status === 200) {
              this.recommandList = res.data.data
            }
          })
        } else {
          fetch.recommendJob().then(res => {
            if (res.status === 200) {
              this.recommandList = res.data.data
            }
          })
        }
      }
    },
    // 查看推荐候选人
    getTableList (id) {
      this.isShow = true
      fetch
        .getResume(id)
        .then(res => {
          if (res.status === 200) {
            if (res.data.success === true) {
              if (res.data.data !== null) {
                this.getResumeList = res.data.data
                this.getResumeList.projectExList = res.data.projectExList;
                this.getResumeList.workExList = res.data.workExList;
                this.getResumeList.educationExList = res.data.educationExList
              }
            }
          }
        })
        .catch(e => {
          console.log(e)
        })
    }
  }

}
</script>
