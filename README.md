# spring-gift-wishlist

## 수정된 코드 구조
```plaintext
└── src
    └── main
        ├── java
        │   └── gift
        │       ├── Application.java
        │       ├── Product.java
        │       ├── dao
        │       │   └── ProductDao.java
        │       ├── controller
        │       │   └── ProductController.java
        │       ├── model
        │       │   └── ProductModel.java
        │       └── service
        │           └── ProductService.java
        └── resources
            ├── data.sql
            ├── schema.sql
            ├── static
            └── templates
                ├── add.html
                ├── edit.html
                ├── list.html
                └── view.html