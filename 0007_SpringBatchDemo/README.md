# 說明

* 切換當前工作目錄到parent project所在目錄
* 確認0007_SpringBatchDemo目錄下有Dockerfile檔案
* 將0007_SpringBatchDemo專案建置並打包為jar檔：`mvn clean package --projects 0007_SpringBatchDemo`
* 建置Docker Image，並對Image加上Tag `spring_batch_demo`：`docker build -t spring_batch_demo .`
* 建置完畢後啟動容器：`docker run -p 8080:8080 --name SpringBatchDemo -t spring_batch_demo`
* 重新啟動容器：`docker restart SpringBatchDemo`