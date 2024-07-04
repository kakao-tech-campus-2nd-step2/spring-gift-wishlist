package gift.dto;

public class EditProduct {



    public static class Request{

        private  long id;
        private String name;
        private int price;
        private String imageUrl;

        public Request( long id , String name, int price,String imageUrl) {
            this.id = id;
            this.name=name;
            this.price=price;
            this.imageUrl=imageUrl;
        }

        public long getId() {return id;}
        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public String getImageUrl() {
            return imageUrl;
        }
        

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }


    }

}
