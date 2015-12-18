package cn.singno.commonsframework.entity;

public class Product
{
        private String images;
        
        private String name;
        
        private String unit;
        
        private String unitVal;
        
        private Double price;

        public Product()
        {
                super();
        }

        public Product(String images, String name, String unit, String unitVal, Double price)
        {
                super();
                this.images = images;
                this.name = name;
                this.unit = unit;
                this.unitVal = unitVal;
                this.price = price;
        }

        public String getImages()
        {
                return images;
        }

        public void setImages(String images)
        {
                this.images = images;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getUnit()
        {
                return unit;
        }

        public void setUnit(String unit)
        {
                this.unit = unit;
        }

        public String getUnitVal()
        {
                return unitVal;
        }

        public void setUnitVal(String unitVal)
        {
                this.unitVal = unitVal;
        }

        public Double getPrice()
        {
                return price;
        }

        public void setPrice(Double price)
        {
                this.price = price;
        }
}
