# ビルド用のベースイメージとして openjdk:21 を使用
FROM openjdk:21-jdk-slim AS builder

# 作業ディレクトリを /app/api に設定
WORKDIR /app/api

# Gradle Wrapperとプロジェクトファイルをコピー
COPY ./api/gradlew ./gradlew
COPY ./api/gradle ./gradle
COPY ./api/build.gradle ./build.gradle
COPY ./api/settings.gradle ./settings.gradle
COPY ./api/src ./src

# Gradleラッパーに実行権限を付与
RUN chmod +x ./gradlew

# Gradleを実行してプロジェクトをビルド（テストをスキップ）
RUN ./gradlew bootJar --no-daemon --info --stacktrace --debug

# ビルド成果物の確認
RUN ls -la build/libs/ || true
RUN find . -name "*.jar" || true

# 実行用のベースイメージとして openjdk:21 を使用
FROM openjdk:21-jdk-slim

# 作業ディレクトリを /app/api に設定
WORKDIR /app/api

# ビルド成果物（JARファイル）をコピー
COPY --from=builder /app/api/build/libs/todo-0.0.1-SNAPSHOT.jar /app/api/todo-api.jar

# JARファイルに実行権限を付与
RUN chmod +x /app/api/todo-api.jar

# ファイルの存在を確認
RUN ls -la /app/api/todo-api.jar

# アプリケーションの実行
ENTRYPOINT ["java", "-jar", "/app/api/todo-api.jar"]

# ポートを開放
EXPOSE 8080
