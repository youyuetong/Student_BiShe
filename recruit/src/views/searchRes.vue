<template>
  <div>
    <div>
          <el-input v-model="search.enterpriseName" style="width: 200px" placeholder="请输入公司名称"></el-input>

          <el-input v-model="search.name" style="width: 200px" placeholder="请输入职位名称"></el-input>

          <el-input v-model="search.address" style="width: 200px" placeholder="请输入工作地点"></el-input>

          <el-select v-model="search.education" style="width: 200px" placeholder="请选择学历要求" >
            <el-option label="大专" value="大专"></el-option>
            <el-option label="本科" value="本科"></el-option>
            <el-option label="研究生" value="研究生"></el-option>
            <el-option label="博士" value="博士"></el-option>
          </el-select>

       <el-button type="primary" @click="getJob">搜索</el-button>
    </div>

    <el-card class="nojob" v-if="!havejob">暂时没有该岗位信息</el-card>
    <el-card v-if="havejob" v-for="(item, key) in list" :key="key" class="jobcard">
      <div  @click="findDetail(item.id)">
     <!-- <img class="jobavatar" :src="item.company.avatar"/> -->
     <div class="introduce">
        <h3><u><b>职位名称：{{item.name}}</b></u></h3>
        <span>发布公司：{{item.companyName}}</span>
        <span>最低学历：{{item.education}}</span>
        <span>职位简介：{{item.introduction}}</span>
        <span>职位诱惑：{{item.temptation}}</span>
        <span>职位标签：{{item.tag}}</span>
       <!-- <p>{{item.name}}</p>
       <p>{{item.workAddress}}<span>|</span>{{item.city}}<span>|</span>{{item.region}}</p> -->
     </div>
      </div>
    </el-card>

    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="paging.total" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>
<style>
.nojob {
  margin: 20px auto auto auto;
}
.jobcard {
  width: 1000px;
  margin: 20px auto auto auto;
}
.jobavatar {
  float: left;
  width: 126px;
  height: 110px;
  margin: auto 14px 20px auto;
}
.introduce {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.introduce p {
  font-size: 18px;
  margin-bottom: 14px;
}
</style>
<script>
import fetch from '../api/fetch'
export default {
  data () {
    return {
      content: localStorage.getItem('content'),
      list: [],
      havejob: false,
      paging: {
        all: null, 			//总页数
        cur: 1, 			//当前页码
        page_size: 10, 		//每页显示数
        total: 0,
        list: [],
      },

      search: {
        enterpriseName: null,
        name: null,
        education: null,
        address: null,
      }
    }
  },
  mounted () {
    this.getJob()
  },
  methods: {
    findDetail (id) {
      localStorage.setItem('jobId', id)
      this.$router.push({name: 'jobInfo'})
    },
    getJob () {
      let _this = this;
      let param = {
        "keyword": this.content.toLowerCase(),
        "enterpriseName": this.search.enterpriseName,
        "name": this.search.name,
        "education": this.search.education,
        "address": this.search.address,
      }
      fetch
        .findJob(param)
        .then(res => {
          let data = JSON.parse(res.data);
          console.log(data);
          if (res.status === 200) {
            console.log("important");
            console.log(data.queryresult);
            _this.list = data.queryresult
            _this.havejob = true

            _this.paging.list = data.queryresult;
            _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
            _this.paging.total = data.recordsFiltered;
            console.log(_this.paging);
          }


          // if (res.status === 200) {
          //   if (res.data.success === true) {
          //     if (res.data.data.recruitList.length !== 0) {
          //       this.list = res.data.data.recruitList
          //       this.havejob = true
          //     }
          //   }
          // }
        })
        .catch(e => {
          console.log(e)
        })
    },
    handleCurrentChange: function(e){
      console.log(e);
      let start = ((e-1) * this.paging.page_size) + 1
      let _this = this;
      let param = {
        "keyword": this.content.toLowerCase()
      }
      fetch
        .findJob(param, start)
        .then(res => {
          let data = JSON.parse(res.data);
          console.log(data);
          if (res.status === 200) {
            console.log("important");
            console.log(data.queryresult);
            _this.list = data.queryresult
            _this.havejob = true

            _this.paging.list = data.queryresult;
            _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
            _this.paging.total = data.recordsFiltered;
            console.log(_this.paging);
          }


          // if (res.status === 200) {
          //   if (res.data.success === true) {
          //     if (res.data.data.recruitList.length !== 0) {
          //       this.list = res.data.data.recruitList
          //       this.havejob = true
          //     }
          //   }
          // }
        })
        .catch(e => {
          console.log(e)
        })
    }
  }
}
</script>
