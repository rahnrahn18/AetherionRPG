AetherionRPG/
├── .gitignore
├── DOCUMENTATION.md
├── Structure.md
├── agent.md
├── app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           ├── assets
│           │   ├── PixelArtTopDown
│           │   │   ├── Changelog.txt
│           │   │   ├── Documentation.url
│           │   │   ├── Pixel Art Top Down - Basic v1.2.3.unitypackage
│           │   │   ├── Scene Overview.png
│           │   │   └── Texture
│           │   │       ├── Extra
│           │   │       │   ├── TX Plant with Shadow.png
│           │   │       │   └── TX Props with Shadow.png
│           │   │       ├── TX Plant.png
│           │   │       ├── TX Player.png
│           │   │       ├── TX Props.png
│           │   │       ├── TX Shadow Plant.png
│           │   │       ├── TX Shadow.png
│           │   │       ├── TX Struct.png
│           │   │       ├── TX Tileset Grass.png
│           │   │       ├── TX Tileset Stone Ground.png
│           │   │       └── TX Tileset Wall.png
│           │   └── PixelPack
│           │       ├── Entities
│           │       │   ├── Characters
│           │       │   │   └── Body_A
│           │       │   │       └── Animations
│           │       │   │           ├── Carry_Idle
│           │       │   │           │   ├── Carry_Idle_Down-Sheet.png
│           │       │   │           │   ├── Carry_Idle_Down.aseprite
│           │       │   │           │   ├── Carry_Idle_Side-Sheet.png
│           │       │   │           │   ├── Carry_Idle_Side.aseprite
│           │       │   │           │   ├── Carry_Idle_Up-Sheet.png
│           │       │   │           │   └── Carry_Idle_Up.aseprite
│           │       │   │           ├── Carry_Run
│           │       │   │           │   ├── Carry_Run_Down-Sheet.png
│           │       │   │           │   ├── Carry_Run_Down.aseprite
│           │       │   │           │   ├── Carry_Run_Side-Sheet.png
│           │       │   │           │   ├── Carry_Run_Side.aseprite
│           │       │   │           │   ├── Carry_Run_Up-Sheet.png
│           │       │   │           │   └── Carry_Run_Up.aseprite
│           │       │   │           ├── Carry_Walk
│           │       │   │           │   ├── Carry_Walk_Down-Sheet.png
│           │       │   │           │   ├── Carry_Walk_Down.aseprite
│           │       │   │           │   ├── Carry_Walk_Side-Sheet.png
│           │       │   │           │   ├── Carry_Walk_Side.aseprite
│           │       │   │           │   ├── Carry_Walk_Up-Sheet.png
│           │       │   │           │   └── Carry_Walk_Up.aseprite
│           │       │   │           ├── Collect_Base
│           │       │   │           │   ├── Collect_Down-Sheet.png
│           │       │   │           │   ├── Collect_Down.aseprite
│           │       │   │           │   ├── Collect_Side-Sheet.png
│           │       │   │           │   ├── Collect_Side.aseprite
│           │       │   │           │   ├── Collect_Up-Sheet.png
│           │       │   │           │   └── Collect_Up.aseprite
│           │       │   │           ├── Crush_Base
│           │       │   │           │   ├── Crush_Down-Sheet.png
│           │       │   │           │   ├── Crush_Down.aseprite
│           │       │   │           │   ├── Crush_Side-Sheet.png
│           │       │   │           │   ├── Crush_Side.aseprite
│           │       │   │           │   ├── Crush_Up-Sheet.png
│           │       │   │           │   └── Crush_Up.aseprite
│           │       │   │           ├── Death_Base
│           │       │   │           │   ├── Death_Down-Sheet.png
│           │       │   │           │   ├── Death_Down.aseprite
│           │       │   │           │   ├── Death_Side-Sheet.png
│           │       │   │           │   ├── Death_Side.aseprite
│           │       │   │           │   ├── Death_Up-Sheet.png
│           │       │   │           │   └── Death_Up.aseprite
│           │       │   │           ├── Fishing_Base
│           │       │   │           │   ├── Fishing_Down-Sheet.png
│           │       │   │           │   ├── Fishing_Down.aseprite
│           │       │   │           │   ├── Fishing_Side-Sheet.png
│           │       │   │           │   ├── Fishing_Side.aseprite
│           │       │   │           │   ├── Fishing_Up-Sheet.png
│           │       │   │           │   └── Fishing_Up.aseprite
│           │       │   │           ├── Hit_Base
│           │       │   │           │   ├── Hit_Down-Sheet.png
│           │       │   │           │   ├── Hit_Down.aseprite
│           │       │   │           │   ├── Hit_Side-Sheet.png
│           │       │   │           │   ├── Hit_Side.aseprite
│           │       │   │           │   ├── Hit_Up-Sheet.png
│           │       │   │           │   └── Hit_Up.aseprite
│           │       │   │           ├── Idle_Base
│           │       │   │           │   ├── Idle_Down-Sheet.png
│           │       │   │           │   ├── Idle_Down.aseprite
│           │       │   │           │   ├── Idle_Side-Sheet.png
│           │       │   │           │   ├── Idle_Side.aseprite
│           │       │   │           │   ├── Idle_Up-Sheet.png
│           │       │   │           │   └── Idle_Up.aseprite
│           │       │   │           ├── Pierce_Base
│           │       │   │           │   ├── Pierce_Down-Sheet.png
│           │       │   │           │   ├── Pierce_Down.aseprite
│           │       │   │           │   ├── Pierce_Side-Sheet.png
│           │       │   │           │   ├── Pierce_Side.aseprite
│           │       │   │           │   ├── Pierce_Top-Sheet.png
│           │       │   │           │   └── Pierce_Top.aseprite
│           │       │   │           ├── Run_Base
│           │       │   │           │   ├── Run_Down-Sheet.png
│           │       │   │           │   ├── Run_Down.aseprite
│           │       │   │           │   ├── Run_Side-Sheet.png
│           │       │   │           │   ├── Run_Side.aseprite
│           │       │   │           │   ├── Run_Up-Sheet.png
│           │       │   │           │   └── Run_Up.aseprite
│           │       │   │           ├── Slice_Base
│           │       │   │           │   ├── Slice_Down-Sheet.png
│           │       │   │           │   ├── Slice_Down.aseprite
│           │       │   │           │   ├── Slice_Side-Sheet.png
│           │       │   │           │   ├── Slice_Side.aseprite
│           │       │   │           │   ├── Slice_Up-Sheet.png
│           │       │   │           │   └── Slice_Up.aseprite
│           │       │   │           ├── Walk_Base
│           │       │   │           │   ├── Walk_Down-Sheet.png
│           │       │   │           │   ├── Walk_Down.aseprite
│           │       │   │           │   ├── Walk_Side-Sheet.png
│           │       │   │           │   ├── Walk_Side.aseprite
│           │       │   │           │   ├── Walk_Up-Sheet.png
│           │       │   │           │   └── Walk_Up.aseprite
│           │       │   │           └── Watering_Base
│           │       │   │               ├── Watering_Down-Sheet.png
│           │       │   │               ├── Watering_Down.aseprite
│           │       │   │               ├── Watering_Side-Sheet.png
│           │       │   │               ├── Watering_Side.aseprite
│           │       │   │               ├── Watering_Up-Sheet.png
│           │       │   │               └── Watering_Up.aseprite
│           │       │   ├── Mobs
│           │       │   │   ├── Orc Crew
│           │       │   │   │   ├── Orc
│           │       │   │   │   │   ├── Death
│           │       │   │   │   │   │   ├── Death-Sheet.png
│           │       │   │   │   │   │   └── Death.aseprite
│           │       │   │   │   │   ├── Idle
│           │       │   │   │   │   │   ├── Idle-Sheet.png
│           │       │   │   │   │   │   └── Idle.aseprite
│           │       │   │   │   │   └── Run
│           │       │   │   │   │       ├── Run-Sheet.png
│           │       │   │   │   │       └── Run.aseprite
│           │       │   │   │   ├── Orc - Rogue
│           │       │   │   │   │   ├── Death
│           │       │   │   │   │   │   ├── Death-Sheet.png
│           │       │   │   │   │   │   └── Death.aseprite
│           │       │   │   │   │   ├── Idle
│           │       │   │   │   │   │   ├── Idle-Sheet.png
│           │       │   │   │   │   │   └── Idle.aseprite
│           │       │   │   │   │   └── Run
│           │       │   │   │   │       ├── Run-Sheet.png
│           │       │   │   │   │       └── Run.aseprite
│           │       │   │   │   ├── Orc - Shaman
│           │       │   │   │   │   ├── Death
│           │       │   │   │   │   │   ├── Death-Sheet.png
│           │       │   │   │   │   │   └── Death.aseprite
│           │       │   │   │   │   ├── Idle
│           │       │   │   │   │   │   ├── Idle-Sheet.png
│           │       │   │   │   │   │   └── Idle.aseprite
│           │       │   │   │   │   └── Run
│           │       │   │   │   │       ├── Run-Sheet.png
│           │       │   │   │   │       └── Run.aseprite
│           │       │   │   │   └── Orc - Warrior
│           │       │   │   │       ├── Death
│           │       │   │   │       │   ├── Death-Sheet.png
│           │       │   │   │       │   └── Death.aseprite
│           │       │   │   │       ├── Idle
│           │       │   │   │       │   ├── Idle-Sheet.png
│           │       │   │   │       │   └── Idle.aseprite
│           │       │   │   │       └── Run
│           │       │   │   │           ├── Run-Sheet.png
│           │       │   │   │           └── Run.aseprite
│           │       │   │   └── Skeleton Crew
│           │       │   │       ├── Skeleton - Base
│           │       │   │       │   ├── Death
│           │       │   │       │   │   ├── Death-Sheet.png
│           │       │   │       │   │   └── Death.aseprite
│           │       │   │       │   ├── Idle
│           │       │   │       │   │   ├── Idle-Sheet.png
│           │       │   │       │   │   └── Idle.aseprite
│           │       │   │       │   └── Run
│           │       │   │       │       ├── Run-Sheet.png
│           │       │   │       │       └── Run.aseprite
│           │       │   │       ├── Skeleton - Mage
│           │       │   │       │   ├── Death
│           │       │   │       │   │   ├── Death-Sheet.png
│           │       │   │       │   │   └── Death.aseprite
│           │       │   │       │   ├── Idle
│           │       │   │       │   │   ├── Idle-Sheet.png
│           │       │   │       │   │   └── Idle.aseprite
│           │       │   │       │   └── Run
│           │       │   │       │       ├── Run-Sheet.png
│           │       │   │       │       └── Run.aseprite
│           │       │   │       ├── Skeleton - Rogue
│           │       │   │       │   ├── Death
│           │       │   │       │   │   ├── Death-Sheet.png
│           │       │   │       │   │   └── Death.aseprite
│           │       │   │       │   ├── Idle
│           │       │   │       │   │   ├── Idle-Sheet.png
│           │       │   │       │   │   └── Idle.aseprite
│           │       │   │       │   └── Run
│           │       │   │       │       ├── Run-Sheet.png
│           │       │   │       │       └── Run.aseprite
│           │       │   │       └── Skeleton - Warrior
│           │       │   │           ├── Death
│           │       │   │           │   ├── Death-Sheet.png
│           │       │   │           │   └── Death.aseprite
│           │       │   │           ├── Idle
│           │       │   │           │   ├── Idle-Sheet.png
│           │       │   │           │   └── Idle.aseprite
│           │       │   │           └── Run
│           │       │   │               ├── Run-Sheet.png
│           │       │   │               └── Run.aseprite
│           │       │   └── Npc's
│           │       │       ├── Knight
│           │       │       │   ├── Death
│           │       │       │   │   ├── Death-Sheet.png
│           │       │       │   │   └── Death.aseprite
│           │       │       │   ├── Idle
│           │       │       │   │   ├── Idle-Sheet.png
│           │       │       │   │   └── Idle.aseprite
│           │       │       │   └── Run
│           │       │       │       ├── Run-Sheet.png
│           │       │       │       └── Run.aseprite
│           │       │       ├── Rogue
│           │       │       │   ├── Death
│           │       │       │   │   ├── Death-Sheet.png
│           │       │       │   │   └── Death.aseprite
│           │       │       │   ├── Idle
│           │       │       │   │   ├── Idle-Sheet.png
│           │       │       │   │   └── Idle.aseprite
│           │       │       │   └── Run
│           │       │       │       ├── Run-Sheet.png
│           │       │       │       └── Run.aseprite
│           │       │       └── Wizzard
│           │       │           ├── Death
│           │       │           │   ├── Death-Sheet.png
│           │       │           │   └── Death.aseprite
│           │       │           ├── Idle
│           │       │           │   ├── Idle-Sheet.png
│           │       │           │   └── Idle.aseprite
│           │       │           └── Run
│           │       │               ├── Run-Sheet.png
│           │       │               └── Run.aseprite
│           │       ├── Environment
│           │       │   ├── Props
│           │       │   │   ├── Animated
│           │       │   │   │   ├── Pan_01-Sheet.png
│           │       │   │   │   ├── Pan_01.aseprite
│           │       │   │   │   ├── Pan_02-Sheet.png
│           │       │   │   │   ├── Pan_02.aseprite
│           │       │   │   │   ├── Pan_03-Sheet.png
│           │       │   │   │   ├── Pan_03.aseprite
│           │       │   │   │   ├── Pan_04-Sheet.png
│           │       │   │   │   ├── Pan_04.aseprite
│           │       │   │   │   ├── Pan_05-Sheet.png
│           │       │   │   │   └── Pan_05.aseprite
│           │       │   │   └── Static
│           │       │   │       ├── Dungeon_Props.aseprite
│           │       │   │       ├── Dungeon_Props.png
│           │       │   │       ├── Esoteric.aseprite
│           │       │   │       ├── Esoteric.png
│           │       │   │       ├── Farm.aseprite
│           │       │   │       ├── Farm.png
│           │       │   │       ├── Furniture.aseprite
│           │       │   │       ├── Furniture.png
│           │       │   │       ├── Meat.aseprite
│           │       │   │       ├── Meat.png
│           │       │   │       ├── Pan.aseprite
│           │       │   │       ├── Pan.png
│           │       │   │       ├── Resources.aseprite
│           │       │   │       ├── Resources.png
│           │       │   │       ├── Rocks.aseprite
│           │       │   │       ├── Rocks.png
│           │       │   │       ├── Shadows.aseprite
│           │       │   │       ├── Shadows.png
│           │       │   │       ├── Tools.aseprite
│           │       │   │       ├── Tools.png
│           │       │   │       ├── Trees
│           │       │   │       │   ├── Model_01
│           │       │   │       │   │   ├── Size_02.aseprite
│           │       │   │       │   │   ├── Size_02.png
│           │       │   │       │   │   ├── Size_03.aseprite
│           │       │   │       │   │   ├── Size_03.png
│           │       │   │       │   │   ├── Size_04.aseprite
│           │       │   │       │   │   ├── Size_04.png
│           │       │   │       │   │   ├── Size_05.aseprite
│           │       │   │       │   │   └── Size_05.png
│           │       │   │       │   ├── Model_02
│           │       │   │       │   │   ├── Size_02.aseprite
│           │       │   │       │   │   ├── Size_02.png
│           │       │   │       │   │   ├── Size_03.aseprite
│           │       │   │       │   │   ├── Size_03.png
│           │       │   │       │   │   ├── Size_04.aseprite
│           │       │   │       │   │   ├── Size_04.png
│           │       │   │       │   │   ├── Size_05.aseprite
│           │       │   │       │   │   └── Size_05.png
│           │       │   │       │   └── Model_03
│           │       │   │       │       ├── Size_02.aseprite
│           │       │   │       │       ├── Size_02.png
│           │       │   │       │       ├── Size_03-export.png
│           │       │   │       │       ├── Size_03.aseprite
│           │       │   │       │       ├── Size_03.png
│           │       │   │       │       ├── Size_04-export-export.png
│           │       │   │       │       ├── Size_04-export.png
│           │       │   │       │       ├── Size_04.aseprite
│           │       │   │       │       ├── Size_04.png
│           │       │   │       │       ├── Size_05.aseprite
│           │       │   │       │       └── Size_05.png
│           │       │   │       ├── Vegetation.aseprite
│           │       │   │       └── Vegetation.png
│           │       │   ├── Structures
│           │       │   │   ├── Buildings
│           │       │   │   │   ├── Floors.aseprite
│           │       │   │   │   ├── Floors.png
│           │       │   │   │   ├── Props.aseprite
│           │       │   │   │   ├── Props.png
│           │       │   │   │   ├── Roofs.aseprite
│           │       │   │   │   ├── Roofs.png
│           │       │   │   │   ├── Shadows.aseprite
│           │       │   │   │   ├── Shadows.png
│           │       │   │   │   ├── Walls.aseprite
│           │       │   │   │   └── Walls.png
│           │       │   │   └── Stations
│           │       │   │       ├── Alchemy
│           │       │   │       │   ├── Alchemy.aseprite
│           │       │   │       │   ├── Alchemy_Table_01-Sheet.png
│           │       │   │       │   ├── Alchemy_Table_01.aseprite
│           │       │   │       │   ├── Alchemy_Table_02-Sheet.png
│           │       │   │       │   ├── Alchemy_Table_02.aseprite
│           │       │   │       │   ├── Alchemy_Table_03-Sheet.png
│           │       │   │       │   └── Alchemy_Table_03.aseprite
│           │       │   │       ├── Anvil
│           │       │   │       │   ├── Anvil.aseprite
│           │       │   │       │   ├── Anvil.png
│           │       │   │       │   ├── Anvil_01-Sheet.png
│           │       │   │       │   ├── Anvil_01.aseprite
│           │       │   │       │   ├── Anvil_02-Sheet.png
│           │       │   │       │   ├── Anvil_02.aseprite
│           │       │   │       │   ├── Anvil_03-Sheet.png
│           │       │   │       │   └── Anvil_03.aseprite
│           │       │   │       ├── Bonfire
│           │       │   │       │   ├── Bonfire.aseprite
│           │       │   │       │   ├── Bonfire.png
│           │       │   │       │   ├── Bonfire_01-Sheet.png
│           │       │   │       │   ├── Bonfire_01.aseprite
│           │       │   │       │   ├── Bonfire_02-Sheet.png
│           │       │   │       │   ├── Bonfire_02.aseprite
│           │       │   │       │   ├── Bonfire_03-Sheet.png
│           │       │   │       │   ├── Bonfire_03.aseprite
│           │       │   │       │   ├── Bonfire_04-Sheet.png
│           │       │   │       │   ├── Bonfire_04.aseprite
│           │       │   │       │   ├── Bonfire_05-Sheet.png
│           │       │   │       │   ├── Bonfire_05.aseprite
│           │       │   │       │   ├── Bonfire_06-Sheet.png
│           │       │   │       │   ├── Bonfire_06.aseprite
│           │       │   │       │   ├── Bonfire_07-Sheet.png
│           │       │   │       │   ├── Bonfire_07.aseprite
│           │       │   │       │   ├── Bonfire_08-Sheet.png
│           │       │   │       │   ├── Bonfire_08.aseprite
│           │       │   │       │   ├── Bonfire_09-Sheet.png
│           │       │   │       │   ├── Bonfire_09.aseprite
│           │       │   │       │   ├── Bonfire_10-Sheet.png
│           │       │   │       │   ├── Bonfire_10.aseprite
│           │       │   │       │   ├── Fire_01-Sheet.png
│           │       │   │       │   ├── Fire_01.aseprite
│           │       │   │       │   ├── Fire_02-Sheet.png
│           │       │   │       │   ├── Fire_02.aseprite
│           │       │   │       │   ├── Smoke-Sheet.png
│           │       │   │       │   └── Smoke.aseprite
│           │       │   │       ├── Cooking Station
│           │       │   │       │   ├── Butchery
│           │       │   │       │   │   ├── Butchery_01-Sheet.png
│           │       │   │       │   │   ├── Butchery_01.aseprite
│           │       │   │       │   │   ├── Butchery_02.aseprite
│           │       │   │       │   │   ├── Butchery_02.png
│           │       │   │       │   │   ├── Butchery_03.aseprite
│           │       │   │       │   │   ├── Butchery_03.png
│           │       │   │       │   │   ├── Butchery_04.aseprite
│           │       │   │       │   │   └── Butchery_04.png
│           │       │   │       │   ├── Cooker
│           │       │   │       │   │   ├── Cooker_01.aseprite
│           │       │   │       │   │   ├── Cooker_01.png
│           │       │   │       │   │   ├── Cooker_02.aseprite
│           │       │   │       │   │   ├── Cooker_02.png
│           │       │   │       │   │   ├── Cooker_03-Sheet.png
│           │       │   │       │   │   ├── Cooker_03.aseprite
│           │       │   │       │   │   ├── Cooker_04-Sheet.png
│           │       │   │       │   │   └── Cooker_04.aseprite
│           │       │   │       │   ├── Cooking Station.aseprite
│           │       │   │       │   ├── Cooking Station.png
│           │       │   │       │   ├── Estructure.png
│           │       │   │       │   └── Grill
│           │       │   │       │       ├── Grill_01-Sheet.png
│           │       │   │       │       ├── Grill_01.aseprite
│           │       │   │       │       ├── Grill_02-Sheet.png
│           │       │   │       │       ├── Grill_02.aseprite
│           │       │   │       │       ├── Grill_03-Sheet.png
│           │       │   │       │       ├── Grill_03.aseprite
│           │       │   │       │       ├── Grill_04-Sheet.png
│           │       │   │       │       └── Grill_04.aseprite
│           │       │   │       ├── Furnace
│           │       │   │       │   ├── Bricks_01-Sheet.png
│           │       │   │       │   ├── Bricks_01.aseprite
│           │       │   │       │   ├── Bricks_02-Sheet.png
│           │       │   │       │   ├── Bricks_02.aseprite
│           │       │   │       │   ├── Bricks_03-Sheet.png
│           │       │   │       │   ├── Bricks_03.aseprite
│           │       │   │       │   ├── Furnace.aseprite
│           │       │   │       │   ├── Furnace.png
│           │       │   │       │   ├── Iron_01-Sheet.png
│           │       │   │       │   ├── Iron_01.aseprite
│           │       │   │       │   ├── Iron_02-Sheet.png
│           │       │   │       │   ├── Iron_02.aseprite
│           │       │   │       │   ├── Iron_03-Sheet.png
│           │       │   │       │   ├── Iron_03.aseprite
│           │       │   │       │   ├── Stone_01-Sheet.png
│           │       │   │       │   ├── Stone_01.aseprite
│           │       │   │       │   ├── Stone_02-Sheet.png
│           │       │   │       │   ├── Stone_02.aseprite
│           │       │   │       │   ├── Stone_03-Sheet.png
│           │       │   │       │   └── Stone_03.aseprite
│           │       │   │       ├── Sawmill
│           │       │   │       │   ├── Base.aseprite
│           │       │   │       │   ├── Base.png
│           │       │   │       │   ├── Level_1.aseprite
│           │       │   │       │   ├── Level_1.png
│           │       │   │       │   ├── Level_2-Sheet.png
│           │       │   │       │   ├── Level_2.aseprite
│           │       │   │       │   ├── Level_3-Sheet.png
│           │       │   │       │   └── Level_3.aseprite
│           │       │   │       └── Workbench
│           │       │   │           ├── Workbench.aseprite
│           │       │   │           └── Workbench.png
│           │       │   └── Tilesets
│           │       │       ├── Dungeon_Tiles.aseprite
│           │       │       ├── Dungeon_Tiles.png
│           │       │       ├── Floors_Tiles.aseprite
│           │       │       ├── Floors_Tiles.png
│           │       │       ├── Wall_Tiles.aseprite
│           │       │       ├── Wall_Tiles.png
│           │       │       ├── Wall_Variations.aseprite
│           │       │       ├── Wall_Variations.png
│           │       │       ├── Water.aseprite
│           │       │       ├── Water_tiles.aseprite
│           │       │       └── Water_tiles.png
│           │       ├── Icons
│           │       │   └── Resources.aseprite
│           │       └── Weapons
│           │           ├── Bone
│           │           │   ├── Bone.aseprite
│           │           │   └── Bone.png
│           │           ├── Hands
│           │           │   ├── Hands.aseprite
│           │           │   └── Hands.png
│           │           └── Wood
│           │               ├── Wood.aseprite
│           │               └── Wood.png
│           ├── kotlin
│           │   └── com
│           │       └── rpg
│           │           └── aetherion
│           │               ├── MainActivity.kt
│           │               ├── engine
│           │               │   ├── Animation.kt
│           │               │   ├── AssetLoader.kt
│           │               │   ├── AssetManager.kt
│           │               │   ├── Camera.kt
│           │               │   ├── GameLoop.kt
│           │               │   ├── GameSurface.kt
│           │               │   ├── InputManager.kt
│           │               │   └── Sprite.kt
│           │               └── game
│           │                   ├── entity
│           │                   │   ├── Entity.kt
│           │                   │   └── Player.kt
│           │                   ├── map
│           │                   │   └── MapManager.kt
│           │                   └── scene
│           │                       ├── PlayScene.kt
│           │                       └── Scene.kt
│           └── res
│               ├── drawable
│               │   └── ic_launcher_background.xml
│               ├── drawable-v24
│               │   └── ic_launcher_foreground.xml
│               ├── mipmap-anydpi-v26
│               │   ├── ic_launcher.xml
│               │   └── ic_launcher_round.xml
│               ├── mipmap-hdpi
│               │   ├── ic_launcher.webp
│               │   └── ic_launcher_round.webp
│               ├── mipmap-mdpi
│               │   ├── ic_launcher.webp
│               │   └── ic_launcher_round.webp
│               ├── mipmap-xhdpi
│               │   ├── ic_launcher.webp
│               │   └── ic_launcher_round.webp
│               ├── mipmap-xxhdpi
│               │   ├── ic_launcher.webp
│               │   └── ic_launcher_round.webp
│               ├── mipmap-xxxhdpi
│               │   ├── ic_launcher.webp
│               │   └── ic_launcher_round.webp
│               ├── values
│               │   ├── colors.xml
│               │   ├── strings.xml
│               │   └── themes.xml
│               ├── values-night
│               │   ├── colors.xml
│               │   └── themes.xml
│               └── xml
│                   ├── backup_rules.xml
│                   └── data_extraction_rules.xml
├── build.gradle.kts
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts

125 directories, 402 files
