include .env
export

.PHONY: help build-local up down logs ps mysql psql test lint-check format-check format-fix
.DEFAULT_GOAL := help

build-local: ## Build docker image to local development
	docker compose build --no-cache

up: ## Do docker compose up
	docker compose up

down: ## Do docker compose down
	docker compose down

logs: ## Tail docker compose logs
	docker compose logs -f

ps: ## Check container status
	docker compose ps

mysql: ## Access MySQL Database
	docker compose exec mysql-db mysql -u $$DB_USER -p$$DB_PASSWORD --default-character-set=utf8mb4

psql: ## Access PostgreSQL Database
	docker compose exec postgresql-db psql -U $$DB_USER -d $$DB_NAME -W

test: ## Execute tests
	docker compose run --rm -v "${PWD}/api:/app/api" -w /app/api --entrypoint "" todo-api ./gradlew test --rerun-tasks

lint-check: ## Check code style with Checkstyle
	docker compose run --rm -v "${PWD}/api:/app/api" -w /app/api --entrypoint "" todo-api ./gradlew checkstyleMain checkstyleTest

format-check: ## Check code format using Spotless
	docker compose run --rm -v "${PWD}/api:/app/api" -w /app/api --entrypoint "" todo-api ./gradlew spotlessCheck

format-fix: ## Fix code format using Spotless
	docker compose run --rm -v "${PWD}/api:/app/api" -w /app/api --entrypoint "" todo-api ./gradlew spotlessApply

help: ## Show options
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' Makefile | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'