# ONI Seed Browser Backend

![Kotlin](https://img.shields.io/badge/kotlin-2.2.20-blue.svg?logo=kotlin)
[![License: AGPL-3.0](https://img.shields.io/badge/license-AGPL--3.0-blue.svg)](https://www.gnu.org/licenses/agpl-3.0)
![JVM](https://img.shields.io/badge/-JVM-gray.svg?style=flat)
[![GitHub Sponsors](https://img.shields.io/badge/Sponsor-gray?&logo=GitHub-Sponsors&logoColor=EA4AAA)](https://github.com/sponsors/StefanOltmann)

This is the backend for https://github.com/StefanOltmann/oni-seed-browser

## Docker multi-arch build (amd64 + arm64)

Docker buildx is required for native multi-arch images:

```bash
docker buildx create --use --name oni-seed-browser-builder
docker buildx inspect --bootstrap
docker buildx build --platform linux/amd64,linux/arm64 -t your-registry/oni-seed-browser-backend:latest --push .
```

Notes:
- Multi-arch builds must be pushed to a registry; `--load` only loads a single arch image locally.
- If you only want one architecture locally, use `--platform linux/arm64` (or `linux/amd64`) with `--load`.
