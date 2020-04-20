package frank.controller;

import frank.model.Article;
import frank.model.Category;
import frank.model.Comment;
import frank.model.User;
import frank.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private User testUser;

    @Autowired
    private List<Article> testArticles;

    @Autowired
    private List<Category> testCategories;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){//根路径跳转到index.ftlh文件
        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User) session.getAttribute("user");
            if(user != null)
                model.addAttribute("user", user);
        }
        model.addAttribute("articleList", articleService.selectAll());
        return "index";
    }

    @RequestMapping("/a/{articleId}")//跳转到文章详情页面info.ftlh文件
    public String detail(@PathVariable("articleId") Long articleId,
                         HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User) session.getAttribute("user");
            if(user != null)
                model.addAttribute("user", user);
        }
        Article article = articleService.selectByPrimaryKey(articleId);
        model.addAttribute("article", article);
        return "info";
    }

    // 添加评论
    @RequestMapping("/a/{articleId}/comments")
    public String addComment(@PathVariable("articleId") Long articleId,
                             HttpServletRequest request,
                             Comment comment, Model model){//跳转到文章详情页面info.ftlh文件
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        comment.setUserId(user.getId());
        comment.setCreatedAt(new Date());
        comment.setArticleId(articleId);
        articleService.addComment(comment);
        return "redirect:/a/"+articleId;
    }

    // writer页面：新建文章的url是写死的 TODO
    @RequestMapping("/writer")
    public String addArticle(Model model){
        // TODO 从session获取用户
        model.addAttribute("categoryList", testCategories);
        model.addAttribute("articleList", testArticles);
        model.addAttribute("activeCid", 1L);//选中的分类id
        return "writer";
    }

    // 添加分类功能，跳转writer页面，可以不实现
    @RequestMapping(value = "/c/add", method = RequestMethod.POST)
    public String addCategory(String name, Model model){
        //TODO 添加分类到数据库
        model.addAttribute("categoryList", testCategories);
        model.addAttribute("articleList", testArticles);
        model.addAttribute("activeCid", 1L);//选中的分类id
        return "writer";
    }

    //点击分类，跳转到分类修改页面，可以不实现
    @RequestMapping("/writer/c/{categoryId}")
    public String modifyCategory(@PathVariable("categoryId") Long categoryId,
                                 Model model){
        //TODO 添加分类到数据库
        model.addAttribute("categoryList", testCategories);
        model.addAttribute("articleList", testArticles);
        model.addAttribute("activeCid", 1L);//选中的分类id
        return "writer";
    }

    // 跳转到新建/修改文章页面
    @RequestMapping("/writer/forward/{type}/{id}/editor")
    public String forwardEditor(@PathVariable("type") int type,//1是新建，2是修改
                                @PathVariable("id") Long id,//分类id或者文章id
                             Model model){
        model.addAttribute("activeCid", testCategories.get(0).getId());
        model.addAttribute("category", testCategories.get(0));
        model.addAttribute("type", type);
        if(type == 2){
            model.addAttribute("article", testArticles.get(0));
        }
        return "editor";
    }

    // 新建/修改文章
    @RequestMapping(value = "/writer/article/{type}/{id}", method = RequestMethod.POST)
    public String addOrModifyArticle(@PathVariable("type") int type,
                             @PathVariable("id") Long id,//新增：分类id，修改：文章id
                             Article article, Model model){//请求editor-html-code: <p>fire in the hole</p>
        // TODO 数据库执行新增/修改操作
        return "redirect:/writer/forward/2/2/editor";
    }
}
