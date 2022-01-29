# 說明

* 切換當前工作目錄到專案0007_SpringBatchDemo所在目錄，並確認該目錄下有Dockerfile檔案
* 將專案建置並打包為jar檔：`mvn clean package --projects 0007_SpringBatchDemo`
* 建置Docker Image，並對Image加上Tag `spring_batch_demo`：`docker build -t spring_batch_demo .`
* 建置完畢後啟動容器：`docker run -p 8080:8080 --name SpringBatchDemo -t spring_batch_demo`
* 啟動容器後，在瀏覽器輸入`http://localhost:8080/actuator/health` 應會回覆`{"status":"UP"}`
* 重新啟動容器：`docker restart SpringBatchDemo`