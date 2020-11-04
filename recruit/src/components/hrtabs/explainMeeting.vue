<template>
  <div class="wrapper">
    <div v-if="show" class="nofind">
      <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png" style="margin: 0 auto">
      <p>暂时没有发布的宣讲会哦，快去发布吧</p>
    </div>
    <el-card  v-for="(item, key) in explainMeetingList" :key="key" class="mycard" style="height: 120px" v-if="!show">
      <div class="myflex" style="width: 70%">
        <h4 style="text-align:left">宣讲会标题：{{item.title}}</h4>
        <h4 style="text-align:left">宣讲会时间：{{item.startDate}}~{{item.endDate}}</h4>
        <h4 style="text-align:left">发布时间：{{item.publishDate}}</h4>
      </div>

      <div class="myflex" style="float:right;">
        <el-button class="" @click="view(item.id)">查看详情</el-button>
        <el-button class="" @click="deleteExplainMeeting(item.id)">删除宣讲</el-button>
      </div>
      <!-- <el-button class="delbtn" @click="deletejob(item.id)">删除职位</el-button>
      <el-button class="delbtn" @click="deletejob(item.id)">删除职位</el-button> -->
    </el-card>

    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="paging.total" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import fetch from '../../api/fetch'
export default {
  data () {
    return {
      explainMeetingList: [],
      count: 0,
      show: false,
      paging: {
          all: null, 			//总页数
          cur: 1, 			//当前页码
          page_size: 10, 		//每页显示数
          total: 0,
          list: [],
        },
    }
  },
  mounted () {
    this.getList()
  },
  watch: {
    count () {
      location.reload()
    }
  },
  methods: {
    view (id) {
      localStorage.setItem('explainMeetingId', id)
      this.$router.push({name: 'explainMeetingInfo'}) //jobInfo
    },
    getList () {
      let _this = this;
      let param = {
        // "keyword": this.content.toLowerCase()
      }

      fetch.doqueryExplainMeeting().then(res => {
        let data = JSON.parse(res.data);
        console.log(data);
        if (res.status === 200) {
          _this.explainMeetingList = data.queryresult
          _this.havejob = true
          console.log(_this.explainMeetingList.length);
          if(_this.explainMeetingList.length == 0){
              _this.show = true;
          }else{
            _this.show = false;
          }
          _this.paging.list = data.queryresult;
          _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
          _this.paging.total = data.recordsFiltered;
          console.log(_this.paging);
        }
      }).catch(e => {
        console.log(e)
      })
      // 
    },

    handleCurrentChange: function(e){
      console.log(e);
      let start = ((e-1) * this.paging.page_size) + 1
      let _this = this;
      let param = {
        // "keyword": this.content.toLowerCase()
      }
      fetch
        .doqueryExplainMeeting(param, start)
        .then(res => {
          let data = JSON.parse(res.data);
          console.log(data);
          if (res.status === 200) {
            _this.explainMeetingList = data.queryresult
            _this.havejob = true

            _this.paging.list = data.queryresult;
            _this.paging.all = Math.ceil(data.recordsFiltered / _this.paging.page_size);
            _this.paging.total = data.recordsFiltered;
            console.log(_this.paging);
          }
        })
        .catch(e => {
          console.log(e)
        })
    },

    deleteExplainMeeting (id) {
      fetch.deleteExplainMeeting(id).then(res => {
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
