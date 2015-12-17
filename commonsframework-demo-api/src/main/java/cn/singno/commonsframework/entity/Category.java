package cn.singno.commonsframework.entity;

import java.util.List;

public class Category
{
        private Integer id;
        private String name;
        private String categoryNo;
        private Integer parantId;
        private Integer level;// 1, 2
        
        private List<Category> children;

        public Category()
        {
                super();
        }

        public Category(Integer id, String name, String categoryNo, Integer parantId, Integer level)
        {
                super();
                this.id = id;
                this.name = name;
                this.categoryNo = categoryNo;
                this.parantId = parantId;
                this.level = level;
        }

        public Integer getId()
        {
                return id;
        }

        public void setId(Integer id)
        {
                this.id = id;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getCategoryNo()
        {
                return categoryNo;
        }

        public void setCategoryNo(String categoryNo)
        {
                this.categoryNo = categoryNo;
        }

        public Integer getLevel()
        {
                return level;
        }

        public void setLevel(Integer level)
        {
                this.level = level;
        }

        public Integer getParantId()
        {
                return parantId;
        }

        public void setParantId(Integer parantId)
        {
                this.parantId = parantId;
        }

        public List<Category> getChildren()
        {
                return children;
        }

        public void setChildren(List<Category> children)
        {
                this.children = children;
        }
}
