<template>
  <div class="ui attached segment" :style="bgColor">
    <div class="typo content">
      <Title :title="title" />
      <div class="ui middle aligned mobile reversed stackable">
        <div class=" m-padded-tb-small line-numbers match-braces rainbow-braces "
             v-viewer
             v-html="about.content"
        ></div>
      </div>
      <!--评论-->
      <Comment :commentEnabled="about.commentEnabled" :type="2"></Comment>
    </div>
  </div>
</template>

<script>
import Title from "@/components/common/Title";
import {getAbout,} from '@/api/about'
import Comment from "@/components/comment/Comment";
import {formatLink} from "@/utils/link";

export default {
  name: "Blog",
  components: {
    Comment,
    Title,
  },
  data() {
    return {
      title: "关于我",
      about: {}
    };
  },

  methods: {

    init() {
      getAbout().then(res => {
        this.about = res.result
        this.about.content = formatLink(this.about.content)
        this.$nextTick(() => {
          this.$primsjs.highlightAll();
        });
      });
    },
  },

  mounted() {
    this.init();
  },
};
</script>

<style scoped>


.el-divider {
  margin: 1rem 0 !important;
}

h1::before,
h2::before,
h3::before,
h4::before,
h5::before,
h6::before {
  display: block;
  content: " ";
  height: 55px;
  margin-top: -55px;
  visibility: hidden;
}

</style>
