<template>
  <div class="wrapper">
    <div v-if="show" class="nofind">
      <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png" style="margin: 0 auto">
      <p>暂时没有发布的职位哦，快去发布吧</p>
    </div>
    <el-card  v-for="(item, key) in joblist" :key="key" class="mycard" style="height: 120px" v-if="!show">
      <div class="myflex" style="width: 60%">
        <h4 style="text-align:left">职位名称：{{item.name}}</h4>
        <h4 style="text-align:left">职位简介：{{item.introduction}}</h4>
        <h4 style="text-align:left">职位诱惑：{{item.temptation}}</h4>
        <h4 style="text-align:left">技能标签：{{item.tag}}</h4>
      </div>

      <!-- <div class="myflex" style="width: 40%">
        <h4 style="text-align:left">学历要求：{{item.education}}</h4>
        <h4 style="text-align:left">工作地点：{{item.workAddress}}</h4>
        <h4 style="text-align:left">是否发布：{{item.activeFlg == '2' ? '已发布' : '未发布'}}</h4>
        <h4 style="text-align:left">发布时间：{{item.publishedTime}}</h4>
      </div> -->

      <div class="myflex" style="float:right;">
        <el-button class="" @click="checkDetail(item.id)">职位详情</el-button>
        <el-button class="" @click="deletejob(item.id)">删除职位</el-button>
      </div>
      <!-- <el-button class="delbtn" @click="deletejob(item.id)">删除职位</el-button>
      <el-button class="delbtn" @click="deletejob(item.id)">删除职位</el-button> -->
    </el-card>
  </div>
</template>
<script>
import fetch from '../../api/fetch'
export default {
  data () {
    return {
      joblist: [],
      count: 0,
      show: false
    }
  },
  mounted () {
    this.checkJob()
  },
  watch: {
    count () {
      location.reload()
    }
  },
  methods: {
    checkDetail (id) {
      localStorage.setItem('jobId', id)
      this.$router.push({name: 'jobInfo'})
    },
    checkJob () {
      fetch.checkJob().then(res => {
        let data = JSON.parse(res.data);
        console.log(data);
        if (data.result === true) {
          this.joblist = data.jobList
          console.log(this.joblist);
          if (this.joblist.length === 0) {
            this.show = true
          }
        }
      }).catch(e => {
        console.log(e)
      })
      // this.show = true;
    },
    deletejob (id) {
      fetch.deletejob(id).then(res => {
        let data = JSON.parse(res.data);
        if (res.status === 200) {
          if (data.result) {
            this.$message({
              message: "删除成功",
              type: 'success'
            })
            this.count++
          }
        }
      }).catch(e => {
        console.log(e)
      })
    }
  }
}
</script>

<style>

.mycard {
  border-left: 5px solid #888;
  height: 100px;
  margin-bottom: 14px;
}
.myflex {
  float: left;
}
.delbtn {
  float: right;
}
</style>
