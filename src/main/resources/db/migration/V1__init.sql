CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  github_id BIGINT UNIQUE NOT NULL,
  email TEXT,
  name TEXT,
  avatar TEXT,
  created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE repos (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users(id),
  github_repo_id BIGINT NOT NULL,
  name TEXT NOT NULL,
  full_name TEXT NOT NULL,
  visibility TEXT,
  default_branch TEXT,
  stars INT,
  forks INT,
  primary_lang TEXT,
  last_scanned_at TIMESTAMPTZ
);

CREATE INDEX idx_repos_user_id ON repos(user_id);
