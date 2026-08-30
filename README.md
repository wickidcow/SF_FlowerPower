<div align="center">

# 🌸 FlowerPower — Slimefun Legacy

**Flower-powered magic, experience storage, charms, teleportation, and utility items for modern Slimefun servers.**

![Slimefun Legacy](https://img.shields.io/badge/Slimefun-Legacy-6bd425?style=for-the-badge)
![Paper 26.2+](https://img.shields.io/badge/Paper-26.2%2B-blue?style=for-the-badge)
![Java 21+](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge)
![License: GPL-3.0](https://img.shields.io/badge/License-GPL--3.0-blue?style=for-the-badge)
![Maintained for AlbionMC.com](https://img.shields.io/badge/Maintained%20for-albionmc.com-7b68ee?style=for-the-badge)

</div>

> [!IMPORTANT]
> FlowerPower Legacy is an **unofficial community-maintained fork** for Slimefun Legacy. It preserves the original FlowerPower identity and Slimefun item IDs while keeping the addon usable on modern servers.

## 🌺 What does FlowerPower add?

FlowerPower expands Slimefun with a small magic progression centered around flowers, experience, charms, and reusable utility items.

### ✨ Core progression

- **Magic Cream** — the starting material, dropped by slimes and used in early recipes.
- **Magic Basin** — the addon multiblock used to craft FlowerPower content.
- **Experience Cauldron** — a Magic Basin component that can also store experience on its own.
- **Magic Wand** — starts Magic Basin reactions.

### 💎 Charms & utility

- **Attribute Charms** — offhand charms with randomized movement, combat, and health-related bonuses.
- **Experience Tome** — stores large amounts of player experience.
- **Overgrowth Seed** — duplicates supported flowers when used.
- **Infinity Apple** — reusable food powered by experience.
- **Infinity Bandage** — reusable healing powered by experience.
- **Recall Charm** — bind a location and later teleport back to it at an experience cost.

## 🧪 Slimefun Legacy maintenance

This fork is maintained as part of the Slimefun Legacy addon collection. The maintenance branch builds and validates against the current **Slimefun Legacy** API and modern Paper rather than relying on the abandoned Blob Builds pipeline.

Current compatibility work includes:

- Java 21+ source compatibility with CI validation on Java 25;
- Paper 26.2+ build targeting;
- current Slimefun item-stack access and `CustomItemStack` APIs;
- modern Bukkit attribute modifier handling;
- safer Minecraft-version detection for the Experience Cauldron;
- preservation of existing FlowerPower item IDs and gameplay wherever practical;
- raw release JAR output with stable versioned filenames.

## 📦 Current release

**Version:** `1.0.1`

Release builds are published as a raw JAR named:

`SF_FlowerPower1.0.1.jar`

Install **Slimefun Legacy** first, then place the FlowerPower JAR in your server's `plugins` directory and restart the server.

## ❤️ Credits & project lineage

- **NCBPFluffyBear** — original creator of FlowerPower and the addon design/content preserved here.
- **NCBPFluffyBear/FlowerPower** — original source repository.
- **captainbboy/FlowerPower** — immediate upstream fork, including the Java 21 / Minecraft 1.21 modernization used as the starting point for this fork.
- **FlowerPower and Slimefun community contributors** — fixes, APIs, testing, and maintenance over the project's lifetime.
- **wickidcow / Slimefun Legacy** — current compatibility and preservation work for modern servers and AlbionMC.com.

This fork keeps upstream attribution intentionally visible. It is not a claim that this maintenance fork created the original addon.

## 📜 GNU General Public License v3.0

FlowerPower is licensed under the **GNU General Public License v3.0 (GPLv3)**. The complete license is included as `LICENSE.txt`.

If you distribute the plugin or a modified GPL-covered version, comply with GPLv3, including preserving applicable notices, identifying modified versions, licensing covered modified source under GPLv3, and providing the required Corresponding Source when distributing object code.

The software is provided **without warranty** as described by GPLv3.

## ⚖️ Independence & trademark notice

**NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.**

FlowerPower, Slimefun Legacy, and this maintenance fork are independent community projects. They are not sponsored, endorsed, approved, or operated by Mojang Studios or Microsoft. Minecraft-related names, brands, and assets remain the property of their respective rights holders.

This repository is not represented as an official release of NCBPFluffyBear, captainbboy, the original Slimefun developers, or any other upstream party unless explicitly stated by them.

---

<div align="center">

**🌸 Keep the flowers magical. Keep FlowerPower running. ✨**

</div>
