#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

choose_java_home() {
  if [[ -n "${JAVA21_HOME:-}" && -x "${JAVA21_HOME}/bin/java" ]]; then
    echo "${JAVA21_HOME}"
    return
  fi

  # Homebrew OpenJDK 21 (Apple Silicon default prefix)
  if [[ -x "/opt/homebrew/opt/openjdk@21/bin/java" ]]; then
    echo "/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home"
    return
  fi

  # Fallback to system selector
  if command -v /usr/libexec/java_home >/dev/null 2>&1; then
    local candidate
    candidate="$(/usr/libexec/java_home -v 21 2>/dev/null || true)"
    if [[ -n "${candidate}" && -x "${candidate}/bin/java" ]]; then
      echo "${candidate}"
      return
    fi
  fi

  echo "JDK 21 not found. Install openjdk@21 (brew install openjdk@21) or set JAVA21_HOME." >&2
  exit 1
}

JAVA_HOME="$(choose_java_home)"
export JAVA_HOME
export PATH="${JAVA_HOME}/bin:${PATH}"

if [[ ! -x "${ROOT_DIR}/gradlew" ]]; then
  echo "gradlew not found. Run 'JAVA_HOME=${JAVA_HOME} gradle wrapper --gradle-version 9.2.1' first." >&2
  exit 1
fi

# Default task: test
if [[ $# -eq 0 ]]; then
  set -- test
fi

echo "Using JAVA_HOME=${JAVA_HOME}"
exec "${ROOT_DIR}/gradlew" "$@"
