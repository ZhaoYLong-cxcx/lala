package frank.config;

import frank.interceptor.LoginInterceptor;
import frank.model.Article;
import frank.model.Category;
import frank.model.Comment;
import frank.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class BlogConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/login/l3")
                .excludePathPatterns("/")//排除根路径
                .excludePathPatterns("/index")//排除首页
                .excludePathPatterns("/login")//排除登录页面
                // 排序static文件夹下所有的静态资源
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/plugins/editor/**")
                ;
    }

    @Bean
    public User testUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("张三");
        user.setNickname("尼古拉斯赵四");
        user.setAvatar("http://xxxxx.com");
        return user;
    }

    @Bean
    public Comment testComment1(User testUser){
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setUserId(1L);
        comment.setUser(testUser);
        comment.setArticleId(1L);
        comment.setContent("评论信息1");
        comment.setCreatedAt(new Date());
        return comment;
    }

    @Bean
    public Comment testComment2(User testUser){
        Comment comment = new Comment();
        comment.setId(2L);
        comment.setUserId(1L);
        comment.setUser(testUser);
        comment.setArticleId(1L);
        comment.setContent("评论信息2");
        comment.setCreatedAt(new Date());
        return comment;
    }

    @Bean
    public Comment testComment3(User testUser){
        Comment comment = new Comment();
        comment.setId(3L);
        comment.setUserId(1L);
        comment.setUser(testUser);
        comment.setArticleId(1L);
        comment.setContent("评论信息3");
        comment.setCreatedAt(new Date());
        return comment;
    }

    @Bean
    public List<Comment> testCommentList(Comment testComment1,
                                 Comment testComment2, Comment testComment3){
        List<Comment> comments = new ArrayList<>();
        comments.add(testComment1);
        comments.add(testComment2);
        comments.add(testComment3);
        return comments;
    }

    @Bean
    public Article testArticle1(User testUser, List<Comment> testCommentList){
        Article article = new Article();
        article.setId(1L);
        article.setCategoryId(1);
        article.setUserId(2L);
        article.setTitle("文章1");
        article.setContent("文章1内容xxx");
        article.setCoverImage("http://xxxxx.com");
        article.setViewCount(8L);
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setStatus((byte)1);
        article.setAuthor(testUser);
        article.setCommentCount(5L);
        article.setCommentList(testCommentList);
        return article;
    }

    @Bean
    public Article testArticle2(User testUser, List<Comment> testCommentList){
        Article article = new Article();
        article.setId(2L);
        article.setCategoryId(1);
        article.setUserId(2L);
        article.setTitle("文章2");
        article.setContent("文章2内容xxx");
        article.setCoverImage("http://xxxxx.com");
        article.setViewCount(8L);
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setStatus((byte)1);
        article.setAuthor(testUser);
        article.setCommentCount(5L);
        article.setCommentList(testCommentList);
        return article;
    }

    @Bean
    public Article testArticle3(User testUser, List<Comment> testCommentList){
        Article article = new Article();
        article.setId(3L);
        article.setCategoryId(1);
        article.setUserId(2L);
        article.setTitle("文章3");
        article.setContent("文章3内容xxx");
        article.setCoverImage("http://xxxxx.com");
        article.setViewCount(8L);
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        article.setStatus((byte)1);
        article.setAuthor(testUser);
        article.setCommentCount(5L);
        article.setCommentList(testCommentList);
        return article;
    }

    @Bean
    public List<Article> testArticles(Article testArticle1,
                                      Article testArticle2, Article testArticle3){
        List<Article> articles = new ArrayList<>();
        articles.add(testArticle1);
        articles.add(testArticle2);
        articles.add(testArticle3);
        return articles;
    }

    @Bean
    public Category testCategory1(){
        Category category = new Category();
        category.setId(1);
        category.setUserId(1L);
        category.setName("计算机");
        return category;
    }

    @Bean
    public Category testCategory2(){
        Category category = new Category();
        category.setId(2);
        category.setUserId(1L);
        category.setName("英语");
        return category;
    }

    @Bean
    public Category testCategory3(){
        Category category = new Category();
        category.setId(3);
        category.setUserId(1L);
        category.setName("数学");
        return category;
    }

    @Bean
    public List<Category> testCategories(Category testCategory1,
                                 Category testCategory2, Category testCategory3){
        List<Category> categories = new ArrayList<>();
        categories.add(testCategory1);
        categories.add(testCategory2);
        categories.add(testCategory3);
        return categories;
    }
}
