# AsyncCatcher for Fabric

A port of PaperMC's AsyncCatcher mechanism to Fabric, preventing asynchronous thread issues and ensuring server state safety.

## 📌 Overview

In the **Spigot and PaperMC** server ecosystem (including their various forks), **AsyncCatcher** is a critical internal tool designed to intercept unsafe operations executed from secondary, asynchronous threads. This mod ports that exact protection layer to **Fabric**, allowing server administrators and mod developers to catch race conditions and thread-unsafe modifications before they cause silent world corruption or hard-to-debug server crashes.

By default, this mod strictly **blocks** unsafe async calls, forcing the server to throw an `IllegalStateException` immediately when an invalid operation is detected outside the main server thread. This prevents race conditions and ConcurrentModificationExceptions (CME), ensuring the absolute integrity of your running server state.

## 🛠️ Features

* **Thread Safety Enforcement:** Automatically detects when sensitive game mechanics (like player teleportation, entity ticking, or block modifications) are called from an asynchronous thread and prevents them from occurring (fail-fast behavior).

## 🚀 Installation & Setup

1. Make sure you are using **Fabric Loader** and **Fabric API** for your server.
2. Drop the `.jar` file into your server's `mods` folder.
3. Restart the server. No further configuration is needed; the protection is active by default.

## 📝 License & Compliance

This project is licensed under the **GNU General Public License v3.0 or later** (`GPL-3.0-or-later`).

It inherits this license due to its nature as a port of the `AsyncCatcher` mechanism originally found in the Spigot/PaperMC source code ecosystem. All original copyright policies apply.

### Credits
* **PaperMC Team / SpigotMC:** Authors of the original `AsyncCatcher` system.
* **Fabric Project:** For the modding toolchain and Mixin environment.

---
*Developed by [MemencioPerez](https://github.com/MemencioPerez).*
