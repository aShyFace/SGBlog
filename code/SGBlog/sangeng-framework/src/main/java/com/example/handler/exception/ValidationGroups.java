package com.example.handler.exception;

/**
 * @ClassName: ValidationGroups
 * @Description: 用于分组校验——相同字段在不同业务逻辑下有不同的校验方式，组规则为：
 *   - 不需要校验的不添加validation注解。
 *   - 需要检验的根据不同的校验规则，把字段划分为不同的组（Dto中添加validation包提供的校验注解），
 *     然后在validation注解的“groups”属性上指明组（本类的功能是定义这些不同的组）
 *
 * 一个业务需要检验多个字段，一个字段也可以被多个业务校验。
 * 业务和字段是多对多的关系，所以需要一个中间类来记录多对多关系。
 * 这个类就是这里的ValidationGroups
 *
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

    public interface TagInsert {}
}
