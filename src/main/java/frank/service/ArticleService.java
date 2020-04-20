package frank.service;

import frank.mapper.ArticleMapper;
import frank.mapper.CommentMapper;
import frank.model.Article;
import frank.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    public List<Article> selectAll() {
        return articleMapper.selectAll();
    }

    public Article selectByPrimaryKey(Long articleId) {
        Article article =  articleMapper.selectByPrimaryKey(articleId);
        List<Comment> comments = commentMapper.selectByArticleId(articleId);
        article.setCommentList(comments);
        article.setCommentCount(new Long(comments.size()));

        return article;
    }

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }
}
