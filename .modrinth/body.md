# MongoDB Java Driver

The official [MongoDB Java sync driver](https://github.com/mongodb/mongo-java-driver) (`mongodb-driver-sync` plus its core and BSON dependencies) repackaged as a Bukkit/Spigot/Paper plugin and a Fabric/Forge/NeoForge mod.

The mod does nothing on its own. Other plugins/mods that want to talk to MongoDB pick this up via softdepend (or just by being on the same classloader) and use the driver via the standard `MongoClients.create(...)` API.

## What's in the jar

The full sync-driver bundle:

- `org.mongodb:mongodb-driver-sync:5.3.1` — the API surface (`MongoClient`, `MongoCollection`, etc.)
- `org.mongodb:mongodb-driver-core:5.3.1` — wire-protocol implementation, connection pooling, server discovery
- `org.mongodb:bson:5.3.1` — BSON codecs and document model
- `org.mongodb:bson-record-codec:5.3.1` — Java record support

Plus minimal loader stubs for Spigot, Forge 1.12, Forge 1.13–1.16, Forge 1.17–1.20, NeoForge 1.21+, and Fabric. Classes stay at canonical `com.mongodb.*` and `org.bson.*` paths — no relocation — so consumers find them with plain `Class.forName`.

Service files for BSON codecs are merged via Shadow's `mergeServiceFiles()` so codec auto-discovery works.

## Compatibility

| Loader | MC versions | Notes |
|---|---|---|
| Bukkit / Spigot / Paper / Folia / Purpur | 1.8 → current | drop into `plugins/` |
| Fabric | 1.16.1 → current | needs Fabric Loader 0.14+ |
| Forge | 1.12 → 1.20 | universal jar, no Mixins |
| NeoForge | 1.21 → current | drop into `mods/` |

Java 8+ required (driver 5.x baseline).

## Using it from a plugin or mod

```kotlin
compileOnly("org.mongodb:mongodb-driver-sync:5.3.1")
```

Probe and connect:

```java
try {
    Class.forName("com.mongodb.client.MongoClients");
} catch (ClassNotFoundException e) {
    getLogger().warning("MongoDB backend disabled — install minecraft-mongodb-driver");
    return;
}
MongoClient client = MongoClients.create("mongodb://user:pass@host:27017/?authSource=admin");
```

On Paper 1.17+ add `softdepend: [minecraft-mongodb-driver]` to your `plugin.yml` so the classes are visible to your plugin's classloader.

Connection strings carry everything — auth source, replica set members, read preference, write concern. Put it all in the URI; don't try to set them via `MongoClientOptions` unless you're doing something unusual.

## Versioning

The jar version tracks the upstream sync driver. `5.3.1+2026-04-25` ships driver-sync 5.3.1 plus matching core/bson; the suffix is the build date. Auto-bump runs daily against Maven Central.

## License

Apache 2.0 (MongoDB, Inc.). The repackage adds no functional changes. Full text in [`LICENSE`](https://github.com/Axionize/minecraft-mongodb-driver/blob/main/LICENSE).

---

Issues, source: [GitHub](https://github.com/Axionize/minecraft-mongodb-driver).
