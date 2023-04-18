package com.example.handler.exception;

/**
 * @ClassName: ValidationGroups
 * @Description: 用于分组校验。相同的字段在不同的业务逻辑下，
 *      组规则为：不需要校验的不添加validation注解。需要检验的根据不同的校验规则，把字段划分为不同的组，然后在validation注解的“groups”属性上指明组
 * @author: Zhi
 * @date: 2023/4/17 下午2:59
 */
public class ValidationGroups {
    public interface PageParams {}

    public interface ArticleQuery {}
    public interface ArticleInsert {}
    public interface ArticleUpdate {}

    public interface UserQuery {}
    public interface UserInsert {}
    public interface UserUpdate {}
    public interface UserLoginCode {}
    public interface UserLoginPassword {}

    public interface CommentQuery {}
    public interface CommentInsert {}
    public interface CommentUpdate {}

}
