/*
 * ONI Seed Browser Backend
 * Copyright (C) 2024 Stefan Oltmann
 * https://stefan-oltmann.de/oni-seed-browser
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

val sampleWorldsJson: String = """
[
  {
    "coordinate": "V-SNDST-C-101520169-0-0-0",
    "cluster": "V-SNDST-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaSandstoneDefault",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [],
        "biomePaths": "Sandstone:124,202 138,190 140,176 134,166 115,158 106,161 95,180 100,195 111,202;141,154 136,144 120,144 115,158 134,166;115,158 120,144 110,134 104,135 96,149 106,161;162,169 158,158 141,154 134,166 140,176;100,195 95,180 82,180 76,197 86,205;153,204 138,190 124,202 131,214;111,202 100,195 86,205 88,215 107,218;129,222 131,214 124,202 111,202 107,218 111,224\nBoggyMarsh:240,83 219,83 211,98 218,111 240,111;240,138 240,111 218,111 210,125 217,138;193,125 185,112 169,112 161,128 169,141 184,142;169,141 161,128 144,128 136,144 141,154 158,158;161,100 168,83 165,77 143,73 134,83 143,101;186,167 191,153 184,142 169,141 158,158 162,169 165,171;222,196 211,180 197,182 187,200 189,204 215,212;55,196 52,172 29,172 22,187 31,201;240,242 217,243 211,259 217,270 240,270;53,279 58,255 54,251 32,250 25,266 31,277 52,280;183,247 189,233 182,220 162,221 156,234 164,248;32,250 26,240 0,239 0,265 25,266;108,268 111,251 105,245 86,245 80,256 87,273 96,275;105,245 111,224 107,218 88,215 79,228 86,245;184,275 190,259 183,247 164,248 158,261 166,275;156,234 162,221 153,204 153,204 131,214 129,222 137,235;190,285 184,275 166,275 158,290 164,302 183,306;30,144 23,130 0,130 0,159 22,159;212,333 216,325 210,312 186,311 182,327 192,339\nFrozenWastes:218,111 211,98 193,96 185,112 193,125 210,125;161,128 169,112 161,100 143,101 135,114 144,128;197,182 186,167 165,171 167,193 187,200;240,166 240,138 217,138 211,152 219,166;48,116 55,105 45,86 30,87 22,102 30,117;60,200 55,196 31,201 27,214 32,223 58,225;217,297 210,286 190,285 183,306 186,311 210,312;69,104 78,86 69,74 53,74 45,86 55,105;57,135 48,116 30,117 23,130 30,144 49,145;29,307 21,294 0,293 0,323 18,324;140,303 143,291 133,278 120,279 109,297 124,310;67,329 66,312 49,304 39,309 38,330 50,338\nBarren:240,83 240,56 218,55 210,67 219,83;219,83 210,67 197,68 187,85 193,96 211,98;197,68 186,54 172,55 165,77 168,83 187,85;172,55 163,47 139,54 143,73 165,77;143,73 139,54 137,53 116,56 111,64 124,83 134,83;124,83 111,64 101,66 91,86 103,99 110,100;91,86 101,66 85,53 78,55 69,74 78,86;69,74 78,55 60,41 44,56 53,74;45,86 53,74 44,56 31,54 22,75 30,87;31,54 29,50 0,48 0,75 22,75\nToxicJungle:193,96 187,85 168,83 161,100 169,112 185,112;135,114 143,101 134,83 124,83 110,100 122,115;136,144 144,128 135,114 122,115 110,134 120,144;110,134 122,115 110,100 103,99 88,118 104,135;106,161 96,149 80,151 72,167 82,180 95,180;96,149 104,135 88,118 81,118 69,135 80,151;211,152 217,138 210,125 193,125 184,142 191,153;211,180 219,166 211,152 191,153 186,167 197,182;72,167 80,151 69,135 57,135 49,145 56,168;240,195 240,166 219,166 211,180 222,196;182,220 189,204 187,200 167,193 153,204 162,221;211,259 217,243 210,232 189,233 183,247 190,259;27,214 31,201 22,187 0,188 0,215;76,197 82,180 72,167 56,168 52,172 55,196 60,200;59,226 58,225 32,223 26,240 32,250 54,251;49,304 52,280 31,277 21,294 29,307 39,309;31,277 25,266 0,265 0,293 21,294;158,290 166,275 158,261 140,263 133,278 143,291\nOcean:88,118 103,99 91,86 78,86 69,104 81,118;69,135 81,118 69,104 55,105 48,116 57,135;30,117 22,102 0,103 0,130 23,130;22,102 30,87 22,75 0,75 0,103;210,232 216,214 215,212 189,204 182,220 189,233;240,242 240,217 216,214 210,232 217,243;52,172 56,168 49,145 30,144 22,159 29,172;22,187 29,172 22,159 0,159 0,188;32,223 27,214 0,215 0,239 26,240;217,270 211,259 190,259 184,275 190,285 210,286;240,270 217,270 210,286 217,297 240,298;86,245 79,228 59,226 54,251 58,255 80,256;79,303 76,284 53,279 52,280 49,304 66,312;158,261 164,248 156,234 137,235 131,249 140,263;97,326 94,307 79,303 66,312 67,329 79,336;240,324 240,298 217,297 210,312 216,325;121,325 124,310 109,297 108,297 94,307 97,326 107,331;154,325 153,313 140,303 124,310 121,325 135,335;182,327 186,311 183,306 164,302 153,313 154,325 163,332\nWasteland:167,193 165,171 162,169 140,176 138,190 153,204 153,204;88,215 86,205 76,197 60,200 58,225 59,226 79,228;87,273 80,256 58,255 53,279 76,284;108,297 96,275 87,273 76,284 79,303 94,307;131,249 137,235 129,222 111,224 105,245 111,251;133,278 140,263 131,249 111,251 108,268 120,279\nRadioactive:240,195 222,196 215,212 216,214 240,217;109,297 120,279 108,268 96,275 108,297;38,330 39,309 29,307 18,324 26,332;164,302 158,290 143,291 140,303 153,313\nOilField:108,351 107,331 97,326 79,336 80,352 91,359;50,354 50,338 38,330 26,332 20,353 29,363;20,353 26,332 18,324 0,323 0,355;135,351 135,335 121,325 107,331 108,351 120,358;189,353 192,339 182,327 163,332 162,352 176,360;80,352 79,336 67,329 50,338 50,354 61,362;222,354 212,333 192,339 189,353 209,368;240,324 216,325 212,333 222,354 240,355;162,352 163,332 154,325 135,335 135,351 147,358\nMagmaCore:29,363 20,353 0,355 0,380 28,380;147,358 135,351 120,358 120,380 147,380;120,358 108,351 91,359 92,380 120,380;176,360 162,352 147,358 147,380 175,380;61,362 50,354 29,363 28,380 61,380;240,355 222,354 209,368 209,380 240,380;209,368 189,353 176,360 175,380 209,380;91,359 80,352 61,362 61,380 92,380",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 120,
            "y": 179
          },
          {
            "id": "MassiveHeatSink",
            "x": 202,
            "y": 107
          },
          {
            "id": "MassiveHeatSink",
            "x": 117,
            "y": 295
          },
          {
            "id": "MassiveHeatSink",
            "x": 42,
            "y": 115
          },
          {
            "id": "WarpConduitSender",
            "x": 57,
            "y": 132
          },
          {
            "id": "WarpConduitReceiver",
            "x": 202,
            "y": 180
          },
          {
            "id": "GravitasPedestal",
            "x": 88,
            "y": 147
          },
          {
            "id": "WarpReceiver",
            "x": 185,
            "y": 239
          },
          {
            "id": "WarpPortal",
            "x": 184,
            "y": 233
          },
          {
            "id": "GeneShuffler",
            "x": 173,
            "y": 138
          },
          {
            "id": "GeneShuffler",
            "x": 138,
            "y": 124
          },
          {
            "id": "GeneShuffler",
            "x": 41,
            "y": 221
          },
          {
            "id": "GeneShuffler",
            "x": 220,
            "y": 344
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 62,
            "y": 348,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 25,
            "y": 345,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 147,
            "y": 344,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "salt_water",
            "x": 167,
            "y": 319,
            "emitRate": 7481,
            "avgEmitRate": 3519,
            "idleTime": 173,
            "eruptionTime": 397,
            "dormancyCycles": 28.9,
            "activeCycles": 60.2
          },
          {
            "id": "steam",
            "x": 56,
            "y": 187,
            "emitRate": 4831,
            "avgEmitRate": 1514,
            "idleTime": 292,
            "eruptionTime": 336,
            "dormancyCycles": 54.2,
            "activeCycles": 76.4
          },
          {
            "id": "steam",
            "x": 173,
            "y": 166,
            "emitRate": 7603,
            "avgEmitRate": 1662,
            "idleTime": 241,
            "eruptionTime": 129,
            "dormancyCycles": 27.2,
            "activeCycles": 45.7
          },
          {
            "id": "chlorine_gas",
            "x": 154,
            "y": 283,
            "emitRate": 674,
            "avgEmitRate": 104,
            "idleTime": 331,
            "eruptionTime": 124,
            "dormancyCycles": 23.1,
            "activeCycles": 30.7
          },
          {
            "id": "methane",
            "x": 171,
            "y": 282,
            "emitRate": 291,
            "avgEmitRate": 91,
            "idleTime": 273,
            "eruptionTime": 308,
            "dormancyCycles": 63.3,
            "activeCycles": 90.8
          },
          {
            "id": "molten_iron",
            "x": 164,
            "y": 201,
            "emitRate": 9921,
            "avgEmitRate": 347,
            "idleTime": 660,
            "eruptionTime": 37,
            "dormancyCycles": 47,
            "activeCycles": 88.1
          },
          {
            "id": "molten_cobalt",
            "x": 60,
            "y": 158,
            "emitRate": 8712,
            "avgEmitRate": 318,
            "idleTime": 682,
            "eruptionTime": 47,
            "dormancyCycles": 57.5,
            "activeCycles": 76.2
          },
          {
            "id": "hot_steam",
            "x": 81,
            "y": 328,
            "emitRate": 2050,
            "avgEmitRate": 492,
            "idleTime": 299,
            "eruptionTime": 315,
            "dormancyCycles": 80.2,
            "activeCycles": 70.5
          },
          {
            "id": "slush_water",
            "x": 186,
            "y": 339,
            "emitRate": 5302,
            "avgEmitRate": 1350,
            "idleTime": 243,
            "eruptionTime": 182,
            "dormancyCycles": 64.7,
            "activeCycles": 94.6
          },
          {
            "id": "liquid_sulfur",
            "x": 110,
            "y": 338,
            "emitRate": 4984,
            "avgEmitRate": 1846,
            "idleTime": 166,
            "eruptionTime": 253,
            "dormancyCycles": 61.2,
            "activeCycles": 96.8
          },
          {
            "id": "hot_water",
            "x": 148,
            "y": 303,
            "emitRate": 7187,
            "avgEmitRate": 2695,
            "idleTime": 234,
            "eruptionTime": 379,
            "dormancyCycles": 48.1,
            "activeCycles": 74.3
          },
          {
            "id": "molten_cobalt",
            "x": 15,
            "y": 291,
            "emitRate": 10842,
            "avgEmitRate": 382,
            "idleTime": 582,
            "eruptionTime": 32,
            "dormancyCycles": 42.7,
            "activeCycles": 91.5
          },
          {
            "id": "slimy_po2",
            "x": 205,
            "y": 227,
            "emitRate": 298,
            "avgEmitRate": 110,
            "idleTime": 248,
            "eruptionTime": 374,
            "dormancyCycles": 59.4,
            "activeCycles": 94.3
          },
          {
            "id": "hot_po2",
            "x": 129,
            "y": 109,
            "emitRate": 443,
            "avgEmitRate": 107,
            "idleTime": 470,
            "eruptionTime": 312,
            "dormancyCycles": 58.5,
            "activeCycles": 90.9
          },
          {
            "id": "hot_water",
            "x": 64,
            "y": 219,
            "emitRate": 7187,
            "avgEmitRate": 2695,
            "idleTime": 234,
            "eruptionTime": 379,
            "dormancyCycles": 48.1,
            "activeCycles": 74.3
          },
          {
            "id": "salt_water",
            "x": 197,
            "y": 256,
            "emitRate": 6695,
            "avgEmitRate": 3168,
            "idleTime": 200,
            "eruptionTime": 478,
            "dormancyCycles": 57.3,
            "activeCycles": 116.8
          },
          {
            "id": "filthy_water",
            "x": 92,
            "y": 222,
            "emitRate": 10095,
            "avgEmitRate": 2995,
            "idleTime": 422,
            "eruptionTime": 349,
            "dormancyCycles": 51,
            "activeCycles": 96.7
          }
        ]
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "DistressSignal",
          "MagmaVents"
        ],
        "biomePaths": "Swamp:96,113 97,100 91,93 74,97 72,110 82,120;116,94 116,82 102,74 92,79 91,93 97,100;120,113 120,98 116,94 97,100 96,113 107,121;72,110 74,97 69,92 48,93 46,101 59,115;91,93 92,79 78,71 70,74 69,92 74,97;81,133 82,120 72,110 59,115 55,126 63,135;55,126 59,115 46,101 35,106 34,119 44,129;108,131 107,121 96,113 82,120 81,133 82,135 100,138\nBoggyMarsh:102,74 104,62 93,52 82,55 78,71 92,79;117,56 115,37 98,39 93,52 104,62;78,71 82,55 73,48 62,48 55,62 58,69 70,74;140,49 138,39 117,35 115,37 117,56 125,59;15,67 22,49 17,44 0,43 0,68\nForest:128,72 125,59 117,56 104,62 102,74 116,82;69,92 70,74 58,69 45,85 48,93;58,69 55,62 40,59 29,77 45,85;46,101 48,93 45,85 29,77 27,78 19,92 22,99 35,106;27,78 15,67 0,68 0,90 19,92;144,109 137,96 120,98 120,113 134,121;135,128 134,121 120,113 107,121 108,131 122,138\nFrozenWastes:62,48 54,38 41,39 34,49 40,59 55,62;153,56 140,49 125,59 128,72 138,76;29,77 40,59 34,49 22,49 15,67 27,78;160,56 153,56 138,76 143,84 160,85;68,158 59,151 44,154 43,156 46,176 66,176;91,161 79,154 68,158 66,176 91,176\nRust:137,96 143,84 138,76 128,72 116,82 116,94 120,98;160,85 143,84 137,96 144,109 160,110;160,131 160,110 144,109 134,121 135,128 143,134;34,119 35,106 22,99 8,119 18,126;44,129 34,119 18,126 20,140 39,141;8,119 22,99 19,92 0,90 0,119\nRadioactive:59,151 63,135 55,126 44,129 39,141 44,154;43,156 44,154 39,141 20,140 14,148 22,161;22,161 14,148 0,149 0,176 19,176;43,156 22,161 19,176 46,176;14,148 20,140 18,126 8,119 0,119 0,149;114,159 102,154 91,161 91,176 115,176;102,154 100,138 82,135 79,154 91,161;142,153 143,134 135,128 122,138 123,152 137,157;123,152 122,138 108,131 100,138 102,154 114,159;160,156 142,153 137,157 137,176 160,176;137,157 123,152 114,159 115,176 137,176\nMagmaCore:79,154 82,135 81,133 63,135 59,151 68,158;160,131 143,134 142,153 160,156",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 59,
            "y": 57
          },
          {
            "id": "WarpConduitReceiver",
            "x": 30,
            "y": 86
          },
          {
            "id": "WarpConduitSender",
            "x": 56,
            "y": 125
          },
          {
            "id": "WarpReceiver",
            "x": 94,
            "y": 108
          },
          {
            "id": "WarpPortal",
            "x": 72,
            "y": 108
          },
          {
            "id": "GeneShuffler",
            "x": 146,
            "y": 88
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 26,
            "y": 163,
            "emitRate": 278,
            "avgEmitRate": 159,
            "idleTime": 113,
            "eruptionTime": 785,
            "dormancyCycles": 41.3,
            "activeCycles": 78
          },
          {
            "id": "liquid_co2",
            "x": 12,
            "y": 152,
            "emitRate": 417,
            "avgEmitRate": 161,
            "idleTime": 138,
            "eruptionTime": 253,
            "dormancyCycles": 58.5,
            "activeCycles": 86.9
          },
          {
            "id": "slush_salt_water",
            "x": 131,
            "y": 70,
            "emitRate": 5715,
            "avgEmitRate": 1513,
            "idleTime": 191,
            "eruptionTime": 171,
            "dormancyCycles": 60.4,
            "activeCycles": 76.8
          },
          {
            "id": "slush_water",
            "x": 33,
            "y": 57,
            "emitRate": 3191,
            "avgEmitRate": 1611,
            "idleTime": 99,
            "eruptionTime": 326,
            "dormancyCycles": 43.5,
            "activeCycles": 83.8
          },
          {
            "id": "steam",
            "x": 99,
            "y": 74,
            "emitRate": 4269,
            "avgEmitRate": 1298,
            "idleTime": 347,
            "eruptionTime": 253,
            "dormancyCycles": 56.2,
            "activeCycles": 145.3
          },
          {
            "id": "filthy_water",
            "x": 73,
            "y": 82,
            "emitRate": 9524,
            "avgEmitRate": 2891,
            "idleTime": 309,
            "eruptionTime": 300,
            "dormancyCycles": 54.3,
            "activeCycles": 87.6
          },
          {
            "id": "molten_cobalt",
            "x": 115,
            "y": 146,
            "emitRate": 12454,
            "avgEmitRate": 332,
            "idleTime": 624,
            "eruptionTime": 28,
            "dormancyCycles": 27.2,
            "activeCycles": 45.7
          },
          {
            "id": "molten_iron",
            "x": 149,
            "y": 100,
            "emitRate": 17853,
            "avgEmitRate": 327,
            "idleTime": 766,
            "eruptionTime": 22,
            "dormancyCycles": 32.1,
            "activeCycles": 59.2
          },
          {
            "id": "molten_gold",
            "x": 119,
            "y": 88,
            "emitRate": 7979,
            "avgEmitRate": 281,
            "idleTime": 687,
            "eruptionTime": 41,
            "dormancyCycles": 40.7,
            "activeCycles": 68.1
          },
          {
            "id": "chlorine_gas",
            "x": 24,
            "y": 104,
            "emitRate": 259,
            "avgEmitRate": 96,
            "idleTime": 262,
            "eruptionTime": 410,
            "dormancyCycles": 50.9,
            "activeCycles": 79.5
          },
          {
            "id": "liquid_co2",
            "x": 119,
            "y": 120,
            "emitRate": 766,
            "avgEmitRate": 138,
            "idleTime": 524,
            "eruptionTime": 247,
            "dormancyCycles": 53.1,
            "activeCycles": 68
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalPoor",
          "DistressSignal"
        ],
        "biomePaths": "FrozenWastes:45,74 51,67 45,55 35,54 28,63 36,75;64,67 51,67 45,74 51,87 64,89;19,63 15,48 0,47 0,64 18,64;51,87 45,74 36,75 27,87 42,97;64,67 64,46 52,46 45,55 51,67;52,46 46,35 36,34 29,41 35,54 45,55;35,54 29,41 20,41 15,48 19,63 28,63;27,87 36,75 28,63 19,63 18,64 16,80 24,88;64,107 64,89 51,87 42,97 42,103 50,108;42,103 42,97 27,87 24,88 19,97 22,107 32,110;32,110 22,107 17,112 18,128 35,128;24,88 16,80 0,83 0,96 19,97;17,112 0,111 0,128 18,128;22,107 19,97 0,96 0,111 17,112;50,108 42,103 32,110 35,128 48,128;16,80 18,64 0,64 0,83;64,107 50,108 48,128 64,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 31,
            "y": 54
          },
          {
            "id": "GravitasPedestal",
            "x": 23,
            "y": 109
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 34,
            "y": 105,
            "emitRate": 9648,
            "avgEmitRate": 297,
            "idleTime": 746,
            "eruptionTime": 42,
            "dormancyCycles": 60.3,
            "activeCycles": 83.1
          },
          {
            "id": "molten_iron",
            "x": 12,
            "y": 78,
            "emitRate": 6789,
            "avgEmitRate": 285,
            "idleTime": 498,
            "eruptionTime": 37,
            "dormancyCycles": 57.8,
            "activeCycles": 92.1
          },
          {
            "id": "molten_iron",
            "x": 22,
            "y": 67,
            "emitRate": 7914,
            "avgEmitRate": 240,
            "idleTime": 659,
            "eruptionTime": 41,
            "dormancyCycles": 53,
            "activeCycles": 58.1
          },
          {
            "id": "molten_iron",
            "x": 14,
            "y": 95,
            "emitRate": 9118,
            "avgEmitRate": 318,
            "idleTime": 758,
            "eruptionTime": 43,
            "dormancyCycles": 46.1,
            "activeCycles": 86.9
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:47,49 40,35 34,34 26,46 36,56;64,38 64,37 48,28 40,35 47,49 47,49;26,46 34,34 29,27 17,27 12,36 20,47;64,38 47,49 57,61 64,62;57,61 47,49 47,49 36,56 35,62 47,70;20,47 12,36 0,37 0,55 14,56\nToxicJungle:47,79 47,70 35,62 28,67 29,79 42,84;35,62 36,56 26,46 20,47 14,56 18,64 28,67;64,62 57,61 47,70 47,79 64,83;29,79 28,67 18,64 9,76 20,86;9,76 18,64 14,56 0,55 0,77\nMagmaCore:42,84 29,79 20,86 20,96 42,96;20,86 9,76 0,77 0,96 20,96;64,83 47,79 42,84 42,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 42,
            "y": 46
          },
          {
            "id": "GravitasPedestal",
            "x": 28,
            "y": 46
          },
          {
            "id": "SapTree",
            "x": 35,
            "y": 46
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 52,
            "y": 68,
            "emitRate": 9843,
            "avgEmitRate": 297,
            "idleTime": 727,
            "eruptionTime": 39,
            "dormancyCycles": 48.7,
            "activeCycles": 70.1
          },
          {
            "id": "molten_tungsten",
            "x": 50,
            "y": 90,
            "emitRate": 11237,
            "avgEmitRate": 399,
            "idleTime": 714,
            "eruptionTime": 44,
            "dormancyCycles": 42.6,
            "activeCycles": 67.2
          },
          {
            "id": "slimy_po2",
            "x": 38,
            "y": 60,
            "emitRate": 521,
            "avgEmitRate": 108,
            "idleTime": 446,
            "eruptionTime": 218,
            "dormancyCycles": 34.4,
            "activeCycles": 58.8
          },
          {
            "id": "methane",
            "x": 44,
            "y": 73,
            "emitRate": 466,
            "avgEmitRate": 113,
            "idleTime": 387,
            "eruptionTime": 256,
            "dormancyCycles": 52.6,
            "activeCycles": 81.5
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:64,51 48,51 44,61 47,66 64,66;64,51 64,34 49,34 44,42 48,51;48,51 44,42 34,41 27,56 31,61 44,61;34,41 28,35 17,35 14,52 17,55 27,56;45,78 47,66 44,61 31,61 28,76 31,79;64,80 64,66 47,66 45,78 46,80;46,80 45,78 31,79 30,96 46,96;31,79 28,76 16,77 13,96 30,96;17,35 15,33 0,34 0,52 14,52;17,55 14,52 0,52 0,73 11,73\nOilField:16,77 11,73 0,73 0,96 13,96;28,76 31,61 27,56 17,55 11,73 16,77;64,80 46,80 46,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 22,
            "y": 57
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 11,
            "y": 87,
            "emitRate": 277821,
            "avgEmitRate": 1258,
            "idleTime": 7461,
            "eruptionTime": 57,
            "dormancyCycles": 69.6,
            "activeCycles": 101.1
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:84,61 78,44 76,43 62,58 67,64 80,65;76,43 74,39 56,38 52,42 57,57 62,58;96,61 96,42 78,44 84,61;38,60 36,52 20,49 16,62 17,65 33,67;41,42 36,34 21,37 18,46 20,49 36,52;20,49 18,46 0,45 0,61 16,62;57,57 52,42 41,42 36,52 38,60 49,63;21,37 17,30 0,30 0,45 18,46\nFrozenWastes:49,63 38,60 33,67 34,80 51,80;80,65 67,64 63,80 82,80;96,61 84,61 80,65 82,80 96,80;67,64 62,58 57,57 49,63 51,80 63,80;33,67 17,65 15,80 34,80;17,65 16,62 0,61 0,80 15,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 17,
            "y": 52
          },
          {
            "id": "GravitasPedestal",
            "x": 10,
            "y": 52
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 81,
            "y": 49,
            "emitRate": 366,
            "avgEmitRate": 108,
            "idleTime": 466,
            "eruptionTime": 353,
            "dormancyCycles": 40.6,
            "activeCycles": 89.4
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:52,43 46,38 37,38 31,50 35,57 48,59;37,38 30,31 19,32 14,42 19,49 31,50;69,53 61,42 52,43 48,59 50,62 61,63;46,76 50,62 48,59 35,57 29,68 35,77;61,82 67,73 61,63 50,62 46,76 52,83;80,53 69,53 61,63 67,73 80,73;31,86 35,77 29,68 18,68 14,84 17,87;33,103 37,96 31,86 17,87 17,104;47,95 52,83 46,76 35,77 31,86 37,96;35,57 31,50 19,49 13,63 18,68 29,68;80,32 67,33 61,42 69,53 80,53;14,84 18,68 13,63 0,63 0,85;19,49 14,42 0,42 0,63 13,63;80,93 80,73 67,73 61,82 69,93;55,104 47,95 37,96 33,103 38,114 47,115;80,113 80,93 69,93 61,104 69,114;19,119 16,105 0,106 0,126 12,127;38,114 33,103 17,104 16,105 19,119 31,122;61,104 69,93 61,82 52,83 47,95 55,104;69,114 61,104 55,104 47,115 52,124 62,125;34,133 31,122 19,119 12,127 18,139 28,140;80,113 69,114 62,125 68,134 80,134;17,158 9,150 0,150 0,174 12,174;29,157 17,158 12,174 33,174;16,105 17,104 17,87 14,84 0,85 0,106;48,159 45,153 32,153 29,157 33,174 44,174;52,124 47,115 38,114 31,122 34,133 46,134;68,134 62,125 52,124 46,134 50,142 62,143;80,154 67,154 62,160 65,174 80,174;62,160 48,159 44,174 65,174\nSwamp:18,139 12,127 0,126 0,150 9,150;29,157 32,153 28,140 18,139 9,150 17,158;50,142 46,134 34,133 28,140 32,153 45,153;80,134 68,134 62,143 67,154 80,154;67,154 62,143 50,142 45,153 48,159 62,160",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 28,
            "y": 153
          },
          {
            "id": "GravitasPedestal",
            "x": 30,
            "y": 153
          }
        ],
        "geysers": [
          {
            "id": "filthy_water",
            "x": 59,
            "y": 91,
            "emitRate": 9575,
            "avgEmitRate": 2871,
            "idleTime": 298,
            "eruptionTime": 272,
            "dormancyCycles": 43.7,
            "activeCycles": 74
          },
          {
            "id": "salt_water",
            "x": 20,
            "y": 112,
            "emitRate": 11008,
            "avgEmitRate": 3343,
            "idleTime": 370,
            "eruptionTime": 361,
            "dormancyCycles": 50.3,
            "activeCycles": 80.5
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:24,78 25,70 19,63 1,73 15,83;19,63 19,54 0,54 0,73 1,73;45,78 41,65 25,70 24,78 32,85;41,65 42,62 38,54 21,51 19,54 19,63 25,70;78,78 81,64 76,59 61,64 62,76 65,79;96,81 96,64 81,64 78,78 81,81\nSandstone:62,76 61,64 56,60 42,62 41,65 45,78 48,81\nFrozenWastes:32,85 24,78 15,83 13,96 32,96;15,83 1,73 0,73 0,96 13,96;48,81 45,78 32,85 32,96 50,96;81,81 78,78 65,79 65,96 80,96;65,79 62,76 48,81 50,96 65,96;96,81 81,81 80,96 96,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 32,
            "y": 66
          },
          {
            "id": "GravitasPedestal",
            "x": 36,
            "y": 66
          },
          {
            "id": "GeneShuffler",
            "x": 34,
            "y": 60
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 44,
            "y": 84,
            "emitRate": 6213,
            "avgEmitRate": 1838,
            "idleTime": 315,
            "eruptionTime": 236,
            "dormancyCycles": 39.5,
            "activeCycles": 88.4
          },
          {
            "id": "hot_steam",
            "x": 22,
            "y": 84,
            "emitRate": 1878,
            "avgEmitRate": 531,
            "idleTime": 299,
            "eruptionTime": 506,
            "dormancyCycles": 84.3,
            "activeCycles": 69
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaSandstoneDefault",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "q": 2,
        "r": 1
      },
      {
        "id": "TundraMoonlet",
        "q": 1,
        "r": -5
      },
      {
        "id": "MarshyMoonlet",
        "q": -4,
        "r": -2
      },
      {
        "id": "NiobiumMoonlet",
        "q": 6,
        "r": -1
      },
      {
        "id": "MooMoonlet",
        "q": -5,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": 0,
        "r": 7
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 8,
        "r": -8
      },
      {
        "id": "TemporalTear",
        "q": -8,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -3,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -2,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -3,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -4,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 0,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -1,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 11,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 7,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -10,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 6,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 3,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 4,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -1,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -2,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 11,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 9,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 10,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -11,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -5,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -4,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 2,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 2,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -11,
        "r": 8
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation6",
        "q": -1,
        "r": -2
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 10,
        "r": -6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": 5,
        "r": -7
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": 6,
        "r": 5
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": -7,
        "r": -1
      }
    ]
  },
  {
    "coordinate": "M-BAD-C-834286562-0-0-0",
    "cluster": "M-BAD-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "MiniBadlandsStart",
        "offsetX": 82,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "Sandstone:77,103 89,88 90,75 86,64 71,53 51,53 45,56 35,72 35,88 43,101 59,108;45,56 32,48 20,58 20,67 35,72;128,134 106,131 104,131 106,153 128,153;35,88 35,72 20,67 15,72 22,91;43,131 44,126 34,112 25,113 21,117 22,134 28,139;57,119 59,108 43,101 34,112 44,126;57,150 43,131 28,139 28,153 57,153;28,139 22,134 0,140 0,153 28,153;72,131 85,115 77,103 59,108 57,119 72,131;83,142 72,131 72,131 57,150 57,153 84,153\nToxicJungle:113,40 106,31 87,37 94,53 106,53;71,53 74,42 62,31 52,35 51,53;51,53 52,35 41,29 30,37 32,48 45,56;94,53 87,37 86,37 74,42 71,53 86,64;106,74 112,63 106,53 94,53 86,64 90,75;112,85 106,74 90,75 89,88 103,97;32,48 30,37 20,33 5,47 20,58;15,72 20,67 20,58 5,47 0,47 0,72;128,63 112,63 106,74 112,85 128,85;128,63 128,40 113,40 106,53 112,63;34,112 43,101 35,88 22,91 19,95 25,113;104,131 103,131 83,142 84,153 106,153;104,99 103,97 89,88 77,103 85,115 88,115\nOilField:128,108 128,85 112,85 103,97 104,99 115,109;106,131 115,109 104,99 88,115 103,131 104,131;128,108 115,109 106,131 128,134;19,95 22,91 15,72 0,72 0,96;25,113 19,95 0,96 0,116 21,117;22,134 21,117 0,116 0,140;72,131 57,119 44,126 43,131 57,150;103,131 88,115 85,115 72,131 83,142",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 60,
            "y": 82
          },
          {
            "id": "WarpConduitReceiver",
            "x": 39,
            "y": 47
          },
          {
            "id": "WarpConduitSender",
            "x": 72,
            "y": 55
          },
          {
            "id": "WarpReceiver",
            "x": 18,
            "y": 68
          },
          {
            "id": "WarpPortal",
            "x": 18,
            "y": 63
          },
          {
            "id": "GeneShuffler",
            "x": 35,
            "y": 111
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 79,
            "y": 136,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 86,
            "y": 119,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 15,
            "y": 93,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 98,
            "y": 132,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 49,
            "y": 133,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "steam",
            "x": 96,
            "y": 77,
            "emitRate": 4943,
            "avgEmitRate": 1344,
            "idleTime": 393,
            "eruptionTime": 531,
            "dormancyCycles": 71.2,
            "activeCycles": 63.9
          },
          {
            "id": "chlorine_gas",
            "x": 95,
            "y": 55,
            "emitRate": 405,
            "avgEmitRate": 117,
            "idleTime": 226,
            "eruptionTime": 210,
            "dormancyCycles": 37.5,
            "activeCycles": 56.2
          },
          {
            "id": "big_volcano",
            "x": 88,
            "y": 108,
            "emitRate": 269828,
            "avgEmitRate": 1189,
            "idleTime": 8997,
            "eruptionTime": 68,
            "dormancyCycles": 46.5,
            "activeCycles": 66.4
          },
          {
            "id": "slimy_po2",
            "x": 111,
            "y": 57,
            "emitRate": 389,
            "avgEmitRate": 105,
            "idleTime": 292,
            "eruptionTime": 208,
            "dormancyCycles": 32.1,
            "activeCycles": 59.8
          },
          {
            "id": "methane",
            "x": 115,
            "y": 119,
            "emitRate": 358,
            "avgEmitRate": 122,
            "idleTime": 221,
            "eruptionTime": 270,
            "dormancyCycles": 54.3,
            "activeCycles": 88
          }
        ]
      },
      {
        "id": "MiniRadioactiveOceanWarp",
        "offsetX": 212,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalCaves",
          "RadioactiveCrust"
        ],
        "biomePaths": "Forest:79,108 92,97 97,84 92,67 90,65 70,58 57,63 46,81 49,94 57,104 76,108;113,78 107,67 92,67 97,84 106,85;109,105 106,85 97,84 92,97 106,107 106,107;106,107 92,97 79,108 90,116;76,108 57,104 54,112 67,122;54,112 57,104 49,94 37,105 44,115;57,63 45,55 44,56 39,77 46,81\nOcean:116,53 106,42 94,45 90,65 92,67 107,67;94,45 86,39 70,45 70,58 90,65;128,53 116,53 107,67 113,78 128,79;39,77 44,56 23,51 16,64 26,79;45,55 49,43 40,32 22,38 20,42 23,51 44,56;49,94 46,81 39,77 26,79 20,90 27,104 37,105;128,104 128,79 113,78 106,85 109,105;27,104 20,90 0,91 0,112 20,113;20,90 26,79 16,64 0,65 0,91;16,64 23,51 20,42 0,43 0,65;70,58 70,45 63,39 49,43 45,55 57,63\nRadioactive:113,129 106,107 106,107 90,116 90,131 106,135;68,130 67,122 54,112 44,115 40,129 43,133 64,135;44,115 37,105 27,104 20,113 24,128 40,129;24,128 20,113 0,112 0,132 21,132;128,104 109,105 106,107 113,129 128,130;90,131 90,116 79,108 76,108 67,122 68,130 86,134\nMagmaCore:128,130 113,129 106,135 107,153 128,153;106,135 90,131 86,134 86,153 107,153;43,133 40,129 24,128 21,132 23,153 40,153;21,132 0,132 0,153 23,153;64,135 43,133 40,153 65,153;86,134 68,130 64,135 65,153 86,153",
        "pointsOfInterest": [
          {
            "id": "WarpConduitReceiver",
            "x": 97,
            "y": 71
          },
          {
            "id": "WarpConduitSender",
            "x": 43,
            "y": 70
          },
          {
            "id": "GravitasPedestal",
            "x": 19,
            "y": 118
          },
          {
            "id": "WarpReceiver",
            "x": 80,
            "y": 89
          },
          {
            "id": "WarpPortal",
            "x": 58,
            "y": 89
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 28,
            "y": 82,
            "emitRate": 9948,
            "avgEmitRate": 3336,
            "idleTime": 320,
            "eruptionTime": 356,
            "dormancyCycles": 54.3,
            "activeCycles": 95.2
          },
          {
            "id": "filthy_water",
            "x": 112,
            "y": 87,
            "emitRate": 9961,
            "avgEmitRate": 3520,
            "idleTime": 312,
            "eruptionTime": 467,
            "dormancyCycles": 56.3,
            "activeCycles": 80.9
          },
          {
            "id": "slush_salt_water",
            "x": 24,
            "y": 60,
            "emitRate": 5520,
            "avgEmitRate": 1469,
            "idleTime": 570,
            "eruptionTime": 379,
            "dormancyCycles": 45.3,
            "activeCycles": 90.4
          },
          {
            "id": "steam",
            "x": 73,
            "y": 55,
            "emitRate": 3596,
            "avgEmitRate": 1353,
            "idleTime": 247,
            "eruptionTime": 492,
            "dormancyCycles": 43.5,
            "activeCycles": 56.7
          }
        ]
      },
      {
        "id": "MiniMetallicSwampy",
        "offsetX": 342,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "BouldersMixed"
        ],
        "biomePaths": "Swamp:84,80 90,70 72,54 61,68 67,81;56,40 40,25 28,39 43,52;47,67 42,61 23,64 19,72 24,83 40,85;42,61 43,52 28,39 23,40 17,49 23,64;67,81 61,68 47,67 40,85 43,90 61,93;17,94 24,83 19,72 0,72 0,94;128,38 110,38 102,48 103,52 128,64;128,66 128,64 103,52 94,69 107,78;109,92 107,78 94,69 90,70 84,80 89,97 103,98\nBoggyMarsh:94,69 103,52 102,48 85,42 72,52 72,54 90,70;72,54 72,52 59,40 56,40 43,52 42,61 47,67 61,68;19,72 23,64 17,49 0,49 0,72;37,107 43,90 40,85 24,83 17,94 25,108;57,113 62,96 61,93 43,90 37,107 47,116;43,131 47,116 37,107 25,108 21,114 24,129 40,132;24,129 21,114 0,115 0,133 19,135;128,66 107,78 109,92 128,94;100,125 108,113 103,98 89,97 83,103 82,113 95,125;128,94 109,92 103,98 108,113 128,114;128,133 128,114 108,113 100,125 108,135\nMetallic:21,114 25,108 17,94 0,94 0,115;89,97 84,80 67,81 61,93 62,96 83,103;68,132 68,121 57,113 47,116 43,131 61,138;82,113 83,103 62,96 57,113 68,121;95,125 82,113 68,121 68,132 84,138\nMagmaCore:40,132 24,129 19,135 21,153 38,153;19,135 0,133 0,153 21,153;61,138 43,131 40,132 38,153 61,153;108,135 100,125 95,125 84,138 86,153 105,153;84,138 68,132 61,138 61,153 86,153;128,133 108,135 105,153 128,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 61,
            "y": 126
          }
        ],
        "geysers": [
          {
            "id": "methane",
            "x": 37,
            "y": 119,
            "emitRate": 338,
            "avgEmitRate": 140,
            "idleTime": 232,
            "eruptionTime": 415,
            "dormancyCycles": 46.2,
            "activeCycles": 84.6
          },
          {
            "id": "molten_cobalt",
            "x": 68,
            "y": 93,
            "emitRate": 8142,
            "avgEmitRate": 323,
            "idleTime": 701,
            "eruptionTime": 49,
            "dormancyCycles": 56.6,
            "activeCycles": 86.8
          },
          {
            "id": "molten_gold",
            "x": 17,
            "y": 103,
            "emitRate": 12985,
            "avgEmitRate": 445,
            "idleTime": 738,
            "eruptionTime": 33,
            "dormancyCycles": 29.2,
            "activeCycles": 112.5
          },
          {
            "id": "molten_cobalt",
            "x": 85,
            "y": 97,
            "emitRate": 7343,
            "avgEmitRate": 298,
            "idleTime": 653,
            "eruptionTime": 48,
            "dormancyCycles": 52.3,
            "activeCycles": 74.5
          },
          {
            "id": "molten_aluminum",
            "x": 16,
            "y": 68,
            "emitRate": 7294,
            "avgEmitRate": 300,
            "idleTime": 712,
            "eruptionTime": 54,
            "dormancyCycles": 82.8,
            "activeCycles": 114
          },
          {
            "id": "molten_copper",
            "x": 112,
            "y": 88,
            "emitRate": 5545,
            "avgEmitRate": 302,
            "idleTime": 757,
            "eruptionTime": 73,
            "dormancyCycles": 38.9,
            "activeCycles": 64.2
          },
          {
            "id": "chlorine_gas",
            "x": 112,
            "y": 119,
            "emitRate": 355,
            "avgEmitRate": 100,
            "idleTime": 358,
            "eruptionTime": 292,
            "dormancyCycles": 49.5,
            "activeCycles": 81.8
          },
          {
            "id": "molten_cobalt",
            "x": 87,
            "y": 109,
            "emitRate": 6924,
            "avgEmitRate": 323,
            "idleTime": 671,
            "eruptionTime": 50,
            "dormancyCycles": 38.8,
            "activeCycles": 79
          }
        ]
      },
      {
        "id": "MiniForestFrozen",
        "offsetX": 472,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MagmaVents",
          "Geodes"
        ],
        "biomePaths": "Forest:104,65 110,57 104,41 88,42 85,57 96,66;85,57 88,42 83,36 64,40 64,56 73,61;128,56 128,37 107,36 104,41 110,57;64,56 64,40 60,37 39,41 39,41 43,58 54,62;43,58 39,41 22,43 16,51 23,66 33,66;23,66 16,51 0,51 0,76 14,76\nToxicJungle:96,66 85,57 73,61 73,80 87,85;128,80 114,80 106,90 111,104 128,104;55,81 54,62 43,58 33,66 41,86;39,89 41,86 33,66 23,66 14,76 23,91;44,105 39,89 23,91 17,101 22,111 33,114;56,128 54,108 44,105 33,114 37,130 44,134\nRust:114,80 104,65 96,66 87,85 89,88 106,90;73,80 73,61 64,56 54,62 55,81 63,85;84,102 89,88 87,85 73,80 63,85 65,103 71,106;105,112 111,104 106,90 89,88 84,102 93,112;65,103 63,85 55,81 41,86 39,89 44,105 54,108;112,128 105,112 93,112 85,129 88,132 107,134;128,128 128,104 111,104 105,112 112,128;128,80 128,56 110,57 104,65 114,80;23,91 14,76 0,76 0,101 17,101;11,127 22,111 17,101 0,101 0,127;37,130 33,114 22,111 11,127 22,137\nMagmaCore:107,134 88,132 84,153 110,153;74,126 71,106 65,103 54,108 56,128 65,131;128,128 112,128 107,134 110,153 128,153;88,132 85,129 74,126 65,131 67,153 84,153;65,131 56,128 44,134 45,153 67,153;44,134 37,130 22,137 20,153 45,153;22,137 11,127 0,127 0,153 20,153;93,112 84,102 71,106 74,126 85,129",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 83,
            "y": 98
          },
          {
            "id": "PropSurfaceSatellite3",
            "x": 71,
            "y": 58
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 38,
            "y": 112,
            "emitRate": 330,
            "avgEmitRate": 81,
            "idleTime": 228,
            "eruptionTime": 195,
            "dormancyCycles": 34.4,
            "activeCycles": 39
          },
          {
            "id": "slush_water",
            "x": 51,
            "y": 96,
            "emitRate": 4374,
            "avgEmitRate": 1296,
            "idleTime": 302,
            "eruptionTime": 346,
            "dormancyCycles": 46.8,
            "activeCycles": 58.3
          },
          {
            "id": "liquid_co2",
            "x": 23,
            "y": 99,
            "emitRate": 293,
            "avgEmitRate": 115,
            "idleTime": 182,
            "eruptionTime": 431,
            "dormancyCycles": 59,
            "activeCycles": 73.7
          },
          {
            "id": "chlorine_gas",
            "x": 29,
            "y": 72,
            "emitRate": 641,
            "avgEmitRate": 88,
            "idleTime": 360,
            "eruptionTime": 126,
            "dormancyCycles": 63.9,
            "activeCycles": 72
          },
          {
            "id": "small_volcano",
            "x": 105,
            "y": 104,
            "emitRate": 117645,
            "avgEmitRate": 675,
            "idleTime": 7982,
            "eruptionTime": 65,
            "dormancyCycles": 21.8,
            "activeCycles": 54.7
          }
        ]
      },
      {
        "id": "MiniFlipped",
        "offsetX": 602,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "SlimeSplats"
        ],
        "biomePaths": "Wasteland:106,91 115,77 105,67 92,69 90,86 95,92;90,86 92,69 86,65 69,68 68,85 72,88;116,103 106,91 95,92 90,110 93,114 107,115;95,92 90,86 72,88 72,108 90,110;128,103 128,77 115,77 106,91 116,103;68,85 69,68 63,63 48,66 45,78 51,88;72,108 72,88 68,85 51,88 48,97 59,110 68,111;48,97 51,88 45,78 26,80 23,86 24,92 37,101;26,80 22,64 0,67 0,83 23,86;34,116 37,101 24,92 12,107 22,119;24,92 23,86 0,83 0,107 12,107;45,78 48,66 40,58 24,61 22,64 26,80\nMagmaCore:128,77 128,47 112,48 105,67 115,77;105,67 112,48 103,40 86,45 86,65 92,69;86,65 86,45 78,39 65,44 63,63 69,68;63,63 65,44 54,36 44,41 40,58 48,66;40,58 44,41 27,30 17,40 24,61;22,64 24,61 17,40 0,38 0,67\nFrozenWastes:128,103 116,103 107,115 114,129 128,131;88,130 93,114 90,110 72,108 68,111 71,130 79,134;114,129 107,115 93,114 88,130 104,140;71,130 68,111 59,110 45,125 45,129 55,137;59,110 48,97 37,101 34,116 45,125;17,131 22,119 12,107 0,107 0,134;45,129 45,125 34,116 22,119 17,131 28,144\nSandstone:79,134 71,130 55,137 56,153 79,153;128,131 114,129 104,140 104,153 128,153;55,137 45,129 28,144 28,153 56,153;28,144 17,131 0,134 0,153 28,153;104,140 88,130 79,134 79,153 104,153",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 48,
            "y": 105
          },
          {
            "id": "MassiveHeatSink",
            "x": 107,
            "y": 139
          },
          {
            "id": "MassiveHeatSink",
            "x": 76,
            "y": 130
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 112,
            "y": 58,
            "emitRate": 4981,
            "avgEmitRate": 1691,
            "idleTime": 329,
            "eruptionTime": 409,
            "dormancyCycles": 35,
            "activeCycles": 55.4
          },
          {
            "id": "slimy_po2",
            "x": 48,
            "y": 116,
            "emitRate": 328,
            "avgEmitRate": 94,
            "idleTime": 147,
            "eruptionTime": 135,
            "dormancyCycles": 32.8,
            "activeCycles": 49.5
          },
          {
            "id": "hot_hydrogen",
            "x": 45,
            "y": 88,
            "emitRate": 252,
            "avgEmitRate": 104,
            "idleTime": 198,
            "eruptionTime": 351,
            "dormancyCycles": 42,
            "activeCycles": 76.6
          },
          {
            "id": "steam",
            "x": 115,
            "y": 97,
            "emitRate": 4187,
            "avgEmitRate": 1230,
            "idleTime": 249,
            "eruptionTime": 257,
            "dormancyCycles": 50.6,
            "activeCycles": 69.4
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 732,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "SubsurfaceOcean",
          "LushCore"
        ],
        "biomePaths": "Ocean:64,47 64,32 48,31 43,43 47,47;46,61 47,47 43,43 33,43 26,53 33,64 42,65;26,53 33,43 27,33 18,32 10,43 18,53;11,65 18,53 10,43 0,43 0,66\nFrozenWastes:64,47 47,47 46,61 64,63;64,63 46,61 42,65 45,77 64,77;45,77 42,65 33,64 21,76 21,79 26,84 42,84;64,91 64,77 45,77 42,84 46,94;24,101 26,84 21,79 0,90 19,102;33,64 26,53 18,53 11,65 21,76;21,79 21,76 11,65 0,66 0,90 0,90;14,111 19,102 0,90 0,90 0,113;46,95 46,94 42,84 26,84 24,101 31,104;52,110 46,95 31,104 34,113 46,116;64,91 46,94 46,95 52,110 64,110\nForest:34,113 31,104 24,101 19,102 14,111 23,128 25,128;14,111 0,113 0,128 23,128;46,116 34,113 25,128 48,128;64,110 52,110 46,116 48,128 64,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 42,
            "y": 62
          },
          {
            "id": "GravitasPedestal",
            "x": 31,
            "y": 108
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 51,
            "y": 103,
            "emitRate": 5226,
            "avgEmitRate": 261,
            "idleTime": 713,
            "eruptionTime": 73,
            "dormancyCycles": 73.7,
            "activeCycles": 86.8
          },
          {
            "id": "molten_iron",
            "x": 22,
            "y": 87,
            "emitRate": 6832,
            "avgEmitRate": 287,
            "idleTime": 765,
            "eruptionTime": 61,
            "dormancyCycles": 39.9,
            "activeCycles": 52.4
          },
          {
            "id": "molten_iron",
            "x": 39,
            "y": 74,
            "emitRate": 6485,
            "avgEmitRate": 303,
            "idleTime": 727,
            "eruptionTime": 56,
            "dormancyCycles": 44.4,
            "activeCycles": 82.3
          },
          {
            "id": "molten_iron",
            "x": 47,
            "y": 90,
            "emitRate": 8050,
            "avgEmitRate": 232,
            "idleTime": 665,
            "eruptionTime": 36,
            "dormancyCycles": 66.6,
            "activeCycles": 83.1
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 798,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:37,57 41,49 30,38 22,40 21,58 25,60;61,60 46,48 41,49 37,57 45,69;64,36 52,38 46,48 61,60 64,60;21,58 22,40 19,38 0,43 0,51 17,59;64,60 61,60 45,69 44,75 48,80 64,80\nToxicJungle:8,74 17,59 0,51 0,74;28,77 25,60 21,58 17,59 8,74 16,80;44,75 45,69 37,57 25,60 28,77 30,78\nMagmaCore:16,80 8,74 0,74 0,96 14,96;48,80 44,75 30,78 32,96 46,96;30,78 28,77 16,80 14,96 32,96;64,80 48,80 46,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 34,
            "y": 51
          },
          {
            "id": "GravitasPedestal",
            "x": 20,
            "y": 51
          },
          {
            "id": "SapTree",
            "x": 27,
            "y": 51
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 12,
            "y": 59,
            "emitRate": 10329,
            "avgEmitRate": 335,
            "idleTime": 906,
            "eruptionTime": 49,
            "dormancyCycles": 37.8,
            "activeCycles": 66.2
          },
          {
            "id": "molten_tungsten",
            "x": 10,
            "y": 91,
            "emitRate": 10093,
            "avgEmitRate": 344,
            "idleTime": 979,
            "eruptionTime": 60,
            "dormancyCycles": 73.7,
            "activeCycles": 104.6
          },
          {
            "id": "molten_tungsten",
            "x": 52,
            "y": 91,
            "emitRate": 7992,
            "avgEmitRate": 326,
            "idleTime": 691,
            "eruptionTime": 52,
            "dormancyCycles": 69.9,
            "activeCycles": 97.1
          },
          {
            "id": "slimy_po2",
            "x": 24,
            "y": 72,
            "emitRate": 314,
            "avgEmitRate": 92,
            "idleTime": 324,
            "eruptionTime": 344,
            "dormancyCycles": 20,
            "activeCycles": 26.8
          },
          {
            "id": "methane",
            "x": 41,
            "y": 69,
            "emitRate": 355,
            "avgEmitRate": 104,
            "idleTime": 303,
            "eruptionTime": 315,
            "dormancyCycles": 46.2,
            "activeCycles": 62.8
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 864,
        "offsetY": 98,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:96,36 80,38 77,43 84,58 96,58;64,61 66,45 56,38 46,48 49,60 59,64;84,58 77,43 66,45 64,61 77,65;56,38 56,36 41,24 30,37 35,45 46,48;27,59 35,45 30,37 19,35 13,55 20,60;13,55 19,35 17,33 0,33 0,55;49,60 46,48 35,45 27,59 38,68\nFrozenWastes:77,65 64,61 59,64 59,80 79,80;59,64 49,60 38,68 38,80 59,80;20,60 13,55 0,55 0,80 15,80;96,58 84,58 77,65 79,80 96,80;38,68 27,59 20,60 15,80 38,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 17,
            "y": 38
          },
          {
            "id": "GravitasPedestal",
            "x": 10,
            "y": 38
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 84,
            "y": 50,
            "emitRate": 375,
            "avgEmitRate": 91,
            "idleTime": 250,
            "eruptionTime": 206,
            "dormancyCycles": 40.7,
            "activeCycles": 47.8
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,68 80,43 69,43 60,54 60,58 77,69;60,58 60,54 47,46 35,53 40,66 51,68;77,69 60,58 51,68 56,81 61,83;80,68 77,69 61,83 67,93 80,94;69,43 59,30 48,36 47,46 60,54;40,66 35,53 34,53 20,58 16,67 20,73 32,76;47,46 48,36 36,28 27,36 34,53 35,53;56,81 51,68 40,66 32,76 35,84 46,88;59,105 67,93 61,83 56,81 46,88 47,101 54,106;80,114 80,94 67,93 59,105 69,115;47,101 46,88 35,84 25,97 34,106;32,116 34,106 25,97 20,96 11,111 20,120;60,130 69,115 59,105 54,106 48,121 60,130;80,134 80,114 69,115 60,130 68,135;48,121 54,106 47,101 34,106 32,116 40,123;20,73 16,67 0,67 0,88 12,88;34,53 27,36 19,36 11,46 20,58;16,67 20,58 11,46 0,46 0,67;35,84 32,76 20,73 12,88 20,96 25,97;36,135 40,123 32,116 20,120 18,131 29,137;11,111 20,96 12,88 0,88 0,111;18,131 20,120 11,111 0,111 0,133 12,134;80,156 66,154 61,156 59,159 62,174 80,174;49,143 60,130 48,121 40,123 36,135 45,143;59,159 41,162 39,174 62,174;41,162 37,157 25,154 20,156 19,174 39,174;20,156 14,154 0,156 0,174 19,174\nSwamp:66,154 68,135 60,130 60,130 49,143 61,156;45,143 36,135 29,137 25,154 37,157;80,134 68,135 66,154 80,156;25,154 29,137 18,131 12,134 14,154 20,156;14,154 12,134 0,133 0,156;59,159 61,156 49,143 45,143 37,157 41,162",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 61,
            "y": 136
          },
          {
            "id": "GravitasPedestal",
            "x": 63,
            "y": 136
          }
        ],
        "geysers": [
          {
            "id": "slush_water",
            "x": 15,
            "y": 88,
            "emitRate": 5255,
            "avgEmitRate": 2167,
            "idleTime": 156,
            "eruptionTime": 230,
            "dormancyCycles": 61.8,
            "activeCycles": 138.5
          },
          {
            "id": "filthy_water",
            "x": 39,
            "y": 60,
            "emitRate": 11661,
            "avgEmitRate": 2674,
            "idleTime": 330,
            "eruptionTime": 211,
            "dormancyCycles": 35.7,
            "activeCycles": 51.2
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 798,
        "offsetY": 98,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "MagmaCore:36,48 32,38 23,34 15,46 19,56 30,56;23,34 23,33 15,27 0,31 0,44 15,46;18,76 14,62 0,62 0,79 15,79;53,37 45,30 32,38 36,48 48,49;19,56 15,46 0,44 0,62 14,62;51,77 46,66 35,66 29,77 32,81 47,82;64,35 53,37 48,49 52,56 64,57\nOilField:52,56 48,49 36,48 30,56 35,66 46,66;35,66 30,56 19,56 14,62 18,76 29,77;64,57 52,56 46,66 51,77 64,77\nFrozenWastes:15,79 0,79 0,96 17,96;32,81 29,77 18,76 15,79 17,96 29,96;64,77 51,77 47,82 49,96 64,96;47,82 32,81 29,96 49,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 39,
            "y": 51
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 30,
            "y": 65,
            "emitRate": 271829,
            "avgEmitRate": 1082,
            "idleTime": 8395,
            "eruptionTime": 57,
            "dormancyCycles": 57.2,
            "activeCycles": 81.8
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 864,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:127,63 124,56 109,54 105,61 114,75 120,75;86,58 87,49 73,41 66,46 67,59 76,63;92,77 95,64 86,58 76,63 76,76 86,81;160,76 160,57 147,56 140,65 144,76;58,76 57,64 50,61 38,69 38,76 50,82;50,61 47,50 34,47 29,61 38,69;19,63 12,55 0,55 0,75 15,76\nSandstone:105,61 109,54 107,48 94,43 87,49 86,58 95,64;141,80 144,76 140,65 127,63 120,75 125,81;114,75 105,61 95,64 92,77 105,83;67,59 66,46 53,43 47,50 50,61 57,64;76,76 76,63 67,59 57,64 58,76 67,81;38,76 38,69 29,61 19,63 15,76 17,78 32,81\nFrozenWastes:160,76 144,76 141,80 143,96 160,96;141,80 125,81 123,96 143,96;125,81 120,75 114,75 105,83 106,96 123,96;105,83 92,77 86,81 85,96 106,96;50,82 38,76 32,81 33,96 49,96;67,81 58,76 50,82 49,96 68,96;86,81 76,76 67,81 68,96 85,96;32,81 17,78 14,96 33,96;17,78 15,76 0,75 0,96 14,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 110,
            "y": 65
          },
          {
            "id": "GravitasPedestal",
            "x": 114,
            "y": 65
          },
          {
            "id": "GeneShuffler",
            "x": 112,
            "y": 59
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 43,
            "y": 85,
            "emitRate": 4264,
            "avgEmitRate": 1749,
            "idleTime": 266,
            "eruptionTime": 461,
            "dormancyCycles": 41.1,
            "activeCycles": 75.4
          },
          {
            "id": "hot_steam",
            "x": 97,
            "y": 86,
            "emitRate": 1866,
            "avgEmitRate": 793,
            "idleTime": 180,
            "eruptionTime": 367,
            "dormancyCycles": 50.6,
            "activeCycles": 87.5
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "MiniBadlandsStart",
        "q": 0,
        "r": 0
      },
      {
        "id": "MiniRadioactiveOceanWarp",
        "q": -4,
        "r": 3
      },
      {
        "id": "MiniMetallicSwampy",
        "q": 1,
        "r": 2
      },
      {
        "id": "MiniForestFrozen",
        "q": 3,
        "r": -2
      },
      {
        "id": "MiniFlipped",
        "q": -1,
        "r": -3
      },
      {
        "id": "TundraMoonlet",
        "q": -1,
        "r": 8
      },
      {
        "id": "MarshyMoonlet",
        "q": 8,
        "r": -7
      },
      {
        "id": "MooMoonlet",
        "q": 4,
        "r": 3
      },
      {
        "id": "WaterMoonlet",
        "q": 9,
        "r": 0
      },
      {
        "id": "NiobiumMoonlet",
        "q": -11,
        "r": 10
      },
      {
        "id": "RegolithMoonlet",
        "q": 1,
        "r": -10
      },
      {
        "id": "TemporalTear",
        "q": -4,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -2,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -4,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 8,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 0,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -9,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 8,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 5,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -2,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 1,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 2,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 2,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 6,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 6,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 11,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": -11,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 4,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 3,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 5,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 4,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -9,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 12,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 12,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -7,
        "r": -4
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 12,
        "r": -12
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": -6,
        "r": 6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": -7,
        "r": 3
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": -12,
        "r": 4
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 4,
        "r": -5
      }
    ]
  },
  {
    "coordinate": "V-HTFST-C-174337039-0-0-0",
    "cluster": "V-HTFST-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaAridio",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "CrashedSatellites",
          "FrozenCore"
        ],
        "biomePaths": "Forest:138,207 142,189 133,174 108,170 106,171 94,196 105,212 127,215;100,223 105,212 94,196 92,196 79,214 86,224;94,196 106,171 86,168 79,182 92,196;137,167 130,154 114,155 108,170 133,174;128,225 127,215 105,212 100,223 110,234;154,223 153,215 138,207 127,215 128,225 139,232;163,203 154,188 142,189 138,207 153,215;154,188 161,173 157,167 137,167 133,174 142,189\nFrozenWastes:27,359 14,348 0,348 0,380 25,380;58,358 47,352 27,359 25,380 58,380;89,360 74,350 58,358 58,380 88,380;120,362 104,351 89,360 88,380 120,380;153,363 137,351 120,362 120,380 153,380;184,361 171,350 153,363 153,380 185,380;213,353 208,348 184,361 185,380 214,380;240,350 213,353 214,380 240,380\nOilField:14,348 29,324 24,315 0,313 0,348;47,352 46,329 29,324 14,348 27,359;74,350 75,333 55,322 46,329 47,352 58,358;104,351 105,337 89,325 75,333 74,350 89,360;137,351 137,339 121,328 105,337 104,351 120,362;171,350 171,341 153,328 137,339 137,351 153,363;208,348 207,340 186,328 171,341 171,350 184,361;240,350 240,327 218,325 207,340 208,348 213,353\nOcean:32,297 21,285 0,285 0,313 24,315;31,223 29,198 0,199 0,224 27,227;51,164 59,154 52,133 32,134 26,142 32,165;78,124 84,108 70,93 50,100 49,102 56,127;138,305 140,287 128,278 109,286 109,303 122,313;184,289 191,278 185,264 162,260 161,261 157,281 171,293;240,296 219,296 211,308 218,325 240,327;240,265 240,233 219,234 209,248 220,265;240,121 240,92 216,93 210,102 218,123\nBoggyMarsh:21,285 32,260 27,255 0,256 0,285;57,240 51,226 31,223 27,227 27,255 32,260 40,260;98,278 100,256 77,248 68,274 79,284;153,328 154,315 138,305 122,313 121,328 137,339;218,325 211,308 192,308 185,314 186,328 207,340;210,102 216,93 210,76 185,76 180,83 189,104;220,265 209,248 197,248 185,264 191,278 211,279;220,142 214,130 192,131 185,146 192,158 210,160\nWasteland:27,255 27,227 0,224 0,256;23,113 31,104 25,85 0,84 0,114;45,296 54,275 40,260 32,260 21,285 32,297;55,194 61,185 51,164 32,165 27,171 32,196;52,133 56,127 49,102 31,104 23,113 32,134;77,300 79,284 68,274 54,275 45,296 57,308;68,274 77,248 76,246 57,240 40,260 54,275;89,325 90,312 77,300 57,308 55,322 75,333;103,137 111,125 103,103 84,108 78,124 88,138;192,131 183,115 166,116 157,137 163,145 185,146;219,296 211,279 191,278 184,289 192,308 211,308;209,216 216,203 209,189 189,188 181,203 189,218;240,203 240,174 218,173 209,189 216,203;240,143 220,142 210,160 218,173 240,174\nToxicJungle:29,198 32,196 27,171 0,171 0,199;27,171 32,165 26,142 0,143 0,171;26,142 32,134 23,113 0,114 0,143;55,322 57,308 45,296 32,297 24,315 29,324 46,329;62,211 55,194 32,196 29,198 31,223 51,226;92,196 79,182 61,185 55,194 62,211 79,214;88,138 78,124 56,127 52,133 59,154 79,154;103,103 103,103 99,78 90,73 71,82 70,93 84,108;121,328 122,313 109,303 90,312 89,325 105,337;130,95 132,81 120,69 99,78 103,103;139,254 139,232 128,225 110,234 109,251 129,261;140,106 130,95 103,103 103,103 111,125 132,126;157,281 161,261 139,254 129,261 128,278 140,287;186,328 185,314 169,306 154,315 153,328 171,341;157,167 163,145 157,137 139,136 130,154 137,167;183,115 189,104 180,83 163,85 156,105 166,116;197,248 183,230 168,234 162,260 185,264;183,230 189,218 181,203 163,203 153,215 154,223 168,234;192,158 185,146 163,145 157,167 161,173 183,175;189,188 183,175 161,173 154,188 163,203 181,203;218,173 210,160 192,158 183,175 189,188 209,189\nBarren:30,79 28,58 26,57 0,59 0,84 25,85;60,74 58,56 51,51 28,58 30,79 47,84;90,73 88,54 78,48 58,56 60,74 71,82;120,69 121,56 107,45 88,54 90,73 99,78;152,73 155,59 136,48 121,56 120,69 132,81;180,83 185,76 181,59 165,53 155,59 152,73 163,85;217,63 212,52 194,47 181,59 185,76 210,76;240,92 240,65 217,63 210,76 216,93\nRadioactive:49,102 50,100 47,84 30,79 25,85 31,104;70,93 71,82 60,74 47,84 50,100;192,308 184,289 171,293 169,306 185,314;240,121 218,123 214,130 220,142 240,143\nRust:86,224 79,214 62,211 51,226 57,240 76,246;79,182 86,168 79,154 59,154 51,164 61,185;109,303 109,286 98,278 79,284 77,300 90,312;109,251 110,234 100,223 86,224 76,246 77,248 100,256;108,170 114,155 103,137 88,138 79,154 86,168 106,171;128,278 129,261 109,251 100,256 98,278 109,286;130,154 139,136 132,126 111,125 103,137 114,155;169,306 171,293 157,281 140,287 138,305 154,315;166,116 156,105 140,106 132,126 139,136 157,137;156,105 163,85 152,73 132,81 130,95 140,106;162,260 168,234 154,223 139,232 139,254 161,261;209,248 219,234 209,216 189,218 183,230 197,248;214,130 218,123 210,102 189,104 183,115 192,131;240,265 220,265 211,279 219,296 240,296;240,233 240,203 216,203 209,216 219,234",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 119,
            "y": 196
          },
          {
            "id": "WarpConduitReceiver",
            "x": 66,
            "y": 104
          },
          {
            "id": "WarpConduitSender",
            "x": 214,
            "y": 285
          },
          {
            "id": "GravitasPedestal",
            "x": 67,
            "y": 121
          },
          {
            "id": "WarpReceiver",
            "x": 95,
            "y": 300
          },
          {
            "id": "WarpPortal",
            "x": 94,
            "y": 294
          },
          {
            "id": "GeneShuffler",
            "x": 32,
            "y": 333
          },
          {
            "id": "GeneShuffler",
            "x": 213,
            "y": 239
          },
          {
            "id": "GeneShuffler",
            "x": 205,
            "y": 320
          },
          {
            "id": "GeneShuffler",
            "x": 48,
            "y": 250
          },
          {
            "id": "GeneShuffler",
            "x": 185,
            "y": 108
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 107,
            "y": 79
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 154,
            "y": 96
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 21,
            "y": 94
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 124,
            "y": 96,
            "emitRate": 5931,
            "avgEmitRate": 1723,
            "idleTime": 328,
            "eruptionTime": 296,
            "dormancyCycles": 52.4,
            "activeCycles": 82.9
          },
          {
            "id": "methane",
            "x": 187,
            "y": 247,
            "emitRate": 313,
            "avgEmitRate": 132,
            "idleTime": 250,
            "eruptionTime": 445,
            "dormancyCycles": 52.9,
            "activeCycles": 103.2
          },
          {
            "id": "steam",
            "x": 142,
            "y": 311,
            "emitRate": 5563,
            "avgEmitRate": 1925,
            "idleTime": 311,
            "eruptionTime": 392,
            "dormancyCycles": 50,
            "activeCycles": 81.9
          },
          {
            "id": "methane",
            "x": 90,
            "y": 271,
            "emitRate": 410,
            "avgEmitRate": 103,
            "idleTime": 274,
            "eruptionTime": 223,
            "dormancyCycles": 32.3,
            "activeCycles": 41.2
          },
          {
            "id": "salt_water",
            "x": 180,
            "y": 280,
            "emitRate": 6094,
            "avgEmitRate": 2658,
            "idleTime": 58,
            "eruptionTime": 119,
            "dormancyCycles": 40,
            "activeCycles": 73.2
          },
          {
            "id": "OilWell",
            "x": 22,
            "y": 343,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 99,
            "y": 341,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 219,
            "y": 344,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_co2",
            "x": 143,
            "y": 292,
            "emitRate": 313,
            "avgEmitRate": 108,
            "idleTime": 291,
            "eruptionTime": 357,
            "dormancyCycles": 58.7,
            "activeCycles": 97.9
          },
          {
            "id": "big_volcano",
            "x": 50,
            "y": 157,
            "emitRate": 313285,
            "avgEmitRate": 1206,
            "idleTime": 10412,
            "eruptionTime": 65,
            "dormancyCycles": 57.8,
            "activeCycles": 94.1
          },
          {
            "id": "slush_salt_water",
            "x": 131,
            "y": 133,
            "emitRate": 4842,
            "avgEmitRate": 1175,
            "idleTime": 364,
            "eruptionTime": 340,
            "dormancyCycles": 53,
            "activeCycles": 53.6
          },
          {
            "id": "methane",
            "x": 116,
            "y": 276,
            "emitRate": 222,
            "avgEmitRate": 115,
            "idleTime": 99,
            "eruptionTime": 465,
            "dormancyCycles": 46.8,
            "activeCycles": 79.8
          },
          {
            "id": "liquid_co2",
            "x": 224,
            "y": 218,
            "emitRate": 921,
            "avgEmitRate": 153,
            "idleTime": 303,
            "eruptionTime": 110,
            "dormancyCycles": 66.1,
            "activeCycles": 108.9
          },
          {
            "id": "oil_drip",
            "x": 185,
            "y": 335,
            "emitRate": 291,
            "avgEmitRate": 163,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "liquid_co2",
            "x": 55,
            "y": 206,
            "emitRate": 556,
            "avgEmitRate": 157,
            "idleTime": 182,
            "eruptionTime": 142,
            "dormancyCycles": 43.5,
            "activeCycles": 77.8
          },
          {
            "id": "slush_salt_water",
            "x": 161,
            "y": 341,
            "emitRate": 10146,
            "avgEmitRate": 1466,
            "idleTime": 471,
            "eruptionTime": 141,
            "dormancyCycles": 40.1,
            "activeCycles": 67.1
          },
          {
            "id": "hot_hydrogen",
            "x": 132,
            "y": 346,
            "emitRate": 408,
            "avgEmitRate": 101,
            "idleTime": 356,
            "eruptionTime": 265,
            "dormancyCycles": 90.3,
            "activeCycles": 123.2
          },
          {
            "id": "molten_gold",
            "x": 36,
            "y": 303,
            "emitRate": 10979,
            "avgEmitRate": 315,
            "idleTime": 513,
            "eruptionTime": 26,
            "dormancyCycles": 56.9,
            "activeCycles": 81
          },
          {
            "id": "liquid_co2",
            "x": 114,
            "y": 265,
            "emitRate": 556,
            "avgEmitRate": 157,
            "idleTime": 182,
            "eruptionTime": 142,
            "dormancyCycles": 43.5,
            "activeCycles": 77.8
          },
          {
            "id": "big_volcano",
            "x": 156,
            "y": 286,
            "emitRate": 219086,
            "avgEmitRate": 1096,
            "idleTime": 8250,
            "eruptionTime": 74,
            "dormancyCycles": 72.5,
            "activeCycles": 94.3
          }
        ]
      },
      {
        "id": "MediumSandySwamp",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "BouldersMixed",
          "BouldersMedium"
        ],
        "biomePaths": "Sandstone:94,104 93,92 83,87 70,95 72,110 77,113;110,59 111,53 96,41 88,43 82,52 85,64 99,67;142,86 134,71 124,72 115,87 120,96 135,97;160,108 160,86 142,86 135,97 141,109;113,111 120,96 115,87 103,85 93,92 94,104 104,112;83,87 79,72 61,72 62,91 70,95;62,91 61,72 60,71 40,78 38,83 48,97;14,77 22,64 17,55 0,54 0,77;144,131 137,117 122,120 119,135 136,141;104,112 94,104 77,113 81,126 97,129;17,135 20,120 0,112 0,137;72,110 70,95 62,91 48,97 46,103 56,117\nFrozenWastes:134,71 139,64 135,48 120,46 111,53 110,59 124,72;160,64 160,43 140,42 135,48 139,64;85,64 82,52 65,50 58,60 60,71 61,72 79,72;24,40 21,36 0,34 0,54 17,55;67,161 55,151 48,152 43,160 44,176 67,176;43,160 22,157 20,176 44,176;92,158 88,153 82,152 67,161 67,176 91,176;115,159 113,157 92,158 91,176 115,176\nSwamp:124,72 110,59 99,67 103,85 115,87;103,85 99,67 85,64 79,72 83,87 93,92;160,108 141,109 137,117 144,131 160,132;119,135 122,120 113,111 104,112 97,129 100,135 113,139;137,117 141,109 135,97 120,96 113,111 122,120;38,83 40,78 33,65 22,64 14,77 23,89;46,103 48,97 38,83 23,89 21,96 30,110;28,115 30,110 21,96 0,102 0,112 20,120;38,138 41,129 28,115 20,120 17,135 24,142;54,125 56,117 46,103 30,110 28,115 41,129;73,136 81,126 77,113 72,110 56,117 54,125 63,136\nBarren:160,86 160,64 139,64 134,71 142,86;65,50 62,40 46,37 40,44 42,55 58,60;60,71 58,60 42,55 33,65 40,78;42,55 40,44 24,40 17,55 22,64 33,65;21,96 23,89 14,77 0,77 0,102\nRadioactive:160,154 160,132 144,131 136,141 138,154;137,155 138,154 136,141 119,135 113,139 113,157 115,159;137,155 115,159 115,176 139,176;160,154 138,154 137,155 139,176 160,176;100,135 97,129 81,126 73,136 82,152 88,153;113,157 113,139 100,135 88,153 92,158;55,151 63,136 54,125 41,129 38,138 48,152;24,142 17,135 0,137 0,155 21,156;82,152 73,136 63,136 55,151 67,161;48,152 38,138 24,142 21,156 22,157 43,160;22,157 21,156 0,155 0,176 20,176",
        "pointsOfInterest": [
          {
            "id": "WarpConduitSender",
            "x": 95,
            "y": 55
          },
          {
            "id": "WarpConduitReceiver",
            "x": 83,
            "y": 116
          },
          {
            "id": "MassiveHeatSink",
            "x": 139,
            "y": 48
          },
          {
            "id": "WarpPortal",
            "x": 72,
            "y": 102
          },
          {
            "id": "WarpReceiver",
            "x": 91,
            "y": 102
          },
          {
            "id": "GeneShuffler",
            "x": 73,
            "y": 73
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 134,
            "y": 162,
            "emitRate": 416,
            "avgEmitRate": 149,
            "idleTime": 206,
            "eruptionTime": 311,
            "dormancyCycles": 60.2,
            "activeCycles": 88.7
          },
          {
            "id": "liquid_co2",
            "x": 119,
            "y": 162,
            "emitRate": 570,
            "avgEmitRate": 163,
            "idleTime": 424,
            "eruptionTime": 355,
            "dormancyCycles": 49.3,
            "activeCycles": 82.9
          },
          {
            "id": "slush_salt_water",
            "x": 136,
            "y": 62,
            "emitRate": 7292,
            "avgEmitRate": 1617,
            "idleTime": 305,
            "eruptionTime": 177,
            "dormancyCycles": 51.8,
            "activeCycles": 78.5
          },
          {
            "id": "slush_water",
            "x": 119,
            "y": 60,
            "emitRate": 5433,
            "avgEmitRate": 1854,
            "idleTime": 338,
            "eruptionTime": 392,
            "dormancyCycles": 42.5,
            "activeCycles": 74.2
          },
          {
            "id": "molten_cobalt",
            "x": 51,
            "y": 130,
            "emitRate": 11552,
            "avgEmitRate": 326,
            "idleTime": 707,
            "eruptionTime": 35,
            "dormancyCycles": 67.7,
            "activeCycles": 98.3
          },
          {
            "id": "hot_steam",
            "x": 102,
            "y": 139,
            "emitRate": 2648,
            "avgEmitRate": 790,
            "idleTime": 321,
            "eruptionTime": 332,
            "dormancyCycles": 65.7,
            "activeCycles": 93.4
          },
          {
            "id": "molten_gold",
            "x": 128,
            "y": 144,
            "emitRate": 10846,
            "avgEmitRate": 356,
            "idleTime": 808,
            "eruptionTime": 42,
            "dormancyCycles": 38.6,
            "activeCycles": 77.5
          },
          {
            "id": "slush_water",
            "x": 58,
            "y": 99,
            "emitRate": 4622,
            "avgEmitRate": 1610,
            "idleTime": 254,
            "eruptionTime": 334,
            "dormancyCycles": 27.2,
            "activeCycles": 43.1
          },
          {
            "id": "small_volcano",
            "x": 73,
            "y": 145,
            "emitRate": 156389,
            "avgEmitRate": 616,
            "idleTime": 8684,
            "eruptionTime": 56,
            "dormancyCycles": 46.1,
            "activeCycles": 73.3
          },
          {
            "id": "hot_po2",
            "x": 117,
            "y": 140,
            "emitRate": 346,
            "avgEmitRate": 98,
            "idleTime": 384,
            "eruptionTime": 314,
            "dormancyCycles": 57.9,
            "activeCycles": 99.4
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "CrashedSatellites"
        ],
        "biomePaths": "FrozenWastes:45,52 51,39 42,30 32,35 30,48 35,53;64,38 51,39 45,52 54,61 64,61;30,48 32,35 21,27 10,40 18,49;54,61 45,52 35,53 32,66 38,73 45,73;32,66 35,53 30,48 18,49 13,63 18,68;64,61 54,61 45,73 53,84 64,84;17,83 18,68 13,63 0,63 0,85 14,86;13,63 18,49 10,40 0,39 0,63;53,84 45,73 38,73 30,86 35,94 45,95;38,73 32,66 18,68 17,83 30,86;28,105 35,94 30,86 17,83 14,86 17,106;47,111 51,106 45,95 35,94 28,105 34,113;64,106 64,84 53,84 45,95 51,106;47,111 34,113 30,128 52,128;64,106 51,106 47,111 52,128 64,128;34,113 28,105 17,106 16,107 17,128 30,128;16,107 0,107 0,128 17,128;16,107 17,106 14,86 0,85 0,107",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 37,
            "y": 50
          },
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 111
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 25,
            "y": 37
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 37,
            "y": 66
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 50,
            "y": 53
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 52,
            "y": 91,
            "emitRate": 9438,
            "avgEmitRate": 279,
            "idleTime": 689,
            "eruptionTime": 39,
            "dormancyCycles": 61.9,
            "activeCycles": 78
          },
          {
            "id": "molten_iron",
            "x": 23,
            "y": 84,
            "emitRate": 5066,
            "avgEmitRate": 223,
            "idleTime": 785,
            "eruptionTime": 66,
            "dormancyCycles": 24.9,
            "activeCycles": 32.9
          },
          {
            "id": "molten_iron",
            "x": 17,
            "y": 60,
            "emitRate": 8329,
            "avgEmitRate": 289,
            "idleTime": 624,
            "eruptionTime": 39,
            "dormancyCycles": 63.7,
            "activeCycles": 90.9
          },
          {
            "id": "molten_iron",
            "x": 52,
            "y": 113,
            "emitRate": 5066,
            "avgEmitRate": 223,
            "idleTime": 785,
            "eruptionTime": 66,
            "dormancyCycles": 24.9,
            "activeCycles": 32.9
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "DistressSignal"
        ],
        "biomePaths": "BoggyMarsh:45,53 47,40 42,34 29,37 26,51 33,56;64,58 64,38 47,40 45,53 50,59;26,51 29,37 21,29 8,39 19,53;13,60 19,53 8,39 0,39 0,61;20,74 13,60 0,61 0,79 15,79\nToxicJungle:50,59 45,53 33,56 31,74 31,74 46,75;64,58 50,59 46,75 49,78 64,78;31,74 33,56 26,51 19,53 13,60 20,74\nMagmaCore:49,78 46,75 31,74 32,96 46,96;64,78 49,78 46,96 64,96;31,74 31,74 20,74 15,79 18,96 32,96;15,79 0,79 0,96 18,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 47
          },
          {
            "id": "GravitasPedestal",
            "x": 30,
            "y": 47
          },
          {
            "id": "SapTree",
            "x": 37,
            "y": 47
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 45,
            "y": 88,
            "emitRate": 15416,
            "avgEmitRate": 319,
            "idleTime": 834,
            "eruptionTime": 29,
            "dormancyCycles": 55.1,
            "activeCycles": 90.4
          },
          {
            "id": "molten_tungsten",
            "x": 30,
            "y": 84,
            "emitRate": 10314,
            "avgEmitRate": 346,
            "idleTime": 760,
            "eruptionTime": 43,
            "dormancyCycles": 44.9,
            "activeCycles": 75.2
          },
          {
            "id": "molten_tungsten",
            "x": 37,
            "y": 91,
            "emitRate": 10314,
            "avgEmitRate": 346,
            "idleTime": 760,
            "eruptionTime": 43,
            "dormancyCycles": 44.9,
            "activeCycles": 75.2
          },
          {
            "id": "slimy_po2",
            "x": 38,
            "y": 69,
            "emitRate": 409,
            "avgEmitRate": 101,
            "idleTime": 288,
            "eruptionTime": 262,
            "dormancyCycles": 55,
            "activeCycles": 58.7
          },
          {
            "id": "slimy_po2",
            "x": 24,
            "y": 63,
            "emitRate": 306,
            "avgEmitRate": 109,
            "idleTime": 94,
            "eruptionTime": 142,
            "dormancyCycles": 54.2,
            "activeCycles": 79.5
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:64,38 52,39 44,50 49,59 64,60;64,78 64,60 49,59 42,73 47,78;64,78 47,78 45,96 64,96;27,60 31,50 16,42 6,49 18,63;18,63 6,49 0,49 0,73 12,73;28,81 17,79 10,96 31,96;49,59 44,50 33,49 31,50 27,60 37,72 42,73\nOilField:47,78 42,73 37,72 28,81 31,96 45,96;37,72 27,60 18,63 12,73 17,79 28,81;17,79 12,73 0,73 0,96 10,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 33,
            "y": 82
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 10,
            "y": 77,
            "emitRate": 274698,
            "avgEmitRate": 1411,
            "idleTime": 7064,
            "eruptionTime": 54,
            "dormancyCycles": 63.5,
            "activeCycles": 136.5
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:55,57 57,43 49,36 38,38 34,45 40,59;74,60 77,43 77,42 68,39 57,43 55,57 59,61;96,62 96,46 77,43 74,60 77,63;38,62 40,59 34,45 20,46 18,48 19,64 20,65;18,48 20,46 16,32 0,32 0,48;19,64 18,48 0,48 0,65\nFrozenWastes:59,61 55,57 40,59 38,62 41,80 57,80;77,63 74,60 59,61 57,80 76,80;38,62 20,65 20,80 41,80;20,65 19,64 0,65 0,80 20,80;96,62 77,63 76,80 96,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 80,
            "y": 51
          },
          {
            "id": "GravitasPedestal",
            "x": 73,
            "y": 51
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 13,
            "y": 53,
            "emitRate": 355,
            "avgEmitRate": 121,
            "idleTime": 206,
            "eruptionTime": 222,
            "dormancyCycles": 39,
            "activeCycles": 74.6
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:64,80 66,64 64,62 50,65 46,72 57,84;46,72 50,65 43,53 35,54 29,65 38,75;68,44 64,33 54,30 47,36 49,46 63,49;57,84 46,72 38,75 34,83 38,92 54,92;80,64 66,64 64,80 80,87;80,94 80,87 64,80 57,84 54,92 58,99 61,101;29,65 35,54 29,47 17,48 13,54 19,66;29,47 33,34 19,29 12,36 17,48;34,83 38,75 29,65 19,66 15,72 20,84;64,62 63,49 49,46 43,53 50,65;43,53 49,46 47,36 36,33 33,34 29,47 35,54;16,90 20,84 15,72 0,73 0,90;80,64 80,45 68,44 63,49 64,62 66,64;13,54 17,48 12,36 0,35 0,55;38,92 34,83 20,84 16,90 20,101 34,101;67,115 61,101 58,99 45,112 51,122 61,123;80,94 61,101 67,115 80,116;16,107 20,101 16,90 0,90 0,107;38,110 34,101 20,101 16,107 20,121 29,121;58,99 54,92 38,92 34,101 38,110 45,112;37,133 29,121 20,121 17,124 18,141 30,142;20,121 16,107 0,107 0,124 17,124;18,141 17,124 0,124 0,140 17,141;51,122 45,112 38,110 29,121 37,133 43,133;15,72 19,66 13,54 0,55 0,73;62,140 66,135 61,123 51,122 43,133 50,142;61,161 46,155 42,157 42,174 62,174;80,134 80,116 67,115 61,123 66,135;80,155 67,154 61,161 62,174 80,174;21,162 15,157 0,159 0,174 22,174;42,157 34,155 21,162 22,174 42,174\nSwamp:46,155 50,142 43,133 37,133 30,142 34,155 42,157;15,157 17,141 0,140 0,159;34,155 30,142 18,141 17,141 15,157 21,162;80,134 66,135 62,140 67,154 80,155;67,154 62,140 50,142 46,155 61,161",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 154
          },
          {
            "id": "GravitasPedestal",
            "x": 43,
            "y": 154
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 64,
            "y": 83,
            "emitRate": 11992,
            "avgEmitRate": 3405,
            "idleTime": 388,
            "eruptionTime": 235,
            "dormancyCycles": 27.4,
            "activeCycles": 83.2
          },
          {
            "id": "slush_water",
            "x": 62,
            "y": 65,
            "emitRate": 5572,
            "avgEmitRate": 1397,
            "idleTime": 279,
            "eruptionTime": 228,
            "dormancyCycles": 61.9,
            "activeCycles": 78
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:96,64 96,49 80,48 74,58 79,66;79,66 74,58 65,57 58,65 60,77 76,79;96,64 79,66 76,79 76,80 96,81;46,62 43,51 33,51 24,63 39,71;40,79 39,71 24,63 22,63 18,79 19,81 37,82;22,63 17,60 0,64 0,77 18,79;17,60 13,47 0,45 0,64\nFrozenWastes:96,81 76,80 76,96 96,96;56,82 40,79 37,82 37,96 57,96;37,82 19,81 17,96 37,96;76,80 76,79 60,77 56,82 57,96 76,96;19,81 18,79 0,77 0,96 17,96\nSandstone:60,77 58,65 46,62 39,71 40,79 56,82",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 22,
            "y": 78
          },
          {
            "id": "GravitasPedestal",
            "x": 26,
            "y": 78
          },
          {
            "id": "GeneShuffler",
            "x": 24,
            "y": 72
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 84,
            "y": 84,
            "emitRate": 2231,
            "avgEmitRate": 806,
            "idleTime": 223,
            "eruptionTime": 353,
            "dormancyCycles": 42.3,
            "activeCycles": 60.7
          },
          {
            "id": "steam",
            "x": 42,
            "y": 84,
            "emitRate": 5650,
            "avgEmitRate": 1669,
            "idleTime": 208,
            "eruptionTime": 206,
            "dormancyCycles": 43.1,
            "activeCycles": 63.2
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaAridio",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumSandySwamp",
        "q": 1,
        "r": -3
      },
      {
        "id": "TundraMoonlet",
        "q": -4,
        "r": 5
      },
      {
        "id": "MarshyMoonlet",
        "q": 1,
        "r": 4
      },
      {
        "id": "NiobiumMoonlet",
        "q": 5,
        "r": -4
      },
      {
        "id": "MooMoonlet",
        "q": 7,
        "r": 0
      },
      {
        "id": "WaterMoonlet",
        "q": -4,
        "r": -3
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 2,
        "r": -8
      },
      {
        "id": "TemporalTear",
        "q": -3,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 3,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 6,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -11,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -10,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -10,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 7,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 6,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 10,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -3,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -6,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -7,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -7,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -6,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": -6,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 11,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 7,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 8,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 9,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 8,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": 1,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 1,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 0,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -7,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 1,
        "r": -11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation1",
        "q": -1,
        "r": 3
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -8,
        "r": -3
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": -11,
        "r": 2
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 5,
        "r": -11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": 10,
        "r": 0
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": 4,
        "r": 3
      }
    ]
  },
  {
    "coordinate": "V-CERS-C-1043692229-0-0-0",
    "cluster": "V-CERS-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "CeresClassicShatteredAsteroid",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "CrashedSatellites",
          "GlaciersLarge",
          "GeoDormant"
        ],
        "biomePaths": "IceCaves:133,144 130,132 111,119 93,129 88,139 96,159 117,164;155,134 144,120 140,120 130,132 133,144 139,146;151,168 139,146 133,144 117,164 118,168 136,178;171,155 162,135 155,134 139,146 151,168 155,169;173,127 171,104 163,99 159,100 144,120 155,134 162,135;144,120 159,100 142,87 129,92 127,108 140,120;167,187 155,169 151,168 136,178 135,189 145,201 152,201;140,120 127,108 111,116 111,119 130,132;113,207 119,192 111,180 96,180 88,194 96,208;118,168 117,164 96,159 91,167 96,180 111,180;145,201 135,189 119,192 113,207 120,218 134,218;88,194 96,180 91,167 73,166 64,178 73,194;135,189 136,178 118,168 111,180 119,192;194,145 190,131 173,127 162,135 171,155 184,157;98,97 99,91 88,74 76,74 66,90 79,105;111,119 111,116 98,97 79,105 77,115 93,129;127,108 129,92 118,84 99,91 98,97 111,116;96,159 88,139 74,139 65,149 73,166 91,167;74,139 69,120 54,118 43,131 53,148 65,149;77,115 79,105 66,90 55,91 48,102 54,118 69,120;73,166 65,149 53,148 41,162 53,178 64,178;88,139 93,129 77,115 69,120 74,139\nFrozenWastes:212,170 217,158 211,148 194,145 184,157 190,173 196,175;221,130 211,116 202,116 190,131 194,145 211,148;226,187 212,170 196,175 200,201 212,204;142,87 146,70 128,59 118,66 118,84 129,92;118,84 118,66 98,59 88,74 99,91;88,222 96,208 88,194 73,194 64,208 75,223;111,236 120,218 113,207 96,208 88,222 98,236;64,208 73,194 64,178 53,178 41,193 52,208;190,100 196,87 183,73 171,75 163,99 171,104;163,99 171,75 157,65 146,70 142,87 159,100;55,91 45,74 29,74 23,83 29,102 48,102;53,148 43,131 29,130 24,135 28,162 41,162\nCarrotQuarry:202,116 190,100 171,104 173,127 190,131;190,173 184,157 171,155 155,169 167,187 168,187;200,201 196,175 190,173 168,187 186,209;240,157 217,158 212,170 226,187 240,187;184,215 186,209 168,187 167,187 152,201 165,221;211,229 218,216 212,204 200,201 186,209 184,215 194,230;138,246 144,233 134,218 120,218 111,236 120,247;161,231 165,221 152,201 145,201 134,218 144,233;240,102 240,74 216,75 211,84 222,102;53,178 41,162 28,162 28,162 24,188 29,193 41,193;54,118 48,102 29,102 23,109 29,130 43,131\nOcean:240,102 222,102 211,116 221,130 240,131;240,187 226,187 212,204 218,216 240,217;89,251 98,236 88,222 75,223 66,238 77,252;19,242 28,223 22,215 0,214 0,242;28,162 28,162 24,135 0,135 0,162\nRadioactive:240,157 240,131 221,130 211,148 217,158;240,243 240,217 218,216 211,229 219,244;66,238 75,223 64,208 52,208 43,223 54,238;29,130 23,109 0,110 0,135 24,135;28,162 0,162 0,188 24,188\nSugarWoods:128,59 129,45 106,36 96,49 98,59 118,66;157,65 160,48 136,39 129,45 128,59 146,70;222,102 211,84 196,87 190,100 202,116 211,116;88,74 98,59 96,49 77,44 65,58 76,74;183,73 190,57 186,50 164,45 160,48 157,65 171,75;240,74 240,49 217,49 210,59 216,75;211,84 216,75 210,59 190,57 183,73 196,87;66,90 76,74 65,58 55,58 45,74 55,91;45,74 55,58 47,47 27,48 22,56 29,74;23,83 29,74 22,56 0,56 0,83;23,109 29,102 23,83 0,83 0,110\nMagmaCore:240,299 240,271 219,271 211,287 219,300;219,271 211,258 197,258 187,276 194,287 211,287;118,295 123,278 112,267 101,268 93,280 101,299 107,301;172,275 161,260 148,261 140,275 152,292 160,293;187,303 194,287 187,276 172,275 160,293 168,304;152,292 140,275 123,278 118,295 137,306;67,299 75,280 68,270 53,270 44,286 55,301;101,299 93,280 75,280 67,299 80,311;44,286 53,270 42,257 31,257 21,273 29,287;29,287 21,273 0,273 0,300 22,300\nOilField:240,299 219,300 212,314 221,328 240,329;212,314 219,300 211,287 194,287 187,303 195,316;189,329 195,316 187,303 168,304 162,322 175,333;205,348 189,329 175,333 170,352 186,362;138,318 137,306 118,295 107,301 110,324 121,328;110,324 107,301 101,299 80,311 80,322 96,331;170,352 175,333 162,322 149,325 146,349 159,356;124,349 121,328 110,324 96,331 96,350 106,356;55,301 44,286 29,287 22,300 29,314 48,316;106,356 96,350 79,356 80,380 106,380;38,347 53,326 48,316 29,314 21,327 32,347;25,354 0,354 0,380 28,380\nRust:187,244 194,230 184,215 165,221 161,231 170,245;29,193 24,188 0,188 0,214 22,215;52,208 41,193 29,193 22,215 28,223 43,223\nSandstone:240,355 240,329 221,328 208,349 214,355;221,328 212,314 195,316 189,329 205,348 208,349;186,362 170,352 159,356 157,380 187,380;96,350 96,331 80,322 68,330 71,352 79,356;21,327 29,314 22,300 0,300 0,328\nForest:240,355 214,355 213,380 240,380;214,355 208,349 205,348 186,362 187,380 213,380;159,356 146,349 132,353 133,380 157,380;146,349 149,325 138,318 121,328 124,349 132,353;162,322 168,304 160,293 152,292 137,306 138,318 149,325;80,322 80,311 67,299 55,301 48,316 53,326 68,330;71,352 68,330 53,326 38,347 52,359;52,359 38,347 32,347 25,354 28,380 50,380;32,347 21,327 0,328 0,354 25,354;79,356 71,352 52,359 50,380 80,380;132,353 124,349 106,356 106,380 133,380",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 110,
            "y": 143
          },
          {
            "id": "WarpConduitSender",
            "x": 182,
            "y": 237
          },
          {
            "id": "WarpConduitReceiver",
            "x": 153,
            "y": 91
          },
          {
            "id": "MassiveHeatSink",
            "x": 42,
            "y": 158
          },
          {
            "id": "MassiveHeatSink",
            "x": 208,
            "y": 164
          },
          {
            "id": "MassiveHeatSink",
            "x": 49,
            "y": 89
          },
          {
            "id": "GravitasPedestal",
            "x": 29,
            "y": 176
          },
          {
            "id": "WarpReceiver",
            "x": 193,
            "y": 73
          },
          {
            "id": "WarpPortal",
            "x": 192,
            "y": 67
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 124,
            "y": 50
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 205,
            "y": 112
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 59,
            "y": 65
          },
          {
            "id": "GeneShuffler",
            "x": 103,
            "y": 212
          }
        ],
        "geysers": [
          {
            "id": "slush_salt_water",
            "x": 218,
            "y": 200,
            "emitRate": 5326,
            "avgEmitRate": 1597,
            "idleTime": 299,
            "eruptionTime": 367,
            "dormancyCycles": 90,
            "activeCycles": 107.4
          },
          {
            "id": "OilWell",
            "x": 173,
            "y": 349,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 43,
            "y": 321,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 105,
            "y": 323,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "slush_water",
            "x": 151,
            "y": 347,
            "emitRate": 6397,
            "avgEmitRate": 1375,
            "idleTime": 503,
            "eruptionTime": 277,
            "dormancyCycles": 44.2,
            "activeCycles": 67.7
          },
          {
            "id": "small_volcano",
            "x": 218,
            "y": 117,
            "emitRate": 122129,
            "avgEmitRate": 592,
            "idleTime": 7215,
            "eruptionTime": 57,
            "dormancyCycles": 53.4,
            "activeCycles": 86.1
          },
          {
            "id": "molten_iron",
            "x": 177,
            "y": 123,
            "emitRate": 7598,
            "avgEmitRate": 313,
            "idleTime": 671,
            "eruptionTime": 51,
            "dormancyCycles": 56.5,
            "activeCycles": 80.5
          },
          {
            "id": "steam",
            "x": 78,
            "y": 347,
            "emitRate": 5994,
            "avgEmitRate": 1222,
            "idleTime": 391,
            "eruptionTime": 196,
            "dormancyCycles": 55.1,
            "activeCycles": 86.5
          },
          {
            "id": "molten_aluminum",
            "x": 178,
            "y": 365,
            "emitRate": 9963,
            "avgEmitRate": 338,
            "idleTime": 741,
            "eruptionTime": 43,
            "dormancyCycles": 40.7,
            "activeCycles": 64.6
          },
          {
            "id": "hot_po2",
            "x": 144,
            "y": 211,
            "emitRate": 408,
            "avgEmitRate": 114,
            "idleTime": 391,
            "eruptionTime": 300,
            "dormancyCycles": 54.9,
            "activeCycles": 98.4
          },
          {
            "id": "oil_drip",
            "x": 112,
            "y": 362,
            "emitRate": 310,
            "avgEmitRate": 169,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.3,
            "activeCycles": 0.3
          },
          {
            "id": "hot_co2",
            "x": 15,
            "y": 220,
            "emitRate": 291,
            "avgEmitRate": 103,
            "idleTime": 201,
            "eruptionTime": 297,
            "dormancyCycles": 47.5,
            "activeCycles": 70.2
          },
          {
            "id": "slush_salt_water",
            "x": 64,
            "y": 189,
            "emitRate": 4158,
            "avgEmitRate": 1440,
            "idleTime": 297,
            "eruptionTime": 386,
            "dormancyCycles": 47.6,
            "activeCycles": 75.2
          }
        ]
      },
      {
        "id": "MediumSwampy",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "BouldersMixed",
          "MagmaVents"
        ],
        "biomePaths": "Swamp:81,114 86,95 76,88 64,92 63,108 77,115;97,93 101,82 92,69 78,72 76,88 86,95;76,88 78,72 67,63 64,63 51,81 52,84 64,92;63,108 64,92 52,84 39,99 41,106 51,114;107,105 97,93 86,95 81,114 97,120;95,136 100,127 97,120 81,114 77,115 73,133 79,139;73,133 77,115 63,108 51,114 51,122 61,134;124,116 116,105 107,105 97,120 100,127 117,130\nBoggyMarsh:136,69 140,62 135,48 119,47 113,59 120,71;92,69 95,60 90,48 76,46 67,63 78,72;67,63 76,46 73,41 53,37 48,43 47,52 64,63;160,107 160,84 142,84 136,93 143,107;47,52 48,43 27,35 19,45 23,55 38,60\nToxicJungle:113,59 119,47 115,40 98,37 90,48 95,60;160,62 160,41 141,41 135,48 140,62;41,106 39,99 23,91 18,94 18,117 22,120;14,69 23,55 19,45 0,43 0,69\nWasteland:115,80 120,71 113,59 95,60 92,69 101,82;116,105 123,93 115,80 101,82 97,93 107,105;136,93 142,84 136,69 120,71 115,80 123,93;52,84 51,81 39,74 24,82 23,91 39,99;64,63 47,52 38,60 39,74 51,81;137,116 143,107 136,93 123,93 116,105 124,116;51,122 51,114 41,106 22,120 25,127 35,134;23,91 24,82 14,69 0,69 0,92 18,94;18,117 18,94 0,92 0,118;102,152 95,136 79,139 77,154 91,162;25,127 22,120 18,117 0,118 0,145 10,145;77,154 79,139 73,133 61,134 54,147 56,153 67,159;141,152 138,140 121,137 113,153 116,157 138,157;56,153 54,147 36,139 27,153 42,167;39,74 38,60 23,55 14,69 24,82\nFrozenWastes:160,84 160,62 140,62 136,69 142,84;144,131 137,116 124,116 117,130 121,137 138,140;160,107 143,107 137,116 144,131 160,131\nBarren:121,137 117,130 100,127 95,136 102,152 113,153\nMagmaCore:116,157 113,153 102,152 91,162 92,176 114,176;27,153 36,139 35,134 25,127 10,145 21,154;160,131 144,131 138,140 141,152 160,153;21,154 10,145 0,145 0,176 13,176;67,159 56,153 42,167 42,176 67,176;61,134 51,122 35,134 36,139 54,147;138,157 116,157 114,176 140,176;160,153 141,152 138,157 140,176 160,176;91,162 77,154 67,159 67,176 92,176;42,167 27,153 21,154 13,176 42,176",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 145,
            "y": 119
          },
          {
            "id": "WarpConduitSender",
            "x": 113,
            "y": 115
          },
          {
            "id": "WarpConduitReceiver",
            "x": 48,
            "y": 108
          },
          {
            "id": "WarpReceiver",
            "x": 85,
            "y": 97
          },
          {
            "id": "WarpPortal",
            "x": 63,
            "y": 97
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 19,
            "y": 94,
            "emitRate": 5504,
            "avgEmitRate": 1342,
            "idleTime": 353,
            "eruptionTime": 260,
            "dormancyCycles": 45.3,
            "activeCycles": 61.5
          },
          {
            "id": "methane",
            "x": 23,
            "y": 113,
            "emitRate": 297,
            "avgEmitRate": 104,
            "idleTime": 185,
            "eruptionTime": 250,
            "dormancyCycles": 30,
            "activeCycles": 46.2
          },
          {
            "id": "steam",
            "x": 77,
            "y": 64,
            "emitRate": 4030,
            "avgEmitRate": 1169,
            "idleTime": 234,
            "eruptionTime": 314,
            "dormancyCycles": 34.5,
            "activeCycles": 35.4
          },
          {
            "id": "slush_salt_water",
            "x": 32,
            "y": 63,
            "emitRate": 2807,
            "avgEmitRate": 1280,
            "idleTime": 101,
            "eruptionTime": 480,
            "dormancyCycles": 60.4,
            "activeCycles": 74.3
          },
          {
            "id": "molten_aluminum",
            "x": 42,
            "y": 71,
            "emitRate": 8511,
            "avgEmitRate": 247,
            "idleTime": 582,
            "eruptionTime": 34,
            "dormancyCycles": 39,
            "activeCycles": 43
          },
          {
            "id": "molten_copper",
            "x": 66,
            "y": 77,
            "emitRate": 6242,
            "avgEmitRate": 289,
            "idleTime": 680,
            "eruptionTime": 62,
            "dormancyCycles": 72.2,
            "activeCycles": 91.1
          },
          {
            "id": "molten_iron",
            "x": 107,
            "y": 135,
            "emitRate": 12561,
            "avgEmitRate": 324,
            "idleTime": 712,
            "eruptionTime": 32,
            "dormancyCycles": 43.2,
            "activeCycles": 66.3
          },
          {
            "id": "liquid_co2",
            "x": 41,
            "y": 92,
            "emitRate": 1147,
            "avgEmitRate": 145,
            "idleTime": 482,
            "eruptionTime": 146,
            "dormancyCycles": 53,
            "activeCycles": 63.4
          },
          {
            "id": "liquid_co2",
            "x": 142,
            "y": 69,
            "emitRate": 457,
            "avgEmitRate": 137,
            "idleTime": 396,
            "eruptionTime": 490,
            "dormancyCycles": 70,
            "activeCycles": 82.4
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "CrashedSatellites"
        ],
        "biomePaths": "FrozenWastes:50,45 49,32 41,26 31,36 38,50 43,50;44,73 50,64 43,50 38,50 30,56 32,70 37,74;38,50 31,36 21,35 16,48 19,53 30,56;21,35 18,31 0,31 0,46 16,48;12,66 19,53 16,48 0,46 0,66;32,70 30,56 19,53 12,66 19,74;64,27 49,32 50,45 64,47;16,86 19,74 12,66 0,66 0,86;37,74 32,70 19,74 16,86 20,90 33,91;64,63 64,47 50,45 43,50 50,64;20,90 16,86 0,86 0,105 16,107;34,109 37,95 33,91 20,90 16,107 16,107 31,111;47,94 54,84 44,73 37,74 33,91 37,95;64,84 64,63 50,64 44,73 54,84;16,107 16,107 0,105 0,128 13,128;55,107 47,94 37,95 34,109 47,114;47,114 34,109 31,111 30,128 48,128;31,111 16,107 13,128 30,128;64,107 55,107 47,114 48,128 64,128;64,84 54,84 47,94 55,107 64,107",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 47,
            "y": 40
          },
          {
            "id": "GravitasPedestal",
            "x": 27,
            "y": 88
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 18,
            "y": 67
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 33,
            "y": 48
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 41,
            "y": 62
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 42,
            "y": 111,
            "emitRate": 7199,
            "avgEmitRate": 282,
            "idleTime": 732,
            "eruptionTime": 48,
            "dormancyCycles": 53.2,
            "activeCycles": 92.9
          },
          {
            "id": "molten_iron",
            "x": 53,
            "y": 61,
            "emitRate": 11962,
            "avgEmitRate": 348,
            "idleTime": 721,
            "eruptionTime": 39,
            "dormancyCycles": 63.7,
            "activeCycles": 84.4
          },
          {
            "id": "molten_iron",
            "x": 37,
            "y": 90,
            "emitRate": 7310,
            "avgEmitRate": 355,
            "idleTime": 662,
            "eruptionTime": 48,
            "dormancyCycles": 29,
            "activeCycles": 72.6
          },
          {
            "id": "molten_iron",
            "x": 48,
            "y": 73,
            "emitRate": 8037,
            "avgEmitRate": 271,
            "idleTime": 721,
            "eruptionTime": 42,
            "dormancyCycles": 38.1,
            "activeCycles": 60.9
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "RadioactiveCrust"
        ],
        "biomePaths": "BoggyMarsh:43,56 45,53 44,36 33,34 25,40 34,56;34,56 25,40 19,40 13,56 24,65;64,52 64,37 48,34 44,36 45,53;24,71 24,65 13,56 0,58 0,75 16,77;13,56 19,40 15,36 0,37 0,58;46,76 49,73 43,56 34,56 24,65 24,71 34,78\nToxicJungle:64,72 64,52 45,53 43,56 49,73\nMagmaCore:16,77 0,75 0,96 17,96;34,78 24,71 16,77 17,96 32,96;64,72 49,73 46,76 52,96 64,96;46,76 34,78 32,96 52,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 46
          },
          {
            "id": "GravitasPedestal",
            "x": 30,
            "y": 46
          },
          {
            "id": "SapTree",
            "x": 37,
            "y": 46
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 37,
            "y": 87,
            "emitRate": 8427,
            "avgEmitRate": 285,
            "idleTime": 812,
            "eruptionTime": 48,
            "dormancyCycles": 56,
            "activeCycles": 87.5
          },
          {
            "id": "molten_tungsten",
            "x": 29,
            "y": 90,
            "emitRate": 7731,
            "avgEmitRate": 307,
            "idleTime": 749,
            "eruptionTime": 52,
            "dormancyCycles": 45.8,
            "activeCycles": 72.9
          },
          {
            "id": "molten_tungsten",
            "x": 14,
            "y": 87,
            "emitRate": 8745,
            "avgEmitRate": 257,
            "idleTime": 626,
            "eruptionTime": 35,
            "dormancyCycles": 68.6,
            "activeCycles": 84.3
          },
          {
            "id": "methane",
            "x": 8,
            "y": 62,
            "emitRate": 300,
            "avgEmitRate": 101,
            "idleTime": 311,
            "eruptionTime": 348,
            "dormancyCycles": 58.2,
            "activeCycles": 103
          },
          {
            "id": "methane",
            "x": 54,
            "y": 63,
            "emitRate": 570,
            "avgEmitRate": 94,
            "idleTime": 521,
            "eruptionTime": 243,
            "dormancyCycles": 60.1,
            "activeCycles": 65.2
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:43,44 42,36 31,31 20,39 21,45 33,51;21,45 20,39 16,37 0,39 0,55 13,56;9,76 17,64 13,56 0,55 0,76;29,79 27,66 17,64 9,76 16,83;45,77 45,66 33,62 27,66 29,79 31,81;49,63 49,49 43,44 33,51 33,62 45,66;64,45 64,35 47,31 42,36 43,44 49,49;64,45 49,49 49,63 64,65;16,83 9,76 0,76 0,96 15,96;64,77 64,65 49,63 45,66 45,77 48,79;31,81 29,79 16,83 15,96 32,96\nOilField:33,62 33,51 21,45 13,56 17,64 27,66;48,79 45,77 31,81 32,96 48,96;64,77 48,79 48,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 32,
            "y": 59
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 17,
            "y": 56,
            "emitRate": 278950,
            "avgEmitRate": 1253,
            "idleTime": 8470,
            "eruptionTime": 62,
            "dormancyCycles": 46.1,
            "activeCycles": 75
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:49,41 46,34 34,30 23,42 24,44 40,51;61,59 58,44 49,41 40,51 41,60 49,64;76,58 80,44 77,39 69,37 58,44 61,59 65,61;41,60 40,51 24,44 19,60 32,66;19,60 24,44 23,42 18,40 0,43 0,58 16,61;96,61 96,45 80,44 76,58 82,63\nFrozenWastes:82,63 76,58 65,61 66,80 80,80;65,61 61,59 49,64 50,80 66,80;16,61 0,58 0,80 14,80;49,64 41,60 32,66 32,80 50,80;96,61 82,63 80,80 96,80;32,66 19,60 16,61 14,80 32,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 20,
            "y": 46
          },
          {
            "id": "GravitasPedestal",
            "x": 13,
            "y": 46
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 84,
            "y": 46,
            "emitRate": 401,
            "avgEmitRate": 100,
            "idleTime": 190,
            "eruptionTime": 163,
            "dormancyCycles": 56.2,
            "activeCycles": 65.9
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:68,55 62,44 52,44 47,51 52,63 62,63;67,75 62,63 52,63 46,70 51,82 60,83;80,35 68,36 62,44 68,55 80,55;80,55 68,55 62,63 67,75 80,76;51,82 46,70 36,70 30,80 33,89 43,92;52,44 47,33 38,32 30,40 35,51 47,51;52,63 47,51 35,51 30,60 36,70 46,70;80,76 67,75 60,83 65,94 80,95;80,95 65,94 59,103 63,114 80,114;65,94 60,83 51,82 43,92 46,101 59,103;60,120 63,114 59,103 46,101 40,110 44,121;61,138 64,133 60,120 44,121 41,126 48,139;80,132 80,114 63,114 60,120 64,133;30,60 35,51 30,40 19,38 17,40 15,58 18,60;36,70 30,60 18,60 16,78 30,80;16,78 18,60 15,58 0,59 0,77 15,79;44,146 48,139 41,126 31,128 27,141 32,147;41,126 44,121 40,110 25,107 19,117 20,119 31,128;80,153 66,153 61,158 63,174 80,174;15,58 17,40 0,38 0,59;46,101 43,92 33,89 23,100 25,107 40,110;25,107 23,100 15,96 0,100 0,112 19,117;27,141 31,128 20,119 10,133 17,142;40,167 29,157 19,162 17,174 41,174;19,162 11,154 0,155 0,174 17,174;61,158 49,157 40,167 41,174 63,174;15,96 15,79 0,77 0,100;20,119 19,117 0,112 0,133 10,133;33,89 30,80 16,78 15,79 15,96 23,100\nSwamp:66,153 61,138 48,139 44,146 49,157 61,158;80,132 64,133 61,138 66,153 80,153;49,157 44,146 32,147 29,157 40,167;29,157 32,147 27,141 17,142 11,154 19,162;11,154 17,142 10,133 0,133 0,155",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 45,
            "y": 158
          },
          {
            "id": "GravitasPedestal",
            "x": 47,
            "y": 158
          }
        ],
        "geysers": [
          {
            "id": "filthy_water",
            "x": 39,
            "y": 72,
            "emitRate": 8344,
            "avgEmitRate": 2824,
            "idleTime": 281,
            "eruptionTime": 320,
            "dormancyCycles": 53.2,
            "activeCycles": 92.9
          },
          {
            "id": "slush_water",
            "x": 25,
            "y": 91,
            "emitRate": 5388,
            "avgEmitRate": 1819,
            "idleTime": 357,
            "eruptionTime": 378,
            "dormancyCycles": 48.7,
            "activeCycles": 93.3
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:78,73 84,64 78,53 69,54 65,65 73,73;96,64 96,47 82,47 78,53 84,64;33,61 34,51 29,47 17,50 14,59 20,66;19,75 20,66 14,59 0,61 0,76 12,79;38,78 39,67 33,61 20,66 19,75 26,80;96,64 84,64 78,73 85,81 96,81\nSandstone:65,65 69,54 63,48 51,51 51,64 55,67;55,82 55,67 51,64 39,67 38,78 40,81 55,82;51,64 51,51 47,47 34,51 33,61 39,67;73,73 65,65 55,67 55,82 67,82\nFrozenWastes:85,81 78,73 73,73 67,82 74,96 78,96;40,81 38,78 26,80 25,96 38,96;96,81 85,81 78,96 96,96;26,80 19,75 12,79 12,96 25,96;67,82 55,82 55,82 54,96 74,96;55,82 40,81 38,96 54,96;12,79 0,76 0,96 12,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 71,
            "y": 72
          },
          {
            "id": "GravitasPedestal",
            "x": 75,
            "y": 72
          },
          {
            "id": "GeneShuffler",
            "x": 73,
            "y": 66
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 32,
            "y": 84,
            "emitRate": 2524,
            "avgEmitRate": 691,
            "idleTime": 205,
            "eruptionTime": 282,
            "dormancyCycles": 68.9,
            "activeCycles": 61.7
          },
          {
            "id": "steam",
            "x": 18,
            "y": 82,
            "emitRate": 5580,
            "avgEmitRate": 1601,
            "idleTime": 360,
            "eruptionTime": 316,
            "dormancyCycles": 24.7,
            "activeCycles": 39.2
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "CeresClassicShatteredAsteroid",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumSwampy",
        "q": 0,
        "r": -3
      },
      {
        "id": "TundraMoonlet",
        "q": 2,
        "r": 3
      },
      {
        "id": "MarshyMoonlet",
        "q": -6,
        "r": 3
      },
      {
        "id": "NiobiumMoonlet",
        "q": 5,
        "r": -5
      },
      {
        "id": "MooMoonlet",
        "q": -3,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": 7,
        "r": -1
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": -5,
        "r": -2
      },
      {
        "id": "TemporalTear",
        "q": 7,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_DLC2CeresOreField",
        "q": -1,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 1,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 10,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 10,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 5,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 5,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 6,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -3,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 1,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -7,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -6,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 0,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 0,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 5,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -11,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": -9,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 1,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 0,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 0,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -1,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -3,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -8,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 11,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 10,
        "r": -3
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation6",
        "q": -3,
        "r": 1
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 10,
        "r": 1
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": -6,
        "r": 10
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 5,
        "r": -11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": -11,
        "r": 8
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": -10,
        "r": 11
      }
    ]
  },
  {
    "coordinate": "M-SWMP-C-254563355-0-0-0",
    "cluster": "M-SWMP-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "MiniMetallicSwampyStart",
        "offsetX": 212,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "RadioactiveCrust",
          "MetalPoor"
        ],
        "biomePaths": "Swamp:84,101 89,93 90,77 81,59 63,52 43,58 34,73 35,93 48,108 62,111;63,52 63,39 62,37 43,38 37,49 43,58;112,81 105,72 90,77 89,93 106,96;43,58 37,49 23,49 17,63 22,71 34,73\nBoggyMarsh:87,53 88,43 81,35 63,39 63,52 81,59;110,58 109,39 105,36 88,43 87,53 107,61;105,72 107,61 87,53 81,59 90,77;128,36 109,39 110,58 128,60;89,116 84,101 62,111 67,124;106,112 110,104 106,96 89,93 84,101 89,116 91,117;48,108 35,93 23,97 20,108 23,114 43,115;128,103 110,104 106,112 115,128 128,129;23,49 18,41 0,41 0,62 17,63;66,129 67,124 62,111 48,108 43,115 45,129 61,133;13,86 22,71 17,63 0,62 0,86;35,93 34,73 22,71 13,86 23,97\nMetallic:128,81 128,60 110,58 107,61 105,72 112,81;128,103 128,81 112,81 106,96 110,104;115,128 106,112 91,117 92,132 106,138;92,132 91,117 89,116 67,124 66,129 83,140;45,129 43,115 23,114 19,130 20,132 39,134;23,114 20,108 0,108 0,129 19,130;20,108 23,97 13,86 0,86 0,108\nMagmaCore:106,138 92,132 83,140 82,153 108,153;128,129 115,128 106,138 108,153 128,153;39,134 20,132 18,153 41,153;61,133 45,129 39,134 41,153 60,153;83,140 66,129 61,133 60,153 82,153;20,132 19,130 0,129 0,153 18,153",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 61,
            "y": 82
          },
          {
            "id": "WarpConduitReceiver",
            "x": 52,
            "y": 114
          },
          {
            "id": "WarpConduitSender",
            "x": 78,
            "y": 126
          },
          {
            "id": "GravitasPedestal",
            "x": 15,
            "y": 125
          },
          {
            "id": "WarpReceiver",
            "x": 77,
            "y": 113
          },
          {
            "id": "WarpPortal",
            "x": 77,
            "y": 108
          }
        ],
        "geysers": [
          {
            "id": "methane",
            "x": 93,
            "y": 63,
            "emitRate": 234,
            "avgEmitRate": 88,
            "idleTime": 220,
            "eruptionTime": 330,
            "dormancyCycles": 46.7,
            "activeCycles": 79.2
          },
          {
            "id": "molten_cobalt",
            "x": 114,
            "y": 99,
            "emitRate": 8584,
            "avgEmitRate": 316,
            "idleTime": 890,
            "eruptionTime": 50,
            "dormancyCycles": 34.9,
            "activeCycles": 76.9
          },
          {
            "id": "molten_gold",
            "x": 95,
            "y": 127,
            "emitRate": 8912,
            "avgEmitRate": 325,
            "idleTime": 662,
            "eruptionTime": 41,
            "dormancyCycles": 40.4,
            "activeCycles": 68.9
          },
          {
            "id": "molten_gold",
            "x": 13,
            "y": 92,
            "emitRate": 6991,
            "avgEmitRate": 260,
            "idleTime": 709,
            "eruptionTime": 47,
            "dormancyCycles": 42.7,
            "activeCycles": 63.9
          },
          {
            "id": "molten_iron",
            "x": 114,
            "y": 54,
            "emitRate": 7761,
            "avgEmitRate": 295,
            "idleTime": 653,
            "eruptionTime": 47,
            "dormancyCycles": 71.8,
            "activeCycles": 92.8
          },
          {
            "id": "molten_copper",
            "x": 92,
            "y": 111,
            "emitRate": 8165,
            "avgEmitRate": 301,
            "idleTime": 930,
            "eruptionTime": 60,
            "dormancyCycles": 30.4,
            "activeCycles": 47.8
          },
          {
            "id": "hot_po2",
            "x": 28,
            "y": 110,
            "emitRate": 345,
            "avgEmitRate": 95,
            "idleTime": 295,
            "eruptionTime": 306,
            "dormancyCycles": 60.8,
            "activeCycles": 71.4
          },
          {
            "id": "molten_gold",
            "x": 13,
            "y": 59,
            "emitRate": 7484,
            "avgEmitRate": 281,
            "idleTime": 607,
            "eruptionTime": 42,
            "dormancyCycles": 45.7,
            "activeCycles": 64.6
          }
        ]
      },
      {
        "id": "MiniBadlands",
        "offsetX": 82,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "ToxicJungle:93,68 86,55 70,56 67,66 81,81;110,59 105,43 91,45 86,55 93,68 103,69;128,38 107,40 105,43 110,59 128,60;22,56 24,48 16,39 0,39 0,60;45,65 46,49 37,44 24,48 22,56 28,69;67,66 70,56 64,46 57,44 46,49 45,65 53,71;128,79 128,60 110,59 103,69 109,81;107,131 106,129 87,134 87,153 108,153;128,107 118,107 105,118 106,129 107,131 128,131\nOilField:28,69 22,56 0,60 0,76 25,77;90,109 94,94 80,84 69,93 68,105 83,113;118,107 105,91 94,94 90,109 105,118;81,129 83,113 68,105 60,110 59,128 65,132;87,134 81,129 65,132 65,153 87,153;45,105 45,89 27,84 21,94 25,108 39,110;41,130 39,110 25,108 18,115 21,133 22,134\nSandstone:80,84 81,81 67,66 53,71 52,84 69,93;52,84 53,71 45,65 28,69 25,77 27,84 45,89;68,105 69,93 52,84 45,89 45,105 60,110;128,79 109,81 105,91 118,107 128,107;106,129 105,118 90,109 83,113 81,129 87,134;59,128 60,110 45,105 39,110 41,130 45,132;65,132 59,128 45,132 46,153 65,153;105,91 109,81 103,69 93,68 81,81 80,84 94,94;21,133 18,115 0,114 0,135;27,84 25,77 0,76 0,93 21,94;25,108 21,94 0,93 0,114 18,115;22,134 21,133 0,135 0,153 23,153;128,131 107,131 108,153 128,153;45,132 41,130 22,134 23,153 46,153",
        "pointsOfInterest": [],
        "geysers": [
          {
            "id": "OilWell",
            "x": 84,
            "y": 95,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 36,
            "y": 108,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 19,
            "y": 120,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 37,
            "y": 127,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 65,
            "y": 111,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "steam",
            "x": 25,
            "y": 59,
            "emitRate": 6278,
            "avgEmitRate": 1388,
            "idleTime": 339,
            "eruptionTime": 240,
            "dormancyCycles": 41.8,
            "activeCycles": 47.9
          },
          {
            "id": "methane",
            "x": 100,
            "y": 131,
            "emitRate": 366,
            "avgEmitRate": 98,
            "idleTime": 295,
            "eruptionTime": 239,
            "dormancyCycles": 57.6,
            "activeCycles": 85.2
          },
          {
            "id": "hot_co2",
            "x": 78,
            "y": 117,
            "emitRate": 265,
            "avgEmitRate": 98,
            "idleTime": 403,
            "eruptionTime": 518,
            "dormancyCycles": 24.5,
            "activeCycles": 47.7
          },
          {
            "id": "big_volcano",
            "x": 43,
            "y": 85,
            "emitRate": 259189,
            "avgEmitRate": 1201,
            "idleTime": 7821,
            "eruptionTime": 61,
            "dormancyCycles": 47.1,
            "activeCycles": 71
          },
          {
            "id": "slimy_po2",
            "x": 98,
            "y": 57,
            "emitRate": 357,
            "avgEmitRate": 140,
            "idleTime": 269,
            "eruptionTime": 313,
            "dormancyCycles": 24.9,
            "activeCycles": 66.2
          }
        ]
      },
      {
        "id": "MiniForestFrozenWarp",
        "offsetX": 342,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "BouldersMixed"
        ],
        "biomePaths": "Forest:78,97 93,82 90,60 75,49 55,51 42,65 48,90 59,97;75,49 77,41 66,30 52,41 55,51;103,95 97,84 93,82 78,97 82,107 88,109;55,51 52,41 44,38 37,41 33,56 42,65 42,65\nRust:94,58 99,42 89,34 77,41 75,49 90,60;82,107 78,97 59,97 57,110 69,117;111,73 109,63 94,58 90,60 93,82 97,84;128,83 128,82 111,73 97,84 103,95 107,97;48,90 42,65 42,65 27,78 39,93;57,110 59,97 48,90 39,93 35,99 40,112 48,116;33,56 37,41 22,34 13,44 20,57;42,65 33,56 20,57 14,67 22,78 27,78;114,110 107,97 103,95 88,109 93,117 107,119;128,52 120,52 109,63 111,73 128,82;71,129 69,117 57,110 48,116 49,131 61,135;49,131 48,116 40,112 25,123 24,129 41,137;86,132 93,117 88,109 82,107 69,117 71,129 84,134;15,111 20,100 14,90 0,89 0,113\nToxicJungle:120,52 108,39 99,42 94,58 109,63;128,83 107,97 114,110 128,111;35,99 39,93 27,78 22,78 14,90 20,100;14,67 20,57 13,44 0,43 0,67;40,112 35,99 20,100 15,111 25,123;128,111 114,110 107,119 111,132 128,133;111,132 107,119 93,117 86,132 105,139;22,78 14,67 0,67 0,89 14,90;24,129 25,123 15,111 0,113 0,132 20,133\nMagmaCore:84,134 71,129 61,135 63,153 81,153;61,135 49,131 41,137 40,153 63,153;20,133 0,132 0,153 20,153;128,133 111,132 105,139 106,153 128,153;41,137 24,129 20,133 20,153 40,153;105,139 86,132 84,134 81,153 106,153",
        "pointsOfInterest": [
          {
            "id": "WarpConduitReceiver",
            "x": 106,
            "y": 74
          },
          {
            "id": "WarpConduitSender",
            "x": 101,
            "y": 90
          },
          {
            "id": "GravitasPedestal",
            "x": 86,
            "y": 115
          },
          {
            "id": "WarpReceiver",
            "x": 75,
            "y": 78
          },
          {
            "id": "WarpPortal",
            "x": 53,
            "y": 78
          },
          {
            "id": "PropSurfaceSatellite3",
            "x": 53,
            "y": 59
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 17,
            "y": 109,
            "emitRate": 340,
            "avgEmitRate": 93,
            "idleTime": 357,
            "eruptionTime": 324,
            "dormancyCycles": 44.3,
            "activeCycles": 59.9
          },
          {
            "id": "slush_water",
            "x": 107,
            "y": 109,
            "emitRate": 5174,
            "avgEmitRate": 1626,
            "idleTime": 232,
            "eruptionTime": 190,
            "dormancyCycles": 36.3,
            "activeCycles": 84.1
          },
          {
            "id": "methane",
            "x": 64,
            "y": 120,
            "emitRate": 258,
            "avgEmitRate": 82,
            "idleTime": 247,
            "eruptionTime": 267,
            "dormancyCycles": 63.3,
            "activeCycles": 98.9
          },
          {
            "id": "slimy_po2",
            "x": 29,
            "y": 61,
            "emitRate": 386,
            "avgEmitRate": 104,
            "idleTime": 288,
            "eruptionTime": 244,
            "dormancyCycles": 27.6,
            "activeCycles": 39.4
          },
          {
            "id": "hot_po2",
            "x": 11,
            "y": 124,
            "emitRate": 289,
            "avgEmitRate": 107,
            "idleTime": 268,
            "eruptionTime": 350,
            "dormancyCycles": 49.2,
            "activeCycles": 93.1
          }
        ]
      },
      {
        "id": "MiniFlipped",
        "offsetX": 472,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "Geodes"
        ],
        "biomePaths": "MagmaCore:90,59 88,38 83,35 67,41 67,56 83,65;109,59 108,35 106,33 88,38 90,59 105,62;128,33 108,35 109,59 128,60;83,73 83,65 67,56 55,64 56,75 72,83;67,56 67,41 60,35 43,41 44,58 55,64;44,58 43,41 39,38 22,41 22,61 29,67;22,61 22,41 19,39 0,40 0,66\nWasteland:100,86 107,76 105,62 90,59 83,65 83,73 99,86;128,60 109,59 105,62 107,76 128,78;72,93 72,83 56,75 47,83 47,93 64,100;89,110 86,99 72,93 64,100 64,111 79,119;56,75 55,64 44,58 29,67 30,74 47,83;30,74 29,67 22,61 0,66 0,76 23,82;47,93 47,83 30,74 23,82 23,95 40,99;20,114 20,98 0,97 0,119;64,111 64,100 47,93 40,99 41,111 54,119;128,94 110,97 106,114 128,117;99,86 83,73 72,83 72,93 86,99\nFrozenWastes:106,114 110,97 100,86 99,86 86,99 89,110 105,115;128,94 128,78 107,76 100,86 110,97;128,134 128,117 106,114 105,115 103,132 105,135;23,95 23,82 0,76 0,97 20,98;54,129 54,119 41,111 27,121 28,128 40,136;103,132 105,115 89,110 79,119 80,130 83,133;80,130 79,119 64,111 54,119 54,129 61,136;28,128 27,121 20,114 0,119 0,131 19,136;41,111 40,99 23,95 20,98 20,114 27,121\nSandstone:19,136 0,131 0,153 20,153;105,135 103,132 83,133 83,153 104,153;128,134 105,135 104,153 128,153;61,136 54,129 40,136 40,153 61,153;40,136 28,128 19,136 20,153 40,153;83,133 80,130 61,136 61,153 83,153",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 43,
            "y": 121
          },
          {
            "id": "MassiveHeatSink",
            "x": 103,
            "y": 126
          },
          {
            "id": "MassiveHeatSink",
            "x": 75,
            "y": 125
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 69,
            "y": 53,
            "emitRate": 4350,
            "avgEmitRate": 1616,
            "idleTime": 259,
            "eruptionTime": 358,
            "dormancyCycles": 22.4,
            "activeCycles": 39.9
          },
          {
            "id": "steam",
            "x": 47,
            "y": 77,
            "emitRate": 4715,
            "avgEmitRate": 1086,
            "idleTime": 158,
            "eruptionTime": 179,
            "dormancyCycles": 57.9,
            "activeCycles": 44.3
          },
          {
            "id": "hot_hydrogen",
            "x": 16,
            "y": 85,
            "emitRate": 341,
            "avgEmitRate": 92,
            "idleTime": 344,
            "eruptionTime": 294,
            "dormancyCycles": 65.2,
            "activeCycles": 92.3
          },
          {
            "id": "hot_po2",
            "x": 18,
            "y": 131,
            "emitRate": 300,
            "avgEmitRate": 96,
            "idleTime": 319,
            "eruptionTime": 355,
            "dormancyCycles": 49.1,
            "activeCycles": 75
          }
        ]
      },
      {
        "id": "MiniRadioactiveOcean",
        "offsetX": 602,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "DistressSignal",
          "MetalRich"
        ],
        "biomePaths": "Forest:128,54 128,41 104,37 98,46 100,55 109,61;110,80 109,61 100,55 86,67 87,78 105,84;100,107 104,104 105,84 87,78 81,85 82,98 100,107;47,95 37,87 22,94 22,111 41,112;128,54 109,61 110,80 128,82;37,87 37,75 25,66 22,67 10,85 22,94;22,111 22,94 10,85 0,86 0,112 21,112;10,85 22,67 1,53 0,53 0,86\nOcean:100,55 98,46 79,41 72,47 72,58 86,67;72,58 72,47 52,40 45,48 52,66 59,68;82,98 81,85 63,81 55,93 69,108;55,93 63,81 59,68 52,66 37,75 37,87 47,95;68,112 69,108 55,93 47,95 41,112 48,121;52,66 45,48 33,48 25,66 37,75;25,66 33,48 24,39 1,53 22,67;87,78 86,67 72,58 59,68 63,81 81,85\nRadioactive:100,107 82,98 69,108 68,112 78,127 80,127;128,82 110,80 105,84 104,104 128,104;78,127 68,112 48,121 47,128 58,139;128,127 128,104 104,104 100,107 110,128;110,128 100,107 100,107 80,127 84,133 105,135;47,128 48,121 41,112 22,111 21,112 22,131 30,138;22,131 21,112 0,112 0,138\nMagmaCore:128,127 110,128 105,135 108,153 128,153;105,135 84,133 83,153 108,153;58,139 47,128 30,138 31,153 59,153;30,138 22,131 0,138 0,153 31,153;84,133 80,127 78,127 58,139 59,153 83,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 72,
            "y": 98
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 25,
            "y": 116,
            "emitRate": 413,
            "avgEmitRate": 138,
            "idleTime": 202,
            "eruptionTime": 320,
            "dormancyCycles": 76.3,
            "activeCycles": 91.2
          },
          {
            "id": "salt_water",
            "x": 53,
            "y": 70,
            "emitRate": 8950,
            "avgEmitRate": 2829,
            "idleTime": 90,
            "eruptionTime": 97,
            "dormancyCycles": 55.1,
            "activeCycles": 85.9
          },
          {
            "id": "hot_steam",
            "x": 105,
            "y": 54,
            "emitRate": 3705,
            "avgEmitRate": 750,
            "idleTime": 566,
            "eruptionTime": 315,
            "dormancyCycles": 45.8,
            "activeCycles": 59.9
          },
          {
            "id": "filthy_water",
            "x": 108,
            "y": 109,
            "emitRate": 8847,
            "avgEmitRate": 3274,
            "idleTime": 288,
            "eruptionTime": 389,
            "dormancyCycles": 33.8,
            "activeCycles": 61.1
          },
          {
            "id": "slush_water",
            "x": 21,
            "y": 61,
            "emitRate": 4632,
            "avgEmitRate": 1271,
            "idleTime": 254,
            "eruptionTime": 252,
            "dormancyCycles": 67.2,
            "activeCycles": 82.3
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 732,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "SubsurfaceOcean"
        ],
        "biomePaths": "Ocean:64,46 50,47 46,57 53,65 64,65;46,57 50,47 43,37 29,47 31,56 35,59;43,37 43,37 32,26 24,29 21,42 29,47;21,42 24,29 16,24 1,34 16,44;11,57 16,44 1,34 0,34 0,58\nFrozenWastes:64,84 64,65 53,65 46,76 50,84;53,65 46,57 35,59 35,74 46,76;35,74 35,59 31,56 17,62 18,72 32,76;46,92 50,84 46,76 35,74 32,76 30,89 36,95;30,89 32,76 18,72 12,77 18,92;31,56 29,47 21,42 16,44 11,57 17,62;64,103 64,84 50,84 46,92 55,104;64,103 55,104 49,111 60,128 64,128;16,94 18,92 12,77 0,77 0,96;18,72 17,62 11,57 0,58 0,77 12,77;20,108 16,94 0,96 0,110 14,112;34,106 36,95 30,89 18,92 16,94 20,108 26,110;43,112 34,106 26,110 29,128 38,128;14,112 0,110 0,128 15,128;49,111 43,112 38,128 60,128;26,110 20,108 14,112 15,128 29,128;49,111 55,104 46,92 36,95 34,106 43,112",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 37,
            "y": 52
          },
          {
            "id": "GravitasPedestal",
            "x": 52,
            "y": 102
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 17,
            "y": 115,
            "emitRate": 9840,
            "avgEmitRate": 312,
            "idleTime": 600,
            "eruptionTime": 34,
            "dormancyCycles": 63.3,
            "activeCycles": 90
          },
          {
            "id": "molten_iron",
            "x": 48,
            "y": 116,
            "emitRate": 9277,
            "avgEmitRate": 342,
            "idleTime": 821,
            "eruptionTime": 46,
            "dormancyCycles": 32.6,
            "activeCycles": 74.2
          },
          {
            "id": "molten_iron",
            "x": 19,
            "y": 98,
            "emitRate": 8409,
            "avgEmitRate": 379,
            "idleTime": 755,
            "eruptionTime": 50,
            "dormancyCycles": 44.2,
            "activeCycles": 117.5
          },
          {
            "id": "molten_iron",
            "x": 28,
            "y": 74,
            "emitRate": 8868,
            "avgEmitRate": 378,
            "idleTime": 692,
            "eruptionTime": 47,
            "dormancyCycles": 57.5,
            "activeCycles": 114.5
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 798,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "BoggyMarsh:34,49 33,33 17,33 16,35 17,48 33,50;52,41 47,33 35,32 33,33 34,49 46,50;31,65 33,50 17,48 15,51 17,66;64,60 64,42 52,41 46,50 51,60;46,67 51,60 46,50 34,49 33,50 31,65 34,68;64,60 51,60 46,67 52,78 64,80;17,48 16,35 0,34 0,50 15,51\nToxicJungle:52,78 46,67 34,68 32,79 43,87;32,79 34,68 31,65 17,66 16,67 17,80 22,85;17,66 15,51 0,50 0,67 16,67;17,80 16,67 0,67 0,84\nMagmaCore:43,87 32,79 22,85 22,96 44,96;22,85 17,80 0,84 0,96 22,96;64,80 52,78 43,87 44,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 31,
            "y": 41
          },
          {
            "id": "GravitasPedestal",
            "x": 17,
            "y": 41
          },
          {
            "id": "SapTree",
            "x": 24,
            "y": 41
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 29,
            "y": 68,
            "emitRate": 8896,
            "avgEmitRate": 282,
            "idleTime": 706,
            "eruptionTime": 40,
            "dormancyCycles": 57.6,
            "activeCycles": 81.2
          },
          {
            "id": "molten_tungsten",
            "x": 50,
            "y": 91,
            "emitRate": 6008,
            "avgEmitRate": 285,
            "idleTime": 769,
            "eruptionTime": 66,
            "dormancyCycles": 43.6,
            "activeCycles": 64.9
          },
          {
            "id": "hot_co2",
            "x": 9,
            "y": 61,
            "emitRate": 409,
            "avgEmitRate": 107,
            "idleTime": 345,
            "eruptionTime": 263,
            "dormancyCycles": 65.8,
            "activeCycles": 99.9
          },
          {
            "id": "slimy_po2",
            "x": 53,
            "y": 66,
            "emitRate": 392,
            "avgEmitRate": 107,
            "idleTime": 442,
            "eruptionTime": 455,
            "dormancyCycles": 49.3,
            "activeCycles": 57.2
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 864,
        "offsetY": 98,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:71,47 72,45 63,32 51,35 50,47 55,51;54,63 55,51 50,47 37,50 34,61 40,67;75,62 71,47 55,51 54,63 60,68;50,47 51,35 47,31 34,33 30,41 37,50;96,58 96,53 78,43 72,45 71,47 75,62 78,64;96,31 84,32 78,43 96,53;19,65 21,62 16,49 0,49 0,65;16,49 20,42 14,32 0,33 0,49;34,61 37,50 30,41 20,42 16,49 21,62\nFrozenWastes:96,58 78,64 80,80 96,80;78,64 75,62 60,68 60,80 80,80;60,68 54,63 40,67 39,80 60,80;40,67 34,61 21,62 19,65 20,80 39,80;19,65 0,65 0,80 20,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 61,
            "y": 44
          },
          {
            "id": "GravitasPedestal",
            "x": 54,
            "y": 44
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 13,
            "y": 47,
            "emitRate": 373,
            "avgEmitRate": 99,
            "idleTime": 274,
            "eruptionTime": 218,
            "dormancyCycles": 46.5,
            "activeCycles": 68.5
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,84 80,66 65,65 61,75 70,85;61,75 65,65 62,59 53,56 45,59 43,72 53,77;70,85 61,75 53,77 51,94 62,97;71,46 63,38 53,38 53,38 53,56 62,59;80,46 71,46 62,59 65,65 80,66;39,73 24,67 19,72 18,85 33,89;51,94 53,77 43,72 39,73 33,89 34,90 49,95;43,72 45,59 35,51 24,56 24,67 39,73;45,112 49,95 34,90 29,106 42,114;29,106 34,90 33,89 18,85 15,87 15,104 21,108;60,117 66,103 62,97 51,94 49,95 45,112 60,117;80,84 70,85 62,97 66,103 80,104;53,56 53,38 37,38 35,51 45,59;24,67 24,56 18,52 0,59 0,67 19,72;18,85 19,72 0,67 0,86 15,87;35,51 37,38 35,36 20,34 16,39 18,52 24,56;18,52 16,39 0,37 0,59;80,131 80,122 60,117 60,117 57,133 60,137;57,133 60,117 45,112 42,114 37,125 43,133;37,125 42,114 29,106 21,108 19,121 23,127;43,133 37,125 23,127 20,139 21,141 38,145;51,156 50,155 40,152 31,161 34,174 47,174;64,161 51,156 47,174 66,174;19,121 21,108 15,104 0,106 0,123;15,104 15,87 0,86 0,106;23,127 19,121 0,123 0,137 20,139;80,104 66,103 60,117 80,122;80,153 73,153 64,161 66,174 80,174;17,157 17,156 0,154 0,174 14,174;31,161 17,157 14,174 34,174\nSwamp:61,141 60,137 57,133 43,133 38,145 40,152 50,155;73,153 61,141 50,155 51,156 64,161;80,153 80,131 60,137 61,141 73,153;40,152 38,145 21,141 17,156 17,157 31,161;21,141 20,139 0,137 0,154 17,156",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 63,
            "y": 159
          },
          {
            "id": "GravitasPedestal",
            "x": 65,
            "y": 159
          }
        ],
        "geysers": [
          {
            "id": "hot_water",
            "x": 39,
            "y": 108,
            "emitRate": 13824,
            "avgEmitRate": 3222,
            "idleTime": 326,
            "eruptionTime": 186,
            "dormancyCycles": 47.6,
            "activeCycles": 85.1
          },
          {
            "id": "salt_water",
            "x": 15,
            "y": 62,
            "emitRate": 7814,
            "avgEmitRate": 2446,
            "idleTime": 346,
            "eruptionTime": 342,
            "dormancyCycles": 41.4,
            "activeCycles": 70.4
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 798,
        "offsetY": 98,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:51,52 46,36 37,36 30,46 34,54 47,56;28,63 34,54 30,46 19,45 13,57 18,64;64,52 64,33 49,33 46,36 51,52;37,36 30,27 18,29 14,37 19,45 30,46;59,75 48,67 35,76 35,77 46,87;46,87 35,77 29,82 29,96 47,96;29,82 15,79 12,96 29,96;35,77 35,76 28,63 18,64 13,76 15,79 29,82;15,79 13,76 0,75 0,96 12,96;18,64 13,57 0,56 0,75 13,76;19,45 14,37 0,38 0,56 13,57\nOilField:64,75 64,52 51,52 47,56 48,67 59,75;48,67 47,56 34,54 28,63 35,76;64,75 59,75 46,87 47,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 42,
            "y": 69
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 55,
            "y": 62,
            "emitRate": 269672,
            "avgEmitRate": 981,
            "idleTime": 8902,
            "eruptionTime": 63,
            "dormancyCycles": 58.1,
            "activeCycles": 63.2
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 864,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [
          "CrashedSatellites"
        ],
        "biomePaths": "Sandstone:109,58 105,49 90,49 86,60 95,69;69,81 73,76 71,67 53,66 49,76 53,82;74,62 68,50 57,49 50,59 53,66 71,67;95,74 95,69 86,60 74,62 71,67 73,76 87,80;145,60 141,49 127,48 123,58 137,69;137,74 137,69 123,58 116,61 116,76 124,81;31,74 36,56 35,55 20,53 14,62 21,75\nBarren:49,76 53,66 50,59 36,56 31,74 36,78;116,76 116,61 109,58 95,69 95,74 106,81;17,80 21,75 14,62 0,62 0,80;160,77 160,62 145,60 137,69 137,74 143,79\nFrozenWastes:36,78 31,74 21,75 17,80 20,96 34,96;17,80 0,80 0,96 20,96;143,79 137,74 124,81 125,96 142,96;124,81 116,76 106,81 105,96 125,96;106,81 95,74 87,80 88,96 105,96;160,77 143,79 142,96 160,96;69,81 53,82 52,96 71,96;87,80 73,76 69,81 71,96 88,96;53,82 49,76 36,78 34,96 52,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 109,
            "y": 79
          },
          {
            "id": "GravitasPedestal",
            "x": 113,
            "y": 79
          },
          {
            "id": "GeneShuffler",
            "x": 111,
            "y": 73
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 30,
            "y": 61
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 10,
            "y": 77
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 128,
            "y": 74
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 83,
            "y": 87,
            "emitRate": 1588,
            "avgEmitRate": 825,
            "idleTime": 279,
            "eruptionTime": 635,
            "dormancyCycles": 27.1,
            "activeCycles": 80.2
          },
          {
            "id": "hot_steam",
            "x": 27,
            "y": 80,
            "emitRate": 2250,
            "avgEmitRate": 829,
            "idleTime": 282,
            "eruptionTime": 353,
            "dormancyCycles": 49.8,
            "activeCycles": 97.7
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "MiniBadlands",
        "q": 0,
        "r": 1
      },
      {
        "id": "MiniMetallicSwampyStart",
        "q": 3,
        "r": 0
      },
      {
        "id": "MiniForestFrozenWarp",
        "q": 2,
        "r": -3
      },
      {
        "id": "MiniFlipped",
        "q": -1,
        "r": -1
      },
      {
        "id": "MiniRadioactiveOcean",
        "q": 1,
        "r": 3
      },
      {
        "id": "TundraMoonlet",
        "q": -3,
        "r": -4
      },
      {
        "id": "MarshyMoonlet",
        "q": -6,
        "r": 5
      },
      {
        "id": "MooMoonlet",
        "q": 6,
        "r": -7
      },
      {
        "id": "WaterMoonlet",
        "q": 8,
        "r": -3
      },
      {
        "id": "NiobiumMoonlet",
        "q": 11,
        "r": -8
      },
      {
        "id": "RegolithMoonlet",
        "q": 4,
        "r": 5
      },
      {
        "id": "TemporalTear",
        "q": 11,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -3,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -6,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -12,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -11,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -12,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -12,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -11,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -10,
        "r": 12
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 5,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 0,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": -1,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -2,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 2,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -7,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -7,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -8,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 11,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": 12,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 6,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 5,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 4,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 3,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -3,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": -6,
        "r": 12
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -10,
        "r": 7
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": 12,
        "r": 0
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": 4,
        "r": 2
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 8,
        "r": 1
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": 1,
        "r": 11
      }
    ]
  },
  {
    "coordinate": "M-FLIP-C-1342297923-0-0-0",
    "cluster": "M-FLIP-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "MiniFlippedStart",
        "offsetX": 212,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "BouldersMedium"
        ],
        "biomePaths": "Sandstone:83,130 86,119 78,109 66,110 61,116 63,129 75,135;103,114 102,102 85,99 78,109 86,119;63,129 61,116 43,115 41,117 42,132 51,138;101,140 83,130 75,135 75,153 100,153;128,138 108,134 101,140 100,153 128,153;25,140 17,131 0,133 0,153 24,153;75,135 63,129 51,138 51,153 75,153;51,138 42,132 25,140 24,153 51,153\nMagmaCore:96,51 96,41 76,30 65,42 71,61 79,63;108,77 108,60 96,51 79,63 84,81 104,82;71,61 65,42 50,38 41,46 48,69 59,70;48,69 41,46 29,44 18,64 23,74 40,76;29,44 24,36 0,36 0,62 18,64;128,54 128,38 103,32 96,41 96,51 108,60\nWasteland:81,85 84,81 79,63 71,61 59,70 64,86;128,54 108,60 108,77 128,79;60,92 64,86 59,70 48,69 40,76 43,93;17,85 23,74 18,64 0,62 0,86;42,95 43,93 40,76 23,74 17,85 23,96;128,79 108,77 104,82 106,96 128,98;106,96 104,82 84,81 81,85 85,99 102,102;128,113 128,98 106,96 102,102 103,114 107,117;78,109 85,99 81,85 64,86 60,92 66,110;43,115 42,95 23,96 18,108 23,115 41,117;23,96 17,85 0,86 0,108 18,108\nFrozenWastes:66,110 60,92 43,93 42,95 43,115 61,116;42,132 41,117 23,115 17,131 25,140;17,131 23,115 18,108 0,108 0,133;108,134 107,117 103,114 86,119 83,130 101,140;128,113 107,117 108,134 128,138",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 72,
            "y": 123
          },
          {
            "id": "MassiveHeatSink",
            "x": 48,
            "y": 111
          },
          {
            "id": "MassiveHeatSink",
            "x": 104,
            "y": 125
          },
          {
            "id": "MassiveHeatSink",
            "x": 28,
            "y": 135
          },
          {
            "id": "WarpConduitReceiver",
            "x": 48,
            "y": 91
          },
          {
            "id": "WarpConduitSender",
            "x": 64,
            "y": 95
          },
          {
            "id": "WarpReceiver",
            "x": 103,
            "y": 98
          },
          {
            "id": "WarpPortal",
            "x": 103,
            "y": 93
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 91,
            "y": 73,
            "emitRate": 5235,
            "avgEmitRate": 1350,
            "idleTime": 221,
            "eruptionTime": 174,
            "dormancyCycles": 63.5,
            "activeCycles": 89.6
          },
          {
            "id": "small_volcano",
            "x": 14,
            "y": 92,
            "emitRate": 120908,
            "avgEmitRate": 419,
            "idleTime": 10159,
            "eruptionTime": 71,
            "dormancyCycles": 57.3,
            "activeCycles": 57.8
          },
          {
            "id": "hot_co2",
            "x": 13,
            "y": 127,
            "emitRate": 632,
            "avgEmitRate": 106,
            "idleTime": 406,
            "eruptionTime": 142,
            "dormancyCycles": 48.6,
            "activeCycles": 88.2
          },
          {
            "id": "slimy_po2",
            "x": 26,
            "y": 92,
            "emitRate": 322,
            "avgEmitRate": 100,
            "idleTime": 278,
            "eruptionTime": 302,
            "dormancyCycles": 66.5,
            "activeCycles": 97.9
          }
        ]
      },
      {
        "id": "MiniBadlandsWarp",
        "offsetX": 82,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "LushCore",
          "MetalRich"
        ],
        "biomePaths": "Sandstone:78,96 85,76 75,56 73,54 54,51 39,57 34,63 30,82 38,98 45,104 66,104;110,85 109,62 109,62 100,62 89,75 107,88;30,82 34,63 18,59 10,66 20,83;69,119 66,104 45,104 43,120 62,127;115,109 105,94 89,103 91,117 107,120;105,94 107,88 89,75 85,76 78,96 89,103;39,135 40,123 23,118 17,133 25,143;91,117 89,103 78,96 66,104 69,119 86,122;20,111 21,107 13,91 0,92 0,112\nToxicJungle:91,51 95,40 83,31 73,37 73,54 75,56;112,42 106,37 95,40 91,51 100,62 109,62;128,40 112,42 109,62 109,62 128,63;73,37 60,31 53,37 54,51 73,54;54,51 53,37 39,33 32,40 39,57;43,120 45,104 38,98 21,107 20,111 23,118 40,123;39,57 32,40 19,40 16,43 18,59 34,63;38,98 30,82 20,83 13,91 21,107;62,134 62,127 43,120 40,123 39,135 51,145;89,75 100,62 91,51 75,56 85,76;18,59 16,43 0,42 0,66 10,66\nOilField:128,63 109,62 110,85 128,86;128,109 115,109 107,120 111,132 128,134;87,134 86,122 69,119 62,127 62,134 78,144;111,132 107,120 91,117 86,122 87,134 104,141;13,91 20,83 10,66 0,66 0,92;17,133 23,118 20,111 0,112 0,134;128,109 128,86 110,85 107,88 105,94 115,109\nForest:104,141 87,134 78,144 78,153 105,153;78,144 62,134 51,145 51,153 78,153;128,134 111,132 104,141 105,153 128,153;51,145 39,135 25,143 24,153 51,153;25,143 17,133 0,134 0,153 24,153",
        "pointsOfInterest": [
          {
            "id": "WarpConduitReceiver",
            "x": 76,
            "y": 54
          },
          {
            "id": "WarpConduitSender",
            "x": 112,
            "y": 78
          },
          {
            "id": "WarpPortal",
            "x": 43,
            "y": 81
          },
          {
            "id": "WarpReceiver",
            "x": 65,
            "y": 81
          },
          {
            "id": "GeneShuffler",
            "x": 46,
            "y": 111
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 111,
            "y": 121,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 90,
            "y": 128,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 106,
            "y": 130,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 17,
            "y": 116,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 108,
            "y": 95,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "steam",
            "x": 86,
            "y": 66,
            "emitRate": 3999,
            "avgEmitRate": 1473,
            "idleTime": 245,
            "eruptionTime": 367,
            "dormancyCycles": 48.9,
            "activeCycles": 77.7
          },
          {
            "id": "chlorine_gas",
            "x": 109,
            "y": 56,
            "emitRate": 279,
            "avgEmitRate": 107,
            "idleTime": 177,
            "eruptionTime": 308,
            "dormancyCycles": 51.8,
            "activeCycles": 77.7
          },
          {
            "id": "hot_co2",
            "x": 82,
            "y": 105,
            "emitRate": 334,
            "avgEmitRate": 106,
            "idleTime": 344,
            "eruptionTime": 470,
            "dormancyCycles": 51.4,
            "activeCycles": 63.1
          },
          {
            "id": "filthy_water",
            "x": 16,
            "y": 60,
            "emitRate": 16275,
            "avgEmitRate": 3511,
            "idleTime": 460,
            "eruptionTime": 240,
            "dormancyCycles": 57.7,
            "activeCycles": 97.9
          },
          {
            "id": "big_volcano",
            "x": 45,
            "y": 47,
            "emitRate": 294219,
            "avgEmitRate": 1358,
            "idleTime": 7727,
            "eruptionTime": 58,
            "dormancyCycles": 56.9,
            "activeCycles": 94.2
          }
        ]
      },
      {
        "id": "MiniMetallicSwampy",
        "offsetX": 342,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MagmaVents",
          "Volcanoes"
        ],
        "biomePaths": "Swamp:81,68 82,50 66,45 60,50 60,68 71,72;128,69 128,49 109,48 104,53 105,63 117,72;99,72 105,63 104,53 85,48 82,50 81,68 88,74;24,52 21,48 0,47 0,69 16,71\nMagmaCore:71,82 71,72 60,68 55,73 55,81 64,86;55,81 55,73 41,70 36,77 40,87;112,82 117,72 105,63 99,72 111,83;88,74 81,68 71,72 71,82 82,85;18,81 20,77 16,71 0,69 0,83;128,69 117,72 112,82 128,86;60,128 64,107 62,105 46,106 42,111 46,125 60,128;109,131 104,116 86,118 83,130 104,137;80,132 60,128 60,128 57,153 78,153;128,132 109,131 104,137 104,153 128,153;46,125 42,111 24,113 21,127 39,132;60,128 46,125 39,132 41,153 57,153;39,132 21,127 20,128 18,153 41,153;104,137 83,130 80,132 78,153 104,153;20,128 0,127 0,153 18,153;39,88 40,87 36,77 20,77 18,81 24,91\nBoggyMarsh:41,70 36,54 24,52 16,71 20,77 36,77;62,105 64,86 55,81 40,87 39,88 46,106;60,68 60,50 44,46 36,54 41,70 55,73;86,94 82,85 71,82 64,86 62,105 64,107 80,107;83,130 86,118 80,107 64,107 60,128 80,132;128,108 108,108 104,116 109,131 128,132\nMetallic:42,111 46,106 39,88 24,91 19,105 24,113;111,83 99,72 88,74 82,85 86,94 103,96;104,116 108,108 103,96 86,94 80,107 86,118;128,108 128,86 112,82 111,83 103,96 108,108;19,105 24,91 18,81 0,83 0,106;21,127 24,113 19,105 0,106 0,127 20,128",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 42,
            "y": 66
          }
        ],
        "geysers": [
          {
            "id": "methane",
            "x": 54,
            "y": 100,
            "emitRate": 380,
            "avgEmitRate": 101,
            "idleTime": 304,
            "eruptionTime": 280,
            "dormancyCycles": 75.4,
            "activeCycles": 92.8
          },
          {
            "id": "molten_cobalt",
            "x": 90,
            "y": 102,
            "emitRate": 10217,
            "avgEmitRate": 299,
            "idleTime": 730,
            "eruptionTime": 37,
            "dormancyCycles": 39.8,
            "activeCycles": 61.1
          },
          {
            "id": "molten_gold",
            "x": 111,
            "y": 105,
            "emitRate": 8194,
            "avgEmitRate": 341,
            "idleTime": 771,
            "eruptionTime": 55,
            "dormancyCycles": 26,
            "activeCycles": 43
          },
          {
            "id": "molten_cobalt",
            "x": 110,
            "y": 115,
            "emitRate": 10511,
            "avgEmitRate": 276,
            "idleTime": 745,
            "eruptionTime": 32,
            "dormancyCycles": 51.2,
            "activeCycles": 86.5
          },
          {
            "id": "molten_copper",
            "x": 70,
            "y": 112,
            "emitRate": 10751,
            "avgEmitRate": 407,
            "idleTime": 767,
            "eruptionTime": 40,
            "dormancyCycles": 34.6,
            "activeCycles": 112
          },
          {
            "id": "big_volcano",
            "x": 108,
            "y": 74,
            "emitRate": 274930,
            "avgEmitRate": 1139,
            "idleTime": 10208,
            "eruptionTime": 74,
            "dormancyCycles": 20.8,
            "activeCycles": 28
          },
          {
            "id": "big_volcano",
            "x": 62,
            "y": 77,
            "emitRate": 337470,
            "avgEmitRate": 1219,
            "idleTime": 9046,
            "eruptionTime": 60,
            "dormancyCycles": 57.6,
            "activeCycles": 70.7
          },
          {
            "id": "big_volcano",
            "x": 28,
            "y": 84,
            "emitRate": 267895,
            "avgEmitRate": 1236,
            "idleTime": 8979,
            "eruptionTime": 67,
            "dormancyCycles": 55.5,
            "activeCycles": 92.2
          },
          {
            "id": "big_volcano",
            "x": 45,
            "y": 78,
            "emitRate": 264272,
            "avgEmitRate": 1011,
            "idleTime": 8446,
            "eruptionTime": 64,
            "dormancyCycles": 68.6,
            "activeCycles": 71
          },
          {
            "id": "big_volcano",
            "x": 78,
            "y": 77,
            "emitRate": 263072,
            "avgEmitRate": 1098,
            "idleTime": 8388,
            "eruptionTime": 60,
            "dormancyCycles": 73.7,
            "activeCycles": 105.1
          }
        ]
      },
      {
        "id": "MiniForestFrozen",
        "offsetX": 472,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "CrashedSatellites"
        ],
        "biomePaths": "Forest:128,40 109,40 104,49 111,62 128,63;111,62 104,49 88,50 87,69 100,78;87,69 88,50 85,47 67,50 66,70 73,74;42,60 47,47 40,38 24,40 19,50 26,61;66,70 67,50 63,46 47,47 42,60 51,73;26,61 19,50 0,51 0,74 21,74\nRust:110,105 106,85 102,82 84,97 90,108 104,111;108,130 108,129 104,111 90,108 80,125 90,133;80,125 90,108 84,97 74,94 66,98 65,121 73,126;74,94 73,74 66,70 51,73 47,81 52,95 66,98;128,82 106,85 110,105 128,106;52,95 47,81 26,84 24,93 32,107 40,107;47,131 51,124 40,107 32,107 23,117 23,131 27,134;23,131 23,117 0,109 0,135\nToxicJungle:128,82 128,63 111,62 100,78 102,82 106,85;128,128 128,106 110,105 104,111 108,129;102,82 100,78 87,69 73,74 74,94 84,97;47,81 51,73 42,60 26,61 21,74 26,84;65,121 66,98 52,95 40,107 51,124;32,107 24,93 0,99 0,109 23,117;24,93 26,84 21,74 0,74 0,99\nMagmaCore:108,130 90,133 88,153 111,153;73,126 65,121 51,124 47,131 55,153 65,153;90,133 80,125 73,126 65,153 88,153;128,128 108,129 108,130 111,153 128,153;27,134 23,131 0,135 0,153 27,153;47,131 27,134 27,153 55,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 18,
            "y": 95
          },
          {
            "id": "PropSurfaceSatellite3",
            "x": 74,
            "y": 67
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 27,
            "y": 79
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 60,
            "y": 83
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 79,
            "y": 91
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 54,
            "y": 99,
            "emitRate": 195,
            "avgEmitRate": 90,
            "idleTime": 161,
            "eruptionTime": 630,
            "dormancyCycles": 67.7,
            "activeCycles": 92.4
          },
          {
            "id": "slush_water",
            "x": 82,
            "y": 106,
            "emitRate": 3980,
            "avgEmitRate": 1413,
            "idleTime": 159,
            "eruptionTime": 192,
            "dormancyCycles": 40.9,
            "activeCycles": 75.7
          },
          {
            "id": "methane",
            "x": 38,
            "y": 64,
            "emitRate": 334,
            "avgEmitRate": 98,
            "idleTime": 333,
            "eruptionTime": 256,
            "dormancyCycles": 55.8,
            "activeCycles": 117.1
          },
          {
            "id": "small_volcano",
            "x": 77,
            "y": 78,
            "emitRate": 110818,
            "avgEmitRate": 488,
            "idleTime": 9628,
            "eruptionTime": 77,
            "dormancyCycles": 49,
            "activeCycles": 60.8
          },
          {
            "id": "hot_co2",
            "x": 97,
            "y": 80,
            "emitRate": 387,
            "avgEmitRate": 101,
            "idleTime": 214,
            "eruptionTime": 177,
            "dormancyCycles": 39.5,
            "activeCycles": 53.6
          }
        ]
      },
      {
        "id": "MiniRadioactiveOcean",
        "offsetX": 602,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "Volcanoes",
          "Geodes"
        ],
        "biomePaths": "Forest:128,58 108,58 104,65 110,81 128,81;128,102 128,81 110,81 103,90 106,103 107,104;110,81 104,65 89,66 83,78 85,85 103,90;106,103 103,90 85,85 78,97 80,106 89,111;22,87 22,67 0,63 0,87\nOcean:104,65 108,58 102,45 93,42 80,52 81,58 89,66;70,70 81,58 80,52 68,45 55,51 54,62 64,71;54,62 55,51 46,45 28,52 28,61 44,70;78,97 85,85 83,78 70,70 64,71 56,81 57,92 62,97;39,86 46,77 44,70 28,61 22,67 22,87 22,87;44,107 46,95 39,86 22,87 22,108 23,109\nMagmaCore:89,66 81,58 70,70 83,78;64,71 54,62 44,70 46,77 56,81;57,92 56,81 46,77 39,86 46,95;61,106 62,97 57,92 46,95 44,107 46,109;80,106 78,97 62,97 61,106 68,112;128,130 112,129 106,133 107,153 128,153;106,133 91,129 84,134 84,153 107,153;40,132 23,127 20,130 19,153 39,153;20,130 0,128 0,153 19,153;61,133 46,128 40,132 39,153 60,153;84,134 67,128 61,133 60,153 84,153\nRadioactive:28,61 28,52 22,46 0,49 0,63 22,67;112,129 107,104 106,103 89,111 91,129 106,133;91,129 89,111 80,106 68,112 67,128 84,134;22,108 22,87 22,87 0,87 0,109;67,128 68,112 61,106 46,109 46,128 61,133;23,127 23,109 22,108 0,109 0,128 20,130;128,102 107,104 112,129 128,130;46,128 46,109 44,107 23,109 23,127 40,132",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 88,
            "y": 117
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 114,
            "y": 114,
            "emitRate": 563,
            "avgEmitRate": 146,
            "idleTime": 275,
            "eruptionTime": 223,
            "dormancyCycles": 44.9,
            "activeCycles": 61.6
          },
          {
            "id": "salt_water",
            "x": 25,
            "y": 65,
            "emitRate": 11219,
            "avgEmitRate": 2940,
            "idleTime": 325,
            "eruptionTime": 277,
            "dormancyCycles": 60.1,
            "activeCycles": 79.5
          },
          {
            "id": "slush_salt_water",
            "x": 88,
            "y": 91,
            "emitRate": 7196,
            "avgEmitRate": 2223,
            "idleTime": 322,
            "eruptionTime": 228,
            "dormancyCycles": 33.8,
            "activeCycles": 98.3
          },
          {
            "id": "filthy_water",
            "x": 110,
            "y": 99,
            "emitRate": 31808,
            "avgEmitRate": 2723,
            "idleTime": 502,
            "eruptionTime": 88,
            "dormancyCycles": 56,
            "activeCycles": 75.3
          },
          {
            "id": "hot_steam",
            "x": 17,
            "y": 96,
            "emitRate": 3371,
            "avgEmitRate": 737,
            "idleTime": 234,
            "eruptionTime": 126,
            "dormancyCycles": 44,
            "activeCycles": 73.2
          },
          {
            "id": "big_volcano",
            "x": 70,
            "y": 104,
            "emitRate": 282095,
            "avgEmitRate": 1618,
            "idleTime": 8434,
            "eruptionTime": 64,
            "dormancyCycles": 47.8,
            "activeCycles": 148.6
          },
          {
            "id": "big_volcano",
            "x": 48,
            "y": 87,
            "emitRate": 288287,
            "avgEmitRate": 1155,
            "idleTime": 9627,
            "eruptionTime": 61,
            "dormancyCycles": 60.6,
            "activeCycles": 105
          },
          {
            "id": "big_volcano",
            "x": 52,
            "y": 101,
            "emitRate": 360438,
            "avgEmitRate": 1286,
            "idleTime": 9861,
            "eruptionTime": 63,
            "dormancyCycles": 64.8,
            "activeCycles": 81.9
          },
          {
            "id": "big_volcano",
            "x": 80,
            "y": 69,
            "emitRate": 357842,
            "avgEmitRate": 1089,
            "idleTime": 8894,
            "eruptionTime": 47,
            "dormancyCycles": 56,
            "activeCycles": 75.3
          },
          {
            "id": "big_volcano",
            "x": 53,
            "y": 72,
            "emitRate": 335055,
            "avgEmitRate": 1311,
            "idleTime": 9291,
            "eruptionTime": 57,
            "dormancyCycles": 36.5,
            "activeCycles": 64.6
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 732,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "FrozenWastes:46,72 48,59 38,49 30,56 31,71 42,75;31,71 30,56 16,55 14,71 20,76;38,49 38,47 22,35 17,38 15,54 16,55 30,56;42,84 42,75 31,71 20,76 20,90 31,92;64,90 64,76 46,72 42,75 42,84 51,92;64,54 64,44 46,38 38,47 38,49 48,59;64,54 48,59 46,72 64,76;20,90 20,76 14,71 0,73 0,89 18,91;17,38 0,33 0,53 15,54;32,111 36,106 31,92 20,90 18,91 16,108 18,111;16,108 18,91 0,89 0,108;14,71 16,55 15,54 0,53 0,73;18,111 16,108 0,108 0,128 16,128;64,90 51,92 46,106 50,110 64,111;32,111 18,111 16,128 36,128;51,92 42,84 31,92 36,106 46,106;50,110 46,106 36,106 32,111 36,128 47,128;64,111 50,110 47,128 64,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 20,
            "y": 51
          },
          {
            "id": "GravitasPedestal",
            "x": 46,
            "y": 112
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 34,
            "y": 95,
            "emitRate": 12413,
            "avgEmitRate": 280,
            "idleTime": 667,
            "eruptionTime": 28,
            "dormancyCycles": 59.7,
            "activeCycles": 74.7
          },
          {
            "id": "molten_iron",
            "x": 50,
            "y": 89,
            "emitRate": 6956,
            "avgEmitRate": 214,
            "idleTime": 756,
            "eruptionTime": 46,
            "dormancyCycles": 52.1,
            "activeCycles": 61.3
          },
          {
            "id": "molten_iron",
            "x": 20,
            "y": 106,
            "emitRate": 8138,
            "avgEmitRate": 338,
            "idleTime": 803,
            "eruptionTime": 52,
            "dormancyCycles": 35.4,
            "activeCycles": 75.7
          },
          {
            "id": "molten_iron",
            "x": 40,
            "y": 73,
            "emitRate": 7271,
            "avgEmitRate": 298,
            "idleTime": 706,
            "eruptionTime": 47,
            "dormancyCycles": 45.4,
            "activeCycles": 87.3
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 798,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:41,53 44,46 43,39 31,33 21,37 21,44 34,54;64,32 49,31 43,39 44,46 64,48;64,61 64,48 44,46 41,53 48,63;34,54 21,44 12,54 17,64 27,64;21,44 21,37 17,34 0,36 0,52 12,54\nToxicJungle:44,74 48,63 41,53 34,54 27,64 33,75;64,79 64,61 48,63 44,74 49,79;30,80 33,75 27,64 17,64 10,74 17,82;10,74 17,64 12,54 0,52 0,74\nMagmaCore:49,79 44,74 33,75 30,80 34,96 46,96;64,79 49,79 46,96 64,96;30,80 17,82 14,96 34,96;17,82 10,74 0,74 0,96 14,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 39,
            "y": 48
          },
          {
            "id": "GravitasPedestal",
            "x": 25,
            "y": 48
          },
          {
            "id": "SapTree",
            "x": 32,
            "y": 48
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 56,
            "y": 59,
            "emitRate": 9301,
            "avgEmitRate": 324,
            "idleTime": 655,
            "eruptionTime": 38,
            "dormancyCycles": 39.7,
            "activeCycles": 69.2
          },
          {
            "id": "molten_tungsten",
            "x": 39,
            "y": 90,
            "emitRate": 7267,
            "avgEmitRate": 259,
            "idleTime": 603,
            "eruptionTime": 45,
            "dormancyCycles": 32.7,
            "activeCycles": 35
          },
          {
            "id": "molten_tungsten",
            "x": 6,
            "y": 91,
            "emitRate": 13468,
            "avgEmitRate": 335,
            "idleTime": 638,
            "eruptionTime": 26,
            "dormancyCycles": 53.4,
            "activeCycles": 91.8
          },
          {
            "id": "hot_hydrogen",
            "x": 43,
            "y": 59,
            "emitRate": 428,
            "avgEmitRate": 125,
            "idleTime": 301,
            "eruptionTime": 237,
            "dormancyCycles": 41.9,
            "activeCycles": 83.1
          },
          {
            "id": "hot_co2",
            "x": 12,
            "y": 66,
            "emitRate": 358,
            "avgEmitRate": 128,
            "idleTime": 203,
            "eruptionTime": 264,
            "dormancyCycles": 43.3,
            "activeCycles": 74.2
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 864,
        "offsetY": 98,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:85,32 71,24 65,33 71,46 80,46;80,63 87,54 80,46 71,46 63,55 63,59 70,65;71,46 65,33 53,34 50,45 63,55;63,59 63,55 50,45 43,49 40,60 51,67;96,54 96,29 85,32 80,46 87,54;50,45 53,34 47,27 33,31 30,36 31,41 43,49;40,60 43,49 31,41 20,53 21,58 34,64;21,58 20,53 7,42 0,43 0,63 15,63;31,41 30,36 16,33 7,42 20,53\nFrozenWastes:96,54 87,54 80,63 90,80 96,80;70,65 63,59 51,67 51,80 68,80;80,63 70,65 68,80 90,80;34,64 21,58 15,63 18,80 32,80;51,67 40,60 34,64 32,80 51,80;15,63 0,63 0,80 18,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 42,
            "y": 39
          },
          {
            "id": "GravitasPedestal",
            "x": 35,
            "y": 39
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 60,
            "y": 43,
            "emitRate": 285,
            "avgEmitRate": 104,
            "idleTime": 235,
            "eruptionTime": 344,
            "dormancyCycles": 36.4,
            "activeCycles": 57.6
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:52,84 48,68 45,67 34,71 30,83 35,90 44,91;61,105 67,97 62,85 52,84 44,91 51,106;28,104 35,90 30,83 18,83 12,95 19,104;45,67 43,52 35,48 22,59 22,61 34,71;61,64 64,54 56,44 43,52 45,67 48,68;30,83 34,71 22,61 11,73 18,83;45,112 51,106 44,91 35,90 28,104 36,113;56,44 56,39 42,29 33,38 35,48 43,52;71,74 61,64 48,68 52,84 62,85;80,51 80,37 62,33 56,39 56,44 64,54;80,74 80,51 64,54 61,64 71,74;67,118 61,105 51,106 45,112 54,128 59,128;80,97 67,97 61,105 67,118 80,118;54,128 45,112 36,113 30,124 38,135 45,135;80,74 71,74 62,85 67,97 80,97;35,48 33,38 19,34 14,51 22,59;30,124 36,113 28,104 19,104 13,117 20,125;12,95 18,83 11,73 0,72 0,95;13,117 19,104 12,95 0,95 0,118;80,139 80,118 67,118 59,128 67,139;30,146 38,135 30,124 20,125 15,139 21,146;22,61 22,59 14,51 0,52 0,72 11,73;20,125 13,117 0,118 0,138 15,139;14,51 19,34 19,33 0,32 0,52;80,161 59,154 55,156 55,174 80,174;55,156 54,155 37,157 29,174 55,174;16,157 0,160 0,174 28,174\nSwamp:59,154 67,139 59,128 54,128 45,135 54,155 55,156;54,155 45,135 38,135 30,146 37,157;80,139 67,139 59,154 80,161;16,157 21,146 15,139 0,138 0,160;37,157 30,146 21,146 16,157 28,174 29,174",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 56,
            "y": 153
          },
          {
            "id": "GravitasPedestal",
            "x": 58,
            "y": 153
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 22,
            "y": 75,
            "emitRate": 12358,
            "avgEmitRate": 2533,
            "idleTime": 297,
            "eruptionTime": 188,
            "dormancyCycles": 78.3,
            "activeCycles": 87.6
          },
          {
            "id": "slush_water",
            "x": 63,
            "y": 107,
            "emitRate": 4120,
            "avgEmitRate": 1094,
            "idleTime": 391,
            "eruptionTime": 369,
            "dormancyCycles": 69.4,
            "activeCycles": 83.6
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 798,
        "offsetY": 98,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "MagmaCore:48,48 51,34 47,30 35,30 33,33 34,47 42,51;34,47 33,33 19,33 15,43 21,52;64,34 51,34 48,48 64,54;15,43 19,33 15,28 0,28 0,45;41,64 42,51 34,47 21,52 20,60 34,68;20,60 21,52 15,43 0,45 0,59 19,61;14,77 19,61 0,59 0,78;32,79 34,68 20,60 19,61 14,77 20,83;50,80 48,68 41,64 34,68 32,79 41,87;64,62 48,68 50,80 64,84;64,84 50,80 41,87 41,96 64,96\nOilField:64,62 64,54 48,48 42,51 41,64 48,68;20,83 14,77 0,78 0,96 19,96;41,87 32,79 20,83 19,96 41,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 47,
            "y": 62
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 36,
            "y": 87,
            "emitRate": 291694,
            "avgEmitRate": 1207,
            "idleTime": 9680,
            "eruptionTime": 70,
            "dormancyCycles": 73.1,
            "activeCycles": 99
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 864,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:121,66 123,62 116,49 105,48 102,64 105,68;83,77 86,63 85,61 68,59 64,66 67,77 80,80;45,63 49,49 48,46 34,44 26,55 32,63;46,79 48,67 45,63 32,63 28,76 40,83;17,78 13,62 0,62 0,81;125,76 121,66 105,68 103,79 118,85;160,71 144,67 138,79 139,82 160,84\nSandstone:64,66 68,59 66,53 49,49 45,63 48,67;105,48 103,46 87,47 85,61 86,63 102,64;103,79 105,68 102,64 86,63 83,77 98,83;67,77 64,66 48,67 46,79 60,84;28,76 32,63 26,55 20,55 13,62 17,78 20,79;144,67 142,62 135,57 123,62 121,66 125,76 138,79;135,57 135,47 123,40 116,49 123,62\nFrozenWastes:60,84 46,79 40,83 40,96 61,96;80,80 67,77 60,84 61,96 79,96;40,83 28,76 20,79 20,96 40,96;20,79 17,78 0,81 0,96 20,96;98,83 83,77 80,80 79,96 98,96;139,82 138,79 125,76 118,85 119,96 137,96;160,84 139,82 137,96 160,96;118,85 103,79 98,83 98,96 119,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 105,
            "y": 64
          },
          {
            "id": "GravitasPedestal",
            "x": 109,
            "y": 64
          },
          {
            "id": "GeneShuffler",
            "x": 107,
            "y": 58
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 89,
            "y": 83,
            "emitRate": 4508,
            "avgEmitRate": 1640,
            "idleTime": 183,
            "eruptionTime": 265,
            "dormancyCycles": 42.2,
            "activeCycles": 67.3
          },
          {
            "id": "steam",
            "x": 71,
            "y": 82,
            "emitRate": 4233,
            "avgEmitRate": 1914,
            "idleTime": 156,
            "eruptionTime": 300,
            "dormancyCycles": 42.5,
            "activeCycles": 93
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "MiniBadlandsWarp",
        "q": 0,
        "r": -1
      },
      {
        "id": "MiniFlippedStart",
        "q": 4,
        "r": -2
      },
      {
        "id": "MiniMetallicSwampy",
        "q": -3,
        "r": 3
      },
      {
        "id": "MiniForestFrozen",
        "q": 2,
        "r": 1
      },
      {
        "id": "MiniRadioactiveOcean",
        "q": -3,
        "r": -1
      },
      {
        "id": "TundraMoonlet",
        "q": 8,
        "r": -7
      },
      {
        "id": "MarshyMoonlet",
        "q": -8,
        "r": 6
      },
      {
        "id": "MooMoonlet",
        "q": 1,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": -8,
        "r": -1
      },
      {
        "id": "NiobiumMoonlet",
        "q": -1,
        "r": 11
      },
      {
        "id": "RegolithMoonlet",
        "q": 6,
        "r": 5
      },
      {
        "id": "TemporalTear",
        "q": 9,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 0,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -6,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -11,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -11,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -10,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -6,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -6,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 9,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -11,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 5,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 5,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -2,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 3,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 3,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 2,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 1,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -4,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -5,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -3,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": -5,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": -4,
        "r": 12
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 12,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 9,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -9,
        "r": -3
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 0,
        "r": -12
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": 4,
        "r": -12
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": 12,
        "r": -1
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 3,
        "r": 8
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": -4,
        "r": -8
      }
    ]
  },
  {
    "coordinate": "SWMP-C-557407634-0-0-0",
    "cluster": "SWMP-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "SwampMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 274,
        "worldTraits": [
          "BouldersSmall",
          "SlimeSplats"
        ],
        "biomePaths": "Swamp:85,194 105,168 98,148 79,135 58,139 46,155 47,177 58,190 84,194;79,135 83,120 78,111 58,112 51,127 58,139;52,203 58,190 47,177 29,181 24,194 30,204;47,177 46,155 29,151 20,168 29,181\nWasteland:160,156 138,159 136,184 139,187 160,188;131,153 128,142 111,136 98,148 105,168 107,169;116,183 107,169 105,168 85,194 107,202;103,222 109,205 107,202 85,194 84,194 77,216 86,226;77,216 84,194 58,190 52,203 59,218\nToxicJungle:160,156 160,129 139,128 128,142 131,153 138,159;160,98 138,98 130,111 139,128 160,129;139,128 130,111 111,111 106,120 111,136 128,142;106,120 111,111 104,93 84,94 78,111 83,120;51,100 57,84 49,73 29,74 23,86 30,101;51,127 58,112 51,100 30,101 24,114 31,127;130,111 138,98 130,83 111,82 104,93 111,111;78,111 84,94 76,83 57,84 51,100 58,112;31,127 24,114 0,114 0,140 24,141;24,114 30,101 23,86 0,86 0,114;55,226 59,218 52,203 30,204 26,221 30,228;24,194 29,181 20,168 0,168 0,195;160,216 160,188 139,187 133,210 137,217;139,187 136,184 116,183 107,202 109,205 133,210;133,228 137,217 133,210 109,205 103,222 113,233;20,168 29,151 24,141 0,140 0,168;26,221 30,204 24,194 0,195 0,221\nSandstone:160,65 141,65 130,83 138,98 160,98;141,65 132,52 116,51 105,66 111,82 130,83;76,83 84,61 84,60 61,51 56,53 49,73 57,84;104,93 111,82 105,66 84,61 76,83 84,94;49,73 56,53 32,40 20,55 29,74;23,86 29,74 20,55 0,54 0,86\nFrozenWastes:111,136 106,120 83,120 79,135 98,148;138,159 131,153 107,169 116,183 136,184;58,139 51,127 31,127 24,141 29,151 46,155\nBarren:110,248 113,233 103,222 86,226 81,244 102,253;143,245 133,228 113,233 110,248 131,258;81,244 86,226 77,216 59,218 55,226 60,244 76,248;60,244 55,226 30,228 27,246 49,253;27,246 30,228 26,221 0,221 0,246 25,248;160,216 137,217 133,228 143,245 160,246\nMagmaCore:102,253 81,244 76,248 75,274 101,274;131,258 110,248 102,253 101,274 132,274;160,246 143,245 131,258 132,274 160,274;49,253 27,246 25,248 23,274 51,274;76,248 60,244 49,253 51,274 75,274;25,248 0,246 0,274 23,274",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 72,
            "y": 166
          },
          {
            "id": "MassiveHeatSink",
            "x": 132,
            "y": 182
          },
          {
            "id": "WarpConduitSender",
            "x": 47,
            "y": 224
          },
          {
            "id": "WarpConduitReceiver",
            "x": 25,
            "y": 102
          },
          {
            "id": "GravitasPedestal",
            "x": 17,
            "y": 177
          },
          {
            "id": "WarpPortal",
            "x": 127,
            "y": 115
          },
          {
            "id": "WarpReceiver",
            "x": 128,
            "y": 121
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 16,
            "y": 134,
            "emitRate": 5677,
            "avgEmitRate": 1479,
            "idleTime": 290,
            "eruptionTime": 251,
            "dormancyCycles": 54.7,
            "activeCycles": 70
          },
          {
            "id": "chlorine_gas",
            "x": 132,
            "y": 141,
            "emitRate": 353,
            "avgEmitRate": 108,
            "idleTime": 259,
            "eruptionTime": 275,
            "dormancyCycles": 58.6,
            "activeCycles": 85.5
          },
          {
            "id": "slush_water",
            "x": 91,
            "y": 124,
            "emitRate": 3835,
            "avgEmitRate": 1380,
            "idleTime": 214,
            "eruptionTime": 302,
            "dormancyCycles": 51.7,
            "activeCycles": 82.7
          },
          {
            "id": "slush_salt_water",
            "x": 53,
            "y": 142,
            "emitRate": 5153,
            "avgEmitRate": 1598,
            "idleTime": 349,
            "eruptionTime": 406,
            "dormancyCycles": 58.4,
            "activeCycles": 79.5
          },
          {
            "id": "small_volcano",
            "x": 35,
            "y": 242,
            "emitRate": 137136,
            "avgEmitRate": 473,
            "idleTime": 8332,
            "eruptionTime": 57,
            "dormancyCycles": 61.6,
            "activeCycles": 63.2
          },
          {
            "id": "small_volcano",
            "x": 18,
            "y": 243,
            "emitRate": 142468,
            "avgEmitRate": 744,
            "idleTime": 10318,
            "eruptionTime": 79,
            "dormancyCycles": 42.9,
            "activeCycles": 92.9
          },
          {
            "id": "small_volcano",
            "x": 21,
            "y": 230,
            "emitRate": 115176,
            "avgEmitRate": 499,
            "idleTime": 9211,
            "eruptionTime": 74,
            "dormancyCycles": 37.8,
            "activeCycles": 44.9
          },
          {
            "id": "liquid_co2",
            "x": 147,
            "y": 177,
            "emitRate": 415,
            "avgEmitRate": 186,
            "idleTime": 167,
            "eruptionTime": 286,
            "dormancyCycles": 31.5,
            "activeCycles": 77.6
          },
          {
            "id": "big_volcano",
            "x": 138,
            "y": 201,
            "emitRate": 244556,
            "avgEmitRate": 1020,
            "idleTime": 8884,
            "eruptionTime": 73,
            "dormancyCycles": 51.2,
            "activeCycles": 54.2
          },
          {
            "id": "chlorine_gas",
            "x": 123,
            "y": 227,
            "emitRate": 374,
            "avgEmitRate": 88,
            "idleTime": 356,
            "eruptionTime": 225,
            "dormancyCycles": 48.6,
            "activeCycles": 75.3
          }
        ]
      },
      {
        "id": "MetalHeavyLandingSite",
        "offsetX": 244,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalRich",
          "Geodes"
        ],
        "biomePaths": "Forest:107,54 112,46 106,35 90,34 85,42 92,54;83,68 92,54 85,42 76,43 69,56 80,68;80,94 73,78 62,79 54,92 67,102;73,78 80,68 69,56 60,57 53,67 62,79;53,67 60,57 50,43 46,42 36,49 41,68;66,114 67,102 54,92 47,93 44,97 46,114 62,117;54,92 62,79 53,67 41,68 39,71 47,93;92,110 84,95 80,94 67,102 66,114 84,119;39,71 41,68 36,49 24,48 17,60 26,72;17,60 24,48 19,40 0,39 0,60;128,45 112,46 107,54 114,67 128,68\nBoggyMarsh:114,67 107,54 92,54 83,68 98,83 101,84;44,97 47,93 39,71 26,72 17,87 26,99;22,113 26,99 17,87 0,88 0,111 21,114;17,87 26,72 17,60 0,60 0,88;128,68 114,67 101,84 108,93 128,94;128,117 128,94 108,93 101,110 108,119\nMetallic:98,83 83,68 80,68 73,78 80,94 84,95;46,114 44,97 26,99 22,113 40,120;108,93 101,84 98,83 84,95 92,110 101,110;128,117 108,119 105,134 128,139\nRadioactive:86,131 84,119 66,114 62,117 62,132 76,139;62,132 62,117 46,114 40,120 40,132 51,139;76,139 62,132 51,139 51,153 77,153;102,136 86,131 76,139 77,153 101,153;51,139 40,132 25,140 25,153 51,153;17,133 21,114 0,111 0,135;40,132 40,120 22,113 21,114 17,133 25,140;25,140 17,133 0,135 0,153 25,153;105,134 108,119 101,110 92,110 84,119 86,131 102,136\nFrozenWastes:128,139 105,134 102,136 101,153 128,153",
        "pointsOfInterest": [
          {
            "id": "PropSurfaceSatellite3",
            "x": 65,
            "y": 62
          }
        ],
        "geysers": [
          {
            "id": "molten_gold",
            "x": 28,
            "y": 111,
            "emitRate": 6923,
            "avgEmitRate": 288,
            "idleTime": 766,
            "eruptionTime": 56,
            "dormancyCycles": 40.6,
            "activeCycles": 63.3
          },
          {
            "id": "molten_aluminum",
            "x": 94,
            "y": 84,
            "emitRate": 8014,
            "avgEmitRate": 334,
            "idleTime": 925,
            "eruptionTime": 68,
            "dormancyCycles": 32.2,
            "activeCycles": 49.9
          },
          {
            "id": "molten_aluminum",
            "x": 102,
            "y": 103,
            "emitRate": 9837,
            "avgEmitRate": 271,
            "idleTime": 800,
            "eruptionTime": 38,
            "dormancyCycles": 54.5,
            "activeCycles": 86.2
          },
          {
            "id": "molten_gold",
            "x": 77,
            "y": 79,
            "emitRate": 9056,
            "avgEmitRate": 276,
            "idleTime": 730,
            "eruptionTime": 44,
            "dormancyCycles": 57.7,
            "activeCycles": 67.8
          },
          {
            "id": "liquid_co2",
            "x": 71,
            "y": 141,
            "emitRate": 332,
            "avgEmitRate": 126,
            "idleTime": 217,
            "eruptionTime": 389,
            "dormancyCycles": 82.8,
            "activeCycles": 120.2
          },
          {
            "id": "liquid_co2",
            "x": 18,
            "y": 139,
            "emitRate": 576,
            "avgEmitRate": 144,
            "idleTime": 355,
            "eruptionTime": 263,
            "dormancyCycles": 37.1,
            "activeCycles": 53
          },
          {
            "id": "small_volcano",
            "x": 39,
            "y": 92,
            "emitRate": 143952,
            "avgEmitRate": 615,
            "idleTime": 8934,
            "eruptionTime": 72,
            "dormancyCycles": 62.2,
            "activeCycles": 71
          },
          {
            "id": "small_volcano",
            "x": 36,
            "y": 124,
            "emitRate": 144432,
            "avgEmitRate": 595,
            "idleTime": 8349,
            "eruptionTime": 60,
            "dormancyCycles": 41.1,
            "activeCycles": 55.4
          },
          {
            "id": "filthy_water",
            "x": 13,
            "y": 44,
            "emitRate": 12696,
            "avgEmitRate": 2479,
            "idleTime": 537,
            "eruptionTime": 341,
            "dormancyCycles": 55.8,
            "activeCycles": 56.5
          }
        ]
      },
      {
        "id": "OilRichWarpTarget",
        "offsetX": 374,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "LushCore"
        ],
        "biomePaths": "Sandstone:75,80 72,63 62,60 51,71 62,86;128,61 113,61 107,76 110,82 128,83;113,61 106,53 90,59 94,74 107,76;128,83 110,82 105,97 107,100 128,102;110,82 107,76 94,74 84,83 87,94 105,97;94,74 90,59 84,55 72,63 75,80 84,83;103,114 107,100 105,97 87,94 81,105 87,115;128,118 128,102 107,100 103,114 107,119;81,105 87,94 84,83 75,80 62,86 61,91 69,105;61,91 62,86 51,71 43,72 36,85 44,97;41,106 44,97 36,85 24,86 18,97 25,109;21,116 25,109 18,97 0,97 0,118;128,61 128,38 107,42 106,53 113,61;18,97 24,86 16,74 0,75 0,97\nOcean:106,53 107,42 102,35 82,42 84,55 90,59;84,55 82,42 78,38 60,42 57,47 62,60 72,63;51,71 62,60 57,47 41,48 35,61 43,72;35,61 41,48 34,39 21,39 14,50 23,62;16,74 23,62 14,50 0,51 0,75\nOilField:105,134 107,119 103,114 87,115 82,129 86,134;82,129 87,115 81,105 69,105 62,115 68,129;68,129 62,115 47,115 42,130 45,134 63,135;47,115 41,106 25,109 21,116 26,129 42,130;128,118 107,119 105,134 106,135 128,136;26,129 21,116 0,118 0,135 22,136\nRust:62,115 69,105 61,91 44,97 41,106 47,115;36,85 43,72 35,61 23,62 16,74 24,86\nForest:86,134 82,129 68,129 63,135 66,153 83,153;106,135 105,134 86,134 83,153 106,153;63,135 45,134 43,153 66,153;128,136 106,135 106,153 128,153;45,134 42,130 26,129 22,136 24,153 43,153;22,136 0,135 0,153 24,153",
        "pointsOfInterest": [
          {
            "id": "WarpConduitSender",
            "x": 30,
            "y": 84
          },
          {
            "id": "WarpConduitReceiver",
            "x": 96,
            "y": 69
          },
          {
            "id": "WarpPortal",
            "x": 53,
            "y": 74
          },
          {
            "id": "WarpReceiver",
            "x": 75,
            "y": 74
          },
          {
            "id": "GeneShuffler",
            "x": 81,
            "y": 115
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 92,
            "y": 92,
            "emitRate": 4560,
            "avgEmitRate": 1603,
            "idleTime": 212,
            "eruptionTime": 263,
            "dormancyCycles": 50.3,
            "activeCycles": 87.3
          },
          {
            "id": "OilWell",
            "x": 25,
            "y": 115,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 91,
            "y": 127,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 69,
            "y": 126,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 102,
            "y": 131,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 26,
            "y": 125,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "liquid_sulfur",
            "x": 28,
            "y": 69,
            "emitRate": 7913,
            "avgEmitRate": 1251,
            "idleTime": 328,
            "eruptionTime": 129,
            "dormancyCycles": 73.9,
            "activeCycles": 93.8
          },
          {
            "id": "methane",
            "x": 116,
            "y": 127,
            "emitRate": 1130,
            "avgEmitRate": 121,
            "idleTime": 758,
            "eruptionTime": 152,
            "dormancyCycles": 40.8,
            "activeCycles": 72.7
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 162,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalPoor",
          "RadioactiveCrust"
        ],
        "biomePaths": "FrozenWastes:64,31 47,32 42,45 42,45 64,51;44,66 38,55 22,58 19,69 20,72 31,76;17,50 20,39 0,29 0,52;64,59 64,51 42,45 38,55 44,66 44,67;64,59 44,67 52,81 64,81;38,55 42,45 42,45 24,36 20,39 17,50 22,58;22,58 17,50 0,52 0,68 19,69;20,72 19,69 0,68 0,86 14,87;52,81 44,67 44,66 31,76 35,88 45,89;35,88 31,76 20,72 14,87 18,93 29,94;64,81 52,81 45,89 49,98 64,99;33,108 29,94 18,93 12,107 16,111 28,112;49,98 45,89 35,88 29,94 33,108 44,109;64,99 49,98 44,109 47,114 64,115;47,114 44,109 33,108 28,112 31,128 44,128;16,111 12,107 0,106 0,128 12,128;28,112 16,111 12,128 31,128;64,115 47,114 44,128 64,128;18,93 14,87 0,86 0,106 12,107",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 28,
            "y": 44
          },
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 117
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 19,
            "y": 100,
            "emitRate": 8563,
            "avgEmitRate": 299,
            "idleTime": 742,
            "eruptionTime": 45,
            "dormancyCycles": 44.5,
            "activeCycles": 71.8
          },
          {
            "id": "molten_iron",
            "x": 35,
            "y": 93,
            "emitRate": 8284,
            "avgEmitRate": 223,
            "idleTime": 690,
            "eruptionTime": 43,
            "dormancyCycles": 59.4,
            "activeCycles": 50.2
          },
          {
            "id": "molten_iron",
            "x": 48,
            "y": 111,
            "emitRate": 16837,
            "avgEmitRate": 340,
            "idleTime": 827,
            "eruptionTime": 27,
            "dormancyCycles": 37.6,
            "activeCycles": 66
          },
          {
            "id": "molten_iron",
            "x": 35,
            "y": 104,
            "emitRate": 7463,
            "avgEmitRate": 257,
            "idleTime": 595,
            "eruptionTime": 44,
            "dormancyCycles": 84.3,
            "activeCycles": 84.3
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 228,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:46,59 50,55 46,38 36,38 29,45 36,59;64,55 64,36 48,36 46,38 50,55;20,45 15,33 0,33 0,49 16,49;36,59 29,45 20,45 16,49 18,65 31,65;64,55 50,55 46,59 51,75 64,76;16,80 17,66 0,65 0,84;35,78 31,65 18,65 17,66 16,80 23,87\nToxicJungle:18,65 16,49 0,49 0,65 17,66;51,75 46,59 36,59 31,65 35,78 44,81\nMagmaCore:64,76 51,75 44,81 46,96 64,96;44,81 35,78 23,87 24,96 46,96;23,87 16,80 0,84 0,96 24,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 48,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 34,
            "y": 49
          },
          {
            "id": "SapTree",
            "x": 41,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 15,
            "y": 65,
            "emitRate": 6705,
            "avgEmitRate": 344,
            "idleTime": 744,
            "eruptionTime": 57,
            "dormancyCycles": 30.1,
            "activeCycles": 76.4
          },
          {
            "id": "molten_tungsten",
            "x": 48,
            "y": 90,
            "emitRate": 8725,
            "avgEmitRate": 306,
            "idleTime": 875,
            "eruptionTime": 53,
            "dormancyCycles": 67.9,
            "activeCycles": 106.9
          },
          {
            "id": "molten_tungsten",
            "x": 54,
            "y": 90,
            "emitRate": 8203,
            "avgEmitRate": 294,
            "idleTime": 773,
            "eruptionTime": 52,
            "dormancyCycles": 45.2,
            "activeCycles": 60.2
          },
          {
            "id": "hot_po2",
            "x": 29,
            "y": 73,
            "emitRate": 534,
            "avgEmitRate": 108,
            "idleTime": 372,
            "eruptionTime": 179,
            "dormancyCycles": 47.5,
            "activeCycles": 79.3
          },
          {
            "id": "hot_co2",
            "x": 41,
            "y": 70,
            "emitRate": 324,
            "avgEmitRate": 99,
            "idleTime": 344,
            "eruptionTime": 363,
            "dormancyCycles": 57.4,
            "activeCycles": 84
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 504,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:70,39 64,29 50,30 46,36 51,47 65,47;68,60 65,47 51,47 45,58 49,63 64,65;51,47 46,36 36,37 29,45 35,58 45,58;19,45 13,37 0,37 0,58 12,58;35,58 29,45 19,45 12,58 17,63 30,64;89,53 78,39 70,39 65,47 68,60 79,61\nFrozenWastes:49,63 45,58 35,58 30,64 33,80 45,80;96,53 89,53 79,61 84,80 96,80;64,65 49,63 45,80 65,80;30,64 17,63 13,80 33,80;79,61 68,60 64,65 65,80 84,80;17,63 12,58 0,58 0,80 13,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 34,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 9,
            "y": 50,
            "emitRate": 313,
            "avgEmitRate": 139,
            "idleTime": 292,
            "eruptionTime": 497,
            "dormancyCycles": 25.9,
            "activeCycles": 62.2
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 162,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,60 80,37 69,38 62,51 71,60;74,85 62,72 57,72 50,86 60,95;62,51 69,38 62,30 51,33 47,44 54,51;62,72 71,60 62,51 54,51 49,64 57,72;50,86 57,72 49,64 39,66 35,77 45,87;49,64 54,51 47,44 36,45 33,60 39,66;80,60 71,60 62,72 74,85 80,85;80,106 80,85 74,85 60,95 60,105 62,107;60,105 60,95 50,86 45,87 37,98 44,109;33,60 36,45 33,42 19,44 15,54 20,61;63,122 62,107 60,105 44,109 42,116 50,127 56,128;80,106 62,107 63,122 80,126;45,87 35,77 22,81 21,84 31,98 37,98;50,127 42,116 26,121 25,123 35,138 39,138;42,116 44,109 37,98 31,98 20,109 26,121;15,54 19,44 14,35 0,36 0,54;16,72 20,61 15,54 0,54 0,72;35,77 39,66 33,60 20,61 16,72 22,81;21,84 22,81 16,72 0,72 0,92 9,93;80,137 80,126 63,122 56,128 62,142;18,108 9,93 0,92 0,116;25,123 26,121 20,109 18,108 0,116 0,123 16,129;35,138 25,123 16,129 15,142 23,148;31,98 21,84 9,93 18,108 20,109;59,166 46,155 38,160 37,174 59,174;18,160 0,158 0,174 19,174;68,156 61,144 47,150 46,155 59,166;80,158 68,156 59,166 59,174 80,174;38,160 23,154 18,160 19,174 37,174;15,142 16,129 0,123 0,146\nSwamp:46,155 47,150 39,138 35,138 23,148 23,154 38,160;61,144 62,142 56,128 50,127 39,138 47,150;23,154 23,148 15,142 0,146 0,158 18,160;80,137 62,142 61,144 68,156 80,158",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 36,
            "y": 158
          },
          {
            "id": "GravitasPedestal",
            "x": 38,
            "y": 158
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 65,
            "y": 119,
            "emitRate": 10447,
            "avgEmitRate": 2709,
            "idleTime": 273,
            "eruptionTime": 256,
            "dormancyCycles": 54.6,
            "activeCycles": 62.9
          },
          {
            "id": "hot_water",
            "x": 64,
            "y": 96,
            "emitRate": 6963,
            "avgEmitRate": 2121,
            "idleTime": 283,
            "eruptionTime": 424,
            "dormancyCycles": 42.1,
            "activeCycles": 43.5
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 294,
        "offsetY": 155,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "MagmaCore:52,44 47,35 37,33 31,39 31,47 43,56;31,47 31,39 18,34 11,40 18,56;43,61 43,61 43,56 31,47 18,56 18,58 23,67;18,58 18,56 11,40 0,39 0,61;64,45 52,44 43,56 43,61 64,62;44,77 43,61 23,67 22,73 32,80;22,73 23,67 18,58 0,61 0,75 14,78;46,79 44,77 32,80 30,96 46,96;14,78 0,75 0,96 15,96\nOilField:64,77 64,62 43,61 43,61 44,77 46,79;32,80 22,73 14,78 15,96 30,96;64,77 46,79 46,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 49,
            "y": 64
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 50,
            "y": 82,
            "emitRate": 221416,
            "avgEmitRate": 1101,
            "idleTime": 7794,
            "eruptionTime": 66,
            "dormancyCycles": 56.9,
            "activeCycles": 81.6
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 360,
        "offsetY": 155,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:88,61 91,54 80,44 68,50 67,58 75,66;55,76 55,64 47,59 35,66 34,75 42,82;47,59 46,49 35,44 24,56 35,66;126,76 127,60 117,55 110,58 107,72 114,78;95,78 97,75 88,61 75,66 74,76 80,82;160,54 143,60 145,76 160,76;18,78 15,62 0,61 0,82\nSandstone:67,58 68,50 57,40 46,49 47,59 55,64;74,76 75,66 67,58 55,64 55,76 61,80;144,77 145,76 143,60 136,56 127,60 126,76 129,78;107,72 110,58 99,51 91,54 88,61 97,75;34,75 35,66 24,56 21,56 15,62 18,78 21,80\nFrozenWastes:61,80 55,76 42,82 41,96 61,96;80,82 74,76 61,80 61,96 79,96;114,78 107,72 97,75 95,78 99,96 111,96;144,77 129,78 129,96 145,96;129,78 126,76 114,78 111,96 129,96;42,82 34,75 21,80 22,96 41,96;95,78 80,82 79,96 99,96;21,80 18,78 0,82 0,96 22,96;160,76 145,76 144,77 145,96 160,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 71
          },
          {
            "id": "GravitasPedestal",
            "x": 48,
            "y": 71
          },
          {
            "id": "GeneShuffler",
            "x": 46,
            "y": 65
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 108,
            "y": 80,
            "emitRate": 2824,
            "avgEmitRate": 1215,
            "idleTime": 89,
            "eruptionTime": 269,
            "dormancyCycles": 55.9,
            "activeCycles": 74.9
          },
          {
            "id": "hot_steam",
            "x": 55,
            "y": 87,
            "emitRate": 4485,
            "avgEmitRate": 732,
            "idleTime": 413,
            "eruptionTime": 171,
            "dormancyCycles": 64.7,
            "activeCycles": 81.4
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "SwampMoonlet",
        "q": 0,
        "r": 0
      },
      {
        "id": "MetalHeavyLandingSite",
        "q": -3,
        "r": 3
      },
      {
        "id": "OilRichWarpTarget",
        "q": 1,
        "r": -4
      },
      {
        "id": "TundraMoonlet",
        "q": -6,
        "r": -1
      },
      {
        "id": "MarshyMoonlet",
        "q": 5,
        "r": -2
      },
      {
        "id": "MooMoonlet",
        "q": 4,
        "r": 3
      },
      {
        "id": "WaterMoonlet",
        "q": -2,
        "r": 8
      },
      {
        "id": "NiobiumMoonlet",
        "q": 9,
        "r": -1
      },
      {
        "id": "RegolithMoonlet",
        "q": -9,
        "r": 5
      },
      {
        "id": "TemporalTear",
        "q": 1,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 0,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -7,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -9,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -4,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -8,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 4,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 3,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -5,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 4,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -2,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 5,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 6,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 7,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -5,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 7,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 10,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 10,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 9,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 9,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": 10,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": 9,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 8,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 9,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 0,
        "r": -7
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -11,
        "r": 9
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 11,
        "r": 0
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": -9,
        "r": 2
      }
    ]
  },
  {
    "coordinate": "CER-C-90675436-0-0-0",
    "cluster": "CER-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "CeresSpacedOutAsteroid",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 274,
        "worldTraits": [
          "MetalCaves",
          "GlaciersLarge",
          "FrozenCore",
          "MetalPoor"
        ],
        "biomePaths": "IceCaves:91,182 104,164 105,148 89,130 60,127 49,139 44,162 52,177 71,187;113,143 118,120 105,111 96,115 89,130 105,148;60,127 60,121 44,107 31,109 25,120 29,133 49,139;69,204 71,187 52,177 39,193 50,208\nFrozenWastes:130,173 137,163 132,149 113,143 105,148 104,164 124,175;77,100 76,95 53,88 44,107 60,121;95,208 100,196 91,182 71,187 69,204 76,211;115,194 124,175 104,164 91,182 100,196;25,250 0,247 0,274 26,274;51,250 29,246 25,250 26,274 49,274;134,248 132,246 122,244 106,257 108,274 133,274;160,250 134,248 133,274 160,274;106,257 89,247 78,257 77,274 108,274;78,257 56,247 51,250 49,274 77,274\nSugarWoods:105,111 110,91 109,89 84,84 76,95 77,100 96,115;31,109 21,92 0,93 0,119 25,120;103,56 102,49 82,37 71,46 72,64 82,70;112,64 103,56 82,70 84,84 109,89;72,64 71,46 52,39 41,51 52,72;132,117 137,105 131,92 110,91 105,111 118,120;139,77 130,61 112,64 109,89 110,91 131,92;160,105 160,77 139,77 131,92 137,105;21,92 28,80 18,62 0,62 0,93;49,78 52,72 41,51 28,50 18,62 28,80;160,77 160,49 135,51 130,61 139,77\nCarrotQuarry:96,115 77,100 60,121 60,127 89,130;84,84 82,70 72,64 52,72 49,78 53,88 76,95;44,162 49,139 29,133 18,147 27,163;44,107 53,88 49,78 28,80 21,92 31,109;160,134 144,134 132,149 137,163 160,163;144,134 132,117 118,120 113,143 132,149;52,177 44,162 27,163 20,174 29,193 39,193;29,193 20,174 0,175 0,200 22,201;27,225 22,201 0,200 0,228;105,224 95,208 76,211 73,227 89,239\nOcean:29,133 25,120 0,119 0,146 18,147;146,192 130,173 124,175 115,194 129,210 130,210;73,227 76,211 69,204 50,208 45,221 58,234;45,221 50,208 39,193 29,193 22,201 27,225 28,225\nRust:20,174 27,163 18,147 0,146 0,175;160,105 137,105 132,117 144,134 160,134;129,210 115,194 100,196 95,208 105,224 111,225;160,192 160,163 137,163 130,173 146,192;160,192 146,192 130,210 142,222 160,222\nForest:56,247 58,234 45,221 28,225 29,246 51,250;29,246 28,225 27,225 0,228 0,247 25,250;89,247 89,239 73,227 58,234 56,247 78,257\nWasteland:142,222 130,210 129,210 111,225 122,244 132,246;160,222 142,222 132,246 134,248 160,250;122,244 111,225 105,224 89,239 89,247 106,257",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 74,
            "y": 158
          },
          {
            "id": "MassiveHeatSink",
            "x": 108,
            "y": 191
          },
          {
            "id": "GravitasPedestal",
            "x": 137,
            "y": 115
          },
          {
            "id": "WarpConduitSender",
            "x": 102,
            "y": 68
          },
          {
            "id": "WarpConduitReceiver",
            "x": 137,
            "y": 176
          },
          {
            "id": "WarpPortal",
            "x": 23,
            "y": 105
          },
          {
            "id": "WarpReceiver",
            "x": 24,
            "y": 111
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 27,
            "y": 199,
            "emitRate": 9872,
            "avgEmitRate": 2634,
            "idleTime": 236,
            "eruptionTime": 224,
            "dormancyCycles": 68.4,
            "activeCycles": 82.7
          },
          {
            "id": "liquid_co2",
            "x": 32,
            "y": 186,
            "emitRate": 940,
            "avgEmitRate": 151,
            "idleTime": 420,
            "eruptionTime": 164,
            "dormancyCycles": 58.6,
            "activeCycles": 78.2
          },
          {
            "id": "slush_salt_water",
            "x": 38,
            "y": 159,
            "emitRate": 8484,
            "avgEmitRate": 1668,
            "idleTime": 282,
            "eruptionTime": 155,
            "dormancyCycles": 62.6,
            "activeCycles": 77.6
          },
          {
            "id": "salt_water",
            "x": 88,
            "y": 78,
            "emitRate": 8981,
            "avgEmitRate": 2836,
            "idleTime": 285,
            "eruptionTime": 343,
            "dormancyCycles": 57.9,
            "activeCycles": 79.3
          },
          {
            "id": "filthy_water",
            "x": 55,
            "y": 67,
            "emitRate": 12890,
            "avgEmitRate": 3338,
            "idleTime": 237,
            "eruptionTime": 186,
            "dormancyCycles": 77.4,
            "activeCycles": 110.3
          },
          {
            "id": "methane",
            "x": 85,
            "y": 100,
            "emitRate": 418,
            "avgEmitRate": 86,
            "idleTime": 296,
            "eruptionTime": 202,
            "dormancyCycles": 56.3,
            "activeCycles": 57.6
          }
        ]
      },
      {
        "id": "SwampyLandingSite",
        "offsetX": 244,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "BouldersMixed"
        ],
        "biomePaths": "Swamp:66,67 67,66 65,48 48,45 43,54 47,68;105,73 110,63 104,52 89,52 83,66 90,75;128,63 128,41 110,42 104,52 110,63;83,66 89,52 83,43 72,42 65,48 67,66;86,86 90,75 83,66 67,66 66,67 66,87 73,90;66,87 66,67 47,68 43,73 46,85 57,90;43,54 48,45 46,39 26,33 21,40 24,54;57,90 46,85 34,97 41,110 51,110;21,58 24,54 21,40 0,38 0,58;96,97 86,86 73,90 77,110 87,110\nMetallic:128,85 128,63 110,63 105,73 112,85;106,96 112,85 105,73 90,75 86,86 96,97;47,68 43,54 24,54 21,58 25,70 43,73;128,109 128,85 112,85 106,96 117,109;128,109 117,109 106,126 112,132 128,133;76,132 72,116 57,116 53,132 69,139;38,131 34,118 20,116 13,130 23,140;41,110 34,97 24,95 16,108 20,116 34,118;13,130 20,116 16,108 0,107 0,132\nBoggyMarsh:117,109 106,96 96,97 87,110 97,125 106,126;46,85 43,73 25,70 17,82 24,95 34,97;25,70 21,58 0,58 0,82 17,82;72,116 77,110 73,90 66,87 57,90 51,110 57,116;24,95 17,82 0,82 0,107 16,108\nRadioactive:112,132 106,126 97,125 88,134 94,153 105,153;97,125 87,110 77,110 72,116 76,132 88,134;53,132 57,116 51,110 41,110 34,118 38,131 46,135;128,133 112,132 105,153 128,153;69,139 53,132 46,135 46,153 69,153;46,135 38,131 23,140 22,153 46,153;23,140 13,130 0,132 0,153 22,153\nFrozenWastes:88,134 76,132 69,139 69,153 94,153",
        "pointsOfInterest": [
          {
            "id": "PropSurfaceSatellite3",
            "x": 50,
            "y": 72
          },
          {
            "id": "GeneShuffler",
            "x": 31,
            "y": 94
          }
        ],
        "geysers": [
          {
            "id": "molten_cobalt",
            "x": 98,
            "y": 92,
            "emitRate": 7262,
            "avgEmitRate": 262,
            "idleTime": 724,
            "eruptionTime": 55,
            "dormancyCycles": 40.7,
            "activeCycles": 43
          },
          {
            "id": "molten_gold",
            "x": 21,
            "y": 114,
            "emitRate": 8812,
            "avgEmitRate": 321,
            "idleTime": 721,
            "eruptionTime": 48,
            "dormancyCycles": 71.6,
            "activeCycles": 99.9
          },
          {
            "id": "molten_cobalt",
            "x": 114,
            "y": 93,
            "emitRate": 9179,
            "avgEmitRate": 322,
            "idleTime": 838,
            "eruptionTime": 47,
            "dormancyCycles": 37.7,
            "activeCycles": 71.4
          },
          {
            "id": "molten_gold",
            "x": 114,
            "y": 82,
            "emitRate": 14051,
            "avgEmitRate": 295,
            "idleTime": 660,
            "eruptionTime": 22,
            "dormancyCycles": 47.1,
            "activeCycles": 86.4
          },
          {
            "id": "liquid_co2",
            "x": 59,
            "y": 137,
            "emitRate": 697,
            "avgEmitRate": 142,
            "idleTime": 469,
            "eruptionTime": 283,
            "dormancyCycles": 44,
            "activeCycles": 51.8
          },
          {
            "id": "liquid_co2",
            "x": 32,
            "y": 140,
            "emitRate": 516,
            "avgEmitRate": 124,
            "idleTime": 198,
            "eruptionTime": 128,
            "dormancyCycles": 55.1,
            "activeCycles": 87.4
          },
          {
            "id": "chlorine_gas",
            "x": 95,
            "y": 63,
            "emitRate": 635,
            "avgEmitRate": 103,
            "idleTime": 317,
            "eruptionTime": 107,
            "dormancyCycles": 47.1,
            "activeCycles": 86.4
          },
          {
            "id": "slimy_po2",
            "x": 77,
            "y": 123,
            "emitRate": 344,
            "avgEmitRate": 105,
            "idleTime": 247,
            "eruptionTime": 247,
            "dormancyCycles": 71.8,
            "activeCycles": 113.4
          },
          {
            "id": "hot_hydrogen",
            "x": 115,
            "y": 121,
            "emitRate": 342,
            "avgEmitRate": 104,
            "idleTime": 465,
            "eruptionTime": 446,
            "dormancyCycles": 47.8,
            "activeCycles": 78.1
          }
        ]
      },
      {
        "id": "OilRichWarpTarget",
        "offsetX": 374,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalRich",
          "RadioactiveCrust"
        ],
        "biomePaths": "Sandstone:75,63 71,57 54,59 52,76 69,78;128,62 113,62 105,75 113,85 128,86;105,99 113,85 105,75 94,75 87,86 95,99;87,86 94,75 88,64 75,63 69,78 74,86;71,57 73,44 66,37 55,39 48,53 54,59;71,116 76,109 69,95 52,98 49,101 57,117;88,108 95,99 87,86 74,86 69,95 76,109;128,109 128,86 113,85 105,99 113,109;26,91 18,85 0,88 0,104 15,107;40,103 26,91 26,91 15,107 20,115 33,117;69,95 74,86 69,78 52,76 48,79 52,98;18,85 17,68 0,67 0,88\nOcean:128,62 128,40 110,40 103,49 113,62;97,49 87,41 73,44 71,57 75,63 88,64;113,62 103,49 97,49 88,64 94,75 105,75;52,76 54,59 48,53 40,53 31,64 39,78 48,79;40,53 31,40 22,40 16,47 22,63 31,64;22,63 16,47 0,48 0,67 17,68\nRust:49,101 52,98 48,79 39,78 26,91 40,103;26,91 39,78 31,64 22,63 17,68 18,85 26,91\nOilField:113,109 105,99 95,99 88,108 96,126 105,126;110,131 105,126 96,126 88,133 91,153 107,153;128,109 113,109 105,126 110,131 128,131;76,131 71,116 57,117 51,126 55,134 68,139;68,139 55,134 43,150 43,153 69,153;96,126 88,108 76,109 71,116 76,131 88,133;32,135 37,124 33,117 20,115 12,129 20,137;88,133 76,131 68,139 69,153 91,153;128,131 110,131 107,153 128,153;57,117 49,101 40,103 33,117 37,124 51,126;12,129 20,115 15,107 0,104 0,129;43,150 32,135 20,137 16,153 43,153\nMagmaCore:55,134 51,126 37,124 32,135 43,150;20,137 12,129 0,129 0,153 16,153",
        "pointsOfInterest": [
          {
            "id": "WarpConduitSender",
            "x": 55,
            "y": 116
          },
          {
            "id": "WarpConduitReceiver",
            "x": 22,
            "y": 113
          },
          {
            "id": "WarpPortal",
            "x": 53,
            "y": 71
          },
          {
            "id": "WarpReceiver",
            "x": 75,
            "y": 71
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 113,
            "y": 137,
            "emitRate": 4752,
            "avgEmitRate": 1514,
            "idleTime": 319,
            "eruptionTime": 426,
            "dormancyCycles": 52.9,
            "activeCycles": 66.7
          },
          {
            "id": "OilWell",
            "x": 17,
            "y": 126,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 80,
            "y": 117,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 107,
            "y": 125,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 84,
            "y": 138,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 107,
            "y": 116,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_co2",
            "x": 71,
            "y": 126,
            "emitRate": 535,
            "avgEmitRate": 101,
            "idleTime": 460,
            "eruptionTime": 233,
            "dormancyCycles": 65.5,
            "activeCycles": 82.7
          },
          {
            "id": "small_volcano",
            "x": 43,
            "y": 81,
            "emitRate": 113916,
            "avgEmitRate": 673,
            "idleTime": 9403,
            "eruptionTime": 84,
            "dormancyCycles": 39.6,
            "activeCycles": 80.8
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 162,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "RadioactiveCrust"
        ],
        "biomePaths": "FrozenWastes:64,34 46,38 50,53 64,55;64,71 64,55 50,53 42,62 48,73;38,61 28,47 25,46 17,54 18,66 24,72;45,80 48,73 42,62 38,61 24,72 24,75 35,83;64,71 48,73 45,80 54,92 64,92;24,75 24,72 18,66 0,71 0,82 15,86;46,108 45,102 33,95 25,98 25,110 32,115;25,46 20,34 19,34 0,39 0,50 17,54;18,66 17,54 0,50 0,71;3,106 17,94 15,86 0,82 0,106;64,110 64,92 54,92 45,102 46,108 49,111;33,95 35,83 24,75 15,86 17,94 25,98;54,92 45,80 35,83 33,95 45,102;49,111 46,108 32,115 33,128 49,128;25,110 25,98 17,94 3,106 16,114;32,115 25,110 16,114 14,128 33,128;50,53 46,38 45,37 28,47 38,61 42,62;64,110 49,111 49,128 64,128;16,114 3,106 0,106 0,128 14,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 21,
            "y": 46
          },
          {
            "id": "GravitasPedestal",
            "x": 29,
            "y": 117
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 13,
            "y": 94,
            "emitRate": 8933,
            "avgEmitRate": 317,
            "idleTime": 678,
            "eruptionTime": 37,
            "dormancyCycles": 32.5,
            "activeCycles": 72.7
          },
          {
            "id": "molten_iron",
            "x": 49,
            "y": 106,
            "emitRate": 14258,
            "avgEmitRate": 337,
            "idleTime": 699,
            "eruptionTime": 27,
            "dormancyCycles": 41.3,
            "activeCycles": 73.8
          },
          {
            "id": "molten_iron",
            "x": 49,
            "y": 84,
            "emitRate": 8272,
            "avgEmitRate": 292,
            "idleTime": 778,
            "eruptionTime": 46,
            "dormancyCycles": 28.6,
            "activeCycles": 48.6
          },
          {
            "id": "molten_iron",
            "x": 43,
            "y": 98,
            "emitRate": 9399,
            "avgEmitRate": 333,
            "idleTime": 814,
            "eruptionTime": 49,
            "dormancyCycles": 54.8,
            "activeCycles": 91.4
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 228,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "BoggyMarsh:35,47 30,31 27,29 16,37 16,47 25,52;49,40 46,28 30,31 35,47 41,49;64,55 64,43 49,40 41,49 46,60;46,61 46,60 41,49 35,47 25,52 24,64 32,68;16,47 16,37 0,30 0,56 2,56\nToxicJungle:52,76 46,61 32,68 33,78 44,83;24,64 25,52 16,47 2,56 17,67;33,78 32,68 24,64 17,67 13,77 22,87;64,55 46,60 46,61 52,76 64,77;13,77 17,67 2,56 0,56 0,80\nFrozenWastes:44,83 33,78 22,87 22,96 45,96;64,77 52,76 44,83 45,96 64,96;22,87 13,77 0,80 0,96 22,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 35,
            "y": 43
          },
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 43
          },
          {
            "id": "SapTree",
            "x": 28,
            "y": 43
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 11,
            "y": 69,
            "emitRate": 13380,
            "avgEmitRate": 366,
            "idleTime": 873,
            "eruptionTime": 40,
            "dormancyCycles": 44.4,
            "activeCycles": 73.8
          },
          {
            "id": "molten_tungsten",
            "x": 54,
            "y": 87,
            "emitRate": 6511,
            "avgEmitRate": 279,
            "idleTime": 687,
            "eruptionTime": 44,
            "dormancyCycles": 41.7,
            "activeCycles": 100.3
          },
          {
            "id": "chlorine_gas",
            "x": 56,
            "y": 66,
            "emitRate": 264,
            "avgEmitRate": 83,
            "idleTime": 119,
            "eruptionTime": 142,
            "dormancyCycles": 56.8,
            "activeCycles": 78.1
          },
          {
            "id": "hot_hydrogen",
            "x": 25,
            "y": 72,
            "emitRate": 311,
            "avgEmitRate": 114,
            "idleTime": 186,
            "eruptionTime": 268,
            "dormancyCycles": 54.4,
            "activeCycles": 88.5
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 504,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:59,60 59,48 50,43 39,50 39,59 48,65;50,43 49,32 38,27 31,32 31,44 39,50;80,60 77,48 67,44 59,48 59,60 65,64;96,60 96,39 87,39 77,48 80,60 80,60;31,44 31,32 19,28 10,38 19,49;39,59 39,50 31,44 19,49 16,60 31,65;16,60 19,49 10,38 0,39 0,59 16,60\nFrozenWastes:80,60 80,60 65,64 65,80 82,80;65,64 59,60 48,65 48,80 65,80;96,60 80,60 82,80 96,80;16,60 0,59 0,80 14,80;31,65 16,60 16,60 14,80 32,80;48,65 39,59 31,65 32,80 48,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 69,
            "y": 56
          },
          {
            "id": "GravitasPedestal",
            "x": 62,
            "y": 56
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 10,
            "y": 52,
            "emitRate": 861,
            "avgEmitRate": 105,
            "idleTime": 418,
            "eruptionTime": 97,
            "dormancyCycles": 49.8,
            "activeCycles": 92.9
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 162,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,61 80,48 62,44 56,54 59,65 60,65;80,61 60,65 64,79 80,83;62,44 60,38 50,33 39,38 37,42 40,50 56,54;64,79 60,65 59,65 44,72 46,86 55,91;59,65 56,54 40,50 34,62 36,68 44,72;80,94 64,99 64,114 80,115;55,92 55,91 46,86 34,91 32,104 33,105 45,108;46,86 44,72 36,68 23,82 34,91;80,94 80,83 64,79 55,91 55,92 64,99;64,114 64,99 55,92 45,108 48,113 64,115;43,128 48,113 45,108 33,105 29,122 37,129;29,122 33,105 32,104 18,103 12,115 19,123;34,91 23,82 21,82 12,94 18,103 32,104;72,135 63,127 50,133 52,144 63,147;32,141 37,129 29,122 19,123 14,135 21,143;63,127 64,115 48,113 43,128 50,133;21,82 14,73 0,73 0,93 12,94;36,68 34,62 19,59 14,73 21,82 23,82;80,135 80,115 64,114 64,115 63,127 72,135;40,50 37,42 21,43 17,54 19,59 34,62;14,73 19,59 17,54 0,53 0,73;58,166 42,153 37,160 37,174 58,174;37,160 19,157 17,174 37,174;21,43 16,35 0,35 0,53 17,54;80,158 66,155 58,166 58,174 80,174;14,135 19,123 12,115 0,115 0,136;12,115 18,103 12,94 0,93 0,115;19,157 17,155 0,155 0,174 17,174\nSwamp:52,144 50,133 43,128 37,129 32,141 42,153;66,155 63,147 52,144 42,153 42,153 58,166;80,135 72,135 63,147 66,155 80,158;21,143 14,135 0,136 0,155 17,155;42,153 42,153 32,141 21,143 17,155 19,157 37,160",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 48,
            "y": 143
          },
          {
            "id": "GravitasPedestal",
            "x": 50,
            "y": 143
          }
        ],
        "geysers": [
          {
            "id": "filthy_water",
            "x": 30,
            "y": 64,
            "emitRate": 12686,
            "avgEmitRate": 3227,
            "idleTime": 436,
            "eruptionTime": 325,
            "dormancyCycles": 51,
            "activeCycles": 75.2
          },
          {
            "id": "hot_water",
            "x": 28,
            "y": 114,
            "emitRate": 8003,
            "avgEmitRate": 3865,
            "idleTime": 225,
            "eruptionTime": 411,
            "dormancyCycles": 30.3,
            "activeCycles": 89.3
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 294,
        "offsetY": 155,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "LushCore"
        ],
        "biomePaths": "MagmaCore:64,26 47,31 46,33 54,46 64,46;54,46 46,33 34,40 33,41 39,55 44,56;33,41 34,40 23,24 22,24 12,36 19,47;39,55 33,41 19,47 16,54 20,61 28,63;33,76 28,63 20,61 11,74 16,81 29,81;16,54 19,47 12,36 0,35 0,55;64,80 64,65 49,64 44,76 48,81\nOilField:64,46 54,46 44,56 49,64 64,65;44,76 49,64 44,56 39,55 28,63 33,76;20,61 16,54 0,55 0,73 11,74\nForest:48,81 44,76 33,76 29,81 32,96 46,96;64,80 48,81 46,96 64,96;16,81 11,74 0,73 0,96 12,96;29,81 16,81 12,96 32,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 16,
            "y": 63
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 50,
            "y": 54,
            "emitRate": 290747,
            "avgEmitRate": 1214,
            "idleTime": 10928,
            "eruptionTime": 79,
            "dormancyCycles": 53,
            "activeCycles": 74
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 360,
        "offsetY": 155,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:123,72 116,57 108,56 100,64 103,76 116,77;80,74 88,60 86,54 74,50 68,54 68,71 73,74;160,71 160,54 142,53 139,56 139,60 149,73;52,78 55,73 49,60 38,60 33,75 39,81;18,78 20,76 16,60 0,60 0,79\nSandstone:139,60 139,56 125,50 116,57 123,72 125,73;101,78 103,76 100,64 88,60 80,74 86,80;68,71 68,54 56,51 49,60 55,73;145,79 149,73 139,60 125,73 133,82;33,75 38,60 34,55 20,55 16,60 20,76\nFrozenWastes:101,78 86,80 84,96 103,96;116,77 103,76 101,78 103,96 118,96;86,80 80,74 73,74 67,96 84,96;73,74 68,71 55,73 52,78 59,96 67,96;133,82 125,73 123,72 116,77 118,96 130,96;145,79 133,82 130,96 152,96;52,78 39,81 36,96 59,96;39,81 33,75 20,76 18,78 20,96 36,96;18,78 0,79 0,96 20,96;160,71 149,73 145,79 152,96 160,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 110,
            "y": 77
          },
          {
            "id": "GravitasPedestal",
            "x": 114,
            "y": 77
          },
          {
            "id": "GeneShuffler",
            "x": 112,
            "y": 71
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 43,
            "y": 84,
            "emitRate": 2609,
            "avgEmitRate": 809,
            "idleTime": 286,
            "eruptionTime": 311,
            "dormancyCycles": 56.9,
            "activeCycles": 83.8
          },
          {
            "id": "steam",
            "x": 28,
            "y": 81,
            "emitRate": 6268,
            "avgEmitRate": 1272,
            "idleTime": 545,
            "eruptionTime": 370,
            "dormancyCycles": 48.5,
            "activeCycles": 48.9
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "CeresSpacedOutAsteroid",
        "q": 0,
        "r": 0
      },
      {
        "id": "SwampyLandingSite",
        "q": -1,
        "r": -2
      },
      {
        "id": "OilRichWarpTarget",
        "q": 3,
        "r": 1
      },
      {
        "id": "TundraMoonlet",
        "q": -1,
        "r": 7
      },
      {
        "id": "MarshyMoonlet",
        "q": -7,
        "r": 0
      },
      {
        "id": "MooMoonlet",
        "q": 1,
        "r": -6
      },
      {
        "id": "WaterMoonlet",
        "q": -6,
        "r": 7
      },
      {
        "id": "NiobiumMoonlet",
        "q": 9,
        "r": -1
      },
      {
        "id": "RegolithMoonlet",
        "q": 10,
        "r": -8
      },
      {
        "id": "TemporalTear",
        "q": -4,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_DLC2CeresOreField",
        "q": 3,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 7,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 11,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -9,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -9,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -10,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 8,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -6,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 11,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": -5,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -5,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": -3,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 4,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 5,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 6,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 4,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": 5,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -2,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 0,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -9,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -8,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -9,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 6,
        "r": 5
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": 6,
        "r": -1
      }
    ]
  },
  {
    "coordinate": "V-LUSH-C-1740050187-0-0-0",
    "cluster": "V-LUSH-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaForestDefault",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "DeepOil",
          "GeoActive",
          "IrregularOil"
        ],
        "biomePaths": "Forest:134,207 132,184 116,174 98,177 90,187 90,207 104,220 115,221;151,201 152,189 136,181 132,184 134,207 136,208;136,181 138,164 127,157 118,163 116,174 132,184;116,174 118,163 101,155 93,164 98,177;90,207 90,187 77,184 72,187 73,207 80,211;104,220 90,207 80,211 81,229 93,234;98,177 93,164 78,163 77,184 90,187;138,228 136,208 134,207 115,221 126,233;26,297 31,282 21,268 0,268 0,299\nRust:206,180 205,174 183,160 170,167 168,180 183,195;240,125 224,125 211,142 217,157 240,160;104,300 103,281 83,271 67,290 83,306;28,326 34,309 26,297 0,299 0,324 24,328;210,308 218,295 210,277 187,282 183,294 191,309;184,323 191,309 183,294 163,293 156,306 165,325;240,326 240,295 218,295 210,308 220,328\nBoggyMarsh:240,182 212,185 214,213 240,214;168,180 170,167 154,155 138,164 136,181 152,189;217,157 211,142 189,138 186,140 183,160 205,174;166,229 166,210 151,201 136,208 138,228 155,236;156,105 154,87 142,79 124,89 124,105 138,113;224,125 211,109 194,111 189,138 211,142;96,247 93,234 81,229 63,238 61,253 82,263;77,184 78,163 75,159 51,159 44,171 50,184 72,187;240,270 240,242 218,242 208,255 213,271;52,309 63,291 49,278 31,282 26,297 34,309;210,277 213,271 208,255 188,251 175,265 175,267 187,282\nOcean:240,182 240,160 217,157 205,174 206,180 212,185;75,159 79,135 53,130 45,144 51,159;155,252 155,236 138,228 126,233 121,252 142,262;121,252 126,233 115,221 104,220 93,234 96,247 117,254;32,231 19,211 0,213 0,242 27,243;113,321 106,301 104,300 83,306 78,322 83,330 104,333;29,170 20,151 0,152 0,184 19,184;53,109 47,88 30,86 19,103 26,117 48,117\nToxicJungle:208,219 214,213 212,185 206,180 183,195 183,200 200,219;137,133 138,113 124,105 109,113 109,133 128,140;45,144 53,130 48,117 26,117 18,131 24,144;211,109 219,93 208,78 186,85 185,103 194,111;114,275 117,254 96,247 82,263 83,271 103,281;67,290 83,271 82,263 61,253 53,258 49,278 63,291;156,306 163,293 161,283 143,274 128,285 128,292 139,307;218,333 220,328 210,308 191,309 184,323 195,341;240,91 219,93 211,109 224,125 240,125\nOilField:154,155 154,141 137,133 128,140 127,157 138,164;127,157 128,140 109,133 100,139 101,155 118,163;124,105 124,89 113,82 96,91 96,105 109,113;81,229 80,211 73,207 54,213 51,226 63,238;43,197 50,184 44,171 29,170 19,184 27,198;73,207 72,187 50,184 43,197 54,213;19,211 27,198 19,184 0,184 0,213;51,159 45,144 24,144 20,151 29,170 44,171;183,200 183,195 168,180 152,189 151,201 166,210;49,278 53,258 30,250 21,268 31,282;78,322 83,306 67,290 63,291 52,309 61,323;75,104 77,88 54,80 47,88 53,109;101,155 100,139 79,134 79,135 75,159 78,163 93,164;143,274 142,262 121,252 117,254 114,275 128,285;21,268 30,250 27,243 0,242 0,268;240,295 240,270 213,271 210,277 218,295;78,346 83,330 78,322 61,323 53,340 78,346;21,345 24,328 0,324 0,346;53,340 61,323 52,309 34,309 28,326 52,340;47,347 52,340 28,326 24,328 21,345 26,350;106,346 104,333 83,330 78,346 99,352;26,117 19,103 0,102 0,130 18,131;136,346 139,334 132,322 113,321 104,333 106,346 127,352;162,348 160,332 139,334 136,346 154,355;160,332 165,325 156,306 139,307 132,322 139,334;139,307 128,292 106,301 113,321 132,322;223,349 218,333 195,341 194,347 212,358;240,326 220,328 218,333 223,349 240,351;194,347 195,341 184,323 165,325 160,332 162,348 183,355;175,267 175,265 155,252 142,262 143,274 161,283\nSwamp:166,133 164,110 156,105 138,113 137,133 154,141;200,219 183,200 166,210 166,229 184,236;240,214 214,213 208,219 218,242 240,242;218,242 208,219 200,219 184,236 188,251 208,255;188,251 184,236 166,229 155,236 155,252 175,265;185,103 186,85 175,75 154,87 156,105 164,110;51,226 54,213 43,197 27,198 19,211 32,231;109,133 109,113 96,105 82,110 79,134 100,139;79,134 82,110 75,104 53,109 48,117 53,130 79,135;183,160 186,140 166,133 154,141 154,155 170,167;189,138 194,111 185,103 164,110 166,133 186,140;61,253 63,238 51,226 32,231 27,243 30,250 53,258\nBarren:142,79 141,62 127,54 111,62 113,82 124,89;175,75 175,63 156,52 141,62 142,79 154,87;113,82 111,62 99,56 85,62 83,85 96,91;208,78 211,63 210,61 186,54 175,63 175,75 186,85;83,85 85,62 71,54 54,62 54,80 77,88;240,91 240,65 211,63 208,78 219,93;54,80 54,62 42,55 29,58 22,71 30,86 47,88;30,86 22,71 0,72 0,102 19,103\nRadioactive:187,282 175,267 161,283 163,293 183,294;128,292 128,285 114,275 103,281 104,300 106,301;96,105 96,91 83,85 77,88 75,104 82,110;20,151 24,144 18,131 0,130 0,152\nMagmaCore:78,346 53,340 52,340 47,347 56,380 69,380;99,352 78,346 78,346 69,380 100,380;154,355 136,346 127,352 126,380 153,380;47,347 26,350 23,380 56,380;127,352 106,346 99,352 100,380 126,380;26,350 21,345 0,346 0,380 23,380;183,355 162,348 154,355 153,380 182,380;212,358 194,347 183,355 182,380 212,380;240,351 223,349 212,358 212,380 240,380",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 110,
            "y": 199
          },
          {
            "id": "WarpConduitSender",
            "x": 129,
            "y": 129
          },
          {
            "id": "WarpConduitReceiver",
            "x": 191,
            "y": 201
          },
          {
            "id": "GravitasPedestal",
            "x": 184,
            "y": 131
          },
          {
            "id": "WarpReceiver",
            "x": 205,
            "y": 231
          },
          {
            "id": "WarpPortal",
            "x": 204,
            "y": 225
          },
          {
            "id": "GeneShuffler",
            "x": 185,
            "y": 315
          },
          {
            "id": "GeneShuffler",
            "x": 171,
            "y": 213
          },
          {
            "id": "GeneShuffler",
            "x": 186,
            "y": 341
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 152,
            "y": 294,
            "emitRate": 4698,
            "avgEmitRate": 1534,
            "idleTime": 201,
            "eruptionTime": 214,
            "dormancyCycles": 34.4,
            "activeCycles": 59.3
          },
          {
            "id": "chlorine_gas",
            "x": 43,
            "y": 121,
            "emitRate": 426,
            "avgEmitRate": 116,
            "idleTime": 439,
            "eruptionTime": 348,
            "dormancyCycles": 56.4,
            "activeCycles": 90.7
          },
          {
            "id": "steam",
            "x": 75,
            "y": 180,
            "emitRate": 5454,
            "avgEmitRate": 1546,
            "idleTime": 469,
            "eruptionTime": 432,
            "dormancyCycles": 27.5,
            "activeCycles": 39.8
          },
          {
            "id": "methane",
            "x": 152,
            "y": 179,
            "emitRate": 252,
            "avgEmitRate": 107,
            "idleTime": 238,
            "eruptionTime": 521,
            "dormancyCycles": 52.1,
            "activeCycles": 85.1
          },
          {
            "id": "salt_water",
            "x": 85,
            "y": 314,
            "emitRate": 9890,
            "avgEmitRate": 3358,
            "idleTime": 269,
            "eruptionTime": 361,
            "dormancyCycles": 52.5,
            "activeCycles": 76.5
          },
          {
            "id": "OilWell",
            "x": 42,
            "y": 184,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 124,
            "y": 318,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 66,
            "y": 205,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_co2",
            "x": 21,
            "y": 293,
            "emitRate": 313,
            "avgEmitRate": 85,
            "idleTime": 401,
            "eruptionTime": 498,
            "dormancyCycles": 59.7,
            "activeCycles": 57.4
          },
          {
            "id": "big_volcano",
            "x": 15,
            "y": 247,
            "emitRate": 248914,
            "avgEmitRate": 1014,
            "idleTime": 9358,
            "eruptionTime": 73,
            "dormancyCycles": 78.6,
            "activeCycles": 86.8
          },
          {
            "id": "steam",
            "x": 96,
            "y": 298,
            "emitRate": 3742,
            "avgEmitRate": 1234,
            "idleTime": 281,
            "eruptionTime": 321,
            "dormancyCycles": 43,
            "activeCycles": 69.8
          },
          {
            "id": "oil_drip",
            "x": 133,
            "y": 339,
            "emitRate": 299,
            "avgEmitRate": 195,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "big_volcano",
            "x": 88,
            "y": 341,
            "emitRate": 216958,
            "avgEmitRate": 1197,
            "idleTime": 9003,
            "eruptionTime": 81,
            "dormancyCycles": 46.5,
            "activeCycles": 74.4
          },
          {
            "id": "hot_co2",
            "x": 24,
            "y": 185,
            "emitRate": 395,
            "avgEmitRate": 94,
            "idleTime": 345,
            "eruptionTime": 268,
            "dormancyCycles": 38.3,
            "activeCycles": 45.4
          },
          {
            "id": "hot_steam",
            "x": 34,
            "y": 257,
            "emitRate": 1536,
            "avgEmitRate": 572,
            "idleTime": 171,
            "eruptionTime": 372,
            "dormancyCycles": 69.6,
            "activeCycles": 83.1
          },
          {
            "id": "salt_water",
            "x": 62,
            "y": 231,
            "emitRate": 8533,
            "avgEmitRate": 2852,
            "idleTime": 210,
            "eruptionTime": 260,
            "dormancyCycles": 45.8,
            "activeCycles": 70.2
          },
          {
            "id": "big_volcano",
            "x": 37,
            "y": 336,
            "emitRate": 237525,
            "avgEmitRate": 1266,
            "idleTime": 7437,
            "eruptionTime": 61,
            "dormancyCycles": 45.8,
            "activeCycles": 87.3
          },
          {
            "id": "molten_aluminum",
            "x": 140,
            "y": 149,
            "emitRate": 9278,
            "avgEmitRate": 351,
            "idleTime": 648,
            "eruptionTime": 41,
            "dormancyCycles": 45.3,
            "activeCycles": 80.9
          },
          {
            "id": "steam",
            "x": 133,
            "y": 291,
            "emitRate": 4053,
            "avgEmitRate": 1136,
            "idleTime": 304,
            "eruptionTime": 266,
            "dormancyCycles": 56.1,
            "activeCycles": 84.2
          },
          {
            "id": "slush_water",
            "x": 147,
            "y": 320,
            "emitRate": 3571,
            "avgEmitRate": 1446,
            "idleTime": 87,
            "eruptionTime": 154,
            "dormancyCycles": 46.2,
            "activeCycles": 79.7
          },
          {
            "id": "liquid_sulfur",
            "x": 16,
            "y": 197,
            "emitRate": 7127,
            "avgEmitRate": 1690,
            "idleTime": 368,
            "eruptionTime": 294,
            "dormancyCycles": 69.3,
            "activeCycles": 79.3
          },
          {
            "id": "methane",
            "x": 49,
            "y": 200,
            "emitRate": 418,
            "avgEmitRate": 113,
            "idleTime": 334,
            "eruptionTime": 256,
            "dormancyCycles": 38.9,
            "activeCycles": 64.1
          },
          {
            "id": "slush_water",
            "x": 41,
            "y": 304,
            "emitRate": 2965,
            "avgEmitRate": 1193,
            "idleTime": 151,
            "eruptionTime": 489,
            "dormancyCycles": 52.7,
            "activeCycles": 58.6
          },
          {
            "id": "molten_aluminum",
            "x": 158,
            "y": 190,
            "emitRate": 9567,
            "avgEmitRate": 319,
            "idleTime": 714,
            "eruptionTime": 43,
            "dormancyCycles": 54.9,
            "activeCycles": 77.5
          },
          {
            "id": "OilWell",
            "x": 149,
            "y": 152,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 107,
            "y": 156,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 102,
            "y": 102,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 54,
            "y": 224,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 29,
            "y": 177,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 48,
            "y": 193,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 15,
            "y": 191,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 41,
            "y": 157,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 167,
            "y": 198,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 32,
            "y": 266,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 69,
            "y": 319,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 60,
            "y": 91,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 83,
            "y": 150,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 121,
            "y": 261,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 10,
            "y": 266,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 234,
            "y": 291,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 41,
            "y": 333,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 16,
            "y": 113,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 150,
            "y": 330,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 124,
            "y": 300,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 155,
            "y": 278,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "MediumSandyRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "SlimeSplats",
          "BouldersMedium"
        ],
        "biomePaths": "Sandstone:102,112 92,93 74,101 78,117;129,79 125,63 114,60 106,65 103,81 117,88;129,128 135,113 134,109 118,101 103,113 105,120 123,130;92,93 94,86 81,75 65,83 66,97 74,101;78,117 74,101 66,97 51,106 59,124 74,125;105,120 103,113 102,112 78,117 74,125 78,133 95,136;66,97 65,83 57,77 45,79 38,89 45,105 51,106;118,101 117,88 103,81 94,86 92,93 102,112 103,113\nToxicJungle:81,75 82,59 70,53 60,58 57,77 65,83;70,53 69,38 56,30 43,42 45,51 60,58;103,81 106,65 89,56 82,59 81,75 94,86;160,66 153,66 138,82 144,94 160,95;34,62 45,51 43,42 27,34 16,49 25,63;45,79 34,62 25,63 17,75 25,89 38,89;57,77 60,58 45,51 34,62 45,79;45,105 38,89 25,89 16,100 23,113 33,115;38,132 33,115 23,113 12,126 24,140;12,126 23,113 16,100 0,100 0,127\nWasteland:114,60 113,40 95,40 89,56 106,65;160,66 160,41 139,40 137,42 137,54 153,66;144,94 138,82 129,79 117,88 118,101 134,109;153,66 137,54 125,63 129,79 138,82;160,95 144,94 134,109 135,113 160,120;160,131 160,120 135,113 129,128 140,138;137,54 137,42 116,37 113,40 114,60 125,63;25,63 16,49 0,48 0,75 17,75;59,124 51,106 45,105 33,115 38,132 49,135;25,89 17,75 0,75 0,100 16,100\nRadioactive:116,146 123,130 105,120 95,136 101,148;98,153 101,148 95,136 78,133 72,149 85,159;72,149 78,133 74,125 59,124 49,135 52,148 63,152;123,155 116,146 101,148 98,153 109,176 115,176;139,155 140,138 129,128 123,130 116,146 123,155;52,148 49,135 38,132 24,140 22,151 22,152 41,158;139,155 139,155 123,155 115,176 140,176;41,158 22,152 19,176 43,176;160,131 140,138 139,155 139,155 160,156;22,151 24,140 12,126 0,127 0,152\nFrozenWastes:63,152 52,148 41,158 43,176 62,176;98,153 85,159 82,176 109,176;160,156 139,155 140,176 160,176;22,152 22,151 0,152 0,176 19,176;85,159 72,149 63,152 62,176 82,176",
        "pointsOfInterest": [
          {
            "id": "WarpConduitSender",
            "x": 80,
            "y": 122
          },
          {
            "id": "WarpConduitReceiver",
            "x": 70,
            "y": 93
          },
          {
            "id": "WarpPortal",
            "x": 77,
            "y": 108
          },
          {
            "id": "WarpReceiver",
            "x": 96,
            "y": 108
          },
          {
            "id": "GeneShuffler",
            "x": 68,
            "y": 78
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 121,
            "y": 165,
            "emitRate": 460,
            "avgEmitRate": 132,
            "idleTime": 114,
            "eruptionTime": 131,
            "dormancyCycles": 45.1,
            "activeCycles": 52.3
          },
          {
            "id": "liquid_co2",
            "x": 79,
            "y": 143,
            "emitRate": 551,
            "avgEmitRate": 157,
            "idleTime": 205,
            "eruptionTime": 239,
            "dormancyCycles": 75.4,
            "activeCycles": 84.1
          },
          {
            "id": "steam",
            "x": 18,
            "y": 117,
            "emitRate": 6529,
            "avgEmitRate": 1490,
            "idleTime": 466,
            "eruptionTime": 310,
            "dormancyCycles": 51.3,
            "activeCycles": 68.4
          },
          {
            "id": "molten_aluminum",
            "x": 149,
            "y": 110,
            "emitRate": 8586,
            "avgEmitRate": 328,
            "idleTime": 707,
            "eruptionTime": 47,
            "dormancyCycles": 60.7,
            "activeCycles": 96.4
          },
          {
            "id": "molten_gold",
            "x": 25,
            "y": 73,
            "emitRate": 6945,
            "avgEmitRate": 276,
            "idleTime": 804,
            "eruptionTime": 60,
            "dormancyCycles": 47.3,
            "activeCycles": 64.1
          },
          {
            "id": "steam",
            "x": 97,
            "y": 119,
            "emitRate": 21567,
            "avgEmitRate": 1440,
            "idleTime": 513,
            "eruptionTime": 77,
            "dormancyCycles": 75.5,
            "activeCycles": 78.8
          },
          {
            "id": "filthy_water",
            "x": 129,
            "y": 137,
            "emitRate": 7943,
            "avgEmitRate": 2722,
            "idleTime": 260,
            "eruptionTime": 369,
            "dormancyCycles": 63.5,
            "activeCycles": 89.2
          },
          {
            "id": "liquid_co2",
            "x": 49,
            "y": 59,
            "emitRate": 487,
            "avgEmitRate": 137,
            "idleTime": 539,
            "eruptionTime": 479,
            "dormancyCycles": 48.1,
            "activeCycles": 71.3
          },
          {
            "id": "liquid_sulfur",
            "x": 149,
            "y": 77,
            "emitRate": 3539,
            "avgEmitRate": 1185,
            "idleTime": 152,
            "eruptionTime": 192,
            "dormancyCycles": 65.9,
            "activeCycles": 99
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "RadioactiveCrust"
        ],
        "biomePaths": "FrozenWastes:64,35 46,36 45,39 52,54 64,55;52,54 45,39 34,45 32,55 45,62;45,39 46,36 45,32 31,26 21,35 21,36 34,45;64,75 64,55 52,54 45,62 45,67 54,75;23,70 25,59 16,53 0,62 0,63 16,73;45,67 45,62 32,55 25,59 23,70 33,76;54,75 45,67 33,76 34,83 46,89;34,83 33,76 23,70 16,73 13,85 19,93 24,93;44,101 48,94 46,89 34,83 24,93 32,103;10,107 19,93 13,85 0,86 0,107;13,85 16,73 0,63 0,86;32,55 34,45 21,36 15,41 16,53 25,59;16,53 15,41 0,39 0,62;64,95 48,94 44,101 49,112 64,113;49,112 44,101 32,103 29,111 41,124;64,75 54,75 46,89 48,94 64,95;29,111 32,103 24,93 19,93 10,107 19,115;64,113 49,112 41,124 41,128 64,128;41,124 29,111 19,115 16,128 41,128;19,115 10,107 0,107 0,128 16,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 28,
            "y": 37
          },
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 91
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 20,
            "y": 61,
            "emitRate": 7155,
            "avgEmitRate": 293,
            "idleTime": 622,
            "eruptionTime": 50,
            "dormancyCycles": 34.1,
            "activeCycles": 42.3
          },
          {
            "id": "molten_iron",
            "x": 31,
            "y": 100,
            "emitRate": 8269,
            "avgEmitRate": 269,
            "idleTime": 834,
            "eruptionTime": 47,
            "dormancyCycles": 32.6,
            "activeCycles": 50.5
          },
          {
            "id": "molten_iron",
            "x": 53,
            "y": 71,
            "emitRate": 6211,
            "avgEmitRate": 325,
            "idleTime": 792,
            "eruptionTime": 74,
            "dormancyCycles": 68.6,
            "activeCycles": 107.8
          },
          {
            "id": "molten_iron",
            "x": 44,
            "y": 58,
            "emitRate": 8160,
            "avgEmitRate": 320,
            "idleTime": 559,
            "eruptionTime": 40,
            "dormancyCycles": 61.9,
            "activeCycles": 87.3
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "BoggyMarsh:33,62 32,45 18,44 15,47 18,62;46,62 52,55 45,41 37,41 32,45 33,62 34,63;37,41 31,27 18,28 16,31 18,44 32,45;64,54 52,55 46,62 53,76 64,77;18,62 15,47 0,47 0,64 16,64;64,54 64,35 51,35 45,41 52,55;18,44 16,31 0,31 0,47 15,47\nToxicJungle:53,76 46,62 34,63 33,78 43,84;33,78 34,63 33,62 18,62 16,64 17,79 22,83;17,79 16,64 0,64 0,82\nFrozenWastes:64,77 53,76 43,84 44,96 64,96;22,83 17,79 0,82 0,96 22,96;43,84 33,78 22,83 22,96 44,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 30,
            "y": 54
          },
          {
            "id": "GravitasPedestal",
            "x": 16,
            "y": 54
          },
          {
            "id": "SapTree",
            "x": 23,
            "y": 54
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 5,
            "y": 77,
            "emitRate": 8081,
            "avgEmitRate": 314,
            "idleTime": 783,
            "eruptionTime": 55,
            "dormancyCycles": 46.7,
            "activeCycles": 68
          },
          {
            "id": "molten_tungsten",
            "x": 55,
            "y": 90,
            "emitRate": 7030,
            "avgEmitRate": 234,
            "idleTime": 755,
            "eruptionTime": 49,
            "dormancyCycles": 48.7,
            "activeCycles": 57.9
          },
          {
            "id": "molten_tungsten",
            "x": 6,
            "y": 90,
            "emitRate": 9193,
            "avgEmitRate": 242,
            "idleTime": 684,
            "eruptionTime": 39,
            "dormancyCycles": 75.4,
            "activeCycles": 71.4
          },
          {
            "id": "methane",
            "x": 12,
            "y": 71,
            "emitRate": 293,
            "avgEmitRate": 101,
            "idleTime": 249,
            "eruptionTime": 378,
            "dormancyCycles": 39.5,
            "activeCycles": 53.3
          },
          {
            "id": "chlorine_gas",
            "x": 32,
            "y": 64,
            "emitRate": 325,
            "avgEmitRate": 108,
            "idleTime": 266,
            "eruptionTime": 333,
            "dormancyCycles": 63.5,
            "activeCycles": 95.6
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:54,41 44,32 35,38 36,54 46,54;64,40 54,41 46,54 52,62 64,63;36,54 35,38 23,34 17,44 21,55 33,56;64,81 48,80 45,96 64,96;64,63 52,62 44,75 48,80 64,81;52,62 46,54 36,54 33,56 35,73 44,75;23,34 21,31 0,29 0,44 17,44;19,75 15,62 0,62 0,78 15,79;29,77 19,75 15,79 16,96 30,96;15,79 0,78 0,96 16,96\nOilField:35,73 33,56 21,55 15,62 19,75 29,77;21,55 17,44 0,44 0,62 15,62;48,80 44,75 35,73 29,77 30,96 45,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 23,
            "y": 71
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 33,
            "y": 85,
            "emitRate": 232683,
            "avgEmitRate": 1298,
            "idleTime": 9772,
            "eruptionTime": 90,
            "dormancyCycles": 68.6,
            "activeCycles": 107.8
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:72,43 72,39 60,30 51,34 48,42 51,47 63,51;96,46 96,35 80,32 72,39 72,43 81,50;80,63 81,50 72,43 63,51 65,63 75,67;45,61 51,47 48,42 34,43 29,57 35,63;96,46 81,50 80,63 96,67;16,61 19,58 14,41 0,41 0,61;29,57 34,43 28,36 19,36 14,41 19,58;65,63 63,51 51,47 45,61 54,69\nFrozenWastes:16,61 0,61 0,80 18,80;35,63 29,57 19,58 16,61 18,80 32,80;54,69 45,61 35,63 32,80 53,80;96,67 80,63 75,67 75,80 96,80;75,67 65,63 54,69 53,80 75,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 48
          },
          {
            "id": "GravitasPedestal",
            "x": 17,
            "y": 48
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 84,
            "y": 46,
            "emitRate": 555,
            "avgEmitRate": 129,
            "idleTime": 386,
            "eruptionTime": 193,
            "dormancyCycles": 43.5,
            "activeCycles": 99.5
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,53 70,53 61,62 65,73 80,73;62,77 65,73 61,62 50,59 42,73 46,78;70,53 62,41 53,41 47,53 50,59 61,62;80,73 65,73 62,77 65,92 80,92;53,41 47,33 35,34 31,39 35,51 47,53;35,72 28,60 20,59 13,73 20,81 25,82;60,103 60,97 45,90 38,95 43,110 50,111;45,90 46,78 42,73 35,72 25,82 32,94 38,95;10,94 20,81 13,73 0,73 0,94;20,59 15,54 0,54 0,73 13,73;35,51 31,39 18,39 15,54 20,59 28,60;15,54 18,39 15,35 0,35 0,54;72,114 60,103 50,111 57,127 59,127;43,110 38,95 32,94 20,105 20,109 35,117;50,59 47,53 35,51 28,60 35,72 42,73;32,94 25,82 20,81 10,94 20,105;20,109 20,105 10,94 0,94 0,115 14,116;80,114 80,92 65,92 60,97 60,103 72,114;19,132 14,116 0,115 0,136 13,136;57,127 50,111 43,110 35,117 35,124 47,132;44,142 47,132 35,124 25,133 32,145;80,32 67,32 62,41 70,53 80,53;80,114 72,114 59,127 66,135 80,136;80,155 63,155 62,174 80,174;63,155 60,152 52,152 42,163 43,174 62,174;22,157 15,154 0,158 0,174 21,174;42,163 28,155 22,157 21,174 43,174;65,92 62,77 46,78 45,90 60,97;35,124 35,117 20,109 14,116 19,132 25,133\nSwamp:66,135 59,127 57,127 47,132 44,142 52,152 60,152;28,155 32,145 25,133 19,132 13,136 15,154 22,157;52,152 44,142 32,145 28,155 42,163;80,155 80,136 66,135 60,152 63,155;15,154 13,136 0,136 0,158",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 22,
            "y": 154
          },
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 154
          }
        ],
        "geysers": [
          {
            "id": "slush_water",
            "x": 52,
            "y": 64,
            "emitRate": 9697,
            "avgEmitRate": 1072,
            "idleTime": 260,
            "eruptionTime": 75,
            "dormancyCycles": 65.1,
            "activeCycles": 63.6
          },
          {
            "id": "filthy_water",
            "x": 31,
            "y": 90,
            "emitRate": 12881,
            "avgEmitRate": 3270,
            "idleTime": 308,
            "eruptionTime": 197,
            "dormancyCycles": 44.3,
            "activeCycles": 82.2
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:96,76 96,69 80,62 72,67 74,78 80,82;96,47 82,50 80,62 96,69;74,78 72,67 71,66 53,72 52,75 62,87;34,76 32,62 21,60 15,66 17,79 21,83;21,60 17,48 0,50 0,64 15,66;17,79 15,66 0,64 0,84\nSandstone:52,75 53,72 48,59 39,57 32,62 34,76 42,80\nFrozenWastes:42,80 34,76 21,83 22,96 42,96;96,76 80,82 81,96 96,96;62,87 52,75 42,80 42,96 61,96;80,82 74,78 62,87 61,96 81,96;21,83 17,79 0,84 0,96 22,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 76,
            "y": 78
          },
          {
            "id": "GravitasPedestal",
            "x": 80,
            "y": 78
          },
          {
            "id": "GeneShuffler",
            "x": 78,
            "y": 72
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 48,
            "y": 84,
            "emitRate": 4377,
            "avgEmitRate": 1206,
            "idleTime": 238,
            "eruptionTime": 286,
            "dormancyCycles": 54.6,
            "activeCycles": 55.6
          },
          {
            "id": "steam",
            "x": 14,
            "y": 85,
            "emitRate": 4925,
            "avgEmitRate": 1136,
            "idleTime": 174,
            "eruptionTime": 121,
            "dormancyCycles": 65.5,
            "activeCycles": 84.6
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaForestDefault",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumSandyRadioactiveVanillaWarpPlanet",
        "q": -2,
        "r": 3
      },
      {
        "id": "TundraMoonlet",
        "q": 5,
        "r": -1
      },
      {
        "id": "MarshyMoonlet",
        "q": -1,
        "r": -5
      },
      {
        "id": "NiobiumMoonlet",
        "q": -5,
        "r": 2
      },
      {
        "id": "MooMoonlet",
        "q": -4,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": 5,
        "r": -6
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 3,
        "r": 4
      },
      {
        "id": "TemporalTear",
        "q": -9,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 2,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 6,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 8,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 3,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -4,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 11,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 11,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -10,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 2,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": -5,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -6,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -7,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 2,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 2,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 10,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 11,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": -2,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -2,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": -10,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": -9,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -9,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -9,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -5,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -4,
        "r": -3
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation1",
        "q": -2,
        "r": -1
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 10,
        "r": -7
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": -1,
        "r": 6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": -8,
        "r": 11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": -6,
        "r": -5
      }
    ]
  },
  {
    "coordinate": "V-SWMP-C-636932322-0-0-0",
    "cluster": "V-SWMP-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaSwampDefault",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "DeepOil",
          "MetalPoor"
        ],
        "biomePaths": "Swamp:139,187 146,177 150,154 142,134 131,125 110,119 89,124 75,142 71,165 80,184 100,197 119,198;169,164 159,153 150,154 146,177 159,179;159,153 165,137 154,128 142,134 150,154;154,128 153,114 137,110 131,125 142,134;137,110 134,104 110,110 110,119 131,125;110,119 110,110 101,99 93,101 87,121 89,124;163,198 165,195 159,179 146,177 139,187 147,200;71,165 75,142 61,136 55,156 64,165;89,124 87,121 66,120 60,134 61,136 75,142;147,200 139,187 119,198 122,208 138,211;122,208 119,198 100,197 96,207 105,221 110,221;96,207 100,197 80,184 72,192 78,207;80,184 71,165 64,165 55,187 55,188 72,192\nRadioactive:240,89 221,94 221,109 240,113;15,258 22,244 16,235 0,234 0,260;188,333 185,315 162,319 162,319 169,336;135,329 135,328 126,313 111,315 109,329 116,336\nToxicJungle:221,109 221,94 210,84 196,87 192,113 210,118;170,257 162,233 145,232 136,242 146,265 158,267;210,306 218,294 209,277 192,276 183,289 190,307;210,211 220,198 210,178 194,179 186,193 197,211;33,127 30,115 0,115 0,141 25,142;78,239 67,224 52,223 43,245 52,258 63,258;240,231 221,230 210,245 219,263 240,263;86,275 99,257 87,240 78,239 63,258 76,276;54,328 48,309 30,308 18,325 22,333 43,341;162,319 162,319 156,297 134,294 126,313 135,328;240,325 240,294 218,294 210,306 220,326\nWasteland:208,145 213,139 210,118 192,113 187,115 182,132 194,146;182,132 187,115 165,104 153,114 154,128 165,137;240,138 240,113 221,109 210,118 213,139;210,178 218,167 208,145 194,146 184,165 194,179;93,101 76,89 62,93 58,103 66,120 87,121;64,165 55,156 32,159 27,170 31,180 55,187;30,115 33,109 26,87 0,89 0,115;240,198 240,166 218,167 210,178 220,198;105,221 96,207 78,207 67,224 78,239 87,240;146,265 136,242 124,242 110,257 124,280 126,280;32,159 25,142 0,141 0,169 27,170;109,329 111,315 99,301 80,310 78,326 92,337\nBarren:240,89 240,65 215,63 210,84 221,94;185,80 184,63 169,56 156,62 155,82 165,90;210,84 215,63 212,59 197,55 184,63 185,80 196,87;155,82 156,62 143,54 129,59 126,79 135,88;101,99 108,83 92,62 83,66 76,89 93,101;76,89 83,66 61,56 57,58 50,78 62,93;26,87 30,81 21,58 0,57 0,89;126,79 129,59 116,50 94,59 92,62 108,83;50,78 57,58 32,47 21,58 30,81\nSandstone:194,146 182,132 165,137 159,153 169,164 184,165;192,113 196,87 185,80 165,90 165,104 187,115;66,120 58,103 33,109 30,115 33,127 60,134;55,156 61,136 60,134 33,127 25,142 32,159;124,242 110,221 105,221 87,240 99,257 110,257;31,180 27,170 0,169 0,199 19,200;78,207 72,192 55,188 45,215 52,223 67,224;29,213 19,200 0,199 0,234 16,235;124,280 110,257 99,257 86,275 100,293\nFrozenWastes:165,104 165,90 155,82 135,88 134,104 137,110 153,114;134,104 135,88 126,79 108,83 101,99 110,110;197,211 186,193 165,195 163,198 171,225 186,225;58,103 62,93 50,78 30,81 26,87 33,109;145,232 138,211 122,208 110,221 124,242 136,242;171,225 163,198 147,200 138,211 145,232 162,233;196,244 186,225 171,225 162,233 170,257 184,258;221,230 210,211 197,211 186,225 196,244 210,245;55,188 55,187 31,180 19,200 29,213 45,215;52,223 45,215 29,213 16,235 22,244 43,245;99,301 100,293 86,275 76,276 62,295 80,310;62,295 76,276 63,258 52,258 40,276 59,296;186,193 194,179 184,165 169,164 159,179 165,195;126,313 134,294 126,280 124,280 100,293 99,301 111,315;165,348 169,336 162,319 135,328 135,329 140,349 159,353\nBoggyMarsh:240,166 240,138 213,139 208,145 218,167;192,276 184,258 170,257 158,267 164,288 183,289;240,294 240,263 219,263 209,277 218,294;219,263 210,245 196,244 184,258 192,276 209,277;240,198 220,198 210,211 221,230 240,231;164,288 158,267 146,265 126,280 134,294 156,297;40,276 52,258 43,245 22,244 15,258 31,277;59,296 40,276 31,277 21,292 30,308 48,309;21,292 31,277 15,258 0,260 0,292;78,326 80,310 62,295 59,296 48,309 54,328 67,332;30,308 21,292 0,292 0,323 18,325;185,315 190,307 183,289 164,288 156,297 162,319;216,334 220,326 210,306 190,307 185,315 188,333 192,337\nOilField:44,347 43,341 22,333 15,347 26,356;15,347 22,333 18,325 0,323 0,349;240,325 220,326 216,334 219,347 240,347;115,348 116,336 109,329 92,337 91,346 106,354;192,349 192,337 188,333 169,336 165,348 187,355;219,347 216,334 192,337 192,349 214,352;68,346 67,332 54,328 43,341 44,347 53,353;91,346 92,337 78,326 67,332 68,346 80,352;140,349 135,329 116,336 115,348 133,354\nMagmaCore:26,356 15,347 0,349 0,380 25,380;53,353 44,347 26,356 25,380 54,380;106,354 91,346 80,352 80,380 105,380;159,353 140,349 133,354 133,380 158,380;80,352 68,346 53,353 54,380 80,380;240,347 219,347 214,352 216,380 240,380;214,352 192,349 187,355 187,380 216,380;133,354 115,348 106,354 105,380 133,380;187,355 165,348 159,353 158,380 187,380",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 109,
            "y": 160
          },
          {
            "id": "MassiveHeatSink",
            "x": 59,
            "y": 291
          },
          {
            "id": "MassiveHeatSink",
            "x": 81,
            "y": 282
          },
          {
            "id": "MassiveHeatSink",
            "x": 81,
            "y": 304
          },
          {
            "id": "WarpConduitSender",
            "x": 53,
            "y": 267
          },
          {
            "id": "WarpConduitReceiver",
            "x": 48,
            "y": 228
          },
          {
            "id": "GravitasPedestal",
            "x": 168,
            "y": 99
          },
          {
            "id": "WarpReceiver",
            "x": 194,
            "y": 236
          },
          {
            "id": "WarpPortal",
            "x": 193,
            "y": 230
          },
          {
            "id": "GeneShuffler",
            "x": 85,
            "y": 247
          },
          {
            "id": "GeneShuffler",
            "x": 35,
            "y": 331
          },
          {
            "id": "GeneShuffler",
            "x": 51,
            "y": 199
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 147,
            "y": 299,
            "emitRate": 4638,
            "avgEmitRate": 1910,
            "idleTime": 180,
            "eruptionTime": 291,
            "dormancyCycles": 30.1,
            "activeCycles": 60.3
          },
          {
            "id": "chlorine_gas",
            "x": 16,
            "y": 138,
            "emitRate": 297,
            "avgEmitRate": 106,
            "idleTime": 405,
            "eruptionTime": 613,
            "dormancyCycles": 51.5,
            "activeCycles": 74.3
          },
          {
            "id": "steam",
            "x": 33,
            "y": 297,
            "emitRate": 4740,
            "avgEmitRate": 1412,
            "idleTime": 258,
            "eruptionTime": 278,
            "dormancyCycles": 43.6,
            "activeCycles": 58.9
          },
          {
            "id": "methane",
            "x": 169,
            "y": 299,
            "emitRate": 409,
            "avgEmitRate": 112,
            "idleTime": 378,
            "eruptionTime": 275,
            "dormancyCycles": 52.1,
            "activeCycles": 96.4
          },
          {
            "id": "slush_water",
            "x": 161,
            "y": 214,
            "emitRate": 8264,
            "avgEmitRate": 1581,
            "idleTime": 341,
            "eruptionTime": 155,
            "dormancyCycles": 41.2,
            "activeCycles": 64.7
          },
          {
            "id": "slush_salt_water",
            "x": 30,
            "y": 90,
            "emitRate": 8262,
            "avgEmitRate": 1906,
            "idleTime": 285,
            "eruptionTime": 183,
            "dormancyCycles": 58.7,
            "activeCycles": 84.2
          },
          {
            "id": "OilWell",
            "x": 203,
            "y": 344,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 23,
            "y": 344,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 94,
            "y": 342,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "slush_salt_water",
            "x": 202,
            "y": 302,
            "emitRate": 5372,
            "avgEmitRate": 1277,
            "idleTime": 343,
            "eruptionTime": 238,
            "dormancyCycles": 43.1,
            "activeCycles": 59.8
          },
          {
            "id": "filthy_water",
            "x": 197,
            "y": 184,
            "emitRate": 11128,
            "avgEmitRate": 2619,
            "idleTime": 381,
            "eruptionTime": 257,
            "dormancyCycles": 47.1,
            "activeCycles": 66
          },
          {
            "id": "slush_salt_water",
            "x": 56,
            "y": 93,
            "emitRate": 4547,
            "avgEmitRate": 1245,
            "idleTime": 481,
            "eruptionTime": 353,
            "dormancyCycles": 47.9,
            "activeCycles": 87.8
          },
          {
            "id": "hot_co2",
            "x": 209,
            "y": 230,
            "emitRate": 355,
            "avgEmitRate": 82,
            "idleTime": 337,
            "eruptionTime": 310,
            "dormancyCycles": 42.5,
            "activeCycles": 40
          },
          {
            "id": "chlorine_gas",
            "x": 207,
            "y": 217,
            "emitRate": 429,
            "avgEmitRate": 119,
            "idleTime": 484,
            "eruptionTime": 275,
            "dormancyCycles": 28.4,
            "activeCycles": 91.1
          },
          {
            "id": "hot_po2",
            "x": 134,
            "y": 282,
            "emitRate": 258,
            "avgEmitRate": 88,
            "idleTime": 252,
            "eruptionTime": 298,
            "dormancyCycles": 66.6,
            "activeCycles": 111.8
          },
          {
            "id": "hot_hydrogen",
            "x": 200,
            "y": 100,
            "emitRate": 1706,
            "avgEmitRate": 111,
            "idleTime": 387,
            "eruptionTime": 45,
            "dormancyCycles": 60.8,
            "activeCycles": 99.9
          },
          {
            "id": "small_volcano",
            "x": 190,
            "y": 300,
            "emitRate": 159198,
            "avgEmitRate": 727,
            "idleTime": 8965,
            "eruptionTime": 63,
            "dormancyCycles": 28.7,
            "activeCycles": 54.8
          },
          {
            "id": "liquid_co2",
            "x": 169,
            "y": 245,
            "emitRate": 345,
            "avgEmitRate": 143,
            "idleTime": 151,
            "eruptionTime": 445,
            "dormancyCycles": 50.6,
            "activeCycles": 62.7
          },
          {
            "id": "molten_cobalt",
            "x": 30,
            "y": 250,
            "emitRate": 9199,
            "avgEmitRate": 270,
            "idleTime": 684,
            "eruptionTime": 38,
            "dormancyCycles": 59.1,
            "activeCycles": 72.3
          },
          {
            "id": "molten_cobalt",
            "x": 154,
            "y": 238,
            "emitRate": 8986,
            "avgEmitRate": 303,
            "idleTime": 653,
            "eruptionTime": 38,
            "dormancyCycles": 51.8,
            "activeCycles": 81.5
          },
          {
            "id": "molten_iron",
            "x": 226,
            "y": 103,
            "emitRate": 12388,
            "avgEmitRate": 255,
            "idleTime": 843,
            "eruptionTime": 36,
            "dormancyCycles": 74.6,
            "activeCycles": 74
          },
          {
            "id": "slimy_po2",
            "x": 225,
            "y": 215,
            "emitRate": 510,
            "avgEmitRate": 85,
            "idleTime": 447,
            "eruptionTime": 239,
            "dormancyCycles": 71.7,
            "activeCycles": 66.3
          },
          {
            "id": "molten_copper",
            "x": 175,
            "y": 180,
            "emitRate": 8604,
            "avgEmitRate": 298,
            "idleTime": 698,
            "eruptionTime": 46,
            "dormancyCycles": 54.1,
            "activeCycles": 70
          },
          {
            "id": "slimy_po2",
            "x": 179,
            "y": 169,
            "emitRate": 510,
            "avgEmitRate": 85,
            "idleTime": 447,
            "eruptionTime": 239,
            "dormancyCycles": 71.7,
            "activeCycles": 66.3
          },
          {
            "id": "hot_co2",
            "x": 223,
            "y": 330,
            "emitRate": 387,
            "avgEmitRate": 96,
            "idleTime": 311,
            "eruptionTime": 251,
            "dormancyCycles": 62.5,
            "activeCycles": 77.7
          },
          {
            "id": "molten_cobalt",
            "x": 49,
            "y": 180,
            "emitRate": 9482,
            "avgEmitRate": 327,
            "idleTime": 705,
            "eruptionTime": 44,
            "dormancyCycles": 54.9,
            "activeCycles": 76.3
          },
          {
            "id": "molten_gold",
            "x": 222,
            "y": 313,
            "emitRate": 9262,
            "avgEmitRate": 318,
            "idleTime": 626,
            "eruptionTime": 39,
            "dormancyCycles": 38.4,
            "activeCycles": 55.2
          },
          {
            "id": "oil_drip",
            "x": 118,
            "y": 226,
            "emitRate": 293,
            "avgEmitRate": 186,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "chlorine_gas",
            "x": 58,
            "y": 335,
            "emitRate": 364,
            "avgEmitRate": 107,
            "idleTime": 305,
            "eruptionTime": 299,
            "dormancyCycles": 48.9,
            "activeCycles": 71.3
          },
          {
            "id": "molten_iron",
            "x": 126,
            "y": 85,
            "emitRate": 8029,
            "avgEmitRate": 253,
            "idleTime": 799,
            "eruptionTime": 57,
            "dormancyCycles": 58.4,
            "activeCycles": 52.9
          },
          {
            "id": "hot_water",
            "x": 222,
            "y": 299,
            "emitRate": 10160,
            "avgEmitRate": 2823,
            "idleTime": 267,
            "eruptionTime": 207,
            "dormancyCycles": 33.4,
            "activeCycles": 58.3
          },
          {
            "id": "molten_iron",
            "x": 122,
            "y": 104,
            "emitRate": 8325,
            "avgEmitRate": 328,
            "idleTime": 612,
            "eruptionTime": 42,
            "dormancyCycles": 45,
            "activeCycles": 72.9
          },
          {
            "id": "methane",
            "x": 82,
            "y": 334,
            "emitRate": 260,
            "avgEmitRate": 116,
            "idleTime": 224,
            "eruptionTime": 529,
            "dormancyCycles": 52.9,
            "activeCycles": 91.6
          },
          {
            "id": "OilWell",
            "x": 27,
            "y": 353,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 34,
            "y": 341,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 4,
            "y": 345,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 9,
            "y": 336,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 231,
            "y": 331,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 222,
            "y": 343,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 186,
            "y": 345,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 181,
            "y": 345,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 212,
            "y": 340,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 196,
            "y": 349,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 122,
            "y": 349,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 133,
            "y": 341,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "MediumForestyRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "MetalRich",
          "Geodes"
        ],
        "biomePaths": "Forest:87,112 86,93 78,88 57,95 55,100 62,116 79,120;96,89 101,73 86,65 75,72 78,88 86,93;78,88 75,72 66,69 53,81 57,95;114,99 122,86 109,71 101,73 96,89 110,100;80,133 79,120 62,116 52,129 56,135 75,139;62,116 55,100 42,103 36,114 45,127 52,129;55,100 57,95 53,81 42,77 28,87 42,103;110,100 96,89 86,93 87,112 100,115;45,127 36,114 21,115 20,134 28,141;127,111 114,99 110,100 100,115 106,126 116,127;106,126 100,115 87,112 79,120 80,133 96,136;27,87 16,70 0,72 0,92 19,94\nRust:109,71 113,62 108,48 96,45 87,51 86,65 101,73;133,84 137,71 133,61 113,62 109,71 122,86;66,69 60,57 40,61 42,77 53,81;160,70 160,53 136,52 133,61 137,71;136,111 139,108 139,90 133,84 122,86 114,99 127,111;42,77 40,61 37,58 22,60 16,70 27,87 28,87;36,114 42,103 28,87 27,87 19,94 20,115 21,115;37,58 38,42 25,34 13,46 22,60;20,134 21,115 20,115 0,115 0,138\nOcean:86,65 87,51 72,44 63,48 60,57 66,69 75,72;133,61 136,52 133,43 118,39 108,48 113,62;60,57 63,48 49,36 38,42 37,58 40,61;160,87 139,90 139,108 160,109;160,129 160,109 139,108 136,111 142,130;142,130 136,111 127,111 116,127 122,136 138,136;160,87 160,70 137,71 133,84 139,90;20,115 19,94 0,92 0,115;160,36 140,34 133,43 136,52 160,53;16,70 22,60 13,46 0,45 0,72\nRadioactive:51,152 56,135 52,129 45,127 28,141 29,148 41,154;29,148 28,141 20,134 0,138 0,153 19,157;61,159 51,152 41,154 39,176 60,176;81,157 76,153 61,159 60,176 81,176;140,156 143,152 138,136 122,136 116,151 121,157;116,151 122,136 116,127 106,126 96,136 101,152;160,152 160,129 142,130 138,136 143,152;100,153 101,152 96,136 80,133 75,139 76,153 81,157;76,153 75,139 56,135 51,152 61,159;160,152 143,152 140,156 143,176 160,176\nFrozenWastes:41,154 29,148 19,157 22,176 39,176;19,157 0,153 0,176 22,176;121,157 116,151 101,152 100,153 103,176 118,176;100,153 81,157 81,176 103,176;140,156 121,157 118,176 143,176",
        "pointsOfInterest": [
          {
            "id": "WarpConduitSender",
            "x": 46,
            "y": 125
          },
          {
            "id": "WarpConduitReceiver",
            "x": 43,
            "y": 98
          },
          {
            "id": "WarpReceiver",
            "x": 82,
            "y": 107
          },
          {
            "id": "WarpPortal",
            "x": 60,
            "y": 107
          },
          {
            "id": "GeneShuffler",
            "x": 13,
            "y": 124
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 75,
            "y": 157,
            "emitRate": 528,
            "avgEmitRate": 150,
            "idleTime": 422,
            "eruptionTime": 356,
            "dormancyCycles": 37.2,
            "activeCycles": 60.6
          },
          {
            "id": "liquid_co2",
            "x": 149,
            "y": 149,
            "emitRate": 423,
            "avgEmitRate": 144,
            "idleTime": 384,
            "eruptionTime": 460,
            "dormancyCycles": 51.4,
            "activeCycles": 84.9
          },
          {
            "id": "salt_water",
            "x": 16,
            "y": 57,
            "emitRate": 31735,
            "avgEmitRate": 2906,
            "idleTime": 670,
            "eruptionTime": 136,
            "dormancyCycles": 74.6,
            "activeCycles": 89
          },
          {
            "id": "molten_aluminum",
            "x": 87,
            "y": 140,
            "emitRate": 8127,
            "avgEmitRate": 318,
            "idleTime": 711,
            "eruptionTime": 47,
            "dormancyCycles": 42,
            "activeCycles": 72.8
          },
          {
            "id": "hot_water",
            "x": 103,
            "y": 103,
            "emitRate": 8459,
            "avgEmitRate": 2871,
            "idleTime": 384,
            "eruptionTime": 460,
            "dormancyCycles": 51.4,
            "activeCycles": 84.9
          },
          {
            "id": "molten_gold",
            "x": 73,
            "y": 133,
            "emitRate": 8429,
            "avgEmitRate": 334,
            "idleTime": 697,
            "eruptionTime": 48,
            "dormancyCycles": 60.3,
            "activeCycles": 94.4
          },
          {
            "id": "slush_water",
            "x": 146,
            "y": 85,
            "emitRate": 4795,
            "avgEmitRate": 1183,
            "idleTime": 415,
            "eruptionTime": 332,
            "dormancyCycles": 61.2,
            "activeCycles": 76.5
          },
          {
            "id": "hot_po2",
            "x": 63,
            "y": 123,
            "emitRate": 340,
            "avgEmitRate": 117,
            "idleTime": 235,
            "eruptionTime": 304,
            "dormancyCycles": 60.3,
            "activeCycles": 94.4
          },
          {
            "id": "chlorine_gas",
            "x": 115,
            "y": 86,
            "emitRate": 250,
            "avgEmitRate": 108,
            "idleTime": 190,
            "eruptionTime": 392,
            "dormancyCycles": 55.8,
            "activeCycles": 98.4
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "RadioactiveCrust"
        ],
        "biomePaths": "FrozenWastes:64,45 64,30 49,27 43,32 43,40 50,47;64,66 64,45 50,47 47,57 56,66;43,40 43,32 32,26 22,34 25,46 28,47;47,57 50,47 43,40 28,47 35,61;25,46 22,34 16,30 0,38 0,42 18,50;33,66 35,61 28,47 25,46 18,50 14,62 19,69 25,70;45,78 33,66 25,70 28,86 35,88;14,62 18,50 0,42 0,62;57,89 46,78 45,78 35,88 38,97 48,99;38,97 35,88 28,86 18,91 17,97 20,105 31,107;28,86 25,70 19,69 10,80 18,91;52,110 48,99 38,97 31,107 33,112 46,117;46,78 56,66 47,57 35,61 33,66 45,78;17,97 18,91 10,80 0,80 0,98;64,89 57,89 48,99 52,110 64,111;64,89 64,66 56,66 46,78 57,89;33,112 31,107 20,105 14,113 20,128 26,128;46,117 33,112 26,128 47,128;64,111 52,110 46,117 47,128 64,128;19,69 14,62 0,62 0,80 10,80;20,105 17,97 0,98 0,113 14,113;14,113 0,113 0,128 20,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 28,
            "y": 41
          },
          {
            "id": "GravitasPedestal",
            "x": 54,
            "y": 110
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 21,
            "y": 119,
            "emitRate": 9828,
            "avgEmitRate": 286,
            "idleTime": 717,
            "eruptionTime": 41,
            "dormancyCycles": 96.6,
            "activeCycles": 112.8
          },
          {
            "id": "molten_iron",
            "x": 36,
            "y": 56,
            "emitRate": 7010,
            "avgEmitRate": 290,
            "idleTime": 680,
            "eruptionTime": 52,
            "dormancyCycles": 56.7,
            "activeCycles": 78.2
          },
          {
            "id": "molten_iron",
            "x": 18,
            "y": 61,
            "emitRate": 7377,
            "avgEmitRate": 354,
            "idleTime": 732,
            "eruptionTime": 53,
            "dormancyCycles": 41.1,
            "activeCycles": 100.9
          },
          {
            "id": "molten_iron",
            "x": 52,
            "y": 78,
            "emitRate": 7651,
            "avgEmitRate": 300,
            "idleTime": 734,
            "eruptionTime": 50,
            "dormancyCycles": 44.1,
            "activeCycles": 71.7
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "BoggyMarsh:34,48 34,34 20,30 14,40 18,47 33,49;52,44 48,32 41,28 34,34 34,48 47,49;50,63 47,49 34,48 33,49 31,65 31,65 46,67;64,45 52,44 47,49 50,63 64,63;64,63 50,63 46,67 48,79 64,82;64,24 48,32 52,44 64,45;48,79 46,67 31,65 30,79 42,86;18,47 14,40 0,39 0,59 13,59\nToxicJungle:33,49 18,47 13,59 16,63 31,65;30,79 31,65 31,65 16,63 13,78 21,84;13,78 16,63 13,59 0,59 0,80\nFrozenWastes:64,82 48,79 42,86 42,96 64,96;21,84 13,78 0,80 0,96 20,96;42,86 30,79 21,84 20,96 42,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 26,
            "y": 45
          },
          {
            "id": "GravitasPedestal",
            "x": 12,
            "y": 45
          },
          {
            "id": "SapTree",
            "x": 19,
            "y": 45
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 18,
            "y": 72,
            "emitRate": 9364,
            "avgEmitRate": 323,
            "idleTime": 601,
            "eruptionTime": 39,
            "dormancyCycles": 48.7,
            "activeCycles": 62.7
          },
          {
            "id": "molten_tungsten",
            "x": 55,
            "y": 91,
            "emitRate": 10005,
            "avgEmitRate": 281,
            "idleTime": 710,
            "eruptionTime": 36,
            "dormancyCycles": 40,
            "activeCycles": 55.9
          },
          {
            "id": "molten_tungsten",
            "x": 7,
            "y": 88,
            "emitRate": 8335,
            "avgEmitRate": 276,
            "idleTime": 642,
            "eruptionTime": 45,
            "dormancyCycles": 62.9,
            "activeCycles": 64.9
          },
          {
            "id": "chlorine_gas",
            "x": 27,
            "y": 58,
            "emitRate": 370,
            "avgEmitRate": 126,
            "idleTime": 327,
            "eruptionTime": 399,
            "dormancyCycles": 39.6,
            "activeCycles": 63.9
          },
          {
            "id": "chlorine_gas",
            "x": 52,
            "y": 57,
            "emitRate": 286,
            "avgEmitRate": 103,
            "idleTime": 221,
            "eruptionTime": 355,
            "dormancyCycles": 60.3,
            "activeCycles": 84.1
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:47,47 52,38 47,30 33,30 32,31 33,47 36,49;55,58 47,47 36,49 36,64 47,66;33,47 32,31 16,31 16,47 19,49;32,78 32,67 18,64 16,65 15,80 21,85;51,78 47,66 36,64 32,67 32,78 43,85;21,85 15,80 0,83 0,96 21,96;16,47 16,31 16,31 0,32 0,48;64,79 51,78 43,85 44,96 64,96;64,37 52,38 47,47 55,58 64,58;64,58 55,58 47,66 51,78 64,79;15,80 16,65 0,63 0,83\nOilField:36,64 36,49 33,47 19,49 18,64 32,67;18,64 19,49 16,47 0,48 0,63 16,65;43,85 32,78 21,85 21,96 44,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 23,
            "y": 51
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 13,
            "y": 63,
            "emitRate": 262153,
            "avgEmitRate": 1111,
            "idleTime": 8831,
            "eruptionTime": 69,
            "dormancyCycles": 56.9,
            "activeCycles": 68
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:81,60 75,47 73,46 58,56 58,63 58,64 77,66;51,47 55,36 50,30 36,32 33,42 38,49;96,39 85,39 75,47 81,60 96,60;73,46 65,35 55,36 51,47 58,56;58,63 58,56 51,47 38,49 36,61 39,65;36,61 38,49 33,42 19,44 17,59 19,62;17,59 19,44 15,39 0,39 0,60\nFrozenWastes:58,64 58,63 39,65 38,80 58,80;96,60 81,60 77,66 79,80 96,80;77,66 58,64 58,80 79,80;39,65 36,61 19,62 18,80 38,80;19,62 17,59 0,60 0,80 18,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 28,
            "y": 54
          },
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 54
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 69,
            "y": 58,
            "emitRate": 347,
            "avgEmitRate": 102,
            "idleTime": 308,
            "eruptionTime": 304,
            "dormancyCycles": 36.2,
            "activeCycles": 52.3
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,49 70,49 62,63 68,72 80,72;80,94 80,72 68,72 60,84 69,94;70,49 62,42 55,41 47,57 51,62 62,63;60,84 68,72 62,63 51,62 44,75 53,85;69,94 60,84 53,85 46,98 53,107 61,107;46,98 53,85 44,75 36,76 30,88 38,98;44,75 51,62 47,57 35,54 27,65 36,76;55,41 48,35 42,35 32,47 35,54 47,57;80,94 69,94 61,107 68,116 80,117;27,65 35,54 32,47 21,43 12,54 21,66;80,136 80,117 68,116 58,130 59,133 65,138;30,88 36,76 27,65 21,66 13,76 20,88;13,76 21,66 12,54 0,54 0,76;45,120 53,107 46,98 38,98 30,110 40,121;30,110 38,98 30,88 20,88 15,97 21,111;68,116 61,107 53,107 45,120 58,130;15,97 20,88 13,76 0,76 0,98;21,111 15,97 0,98 0,116 17,116;33,132 40,121 30,110 21,111 17,116 20,132 20,132;20,132 17,116 0,116 0,132;18,146 20,132 20,132 0,132 0,147;40,163 30,154 23,154 19,161 21,174 40,174;19,161 0,160 0,174 21,174;21,43 18,36 14,32 0,34 0,54 12,54;60,158 52,155 40,163 40,174 61,174;59,133 58,130 45,120 40,121 33,132 39,141 47,143;80,157 65,155 60,158 61,174 80,174\nSwamp:30,154 39,141 33,132 20,132 18,146 23,154;65,155 65,138 59,133 47,143 52,155 60,158;23,154 18,146 0,147 0,160 19,161;52,155 47,143 39,141 30,154 40,163;80,136 65,138 65,155 80,157",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 52,
            "y": 144
          },
          {
            "id": "GravitasPedestal",
            "x": 54,
            "y": 144
          }
        ],
        "geysers": [
          {
            "id": "slush_water",
            "x": 16,
            "y": 106,
            "emitRate": 3830,
            "avgEmitRate": 1336,
            "idleTime": 192,
            "eruptionTime": 268,
            "dormancyCycles": 24.8,
            "activeCycles": 37.1
          },
          {
            "id": "salt_water",
            "x": 40,
            "y": 117,
            "emitRate": 9096,
            "avgEmitRate": 3287,
            "idleTime": 372,
            "eruptionTime": 611,
            "dormancyCycles": 61.7,
            "activeCycles": 85.7
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:73,76 78,65 76,58 62,56 55,62 67,76;96,51 81,50 76,58 78,65 96,67;31,74 34,60 26,53 20,53 14,60 20,76;96,80 96,67 78,65 73,76 78,82;18,78 20,76 14,60 0,61 0,79\nSandstone:45,78 53,62 46,57 34,60 31,74 38,79;67,76 55,62 53,62 45,78 57,87\nFrozenWastes:57,87 45,78 38,79 35,96 57,96;78,82 73,76 67,76 57,87 57,96 77,96;38,79 31,74 20,76 18,78 20,96 35,96;96,80 78,82 77,96 96,96;18,78 0,79 0,96 20,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 65
          },
          {
            "id": "GravitasPedestal",
            "x": 25,
            "y": 65
          },
          {
            "id": "GeneShuffler",
            "x": 23,
            "y": 59
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 27,
            "y": 81,
            "emitRate": 5631,
            "avgEmitRate": 1604,
            "idleTime": 159,
            "eruptionTime": 138,
            "dormancyCycles": 50,
            "activeCycles": 79.2
          },
          {
            "id": "steam",
            "x": 71,
            "y": 86,
            "emitRate": 4019,
            "avgEmitRate": 1194,
            "idleTime": 275,
            "eruptionTime": 316,
            "dormancyCycles": 70.7,
            "activeCycles": 88.2
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaSwampDefault",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumForestyRadioactiveVanillaWarpPlanet",
        "q": -2,
        "r": 3
      },
      {
        "id": "TundraMoonlet",
        "q": 3,
        "r": -5
      },
      {
        "id": "MarshyMoonlet",
        "q": -4,
        "r": -2
      },
      {
        "id": "NiobiumMoonlet",
        "q": 5,
        "r": 1
      },
      {
        "id": "MooMoonlet",
        "q": -1,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": -7,
        "r": 7
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 7,
        "r": -4
      },
      {
        "id": "TemporalTear",
        "q": -3,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -1,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -7,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -8,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -8,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -8,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 7,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 8,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -8,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -9,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -3,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 1,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 0,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 0,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 1,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 10,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 10,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": 2,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 6,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 1,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 0,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -1,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -2,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 6,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -11,
        "r": 2
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation6",
        "q": 1,
        "r": 2
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -5,
        "r": 10
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": 4,
        "r": -3
      }
    ]
  },
  {
    "coordinate": "M-FRZ-C-2099443363-0-0-0",
    "cluster": "M-FRZ-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "MiniForestFrozenStart",
        "offsetX": 212,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalPoor",
          "RadioactiveCrust"
        ],
        "biomePaths": "Forest:85,95 89,89 90,71 85,61 69,52 53,54 46,59 39,77 40,87 55,103 68,104;53,54 50,38 39,33 29,41 31,52 46,59;93,111 85,95 68,104 72,116 86,120;46,59 31,52 20,62 21,71 39,77\nToxicJungle:128,57 128,38 110,38 105,49 110,58;128,77 111,78 106,90 111,97 128,97;31,52 29,41 20,37 3,51 20,62;128,116 109,116 106,134 106,134 128,136;89,131 86,120 72,116 62,129 64,133 84,138;32,115 34,113 31,95 20,93 10,104 19,116;11,130 19,116 10,104 0,103 0,131\nRust:110,38 105,28 89,27 85,35 93,49 105,49;106,69 110,58 105,49 93,49 85,61 90,71;128,77 128,57 110,58 106,69 111,78;93,49 85,35 73,38 69,52 85,61;69,52 73,38 62,29 50,38 53,54;111,97 106,90 89,89 85,95 93,111 105,111;111,78 106,69 90,71 89,89 106,90;109,116 105,111 93,111 86,120 89,131 106,134;128,116 128,97 111,97 105,111 109,116;21,71 20,62 3,51 0,51 0,78 14,79;40,87 39,77 21,71 14,79 20,93 31,95;72,116 68,104 55,103 49,111 56,128 62,129;56,128 49,111 34,113 32,115 35,132 43,135;49,111 55,103 40,87 31,95 34,113;20,93 14,79 0,78 0,103 10,104;35,132 32,115 19,116 11,130 21,139\nMagmaCore:106,134 106,134 89,131 84,138 85,153 105,153;128,136 106,134 105,153 128,153;84,138 64,133 61,153 85,153;64,133 62,129 56,128 43,135 45,153 61,153;43,135 35,132 21,139 20,153 45,153;21,139 11,130 0,131 0,153 20,153",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 62,
            "y": 80
          },
          {
            "id": "WarpConduitReceiver",
            "x": 74,
            "y": 106
          },
          {
            "id": "WarpConduitSender",
            "x": 27,
            "y": 127
          },
          {
            "id": "GravitasPedestal",
            "x": 113,
            "y": 112
          },
          {
            "id": "PropSurfaceSatellite3",
            "x": 27,
            "y": 58
          },
          {
            "id": "WarpReceiver",
            "x": 36,
            "y": 86
          },
          {
            "id": "WarpPortal",
            "x": 36,
            "y": 81
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 15,
            "y": 107,
            "emitRate": 832,
            "avgEmitRate": 101,
            "idleTime": 353,
            "eruptionTime": 81,
            "dormancyCycles": 62,
            "activeCycles": 114.8
          },
          {
            "id": "slush_water",
            "x": 110,
            "y": 72,
            "emitRate": 3192,
            "avgEmitRate": 1206,
            "idleTime": 191,
            "eruptionTime": 324,
            "dormancyCycles": 55.7,
            "activeCycles": 83.9
          },
          {
            "id": "hot_co2",
            "x": 95,
            "y": 54,
            "emitRate": 244,
            "avgEmitRate": 118,
            "idleTime": 122,
            "eruptionTime": 330,
            "dormancyCycles": 38.8,
            "activeCycles": 75.7
          },
          {
            "id": "hot_hydrogen",
            "x": 54,
            "y": 110,
            "emitRate": 502,
            "avgEmitRate": 102,
            "idleTime": 408,
            "eruptionTime": 267,
            "dormancyCycles": 65.4,
            "activeCycles": 69.4
          },
          {
            "id": "hot_po2",
            "x": 61,
            "y": 39,
            "emitRate": 328,
            "avgEmitRate": 104,
            "idleTime": 282,
            "eruptionTime": 349,
            "dormancyCycles": 47.5,
            "activeCycles": 62.8
          }
        ]
      },
      {
        "id": "MiniBadlandsWarp",
        "offsetX": 82,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "BouldersMixed"
        ],
        "biomePaths": "Sandstone:93,107 102,87 94,70 76,60 58,64 48,78 48,96 60,112 77,114;128,99 128,74 118,73 104,87 116,100;128,126 128,99 116,100 107,112 118,126;99,133 95,129 81,129 79,131 85,153 92,153;79,131 81,129 77,114 60,112 56,117 58,131 65,135;60,112 48,96 38,100 35,115 56,117;58,131 56,117 35,115 34,115 35,135 41,139;26,90 38,75 33,65 20,64 12,78 23,90;12,78 20,64 15,57 0,57 0,79;48,96 48,78 38,75 26,90 38,100\nToxicJungle:118,73 108,61 102,61 94,70 102,87 104,87;94,47 96,40 92,34 74,33 70,41 77,53;107,112 116,100 104,87 102,87 93,107 100,113;128,48 118,48 108,61 118,73 128,74;118,48 109,38 96,40 94,47 102,61 108,61;95,129 100,113 93,107 77,114 81,129;118,126 107,112 100,113 95,129 99,133 110,134;110,134 99,133 92,153 115,153;102,61 94,47 77,53 76,60 94,70;76,60 77,53 70,41 58,42 53,56 58,64;39,55 33,44 21,44 15,57 20,64 33,65;58,64 53,56 39,55 33,65 38,75 48,78;79,131 65,135 65,153 85,153;33,44 39,36 32,22 22,20 16,37 21,44;58,42 52,35 39,36 33,44 39,55 53,56;41,139 35,135 21,140 19,153 42,153;21,44 16,37 0,37 0,57 15,57\nOilField:128,126 118,126 110,134 115,153 128,153;65,135 58,131 41,139 42,153 65,153;35,135 34,115 21,114 10,129 21,140;35,115 38,100 26,90 23,90 13,103 21,114 34,115;10,129 21,114 13,103 0,102 0,130;21,140 10,129 0,130 0,153 19,153;23,90 12,78 0,79 0,102 13,103",
        "pointsOfInterest": [
          {
            "id": "WarpConduitReceiver",
            "x": 64,
            "y": 124
          },
          {
            "id": "WarpConduitSender",
            "x": 102,
            "y": 92
          },
          {
            "id": "WarpPortal",
            "x": 62,
            "y": 93
          },
          {
            "id": "WarpReceiver",
            "x": 84,
            "y": 93
          },
          {
            "id": "GeneShuffler",
            "x": 40,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 22,
            "y": 110,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 21,
            "y": 129,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 25,
            "y": 96,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 11,
            "y": 82,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 59,
            "y": 138,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "steam",
            "x": 36,
            "y": 66,
            "emitRate": 5163,
            "avgEmitRate": 1357,
            "idleTime": 236,
            "eruptionTime": 230,
            "dormancyCycles": 56.7,
            "activeCycles": 64.6
          },
          {
            "id": "methane",
            "x": 91,
            "y": 122,
            "emitRate": 256,
            "avgEmitRate": 93,
            "idleTime": 328,
            "eruptionTime": 458,
            "dormancyCycles": 39.7,
            "activeCycles": 65.5
          },
          {
            "id": "filthy_water",
            "x": 102,
            "y": 66,
            "emitRate": 8069,
            "avgEmitRate": 2894,
            "idleTime": 291,
            "eruptionTime": 327,
            "dormancyCycles": 41,
            "activeCycles": 86.2
          },
          {
            "id": "oil_drip",
            "x": 64,
            "y": 54,
            "emitRate": 207,
            "avgEmitRate": 113,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.3,
            "activeCycles": 0.4
          },
          {
            "id": "liquid_co2",
            "x": 86,
            "y": 53,
            "emitRate": 497,
            "avgEmitRate": 152,
            "idleTime": 329,
            "eruptionTime": 333,
            "dormancyCycles": 62.7,
            "activeCycles": 97.2
          }
        ]
      },
      {
        "id": "MiniMetallicSwampy",
        "offsetX": 342,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "DistressSignal"
        ],
        "biomePaths": "Swamp:110,51 110,35 101,28 88,40 90,51 108,53;93,76 86,56 69,59 67,62 73,79;128,29 110,35 110,51 128,51;68,87 73,79 67,62 49,66 45,76 61,89;128,51 110,51 108,53 109,68 128,74;109,68 108,53 90,51 86,56 93,76 97,79;45,76 49,66 42,54 26,54 21,63 23,72 41,78;26,54 21,42 0,42 0,61 21,63;23,72 21,63 0,61 0,83 15,84;42,54 48,41 45,34 25,33 21,42 26,54\nMetallic:128,86 128,74 109,68 97,79 97,82 110,92;128,86 110,92 108,109 128,112;86,102 97,82 97,79 93,76 73,79 68,87 82,102;41,78 23,72 15,84 21,94 35,95;22,117 14,107 0,107 0,130 17,130\nBoggyMarsh:86,56 90,51 88,40 73,32 63,44 69,59;108,109 110,92 97,82 86,102 104,112;128,131 128,112 108,109 104,112 102,121 112,132;76,112 82,102 68,87 61,89 54,103 61,113;67,62 69,59 63,44 48,41 42,54 49,66;102,121 104,112 86,102 82,102 76,112 82,128 88,129;54,103 61,89 45,76 41,78 35,95 41,103;61,113 54,103 41,103 36,114 44,127 54,128;36,114 41,103 35,95 21,94 14,107 22,117;82,128 76,112 61,113 54,128 58,132 75,135;44,127 36,114 22,117 17,130 20,134 36,136;14,107 21,94 15,84 0,83 0,107\nMagmaCore:112,132 102,121 88,129 99,153 103,153;88,129 82,128 75,135 76,153 99,153;128,131 112,132 103,153 128,153;75,135 58,132 54,153 76,153;36,136 20,134 17,153 39,153;58,132 54,128 44,127 36,136 39,153 54,153;20,134 17,130 0,130 0,153 17,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 89,
            "y": 83
          }
        ],
        "geysers": [
          {
            "id": "methane",
            "x": 39,
            "y": 101,
            "emitRate": 299,
            "avgEmitRate": 93,
            "idleTime": 149,
            "eruptionTime": 236,
            "dormancyCycles": 44.7,
            "activeCycles": 45.8
          },
          {
            "id": "molten_gold",
            "x": 113,
            "y": 106,
            "emitRate": 12897,
            "avgEmitRate": 294,
            "idleTime": 714,
            "eruptionTime": 31,
            "dormancyCycles": 67.7,
            "activeCycles": 84.5
          },
          {
            "id": "molten_cobalt",
            "x": 76,
            "y": 87,
            "emitRate": 8289,
            "avgEmitRate": 250,
            "idleTime": 594,
            "eruptionTime": 37,
            "dormancyCycles": 100.2,
            "activeCycles": 108.3
          },
          {
            "id": "molten_cobalt",
            "x": 37,
            "y": 81,
            "emitRate": 7820,
            "avgEmitRate": 242,
            "idleTime": 710,
            "eruptionTime": 42,
            "dormancyCycles": 78.3,
            "activeCycles": 95.7
          },
          {
            "id": "molten_aluminum",
            "x": 58,
            "y": 61,
            "emitRate": 11961,
            "avgEmitRate": 315,
            "idleTime": 727,
            "eruptionTime": 32,
            "dormancyCycles": 52,
            "activeCycles": 87.3
          },
          {
            "id": "molten_copper",
            "x": 20,
            "y": 87,
            "emitRate": 8050,
            "avgEmitRate": 281,
            "idleTime": 760,
            "eruptionTime": 43,
            "dormancyCycles": 26.3,
            "activeCycles": 47.9
          },
          {
            "id": "methane",
            "x": 12,
            "y": 113,
            "emitRate": 321,
            "avgEmitRate": 100,
            "idleTime": 311,
            "eruptionTime": 340,
            "dormancyCycles": 59.7,
            "activeCycles": 86.9
          },
          {
            "id": "small_volcano",
            "x": 62,
            "y": 118,
            "emitRate": 176019,
            "avgEmitRate": 695,
            "idleTime": 9771,
            "eruptionTime": 61,
            "dormancyCycles": 42.5,
            "activeCycles": 75.1
          }
        ]
      },
      {
        "id": "MiniFlipped",
        "offsetX": 472,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalCaves",
          "BouldersSmall"
        ],
        "biomePaths": "MagmaCore:128,44 114,45 104,63 111,71 128,72;114,45 103,34 86,43 92,62 104,63;92,62 86,43 81,41 66,48 68,68 85,69;68,68 66,48 58,44 47,47 42,65 46,71 65,71;47,47 37,39 22,42 18,50 25,64 42,65;25,64 18,50 0,51 0,74 18,75\nWasteland:128,72 111,71 104,86 108,92 128,93;111,71 104,63 92,62 85,69 89,84 104,86;85,106 82,91 67,89 59,96 65,111 80,112;67,89 65,71 46,71 43,82 52,94 59,96;89,84 85,69 68,68 65,71 67,89 82,91;43,82 46,71 42,65 25,64 18,75 23,86 27,87;65,111 59,96 52,94 37,109 41,115 58,119;52,94 43,82 27,87 33,107 37,109;108,92 104,86 89,84 82,91 85,106 103,108;23,86 18,75 0,74 0,100 5,101;33,107 27,87 23,86 5,101 20,112\nFrozenWastes:128,93 108,92 103,108 106,113 128,113;128,131 128,113 106,113 103,127 109,133;35,131 41,115 37,109 33,107 20,112 16,128 22,133;83,128 80,112 65,111 58,119 60,131 68,135;103,127 106,113 103,108 85,106 80,112 83,128 88,131;16,128 20,112 5,101 0,100 0,129;60,131 58,119 41,115 35,131 45,140\nSandstone:68,135 60,131 45,140 44,153 69,153;109,133 103,127 88,131 89,153 107,153;128,131 109,133 107,153 128,153;45,140 35,131 22,133 20,153 44,153;22,133 16,128 0,129 0,153 20,153;88,131 83,128 68,135 69,153 89,153",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 50,
            "y": 129
          },
          {
            "id": "MassiveHeatSink",
            "x": 72,
            "y": 134
          },
          {
            "id": "MassiveHeatSink",
            "x": 116,
            "y": 117
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 29,
            "y": 56,
            "emitRate": 7025,
            "avgEmitRate": 1698,
            "idleTime": 360,
            "eruptionTime": 239,
            "dormancyCycles": 44.5,
            "activeCycles": 68.6
          },
          {
            "id": "hot_po2",
            "x": 20,
            "y": 107,
            "emitRate": 419,
            "avgEmitRate": 105,
            "idleTime": 454,
            "eruptionTime": 333,
            "dormancyCycles": 51.3,
            "activeCycles": 75.2
          },
          {
            "id": "slimy_po2",
            "x": 115,
            "y": 128,
            "emitRate": 227,
            "avgEmitRate": 108,
            "idleTime": 182,
            "eruptionTime": 455,
            "dormancyCycles": 37.8,
            "activeCycles": 74.4
          },
          {
            "id": "big_volcano",
            "x": 33,
            "y": 113,
            "emitRate": 275880,
            "avgEmitRate": 1142,
            "idleTime": 10329,
            "eruptionTime": 70,
            "dormancyCycles": 22.5,
            "activeCycles": 36.1
          }
        ]
      },
      {
        "id": "MiniRadioactiveOcean",
        "offsetX": 602,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "Forest:128,44 111,44 105,64 128,66;128,106 128,85 107,86 104,89 110,107;24,47 18,40 0,41 0,63 17,63;24,73 17,63 0,63 0,86 17,86;106,112 110,107 104,89 90,91 85,107 92,113\nOcean:98,64 86,47 77,48 71,64 83,74;71,64 77,48 67,39 58,41 52,57 61,66;104,89 107,86 104,65 98,64 83,74 82,83 90,91;82,83 83,74 71,64 61,66 56,82 64,90;35,72 42,59 31,47 24,47 17,63 24,73;56,82 61,66 52,57 42,59 35,72 44,83;64,100 64,90 56,82 44,83 37,97 45,108 48,109;44,83 35,72 24,73 17,86 24,96 37,97;85,107 90,91 82,83 64,90 64,100 74,109;105,64 111,44 106,38 93,38 86,47 98,64 104,65\nRadioactive:128,85 128,66 105,64 104,65 107,86;88,131 92,113 85,107 74,109 68,126 73,131;68,126 74,109 64,100 48,109 57,126;17,108 24,96 17,86 0,86 0,109;112,129 106,112 92,113 88,131 91,133 108,134;51,133 57,126 48,109 45,108 29,123 38,133;128,129 128,106 110,107 106,112 112,129;45,108 37,97 24,96 17,108 26,123 29,123;26,123 17,108 0,109 0,131 16,132\nFrozenWastes:73,131 68,126 57,126 51,133 57,153 69,153;91,133 88,131 73,131 69,153 89,153;128,129 112,129 108,134 111,153 128,153;108,134 91,133 89,153 111,153;38,133 29,123 26,123 16,132 22,153 31,153;16,132 0,131 0,153 22,153;51,133 38,133 31,153 57,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 123
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 27,
            "y": 117,
            "emitRate": 1354,
            "avgEmitRate": 181,
            "idleTime": 356,
            "eruptionTime": 93,
            "dormancyCycles": 47.4,
            "activeCycles": 86.4
          },
          {
            "id": "liquid_co2",
            "x": 110,
            "y": 115,
            "emitRate": 498,
            "avgEmitRate": 131,
            "idleTime": 208,
            "eruptionTime": 192,
            "dormancyCycles": 65.5,
            "activeCycles": 79.6
          },
          {
            "id": "salt_water",
            "x": 61,
            "y": 73,
            "emitRate": 10175,
            "avgEmitRate": 2402,
            "idleTime": 159,
            "eruptionTime": 164,
            "dormancyCycles": 58.4,
            "activeCycles": 50.7
          },
          {
            "id": "slush_water",
            "x": 13,
            "y": 117,
            "emitRate": 6102,
            "avgEmitRate": 1522,
            "idleTime": 194,
            "eruptionTime": 139,
            "dormancyCycles": 54.2,
            "activeCycles": 80.4
          },
          {
            "id": "slush_water",
            "x": 22,
            "y": 61,
            "emitRate": 4646,
            "avgEmitRate": 1644,
            "idleTime": 193,
            "eruptionTime": 229,
            "dormancyCycles": 43.9,
            "activeCycles": 82
          },
          {
            "id": "hot_steam",
            "x": 11,
            "y": 45,
            "emitRate": 2931,
            "avgEmitRate": 590,
            "idleTime": 387,
            "eruptionTime": 244,
            "dormancyCycles": 62.6,
            "activeCycles": 67.9
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 732,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "DistressSignal",
          "LushCore"
        ],
        "biomePaths": "FrozenWastes:64,52 64,37 46,34 43,36 43,54;43,54 43,36 29,32 21,40 21,48 41,56;64,72 64,52 43,54 41,56 41,58 51,72;41,58 41,56 21,48 14,55 20,70 29,70;51,72 41,58 29,70 36,83 43,84;64,72 51,72 43,84 49,92 64,92;20,70 14,55 0,55 0,78 12,78;36,83 29,70 20,70 12,78 19,91 27,92;64,110 64,92 49,92 44,106 48,111;44,106 49,92 43,84 36,83 27,92 32,106;19,91 12,78 0,78 0,103 8,103;29,110 32,106 27,92 19,91 8,103 17,112\nForest:48,111 44,106 32,106 29,110 33,128 46,128;29,110 17,112 12,128 33,128;17,112 8,103 0,103 0,128 12,128;64,110 48,111 46,128 64,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 31,
            "y": 68
          },
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 90
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 24,
            "y": 101,
            "emitRate": 8701,
            "avgEmitRate": 261,
            "idleTime": 793,
            "eruptionTime": 49,
            "dormancyCycles": 72.3,
            "activeCycles": 77.3
          },
          {
            "id": "molten_iron",
            "x": 52,
            "y": 59,
            "emitRate": 7922,
            "avgEmitRate": 278,
            "idleTime": 700,
            "eruptionTime": 42,
            "dormancyCycles": 54,
            "activeCycles": 89.8
          },
          {
            "id": "molten_iron",
            "x": 51,
            "y": 98,
            "emitRate": 8126,
            "avgEmitRate": 286,
            "idleTime": 751,
            "eruptionTime": 46,
            "dormancyCycles": 39.9,
            "activeCycles": 64
          },
          {
            "id": "molten_iron",
            "x": 51,
            "y": 109,
            "emitRate": 7450,
            "avgEmitRate": 309,
            "idleTime": 700,
            "eruptionTime": 50,
            "dormancyCycles": 59.3,
            "activeCycles": 97.2
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 798,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:47,51 49,47 44,32 32,37 31,50 35,53;34,67 35,53 31,50 19,52 15,61 18,66 31,69;64,45 64,29 44,32 44,32 49,47;64,63 52,63 47,69 49,79 64,82;52,63 47,51 35,53 34,67 47,69;21,30 20,29 0,27 0,43 14,44;31,50 32,37 21,30 14,44 19,52\nToxicJungle:64,45 49,47 47,51 52,63 64,63;19,52 14,44 0,43 0,60 15,61;31,80 31,69 18,66 12,78 20,87;12,78 18,66 15,61 0,60 0,79;49,79 47,69 34,67 31,69 31,80 42,87\nMagmaCore:42,87 31,80 20,87 20,96 43,96;20,87 12,78 0,79 0,96 20,96;64,82 49,79 42,87 43,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 43
          },
          {
            "id": "GravitasPedestal",
            "x": 27,
            "y": 43
          },
          {
            "id": "SapTree",
            "x": 34,
            "y": 43
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 11,
            "y": 54,
            "emitRate": 13195,
            "avgEmitRate": 276,
            "idleTime": 803,
            "eruptionTime": 27,
            "dormancyCycles": 40.6,
            "activeCycles": 72.6
          },
          {
            "id": "slimy_po2",
            "x": 35,
            "y": 69,
            "emitRate": 417,
            "avgEmitRate": 122,
            "idleTime": 305,
            "eruptionTime": 248,
            "dormancyCycles": 36.9,
            "activeCycles": 69.9
          },
          {
            "id": "hot_co2",
            "x": 9,
            "y": 67,
            "emitRate": 321,
            "avgEmitRate": 69,
            "idleTime": 263,
            "eruptionTime": 273,
            "dormancyCycles": 53.7,
            "activeCycles": 39.6
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 864,
        "offsetY": 98,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:62,42 61,36 52,28 37,37 37,38 40,45 54,50;75,59 75,50 62,42 54,50 55,60 63,64;81,46 81,33 74,27 61,36 62,42 75,50;55,60 54,50 40,45 34,60 46,66;34,60 40,45 37,38 19,42 20,56 32,61;96,62 96,49 81,46 75,50 75,59 80,65;20,56 19,42 16,39 0,40 0,59 14,60;96,29 81,33 81,46 96,49\nFrozenWastes:80,65 75,59 63,64 64,80 79,80;63,64 55,60 46,66 46,80 64,80;32,61 20,56 14,60 16,80 28,80;14,60 0,59 0,80 16,80;46,66 34,60 32,61 28,80 46,80;96,62 80,65 79,80 96,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 20,
            "y": 71
          },
          {
            "id": "GravitasPedestal",
            "x": 13,
            "y": 71
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 83,
            "y": 44,
            "emitRate": 278,
            "avgEmitRate": 101,
            "idleTime": 200,
            "eruptionTime": 285,
            "dormancyCycles": 46,
            "activeCycles": 73.7
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:64,61 61,50 49,47 44,50 46,66 58,69;46,66 44,50 35,49 29,53 30,67 39,71;66,45 64,31 60,28 49,33 49,47 61,50;80,46 66,45 61,50 64,61 80,62;49,47 49,33 38,28 32,34 35,49 44,50;57,86 61,77 58,69 46,66 39,71 39,86;80,62 64,61 58,69 61,77 80,78;80,78 61,77 57,86 61,93 80,94;39,86 39,71 30,67 20,71 17,78 21,86 39,87;35,49 32,34 21,32 15,42 18,50 29,53;17,78 20,71 11,60 0,60 0,80;50,107 39,98 28,106 33,119 42,121;21,86 17,78 0,80 0,96 16,98;57,106 61,93 57,86 39,86 39,87 39,98 50,107;30,67 29,53 18,50 11,60 20,71;80,27 64,31 66,45 80,46;11,60 18,50 15,42 0,40 0,60;33,119 28,106 20,104 12,117 21,127;12,117 20,104 16,98 0,96 0,118;39,98 39,87 21,86 16,98 20,104 28,106;80,108 62,111 63,123 80,127;63,123 62,111 57,106 50,107 42,121 45,127 58,128;80,108 80,94 61,93 57,106 62,111;40,139 45,127 42,121 33,119 21,127 21,133 34,141;21,133 21,127 12,117 0,118 0,136 15,137;67,157 60,146 50,149 47,157 58,172;17,156 16,155 0,156 0,174 17,174;36,161 29,154 17,156 17,174 34,174;80,157 67,157 58,172 58,174 80,174;58,172 47,157 36,161 34,174 58,174;80,138 80,127 63,123 58,128 62,141\nSwamp:80,138 62,141 60,146 67,157 80,157;16,155 15,137 0,136 0,156;29,154 34,141 21,133 15,137 16,155 17,156;60,146 62,141 58,128 45,127 40,139 50,149;47,157 50,149 40,139 34,141 29,154 36,161",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 58,
            "y": 142
          },
          {
            "id": "GravitasPedestal",
            "x": 60,
            "y": 142
          }
        ],
        "geysers": [
          {
            "id": "filthy_water",
            "x": 55,
            "y": 73,
            "emitRate": 14698,
            "avgEmitRate": 4627,
            "idleTime": 423,
            "eruptionTime": 304,
            "dormancyCycles": 47.9,
            "activeCycles": 146
          },
          {
            "id": "slush_water",
            "x": 42,
            "y": 58,
            "emitRate": 3813,
            "avgEmitRate": 1364,
            "idleTime": 262,
            "eruptionTime": 299,
            "dormancyCycles": 39.2,
            "activeCycles": 80
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 798,
        "offsetY": 98,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "MagmaCore:64,31 49,31 45,39 51,47 64,47;51,47 45,39 36,40 31,48 38,60 45,60;31,48 36,40 30,32 17,32 16,33 17,49 18,50;17,49 16,33 0,33 0,50;18,64 18,50 17,49 0,50 0,64 15,67;32,78 30,67 18,64 15,67 15,80 22,86;15,80 15,67 0,64 0,84;64,63 48,64 48,79 64,81;22,86 15,80 0,84 0,96 23,96;48,79 48,64 45,60 38,60 30,67 32,78 44,82;44,82 32,78 22,86 23,96 44,96\nOilField:64,63 64,47 51,47 45,60 48,64;38,60 31,48 18,50 18,64 30,67;64,81 48,79 44,82 44,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 29,
            "y": 64
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 49,
            "y": 82,
            "emitRate": 265589,
            "avgEmitRate": 1208,
            "idleTime": 8773,
            "eruptionTime": 66,
            "dormancyCycles": 46.2,
            "activeCycles": 70.7
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 864,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Sandstone:100,64 101,54 91,48 81,53 81,63 92,70;81,63 81,53 73,47 61,53 61,62 71,69;140,74 138,64 126,57 122,59 116,73 116,74 125,80;92,78 92,70 81,63 71,69 71,77 82,85;40,62 40,53 36,49 19,53 17,55 19,63 31,69;51,78 51,69 40,62 31,69 31,78 40,85\nBarren:122,59 110,50 101,54 100,64 116,73;116,74 116,73 100,64 92,70 92,78 104,86;61,62 61,53 54,48 40,53 40,62 51,69;71,77 71,69 61,62 51,69 51,78 61,85;9,75 19,63 17,55 0,54 0,75;31,78 31,69 19,63 9,75 19,85\nFrozenWastes:143,77 140,74 125,80 125,96 143,96;160,75 143,77 143,96 160,96;125,80 116,74 104,86 105,96 125,96;104,86 92,78 82,85 82,96 105,96;40,85 31,78 19,85 18,96 40,96;61,85 51,78 40,85 40,96 61,96;82,85 71,77 61,85 61,96 82,96;19,85 9,75 0,75 0,96 18,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 52,
            "y": 63
          },
          {
            "id": "GravitasPedestal",
            "x": 56,
            "y": 63
          },
          {
            "id": "GeneShuffler",
            "x": 54,
            "y": 57
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 54,
            "y": 88,
            "emitRate": 4843,
            "avgEmitRate": 1454,
            "idleTime": 356,
            "eruptionTime": 336,
            "dormancyCycles": 43.4,
            "activeCycles": 70.4
          },
          {
            "id": "steam",
            "x": 92,
            "y": 84,
            "emitRate": 3886,
            "avgEmitRate": 1414,
            "idleTime": 145,
            "eruptionTime": 212,
            "dormancyCycles": 43,
            "activeCycles": 68.2
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "MiniBadlandsWarp",
        "q": -1,
        "r": 0
      },
      {
        "id": "MiniForestFrozenStart",
        "q": 1,
        "r": 1
      },
      {
        "id": "MiniMetallicSwampy",
        "q": -3,
        "r": 3
      },
      {
        "id": "MiniFlipped",
        "q": 4,
        "r": -2
      },
      {
        "id": "MiniRadioactiveOcean",
        "q": 2,
        "r": -3
      },
      {
        "id": "TundraMoonlet",
        "q": 4,
        "r": 5
      },
      {
        "id": "MarshyMoonlet",
        "q": -2,
        "r": -6
      },
      {
        "id": "MooMoonlet",
        "q": -6,
        "r": -1
      },
      {
        "id": "WaterMoonlet",
        "q": -9,
        "r": 9
      },
      {
        "id": "NiobiumMoonlet",
        "q": 11,
        "r": -5
      },
      {
        "id": "RegolithMoonlet",
        "q": 9,
        "r": 1
      },
      {
        "id": "TemporalTear",
        "q": 10,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -2,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 4,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -4,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -1,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -11,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -9,
        "r": 12
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -10,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 4,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -3,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": -1,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -5,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -6,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 8,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 5,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -10,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -11,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -10,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -5,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -6,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -5,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 12,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 1,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 0,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 12,
        "r": -9
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 6,
        "r": 6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": -12,
        "r": 10
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": -7,
        "r": 2
      }
    ]
  },
  {
    "coordinate": "FRST-C-858965588-0-0-0",
    "cluster": "FRST-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "ForestMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 274,
        "worldTraits": [
          "BouldersSmall",
          "BouldersLarge",
          "SubsurfaceOcean",
          "Volcanoes"
        ],
        "biomePaths": "Forest:105,166 112,151 106,127 94,118 84,116 58,127 52,143 57,165 82,178;131,150 136,144 134,132 115,121 106,127 112,151;58,127 50,116 31,118 27,135 31,142 52,143;57,165 52,143 31,142 25,158 29,165 51,169\nWasteland:160,116 151,115 134,132 136,144 160,146;160,169 160,146 136,144 131,150 136,169;151,115 142,101 115,112 115,121 134,132;86,95 84,84 70,75 55,83 54,99 81,103;65,204 64,200 50,189 31,196 29,206 33,214 52,217;127,213 131,196 116,186 99,198 98,205 116,217;29,206 31,196 22,183 0,184 0,207\nToxicJungle:132,175 136,169 131,150 112,151 105,166 116,179;84,116 81,103 54,99 50,105 50,116 58,127;22,183 29,165 25,158 0,157 0,184;160,169 136,169 132,175 135,193 160,194\nMagmaCore:135,193 132,175 116,179 116,186 131,196;115,121 115,112 106,103 104,104 94,118 106,127;160,93 144,96 142,101 151,115 160,116;104,104 86,95 81,103 84,116 94,118;50,116 50,105 30,106 28,111 31,118;137,249 121,252 118,274 156,274;160,229 150,231 137,249 156,274 160,274;24,247 22,244 0,241 0,274 19,274;48,253 24,247 19,274 50,274;96,247 84,241 72,246 74,274 94,274;121,252 110,243 96,247 94,274 118,274;72,246 60,243 48,253 50,274 74,274\nOcean:106,103 119,78 105,63 84,84 86,95 104,104;160,93 160,62 141,61 130,78 144,96;105,63 105,62 88,48 71,53 70,75 84,84;70,75 71,53 61,46 47,50 39,72 55,83;22,88 31,74 15,56 0,55 0,89;142,101 144,96 130,78 119,78 106,103 115,112;50,105 54,99 55,83 39,72 31,74 22,88 30,106\nFrozenWastes:28,111 30,106 22,88 0,89 0,114;31,118 28,111 0,114 0,134 27,135;31,142 27,135 0,134 0,157 25,158;50,189 51,169 29,165 22,183 31,196;98,205 99,198 82,180 64,200 65,204 85,213\nBarren:160,229 160,194 135,193 131,196 127,213 150,231;33,214 29,206 0,207 0,241 22,244;60,243 52,217 33,214 22,244 24,247 48,253;137,249 150,231 127,213 116,217 110,243 121,252;110,243 116,217 98,205 85,213 84,241 96,247;84,241 85,213 65,204 52,217 60,243 72,246\nRust:116,186 116,179 105,166 82,178 82,180 99,198;82,180 82,178 57,165 51,169 50,189 64,200",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 81,
            "y": 151
          },
          {
            "id": "MassiveHeatSink",
            "x": 97,
            "y": 205
          },
          {
            "id": "WarpConduitSender",
            "x": 123,
            "y": 158
          },
          {
            "id": "WarpConduitReceiver",
            "x": 47,
            "y": 199
          },
          {
            "id": "GravitasPedestal",
            "x": 16,
            "y": 93
          },
          {
            "id": "WarpPortal",
            "x": 126,
            "y": 122
          },
          {
            "id": "WarpReceiver",
            "x": 127,
            "y": 128
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 17,
            "y": 173,
            "emitRate": 7684,
            "avgEmitRate": 1270,
            "idleTime": 303,
            "eruptionTime": 159,
            "dormancyCycles": 78.4,
            "activeCycles": 72.5
          },
          {
            "id": "hot_hydrogen",
            "x": 70,
            "y": 122,
            "emitRate": 258,
            "avgEmitRate": 109,
            "idleTime": 280,
            "eruptionTime": 393,
            "dormancyCycles": 18,
            "activeCycles": 47.3
          },
          {
            "id": "slush_salt_water",
            "x": 75,
            "y": 193,
            "emitRate": 4722,
            "avgEmitRate": 1404,
            "idleTime": 246,
            "eruptionTime": 289,
            "dormancyCycles": 76.3,
            "activeCycles": 93.3
          },
          {
            "id": "slush_water",
            "x": 20,
            "y": 140,
            "emitRate": 5916,
            "avgEmitRate": 1436,
            "idleTime": 397,
            "eruptionTime": 296,
            "dormancyCycles": 54.3,
            "activeCycles": 71.4
          },
          {
            "id": "small_volcano",
            "x": 95,
            "y": 238,
            "emitRate": 146965,
            "avgEmitRate": 675,
            "idleTime": 11149,
            "eruptionTime": 77,
            "dormancyCycles": 43.5,
            "activeCycles": 88.4
          },
          {
            "id": "small_volcano",
            "x": 33,
            "y": 241,
            "emitRate": 128184,
            "avgEmitRate": 616,
            "idleTime": 10118,
            "eruptionTime": 81,
            "dormancyCycles": 55.6,
            "activeCycles": 85.7
          },
          {
            "id": "liquid_co2",
            "x": 91,
            "y": 88,
            "emitRate": 645,
            "avgEmitRate": 175,
            "idleTime": 432,
            "eruptionTime": 310,
            "dormancyCycles": 50.8,
            "activeCycles": 93.6
          },
          {
            "id": "big_volcano",
            "x": 133,
            "y": 97,
            "emitRate": 233969,
            "avgEmitRate": 1051,
            "idleTime": 8961,
            "eruptionTime": 64,
            "dormancyCycles": 55.1,
            "activeCycles": 93.7
          },
          {
            "id": "methane",
            "x": 64,
            "y": 64,
            "emitRate": 439,
            "avgEmitRate": 97,
            "idleTime": 539,
            "eruptionTime": 321,
            "dormancyCycles": 37.6,
            "activeCycles": 55.1
          },
          {
            "id": "big_volcano",
            "x": 125,
            "y": 185,
            "emitRate": 323876,
            "avgEmitRate": 1300,
            "idleTime": 8621,
            "eruptionTime": 60,
            "dormancyCycles": 46.9,
            "activeCycles": 65.3
          },
          {
            "id": "big_volcano",
            "x": 106,
            "y": 116,
            "emitRate": 229602,
            "avgEmitRate": 1070,
            "idleTime": 6953,
            "eruptionTime": 47,
            "dormancyCycles": 60.9,
            "activeCycles": 136.3
          },
          {
            "id": "big_volcano",
            "x": 39,
            "y": 112,
            "emitRate": 266605,
            "avgEmitRate": 1056,
            "idleTime": 9000,
            "eruptionTime": 60,
            "dormancyCycles": 51.2,
            "activeCycles": 76.7
          },
          {
            "id": "big_volcano",
            "x": 90,
            "y": 107,
            "emitRate": 275095,
            "avgEmitRate": 1243,
            "idleTime": 10506,
            "eruptionTime": 74,
            "dormancyCycles": 44.6,
            "activeCycles": 80.1
          }
        ]
      },
      {
        "id": "SwampyLandingSite",
        "offsetX": 244,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "Swamp:70,61 61,47 46,49 44,54 50,70 60,72;87,46 83,39 66,37 61,47 70,61 77,62;83,85 84,69 77,62 70,61 60,72 66,86;128,66 128,47 109,45 103,51 104,64 107,68;85,106 84,86 83,85 66,86 62,93 67,109 71,110;50,70 44,54 24,61 26,71 42,77;104,64 103,51 87,46 77,62 84,69;44,54 46,49 41,39 23,38 21,42 22,60 24,61;62,93 66,86 60,72 50,70 42,77 44,94;67,109 62,93 44,94 42,98 44,112 51,117;22,60 21,42 0,41 0,60\nMetallic:106,83 107,68 104,64 84,69 83,85 84,86;128,88 128,66 107,68 106,83 111,89;128,111 128,88 111,89 108,108 112,112;92,129 92,110 85,106 71,110 74,129 85,133;52,130 51,117 44,112 26,121 26,129 42,137;44,112 42,98 24,100 20,112 26,121\nBoggyMarsh:108,108 111,89 106,83 84,86 85,106 92,110;26,71 24,61 22,60 0,60 0,85 15,86;42,98 44,94 42,77 26,71 15,86 24,100;20,112 24,100 15,86 0,85 0,114\nRadioactive:128,111 112,112 108,132 128,134;107,133 92,129 85,133 86,153 105,153;108,132 112,112 108,108 92,110 92,129 107,133;85,133 74,129 64,136 65,153 86,153;64,136 52,130 42,137 42,153 65,153;128,134 108,132 107,133 105,153 128,153;74,129 71,110 67,109 51,117 52,130 64,136;42,137 26,129 21,133 21,153 42,153;26,129 26,121 20,112 0,114 0,131 21,133;21,133 0,131 0,153 21,153",
        "pointsOfInterest": [
          {
            "id": "PropSurfaceSatellite3",
            "x": 51,
            "y": 70
          }
        ],
        "geysers": [
          {
            "id": "molten_cobalt",
            "x": 89,
            "y": 124,
            "emitRate": 7447,
            "avgEmitRate": 256,
            "idleTime": 788,
            "eruptionTime": 52,
            "dormancyCycles": 72.9,
            "activeCycles": 89.6
          },
          {
            "id": "molten_gold",
            "x": 103,
            "y": 79,
            "emitRate": 5692,
            "avgEmitRate": 267,
            "idleTime": 613,
            "eruptionTime": 48,
            "dormancyCycles": 43.6,
            "activeCycles": 81.4
          },
          {
            "id": "molten_gold",
            "x": 29,
            "y": 116,
            "emitRate": 7991,
            "avgEmitRate": 263,
            "idleTime": 742,
            "eruptionTime": 41,
            "dormancyCycles": 55.1,
            "activeCycles": 93.7
          },
          {
            "id": "molten_cobalt",
            "x": 35,
            "y": 103,
            "emitRate": 9882,
            "avgEmitRate": 340,
            "idleTime": 624,
            "eruptionTime": 40,
            "dormancyCycles": 66.8,
            "activeCycles": 88.6
          },
          {
            "id": "liquid_co2",
            "x": 109,
            "y": 139,
            "emitRate": 720,
            "avgEmitRate": 161,
            "idleTime": 309,
            "eruptionTime": 187,
            "dormancyCycles": 74.9,
            "activeCycles": 109
          },
          {
            "id": "liquid_co2",
            "x": 80,
            "y": 139,
            "emitRate": 418,
            "avgEmitRate": 151,
            "idleTime": 260,
            "eruptionTime": 428,
            "dormancyCycles": 61.5,
            "activeCycles": 84.8
          },
          {
            "id": "hot_hydrogen",
            "x": 67,
            "y": 113,
            "emitRate": 250,
            "avgEmitRate": 97,
            "idleTime": 189,
            "eruptionTime": 427,
            "dormancyCycles": 57,
            "activeCycles": 72
          },
          {
            "id": "molten_copper",
            "x": 38,
            "y": 125,
            "emitRate": 7991,
            "avgEmitRate": 263,
            "idleTime": 742,
            "eruptionTime": 41,
            "dormancyCycles": 55.1,
            "activeCycles": 93.7
          },
          {
            "id": "chlorine_gas",
            "x": 114,
            "y": 117,
            "emitRate": 1065,
            "avgEmitRate": 124,
            "idleTime": 367,
            "eruptionTime": 85,
            "dormancyCycles": 54.2,
            "activeCycles": 87.8
          }
        ]
      },
      {
        "id": "OilRichWarpTarget",
        "offsetX": 374,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "IrregularOil",
          "Geodes"
        ],
        "biomePaths": "Sandstone:84,91 84,74 82,72 62,76 59,86 66,95;104,93 108,85 102,72 84,74 84,91 89,95;88,110 89,95 84,91 66,95 64,107 71,114;59,86 62,76 56,66 41,67 37,80 43,90;64,107 66,95 59,86 43,90 41,98 49,111;128,45 110,45 103,55 106,65 128,65;128,104 128,83 108,85 104,93 110,106;46,118 49,111 41,98 23,103 21,112 28,123\nRust:128,83 128,65 106,65 102,72 108,85;41,98 43,90 37,80 21,81 17,93 23,103\nOcean:102,72 106,65 103,55 85,51 81,56 82,72 84,74;56,66 61,55 57,46 41,43 36,62 41,67;82,72 81,56 61,55 56,66 62,76;22,62 14,50 0,50 0,74 16,74;36,62 41,43 40,42 21,40 14,50 22,62\nOilField:107,112 110,106 104,93 89,95 88,110 92,114;69,129 71,114 64,107 49,111 46,118 51,129 63,133;85,136 69,129 63,133 63,153 84,153;91,132 92,114 88,110 71,114 69,129 85,136;51,129 46,118 28,123 28,128 42,137;17,93 21,81 16,74 0,74 0,94;115,129 107,112 92,114 91,132 107,137;20,135 0,130 0,153 20,153;28,128 28,123 21,112 0,116 0,130 20,135;37,80 41,67 36,62 22,62 16,74 21,81;128,130 115,129 107,137 108,153 128,153;21,112 23,103 17,93 0,94 0,116;107,137 91,132 85,136 84,153 108,153;128,104 110,106 107,112 115,129 128,130\nMagmaCore:63,133 51,129 42,137 42,153 63,153;42,137 28,128 20,135 20,153 42,153",
        "pointsOfInterest": [
          {
            "id": "WarpConduitSender",
            "x": 108,
            "y": 62
          },
          {
            "id": "WarpConduitReceiver",
            "x": 114,
            "y": 101
          },
          {
            "id": "WarpPortal",
            "x": 60,
            "y": 87
          },
          {
            "id": "WarpReceiver",
            "x": 82,
            "y": 87
          },
          {
            "id": "GeneShuffler",
            "x": 107,
            "y": 121
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 104,
            "y": 143,
            "emitRate": 3677,
            "avgEmitRate": 1064,
            "idleTime": 306,
            "eruptionTime": 389,
            "dormancyCycles": 71.5,
            "activeCycles": 76.5
          },
          {
            "id": "OilWell",
            "x": 59,
            "y": 112,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 67,
            "y": 135,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 67,
            "y": 123,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 88,
            "y": 116,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 51,
            "y": 123,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "methane",
            "x": 78,
            "y": 129,
            "emitRate": 414,
            "avgEmitRate": 116,
            "idleTime": 267,
            "eruptionTime": 220,
            "dormancyCycles": 40.1,
            "activeCycles": 65.8
          },
          {
            "id": "liquid_sulfur",
            "x": 86,
            "y": 142,
            "emitRate": 3752,
            "avgEmitRate": 1330,
            "idleTime": 277,
            "eruptionTime": 423,
            "dormancyCycles": 40.9,
            "activeCycles": 58.1
          },
          {
            "id": "OilWell",
            "x": 95,
            "y": 102,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 17,
            "y": 83,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 28,
            "y": 79,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 162,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "FrozenWastes:43,71 42,58 24,54 20,61 23,73 40,76;41,87 40,76 23,73 19,79 20,92 24,97;20,61 24,54 23,50 0,42 0,62;64,54 47,53 42,58 43,71 64,73;64,87 64,73 43,71 40,76 41,87 47,91;28,39 25,33 16,29 0,39 0,42 23,50;47,53 44,40 28,39 23,50 24,54 42,58;23,73 20,61 0,62 0,76 19,79;47,108 47,91 41,87 24,97 24,99 35,110 46,110;64,87 47,91 47,108 64,109;20,92 19,79 0,76 0,95;64,34 47,35 44,40 47,53 64,54;64,109 47,108 46,110 48,128 64,128;46,110 35,110 25,128 48,128;35,110 24,99 14,111 23,128 25,128;24,99 24,97 20,92 0,95 0,110 14,111;14,111 0,110 0,128 23,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 28,
            "y": 52
          },
          {
            "id": "GravitasPedestal",
            "x": 28,
            "y": 84
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 12,
            "y": 116,
            "emitRate": 7447,
            "avgEmitRate": 256,
            "idleTime": 788,
            "eruptionTime": 52,
            "dormancyCycles": 72.9,
            "activeCycles": 89.6
          },
          {
            "id": "molten_iron",
            "x": 18,
            "y": 100,
            "emitRate": 16894,
            "avgEmitRate": 280,
            "idleTime": 696,
            "eruptionTime": 22,
            "dormancyCycles": 61.5,
            "activeCycles": 70.4
          },
          {
            "id": "molten_iron",
            "x": 36,
            "y": 107,
            "emitRate": 7685,
            "avgEmitRate": 258,
            "idleTime": 721,
            "eruptionTime": 50,
            "dormancyCycles": 82.9,
            "activeCycles": 90.6
          },
          {
            "id": "molten_iron",
            "x": 45,
            "y": 68,
            "emitRate": 9864,
            "avgEmitRate": 321,
            "idleTime": 747,
            "eruptionTime": 40,
            "dormancyCycles": 36.8,
            "activeCycles": 64.8
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 228,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:41,42 42,38 24,29 17,39 20,47 31,51;64,52 64,33 47,32 42,38 41,42 52,53;64,73 64,52 52,53 44,66 47,74;46,76 47,74 44,66 33,62 22,70 21,74 33,82;12,57 20,47 17,39 0,36 0,58\nToxicJungle:52,53 41,42 31,51 33,62 44,66;33,62 31,51 20,47 12,57 22,70;21,74 22,70 12,57 0,58 0,77 16,78\nMagmaCore:64,73 47,74 46,76 52,96 64,96;46,76 33,82 32,96 52,96;16,78 0,77 0,96 16,96;33,82 21,74 16,78 16,96 32,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 28,
            "y": 44
          },
          {
            "id": "GravitasPedestal",
            "x": 14,
            "y": 44
          },
          {
            "id": "SapTree",
            "x": 21,
            "y": 44
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 50,
            "y": 47,
            "emitRate": 11383,
            "avgEmitRate": 363,
            "idleTime": 657,
            "eruptionTime": 34,
            "dormancyCycles": 12.3,
            "activeCycles": 23.5
          },
          {
            "id": "molten_tungsten",
            "x": 17,
            "y": 89,
            "emitRate": 7825,
            "avgEmitRate": 384,
            "idleTime": 642,
            "eruptionTime": 46,
            "dormancyCycles": 54.3,
            "activeCycles": 150.2
          },
          {
            "id": "molten_tungsten",
            "x": 5,
            "y": 90,
            "emitRate": 8799,
            "avgEmitRate": 299,
            "idleTime": 696,
            "eruptionTime": 47,
            "dormancyCycles": 48.6,
            "activeCycles": 55.4
          },
          {
            "id": "hot_hydrogen",
            "x": 23,
            "y": 63,
            "emitRate": 451,
            "avgEmitRate": 101,
            "idleTime": 378,
            "eruptionTime": 231,
            "dormancyCycles": 61.6,
            "activeCycles": 89
          },
          {
            "id": "slimy_po2",
            "x": 10,
            "y": 69,
            "emitRate": 281,
            "avgEmitRate": 115,
            "idleTime": 328,
            "eruptionTime": 534,
            "dormancyCycles": 49.5,
            "activeCycles": 95.4
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 504,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:79,60 79,60 76,41 68,41 57,52 59,60 62,62;68,41 59,30 48,35 48,46 57,52;96,59 96,40 78,40 76,41 79,60;30,47 30,35 20,29 10,40 18,50;59,60 57,52 48,46 36,52 37,62 43,67;37,62 36,52 30,47 18,50 15,60 22,68;48,46 48,35 40,29 30,35 30,47 36,52;15,60 18,50 10,40 0,40 0,62\nFrozenWastes:62,62 59,60 43,67 43,80 63,80;43,67 37,62 22,68 21,80 43,80;79,60 62,62 63,80 80,80;96,59 79,60 79,60 80,80 96,80;22,68 15,60 0,62 0,80 21,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 22,
            "y": 41
          },
          {
            "id": "GravitasPedestal",
            "x": 15,
            "y": 41
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 68,
            "y": 53,
            "emitRate": 318,
            "avgEmitRate": 78,
            "idleTime": 294,
            "eruptionTime": 235,
            "dormancyCycles": 55.8,
            "activeCycles": 68.7
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 162,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,89 80,74 62,75 59,86 63,90;80,89 63,90 63,105 80,106;80,74 80,60 60,59 58,67 62,75;61,107 63,105 63,90 59,86 49,88 43,97 51,107;80,46 62,45 58,53 60,59 80,60;62,45 59,36 46,34 40,39 42,51 58,53;59,86 62,75 58,67 41,71 39,74 49,88;58,67 60,59 58,53 42,51 38,57 41,71;80,122 80,106 63,105 61,107 65,122;61,127 65,122 61,107 51,107 44,118 50,127;28,89 34,76 20,69 12,79 18,90;39,74 41,71 38,57 21,55 18,59 20,69 34,76;51,107 43,97 35,98 29,108 36,118 44,118;65,140 61,127 50,127 43,140 46,144 61,145;20,69 18,59 0,58 0,78 12,79;21,55 20,45 12,39 0,41 0,58 18,59;42,51 40,39 32,37 20,45 21,55 38,57;29,108 35,98 28,89 18,90 13,99 19,109;13,99 18,90 12,79 0,78 0,99;50,127 44,118 36,118 29,128 34,138 43,140;36,118 29,108 19,109 13,119 18,127 29,128;80,140 80,122 65,122 61,127 65,140;19,109 13,99 0,99 0,119 13,119;80,159 64,157 58,164 58,174 80,174;58,164 43,156 37,160 36,174 58,174;34,138 29,128 18,127 13,138 22,150;13,138 18,127 13,119 0,119 0,140;18,157 0,156 0,174 18,174;37,160 22,152 18,157 18,174 36,174;43,97 49,88 39,74 34,76 28,89 35,98;80,31 63,30 59,36 62,45 80,46\nSwamp:80,140 65,140 61,145 64,157 80,159;64,157 61,145 46,144 43,156 58,164;43,156 46,144 43,140 34,138 22,150 22,152 37,160;22,152 22,150 13,138 0,140 0,156 18,157",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 48,
            "y": 157
          },
          {
            "id": "GravitasPedestal",
            "x": 50,
            "y": 157
          }
        ],
        "geysers": [
          {
            "id": "slush_water",
            "x": 16,
            "y": 67,
            "emitRate": 5114,
            "avgEmitRate": 1806,
            "idleTime": 186,
            "eruptionTime": 277,
            "dormancyCycles": 50.5,
            "activeCycles": 72.6
          },
          {
            "id": "hot_water",
            "x": 58,
            "y": 124,
            "emitRate": 9962,
            "avgEmitRate": 2805,
            "idleTime": 389,
            "eruptionTime": 323,
            "dormancyCycles": 41.2,
            "activeCycles": 67.3
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 294,
        "offsetY": 155,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "MagmaCore:64,50 64,33 46,32 46,32 44,42 51,51;44,42 46,32 32,28 21,39 22,42 31,48;64,72 64,50 51,51 46,61 53,73;22,42 21,39 16,35 0,38 0,54 12,55;46,61 51,51 44,42 31,48 33,62;33,79 33,79 31,64 18,64 12,75 18,81;48,78 53,73 46,61 33,62 31,64 33,79;33,79 18,81 15,96 34,96;64,72 53,73 48,78 53,96 64,96;12,75 18,64 12,55 0,54 0,75\nOilField:31,64 33,62 31,48 22,42 12,55 18,64;18,81 12,75 0,75 0,96 15,96;48,78 33,79 33,79 34,96 53,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 31,
            "y": 52
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 9,
            "y": 86,
            "emitRate": 257697,
            "avgEmitRate": 1019,
            "idleTime": 9877,
            "eruptionTime": 79,
            "dormancyCycles": 63.6,
            "activeCycles": 63.5
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 360,
        "offsetY": 155,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:123,58 124,49 115,42 102,49 101,56 114,63;96,76 97,59 82,58 78,73 85,79;160,43 142,47 144,61 160,61;66,75 61,57 47,60 43,72 53,79;25,61 26,56 19,46 0,54 0,58 17,66;160,61 144,61 140,65 148,79 160,79;15,78 17,66 0,58 0,80;128,78 134,66 123,58 114,63 114,76 120,81\nSandstone:140,65 144,61 142,47 137,43 124,49 123,58 134,66;114,76 114,63 101,56 97,59 96,76 101,79;78,73 82,58 78,53 63,54 61,57 66,75 66,76;43,72 47,60 40,51 26,56 25,61 36,74;33,79 36,74 25,61 17,66 15,78 19,82;148,79 140,65 134,66 128,78 139,88\nFrozenWastes:120,81 114,76 101,79 102,96 118,96;85,79 78,73 66,76 69,96 83,96;101,79 96,76 85,79 83,96 102,96;66,76 66,75 53,79 50,96 69,96;33,79 19,82 18,96 38,96;53,79 43,72 36,74 33,79 38,96 50,96;19,82 15,78 0,80 0,96 18,96;160,79 148,79 139,88 140,96 160,96;139,88 128,78 120,81 118,96 140,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 116,
            "y": 59
          },
          {
            "id": "GravitasPedestal",
            "x": 120,
            "y": 59
          },
          {
            "id": "GeneShuffler",
            "x": 118,
            "y": 53
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 9,
            "y": 84,
            "emitRate": 2569,
            "avgEmitRate": 705,
            "idleTime": 420,
            "eruptionTime": 319,
            "dormancyCycles": 27.4,
            "activeCycles": 47.8
          },
          {
            "id": "steam",
            "x": 113,
            "y": 85,
            "emitRate": 6607,
            "avgEmitRate": 1601,
            "idleTime": 191,
            "eruptionTime": 144,
            "dormancyCycles": 30,
            "activeCycles": 38.7
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "ForestMoonlet",
        "q": 0,
        "r": 0
      },
      {
        "id": "SwampyLandingSite",
        "q": -3,
        "r": 3
      },
      {
        "id": "OilRichWarpTarget",
        "q": -4,
        "r": 0
      },
      {
        "id": "TundraMoonlet",
        "q": 2,
        "r": -8
      },
      {
        "id": "MarshyMoonlet",
        "q": 5,
        "r": -1
      },
      {
        "id": "MooMoonlet",
        "q": -4,
        "r": -4
      },
      {
        "id": "WaterMoonlet",
        "q": -5,
        "r": 8
      },
      {
        "id": "NiobiumMoonlet",
        "q": 9,
        "r": -6
      },
      {
        "id": "RegolithMoonlet",
        "q": -9,
        "r": 2
      },
      {
        "id": "TemporalTear",
        "q": -3,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 1,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 2,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 3,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 8,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 7,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 6,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 9,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -8,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 10,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -7,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -1,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -1,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 6,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 6,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": -11,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -11,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -10,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 9,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 10,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 1,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -2,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -3,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -3,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 4,
        "r": -11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation6",
        "q": 2,
        "r": 1
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -8,
        "r": 11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": -2,
        "r": 6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": 6,
        "r": 1
      }
    ]
  },
  {
    "coordinate": "V-CER-C-2127567822-0-0-0",
    "cluster": "V-CER-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "CeresClassicAsteroid",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "Volcanoes",
          "FrozenCore",
          "GlaciersLarge"
        ],
        "biomePaths": "IceCaves:137,184 151,169 151,157 137,141 118,140 104,157 105,172 120,185;159,190 162,179 151,169 137,184 143,196;163,147 161,134 141,133 137,141 151,157;141,133 136,122 121,122 115,133 118,140 137,141;143,197 143,196 137,184 120,185 115,197 126,207;118,140 115,133 99,130 94,150 104,157;121,122 117,112 105,109 96,125 99,130 115,133;115,197 120,185 105,172 94,182 102,198\nFrozenWastes:182,173 186,161 180,150 163,147 151,157 151,169 162,179;193,190 182,173 162,179 159,190 169,206 183,207;209,189 217,176 209,159 186,161 182,173 193,190;167,129 168,109 159,103 139,109 136,122 141,133 161,134;125,221 126,207 115,197 102,198 93,212 98,228 108,231;93,212 102,198 94,182 78,180 67,199 76,213;25,155 30,138 25,127 0,127 0,153 20,158;27,201 32,189 16,171 0,175 0,205;190,350 185,346 164,354 164,380 191,380;215,348 213,346 190,350 191,380 215,380;240,346 215,348 215,380 240,380;136,355 122,347 108,356 108,380 136,380;108,356 90,347 81,353 81,380 108,380;81,353 61,347 55,350 55,380 81,380;164,354 153,347 136,355 136,380 164,380;55,350 35,347 28,354 31,380 55,380;28,354 0,352 0,380 31,380\nOcean:240,237 240,205 219,206 210,221 219,237;131,270 131,256 108,247 99,252 102,278 111,282;172,294 174,275 158,263 154,263 142,275 145,294 166,299;73,221 76,213 67,199 49,197 38,214 38,216 59,230;34,274 27,253 0,254 0,279 29,280;32,303 29,280 0,279 0,307\nRadioactive:210,221 219,206 209,189 193,190 183,207 192,221;240,205 240,175 217,176 209,189 219,206;240,175 240,147 215,147 209,159 217,176;240,119 229,116 216,119 209,134 215,147 240,147;197,130 198,112 186,103 168,109 167,129 183,138;42,187 51,168 45,158 25,155 20,158 16,171 32,189;78,180 71,169 51,168 42,187 49,197 67,199;38,216 38,214 27,201 0,205 0,224 28,228\nMagmaCore:169,206 159,190 143,196 143,197 159,215;183,138 167,129 161,134 163,147 180,150;216,119 208,111 198,112 197,130 209,134;192,251 183,237 172,240 170,252 185,260;184,268 185,260 170,252 158,263 174,275;218,315 219,303 208,293 198,303 200,315 212,320;229,116 225,97 219,96 208,111 216,119;240,92 225,97 229,116 240,119;108,247 108,231 98,228 91,233 90,248 99,252;98,228 93,212 76,213 73,221 91,233;105,109 100,103 85,107 85,119 96,125;154,263 139,251 131,256 131,270 142,275;139,109 130,102 117,112 121,122 136,122;48,144 30,138 25,155 45,158;16,171 20,158 0,153 0,175;49,197 42,187 32,189 27,201 38,214;240,298 219,303 218,315 240,319;112,296 111,282 102,278 90,285 91,293 106,300;74,318 73,309 57,302 55,304 53,318 62,325;42,272 45,252 31,249 27,253 34,274;53,318 55,304 33,304 36,321\nCarrotQuarry:211,249 219,237 210,221 192,221 183,237 192,251;209,159 215,147 209,134 197,130 183,138 180,150 186,161;186,103 189,82 180,74 159,82 159,103 168,109;240,237 219,237 211,249 221,270 240,272;158,263 170,252 172,240 157,226 142,233 139,251 154,263;208,293 208,284 184,268 174,275 172,294 198,303;157,226 159,215 143,197 126,207 125,221 142,233;139,251 142,233 125,221 108,231 108,247 131,256;159,103 159,82 148,74 128,83 130,102 139,109;94,150 99,130 96,125 85,119 63,132 63,134 79,153;85,119 85,107 72,96 54,101 50,111 63,132;130,102 128,83 116,76 104,81 100,103 105,109 117,112;79,153 63,134 48,144 45,158 51,168 71,169;105,172 104,157 94,150 79,153 71,169 78,180 94,182;63,134 63,132 50,111 31,116 25,127 30,138 48,144;25,127 31,116 19,96 0,96 0,127;57,246 59,230 38,216 28,228 31,249 45,252\nRust:183,237 192,221 183,207 169,206 159,215 157,226 172,240;90,248 91,233 73,221 59,230 57,246 74,256;102,278 99,252 90,248 74,256 72,274 90,285;72,274 74,256 57,246 45,252 42,272 59,281;27,253 31,249 28,228 0,224 0,254\nSugarWoods:240,92 240,67 215,65 209,78 219,96 225,97;208,111 219,96 209,78 189,82 186,103 198,112;180,74 181,59 163,49 148,58 148,74 159,82;209,78 215,65 210,56 193,50 181,59 180,74 189,82;148,74 148,58 133,49 117,56 116,76 128,83;116,76 117,56 102,46 87,54 84,70 104,81;100,103 104,81 84,70 77,75 72,96 85,107;50,111 54,101 42,82 30,82 19,96 31,116;72,96 77,75 54,66 42,82 54,101;42,82 54,66 52,56 31,47 18,62 30,82;19,96 30,82 18,62 0,62 0,96\nForest:240,298 240,272 221,270 208,284 208,293 219,303;221,270 211,249 192,251 185,260 184,268 208,284;136,320 135,304 112,296 106,300 104,322 122,331;145,294 142,275 131,270 111,282 112,296 135,304;91,293 90,285 72,274 59,281 57,302 73,309;104,322 106,300 91,293 73,309 74,318 91,329;200,315 198,303 172,294 166,299 166,320 183,328;166,320 166,299 145,294 135,304 136,320 153,329;55,304 57,302 59,281 42,272 34,274 29,280 32,303 33,304;36,321 33,304 32,303 0,307 0,328 31,330\nOilField:153,347 153,329 136,320 122,331 122,347 136,355;90,347 91,329 74,318 62,325 61,347 81,353;213,346 212,320 200,315 183,328 185,346 190,350;240,346 240,319 218,315 212,320 213,346 215,348;185,346 183,328 166,320 153,329 153,347 164,354;122,347 122,331 104,322 91,329 90,347 108,356;61,347 62,325 53,318 36,321 31,330 35,347 55,350;35,347 31,330 0,328 0,352 28,354",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 127,
            "y": 163
          },
          {
            "id": "WarpConduitSender",
            "x": 62,
            "y": 128
          },
          {
            "id": "WarpConduitReceiver",
            "x": 71,
            "y": 186
          },
          {
            "id": "GravitasPedestal",
            "x": 32,
            "y": 233
          },
          {
            "id": "MassiveHeatSink",
            "x": 167,
            "y": 184
          },
          {
            "id": "WarpReceiver",
            "x": 49,
            "y": 108
          },
          {
            "id": "WarpPortal",
            "x": 48,
            "y": 102
          },
          {
            "id": "GeneShuffler",
            "x": 99,
            "y": 269
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 100,
            "y": 342,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 18,
            "y": 335,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 148,
            "y": 334,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "salt_water",
            "x": 221,
            "y": 227,
            "emitRate": 9852,
            "avgEmitRate": 2896,
            "idleTime": 338,
            "eruptionTime": 371,
            "dormancyCycles": 66.1,
            "activeCycles": 84.7
          },
          {
            "id": "big_volcano",
            "x": 86,
            "y": 221,
            "emitRate": 254738,
            "avgEmitRate": 1454,
            "idleTime": 10127,
            "eruptionTime": 75,
            "dormancyCycles": 24.8,
            "activeCycles": 85.6
          },
          {
            "id": "big_volcano",
            "x": 171,
            "y": 141,
            "emitRate": 248285,
            "avgEmitRate": 1183,
            "idleTime": 8586,
            "eruptionTime": 69,
            "dormancyCycles": 50.4,
            "activeCycles": 75.3
          },
          {
            "id": "big_volcano",
            "x": 36,
            "y": 261,
            "emitRate": 248086,
            "avgEmitRate": 1084,
            "idleTime": 8955,
            "eruptionTime": 68,
            "dormancyCycles": 35.7,
            "activeCycles": 49.6
          },
          {
            "id": "big_volcano",
            "x": 180,
            "y": 249,
            "emitRate": 277783,
            "avgEmitRate": 1359,
            "idleTime": 8477,
            "eruptionTime": 65,
            "dormancyCycles": 41.5,
            "activeCycles": 75.4
          },
          {
            "id": "big_volcano",
            "x": 219,
            "y": 109,
            "emitRate": 240210,
            "avgEmitRate": 1151,
            "idleTime": 9634,
            "eruptionTime": 76,
            "dormancyCycles": 50.3,
            "activeCycles": 80.4
          },
          {
            "id": "big_volcano",
            "x": 37,
            "y": 200,
            "emitRate": 250183,
            "avgEmitRate": 1191,
            "idleTime": 9352,
            "eruptionTime": 77,
            "dormancyCycles": 47.1,
            "activeCycles": 65.1
          },
          {
            "id": "big_volcano",
            "x": 205,
            "y": 123,
            "emitRate": 311323,
            "avgEmitRate": 1461,
            "idleTime": 9123,
            "eruptionTime": 60,
            "dormancyCycles": 38,
            "activeCycles": 94.4
          },
          {
            "id": "big_volcano",
            "x": 99,
            "y": 240,
            "emitRate": 253380,
            "avgEmitRate": 1009,
            "idleTime": 8115,
            "eruptionTime": 57,
            "dormancyCycles": 23.5,
            "activeCycles": 31.2
          },
          {
            "id": "small_volcano",
            "x": 120,
            "y": 230,
            "emitRate": 114008,
            "avgEmitRate": 544,
            "idleTime": 8395,
            "eruptionTime": 71,
            "dormancyCycles": 57.6,
            "activeCycles": 76.2
          },
          {
            "id": "slush_water",
            "x": 221,
            "y": 71,
            "emitRate": 4196,
            "avgEmitRate": 1400,
            "idleTime": 286,
            "eruptionTime": 329,
            "dormancyCycles": 34.9,
            "activeCycles": 57.8
          },
          {
            "id": "slush_salt_water",
            "x": 68,
            "y": 92,
            "emitRate": 13296,
            "avgEmitRate": 1242,
            "idleTime": 491,
            "eruptionTime": 105,
            "dormancyCycles": 90,
            "activeCycles": 102.2
          },
          {
            "id": "hot_steam",
            "x": 226,
            "y": 191,
            "emitRate": 2485,
            "avgEmitRate": 673,
            "idleTime": 416,
            "eruptionTime": 308,
            "dormancyCycles": 47.4,
            "activeCycles": 82.9
          },
          {
            "id": "hot_co2",
            "x": 19,
            "y": 123,
            "emitRate": 501,
            "avgEmitRate": 131,
            "idleTime": 156,
            "eruptionTime": 82,
            "dormancyCycles": 39.7,
            "activeCycles": 123.3
          },
          {
            "id": "slush_water",
            "x": 214,
            "y": 286,
            "emitRate": 4652,
            "avgEmitRate": 1343,
            "idleTime": 290,
            "eruptionTime": 276,
            "dormancyCycles": 58,
            "activeCycles": 84.3
          },
          {
            "id": "liquid_sulfur",
            "x": 183,
            "y": 85,
            "emitRate": 6782,
            "avgEmitRate": 1642,
            "idleTime": 291,
            "eruptionTime": 227,
            "dormancyCycles": 29.3,
            "activeCycles": 36.2
          },
          {
            "id": "chlorine_gas",
            "x": 219,
            "y": 359,
            "emitRate": 272,
            "avgEmitRate": 114,
            "idleTime": 196,
            "eruptionTime": 387,
            "dormancyCycles": 38.2,
            "activeCycles": 66.4
          },
          {
            "id": "slush_water",
            "x": 196,
            "y": 245,
            "emitRate": 4496,
            "avgEmitRate": 1488,
            "idleTime": 220,
            "eruptionTime": 227,
            "dormancyCycles": 47.1,
            "activeCycles": 88
          },
          {
            "id": "molten_cobalt",
            "x": 45,
            "y": 62,
            "emitRate": 7609,
            "avgEmitRate": 316,
            "idleTime": 731,
            "eruptionTime": 58,
            "dormancyCycles": 53.5,
            "activeCycles": 70.1
          },
          {
            "id": "molten_aluminum",
            "x": 33,
            "y": 364,
            "emitRate": 9781,
            "avgEmitRate": 329,
            "idleTime": 719,
            "eruptionTime": 43,
            "dormancyCycles": 48.8,
            "activeCycles": 73.4
          },
          {
            "id": "big_volcano",
            "x": 27,
            "y": 68,
            "emitRate": 289056,
            "avgEmitRate": 1143,
            "idleTime": 8941,
            "eruptionTime": 60,
            "dormancyCycles": 47,
            "activeCycles": 68.2
          }
        ]
      },
      {
        "id": "MediumSwampy",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "Geodes",
          "Volcanoes"
        ],
        "biomePaths": "Swamp:108,88 92,79 81,87 83,102 93,108;135,91 139,84 136,76 121,68 120,69 110,88 119,95;119,113 119,95 110,88 108,88 93,108 94,110 113,118;94,110 93,108 83,102 65,110 69,127 86,127;68,82 66,68 59,64 44,70 44,88 58,91;63,109 58,91 44,88 41,90 39,109 50,117;83,102 81,87 68,82 58,91 63,109 65,110\nBoggyMarsh:150,59 137,47 130,48 121,68 136,76;121,68 130,48 120,39 105,46 105,59 120,69;160,59 150,59 136,76 139,84 160,85;82,60 81,46 70,40 58,47 59,64 66,68;59,64 58,47 46,41 38,46 37,65 44,70;18,78 23,68 12,51 0,51 0,78;15,103 24,87 18,78 0,78 0,103\nWasteland:143,108 135,91 119,95 119,113 133,120;160,85 139,84 135,91 143,108 160,109;160,153 160,130 140,131 135,149 140,154;135,149 140,131 133,123 114,129 113,132 119,150;117,153 119,150 113,132 92,137 90,148 98,155;90,148 92,137 86,127 69,127 68,128 67,148 77,152;46,148 44,133 24,129 19,150 20,151 38,154;24,129 22,126 0,126 0,148 19,150\nToxicJungle:110,88 120,69 105,59 92,66 92,79 108,88;92,79 92,66 82,60 66,68 68,82 81,87;160,130 160,109 143,108 133,120 133,123 140,131;37,65 38,46 23,40 12,51 23,68\nFrozenWastes:39,109 41,90 24,87 15,103 25,115;25,115 15,103 0,103 0,126 22,126;51,125 50,117 39,109 25,115 22,126 24,129 44,133\nMagmaCore:133,123 133,120 119,113 113,118 114,129;140,154 135,149 119,150 117,153 121,176 137,176;160,153 140,154 137,176 160,176;69,127 65,110 63,109 50,117 51,125 68,128;98,155 90,148 77,152 77,176 95,176;77,152 67,148 58,152 58,176 77,176;117,153 98,155 95,176 121,176;38,154 20,151 16,176 40,176;58,152 46,148 38,154 40,176 58,176;20,151 19,150 0,148 0,176 16,176\nBarren:113,132 114,129 113,118 94,110 86,127 92,137;44,88 44,70 37,65 23,68 18,78 24,87 41,90;67,148 68,128 51,125 44,133 46,148 58,152",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 18,
            "y": 121
          },
          {
            "id": "WarpConduitSender",
            "x": 81,
            "y": 125
          },
          {
            "id": "WarpConduitReceiver",
            "x": 124,
            "y": 76
          },
          {
            "id": "WarpReceiver",
            "x": 103,
            "y": 96
          },
          {
            "id": "WarpPortal",
            "x": 81,
            "y": 96
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 27,
            "y": 62,
            "emitRate": 267,
            "avgEmitRate": 96,
            "idleTime": 210,
            "eruptionTime": 358,
            "dormancyCycles": 61.2,
            "activeCycles": 81.8
          },
          {
            "id": "methane",
            "x": 49,
            "y": 62,
            "emitRate": 444,
            "avgEmitRate": 107,
            "idleTime": 438,
            "eruptionTime": 329,
            "dormancyCycles": 53.2,
            "activeCycles": 68
          },
          {
            "id": "molten_cobalt",
            "x": 132,
            "y": 164,
            "emitRate": 6768,
            "avgEmitRate": 350,
            "idleTime": 675,
            "eruptionTime": 61,
            "dormancyCycles": 44.5,
            "activeCycles": 73
          },
          {
            "id": "molten_iron",
            "x": 27,
            "y": 165,
            "emitRate": 6972,
            "avgEmitRate": 325,
            "idleTime": 657,
            "eruptionTime": 51,
            "dormancyCycles": 51.1,
            "activeCycles": 93.2
          },
          {
            "id": "molten_copper",
            "x": 109,
            "y": 168,
            "emitRate": 12409,
            "avgEmitRate": 321,
            "idleTime": 721,
            "eruptionTime": 33,
            "dormancyCycles": 54,
            "activeCycles": 79.5
          },
          {
            "id": "salt_water",
            "x": 79,
            "y": 166,
            "emitRate": 8991,
            "avgEmitRate": 2976,
            "idleTime": 220,
            "eruptionTime": 227,
            "dormancyCycles": 47.1,
            "activeCycles": 88
          },
          {
            "id": "small_volcano",
            "x": 41,
            "y": 117,
            "emitRate": 162582,
            "avgEmitRate": 730,
            "idleTime": 8697,
            "eruptionTime": 61,
            "dormancyCycles": 75.1,
            "activeCycles": 135.9
          },
          {
            "id": "hot_po2",
            "x": 15,
            "y": 167,
            "emitRate": 461,
            "avgEmitRate": 97,
            "idleTime": 249,
            "eruptionTime": 135,
            "dormancyCycles": 58.6,
            "activeCycles": 86.9
          },
          {
            "id": "big_volcano",
            "x": 121,
            "y": 122,
            "emitRate": 227279,
            "avgEmitRate": 1093,
            "idleTime": 8704,
            "eruptionTime": 68,
            "dormancyCycles": 16.4,
            "activeCycles": 26.9
          },
          {
            "id": "big_volcano",
            "x": 60,
            "y": 120,
            "emitRate": 258245,
            "avgEmitRate": 883,
            "idleTime": 7705,
            "eruptionTime": 57,
            "dormancyCycles": 63.6,
            "activeCycles": 55.5
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "DistressSignal",
          "LushCore"
        ],
        "biomePaths": "FrozenWastes:64,54 64,37 48,36 43,40 42,45 52,55;47,66 52,55 42,45 32,54 35,67;42,45 43,40 31,34 19,40 21,50 32,54;64,54 52,55 47,66 53,75 64,75;53,75 47,66 35,67 33,69 36,84 46,85;21,50 19,40 16,39 0,41 0,58 14,59;64,75 53,75 46,85 51,94 64,94;36,84 33,69 19,70 15,77 17,83 32,87;44,105 51,94 46,85 36,84 32,87 31,97 38,105;19,70 14,59 0,58 0,76 15,77;32,114 38,105 31,97 18,101 16,112 18,114;31,97 32,87 17,83 12,95 18,101;33,69 35,67 32,54 21,50 14,59 19,70;64,112 64,94 51,94 44,105 51,112;16,112 18,101 12,95 0,95 0,112;12,95 17,83 15,77 0,76 0,95\nForest:18,114 16,112 0,112 0,128 17,128;32,114 18,114 17,128 38,128;64,112 51,112 45,128 64,128;51,112 44,105 38,105 32,114 38,128 45,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 30,
            "y": 41
          },
          {
            "id": "GravitasPedestal",
            "x": 36,
            "y": 86
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 23,
            "y": 104,
            "emitRate": 5732,
            "avgEmitRate": 240,
            "idleTime": 630,
            "eruptionTime": 51,
            "dormancyCycles": 43.6,
            "activeCycles": 56.3
          },
          {
            "id": "molten_iron",
            "x": 41,
            "y": 103,
            "emitRate": 12759,
            "avgEmitRate": 402,
            "idleTime": 781,
            "eruptionTime": 40,
            "dormancyCycles": 36.7,
            "activeCycles": 68.7
          },
          {
            "id": "molten_iron",
            "x": 34,
            "y": 95,
            "emitRate": 6894,
            "avgEmitRate": 285,
            "idleTime": 915,
            "eruptionTime": 72,
            "dormancyCycles": 52.5,
            "activeCycles": 69.1
          },
          {
            "id": "molten_iron",
            "x": 45,
            "y": 94,
            "emitRate": 10340,
            "avgEmitRate": 336,
            "idleTime": 741,
            "eruptionTime": 40,
            "dormancyCycles": 19.2,
            "activeCycles": 32.3
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:36,47 34,35 21,31 16,36 17,44 29,52;7,55 17,44 16,36 0,33 0,55;51,44 48,31 42,27 34,35 36,47 46,49;50,78 46,65 35,66 30,79 31,80 46,83;64,25 48,31 51,44 64,45;64,61 49,61 46,65 50,78 64,79\nToxicJungle:28,59 29,52 17,44 7,55 17,64;15,76 17,64 7,55 0,55 0,76 15,76;35,66 28,59 17,64 15,76 30,79;46,65 49,61 46,49 36,47 29,52 28,59 35,66;64,61 64,45 51,44 46,49 49,61\nMagmaCore:31,80 30,79 15,76 15,76 14,96 28,96;15,76 0,76 0,96 14,96;46,83 31,80 28,96 47,96;64,79 50,78 46,83 47,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 33,
            "y": 42
          },
          {
            "id": "GravitasPedestal",
            "x": 19,
            "y": 42
          },
          {
            "id": "SapTree",
            "x": 26,
            "y": 42
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 22,
            "y": 51,
            "emitRate": 7426,
            "avgEmitRate": 352,
            "idleTime": 641,
            "eruptionTime": 45,
            "dormancyCycles": 28.9,
            "activeCycles": 72.7
          },
          {
            "id": "molten_tungsten",
            "x": 22,
            "y": 91,
            "emitRate": 7417,
            "avgEmitRate": 341,
            "idleTime": 707,
            "eruptionTime": 51,
            "dormancyCycles": 43.8,
            "activeCycles": 92
          },
          {
            "id": "molten_tungsten",
            "x": 9,
            "y": 91,
            "emitRate": 8184,
            "avgEmitRate": 263,
            "idleTime": 745,
            "eruptionTime": 51,
            "dormancyCycles": 79.5,
            "activeCycles": 79
          },
          {
            "id": "chlorine_gas",
            "x": 23,
            "y": 71,
            "emitRate": 354,
            "avgEmitRate": 102,
            "idleTime": 314,
            "eruptionTime": 306,
            "dormancyCycles": 39.4,
            "activeCycles": 55.7
          },
          {
            "id": "slimy_po2",
            "x": 31,
            "y": 73,
            "emitRate": 329,
            "avgEmitRate": 95,
            "idleTime": 240,
            "eruptionTime": 291,
            "dormancyCycles": 56.5,
            "activeCycles": 63.3
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "MagmaCore:46,50 49,46 44,32 32,34 29,48 33,51;64,45 64,29 45,30 44,32 49,46;50,63 46,50 33,51 32,64 44,70;29,48 32,34 24,27 16,31 13,44 18,49;64,79 64,64 50,63 44,70 44,77 48,81;44,77 44,70 32,64 22,70 21,75 32,83;13,44 16,31 0,23 0,45;21,75 22,70 14,62 0,64 0,78 15,80\nOilField:14,62 18,49 13,44 0,45 0,64;64,45 49,46 46,50 50,63 64,64;32,64 33,51 29,48 18,49 14,62 22,70\nFrozenWastes:32,83 21,75 15,80 16,96 31,96;15,80 0,78 0,96 16,96;48,81 44,77 32,83 31,96 48,96;64,79 48,81 48,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 14,
            "y": 53
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 49,
            "y": 50,
            "emitRate": 268101,
            "avgEmitRate": 1296,
            "idleTime": 9066,
            "eruptionTime": 70,
            "dormancyCycles": 46.4,
            "activeCycles": 79.4
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:77,47 78,45 77,32 62,29 56,39 62,48;79,62 77,47 62,48 57,61 59,64 77,65;62,48 56,39 46,40 42,59 57,61;42,59 46,40 39,34 33,35 25,46 33,59 39,60;25,46 33,35 20,24 9,35 17,46;33,59 25,46 17,46 9,58 20,67;9,58 17,46 9,35 0,35 0,59;96,45 78,45 77,47 79,62 96,62\nFrozenWastes:39,60 33,59 20,67 19,80 40,80;59,64 57,61 42,59 39,60 40,80 57,80;20,67 9,58 0,59 0,80 19,80;96,62 79,62 77,65 77,80 96,80;77,65 59,64 57,80 77,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 68,
            "y": 41
          },
          {
            "id": "GravitasPedestal",
            "x": 61,
            "y": 41
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 20,
            "y": 37,
            "emitRate": 310,
            "avgEmitRate": 91,
            "idleTime": 168,
            "eruptionTime": 185,
            "dormancyCycles": 60.5,
            "activeCycles": 76.8
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,57 67,58 63,70 74,81 80,81;80,57 80,37 65,39 61,53 67,58;51,81 52,74 45,67 35,69 30,80 38,89;61,53 65,39 59,33 47,36 44,48 49,54;74,81 63,70 52,74 51,81 61,91;30,80 35,69 27,59 19,60 13,69 20,81;61,97 61,91 51,81 38,89 38,92 48,102;46,112 48,102 38,92 26,102 35,114;80,103 80,81 74,81 61,91 61,97 66,103;38,92 38,89 30,80 20,81 15,88 21,102 26,102;45,67 49,54 44,48 32,49 27,59 35,69;27,59 32,49 25,38 20,37 10,47 19,60;15,88 20,81 13,69 0,70 0,89;31,122 35,114 26,102 21,102 15,107 17,122 20,125;48,132 51,117 46,112 35,114 31,122 42,135;63,116 66,103 61,97 48,102 46,112 51,117;44,48 47,36 37,27 25,38 32,49;68,122 63,116 51,117 48,132 62,136;42,137 42,135 31,122 20,125 20,137 31,143;80,122 68,122 62,136 64,140 80,141;66,158 60,152 50,153 48,156 52,174 62,174;13,69 19,60 10,47 0,47 0,70;20,137 20,125 17,122 0,125 0,138 15,141;63,70 67,58 61,53 49,54 45,67 52,74;80,122 80,103 66,103 63,116 68,122;21,102 15,88 0,89 0,106 15,107;17,122 15,107 0,106 0,125;34,159 31,156 17,158 17,174 33,174;80,156 66,158 62,174 80,174;17,158 15,157 0,158 0,174 17,174;48,156 34,159 33,174 52,174\nSwamp:60,152 64,140 62,136 48,132 42,135 42,137 50,153;80,156 80,141 64,140 60,152 66,158;48,156 50,153 42,137 31,143 31,156 34,159;31,156 31,143 20,137 15,141 15,157 17,158;15,157 15,141 0,138 0,158",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 19,
            "y": 155
          },
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 155
          }
        ],
        "geysers": [
          {
            "id": "hot_water",
            "x": 41,
            "y": 91,
            "emitRate": 10360,
            "avgEmitRate": 3709,
            "idleTime": 343,
            "eruptionTime": 313,
            "dormancyCycles": 22.7,
            "activeCycles": 68.2
          },
          {
            "id": "filthy_water",
            "x": 23,
            "y": 82,
            "emitRate": 14293,
            "avgEmitRate": 3627,
            "idleTime": 132,
            "eruptionTime": 78,
            "dormancyCycles": 31.9,
            "activeCycles": 69.2
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Sandstone:58,58 56,54 46,51 35,59 41,73 46,74;67,78 66,62 58,58 46,74 50,79 65,79\nBarren:96,58 96,42 77,45 77,58 80,60;96,58 80,60 83,77 96,77;83,77 80,60 77,58 66,62 67,78 80,79;41,73 35,59 31,58 19,65 18,76 32,80;18,76 19,65 9,56 0,57 0,77 16,77\nFrozenWastes:65,79 50,79 48,96 65,96;50,79 46,74 41,73 32,80 33,96 48,96;80,79 67,78 65,79 65,96 82,96;96,77 83,77 80,79 82,96 96,96;32,80 18,76 16,77 16,96 33,96;16,77 0,77 0,96 16,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 72,
            "y": 70
          },
          {
            "id": "GravitasPedestal",
            "x": 76,
            "y": 70
          },
          {
            "id": "GeneShuffler",
            "x": 74,
            "y": 64
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 42,
            "y": 81,
            "emitRate": 5790,
            "avgEmitRate": 1908,
            "idleTime": 226,
            "eruptionTime": 234,
            "dormancyCycles": 39.6,
            "activeCycles": 72.8
          },
          {
            "id": "steam",
            "x": 57,
            "y": 84,
            "emitRate": 3526,
            "avgEmitRate": 1306,
            "idleTime": 227,
            "eruptionTime": 446,
            "dormancyCycles": 69.6,
            "activeCycles": 88.3
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "CeresClassicAsteroid",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumSwampy",
        "q": -2,
        "r": -1
      },
      {
        "id": "TundraMoonlet",
        "q": -1,
        "r": 5
      },
      {
        "id": "MarshyMoonlet",
        "q": 5,
        "r": 1
      },
      {
        "id": "NiobiumMoonlet",
        "q": 3,
        "r": -5
      },
      {
        "id": "MooMoonlet",
        "q": 1,
        "r": -7
      },
      {
        "id": "WaterMoonlet",
        "q": -6,
        "r": 4
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 8,
        "r": -8
      },
      {
        "id": "TemporalTear",
        "q": 1,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_DLC2CeresOreField",
        "q": 3,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -6,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 6,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 6,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 0,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 1,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 2,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 11,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -6,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -5,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 7,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 7,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 6,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 6,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -4,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -5,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -10,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": 8,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -9,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -11,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": -11,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 4,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 3,
        "r": 4
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation4",
        "q": -3,
        "r": 2
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 10,
        "r": -11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": 0,
        "r": -4
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": 11,
        "r": -2
      }
    ]
  },
  {
    "coordinate": "V-SFRZ-C-1319193308-0-0-0",
    "cluster": "V-SFRZ-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaSandstoneFrozen",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "MetalPoor",
          "BouldersSmall"
        ],
        "biomePaths": "Sandstone:118,226 122,205 108,191 83,194 76,215 85,232 105,237;34,209 28,200 0,201 0,224 29,227;28,200 32,179 26,173 0,174 0,201;25,116 30,111 21,87 0,87 0,117;59,235 63,216 56,207 34,209 29,227 32,232 53,238;76,215 83,194 82,193 61,193 56,207 63,216;85,232 76,215 63,216 59,235 78,241;108,191 110,184 101,172 86,176 82,193 83,194;76,95 78,93 72,68 56,64 45,78 53,96;106,244 105,237 85,232 78,241 81,250 98,254;130,94 133,77 120,66 104,73 101,88 111,100;129,232 118,226 105,237 106,244 130,250;128,204 137,189 129,180 110,184 108,191 122,205;142,221 128,204 122,205 118,226 129,232;187,107 188,82 183,77 163,82 159,99 169,110;240,271 240,242 215,242 209,252 217,271\nMagmaCore:27,359 13,347 0,347 0,380 25,380;56,357 47,351 27,359 25,380 57,380;86,359 71,350 56,357 57,380 85,380;119,364 97,353 86,359 85,380 119,380;151,362 143,353 129,353 119,364 119,380 150,380;184,361 175,353 151,362 150,380 185,380;213,354 207,349 184,361 185,380 214,380;240,351 213,354 214,380 240,380\nOilField:13,347 29,323 24,315 0,312 0,347;47,351 44,328 29,323 13,347 27,359;71,350 72,330 56,321 44,328 47,351 56,357;97,353 100,333 88,323 72,330 71,350 86,359;129,353 119,328 100,333 97,353 119,364;154,327 151,322 121,325 119,328 129,353 143,353;175,353 174,333 154,327 143,353 151,362;207,349 206,339 181,328 174,333 175,353 184,361;240,351 240,330 215,327 206,339 207,349 213,354\nRust:32,297 23,285 0,286 0,312 24,315;88,323 90,310 78,298 58,305 56,321 72,330;136,294 138,281 128,270 105,277 111,301 117,304;151,322 153,310 136,294 117,304 121,325;159,99 163,82 151,70 133,77 130,94 140,103;154,188 161,179 157,163 145,156 134,161 129,180 137,189;192,282 182,266 161,270 159,274 169,300 180,302;162,209 154,188 137,189 128,204 142,221 152,221;208,168 216,155 210,140 191,137 180,144 180,147 192,168;211,108 218,94 209,79 188,82 187,107 190,110\nToxicJungle:23,285 30,266 22,256 0,255 0,286;78,298 80,284 68,270 56,270 48,296 58,305;93,143 98,120 80,111 62,128 79,147;107,116 111,100 101,88 78,93 76,95 80,111 98,120;119,328 121,325 117,304 111,301 90,310 88,323 100,333;136,124 140,103 130,94 111,100 107,116 124,128;159,274 161,270 154,252 131,251 128,270 138,281;169,300 159,274 138,281 136,294 153,310;215,327 211,311 185,310 181,328 206,339;182,266 188,254 179,239 162,239 154,252 161,270;217,271 209,252 188,254 182,266 192,282 209,283;209,252 215,242 208,226 187,226 179,239 188,254;222,124 211,108 190,110 191,137 210,140;240,156 216,155 208,168 217,185 240,185;240,92 218,94 211,108 222,124 240,125\nBoggyMarsh:32,232 29,227 0,224 0,255 22,256;50,265 53,238 32,232 22,256 30,266;56,207 61,193 51,177 32,179 28,200 34,209;68,270 81,250 78,241 59,235 53,238 50,265 56,270;111,301 105,277 104,276 80,284 78,298 90,310;101,172 107,153 93,143 79,147 72,161 86,176;128,270 131,251 130,250 106,244 98,254 104,276 105,277;129,180 134,161 118,150 107,153 101,172 110,184;181,328 185,310 180,302 169,300 153,310 151,322 154,327 174,333;162,239 152,221 142,221 129,232 130,250 131,251 154,252;163,128 169,110 159,99 140,103 136,124 148,132;188,197 183,182 161,179 154,188 162,209 180,209;217,301 209,283 192,282 180,302 185,310 211,311;215,214 209,198 188,197 180,209 187,226 208,226\nRadioactive:26,173 31,145 30,145 0,145 0,174;43,145 58,128 45,110 30,111 25,116 30,145 31,145;45,110 53,96 45,78 29,78 21,87 30,111;191,137 190,110 187,107 169,110 163,128 180,144;217,185 208,168 192,168 183,182 188,197 209,198;240,300 240,271 217,271 209,283 217,301;240,185 217,185 209,198 215,214 240,214\nOcean:30,145 25,116 0,117 0,145;56,321 58,305 48,296 32,297 24,315 29,323 44,328;48,296 56,270 50,265 30,266 23,285 32,297;72,161 79,147 62,128 58,128 43,145 58,165;80,111 76,95 53,96 45,110 58,128 62,128;101,88 104,73 86,57 72,68 78,93;118,150 124,128 107,116 98,120 93,143 107,153;192,168 180,147 157,163 161,179 183,182;240,242 240,214 215,214 208,226 215,242;240,125 222,124 210,140 216,155 240,156\nBarren:21,87 29,78 22,57 0,56 0,87;56,64 52,52 31,46 22,57 29,78 45,78;86,57 87,53 68,32 65,32 52,52 56,64 72,68;120,66 122,51 105,38 87,53 86,57 104,73;151,70 153,55 136,43 122,51 120,66 133,77;183,77 183,58 166,49 153,55 151,70 163,82;209,79 215,64 211,56 194,51 183,58 183,77 188,82;240,92 240,66 215,64 209,79 218,94\nForest:51,177 58,165 43,145 31,145 26,173 32,179;82,193 86,176 72,161 58,165 51,177 61,193;104,276 98,254 81,250 68,270 80,284;145,156 148,132 136,124 124,128 118,150 134,161;180,147 180,144 163,128 148,132 145,156 157,163;179,239 187,226 180,209 162,209 152,221 162,239;240,300 217,301 211,311 215,327 240,330",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 96,
            "y": 216
          },
          {
            "id": "WarpConduitSender",
            "x": 42,
            "y": 284
          },
          {
            "id": "WarpConduitReceiver",
            "x": 63,
            "y": 160
          },
          {
            "id": "GravitasPedestal",
            "x": 155,
            "y": 136
          },
          {
            "id": "WarpReceiver",
            "x": 101,
            "y": 141
          },
          {
            "id": "WarpPortal",
            "x": 100,
            "y": 135
          },
          {
            "id": "GeneShuffler",
            "x": 194,
            "y": 216
          },
          {
            "id": "GeneShuffler",
            "x": 164,
            "y": 262
          },
          {
            "id": "GeneShuffler",
            "x": 158,
            "y": 339
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 67,
            "y": 127,
            "emitRate": 5178,
            "avgEmitRate": 1736,
            "idleTime": 262,
            "eruptionTime": 350,
            "dormancyCycles": 26.3,
            "activeCycles": 37.3
          },
          {
            "id": "chlorine_gas",
            "x": 108,
            "y": 307,
            "emitRate": 323,
            "avgEmitRate": 105,
            "idleTime": 332,
            "eruptionTime": 365,
            "dormancyCycles": 43.8,
            "activeCycles": 71.2
          },
          {
            "id": "steam",
            "x": 76,
            "y": 246,
            "emitRate": 4037,
            "avgEmitRate": 1584,
            "idleTime": 192,
            "eruptionTime": 313,
            "dormancyCycles": 46.8,
            "activeCycles": 81
          },
          {
            "id": "methane",
            "x": 80,
            "y": 297,
            "emitRate": 1094,
            "avgEmitRate": 122,
            "idleTime": 479,
            "eruptionTime": 109,
            "dormancyCycles": 50.1,
            "activeCycles": 75.7
          },
          {
            "id": "salt_water",
            "x": 73,
            "y": 106,
            "emitRate": 8079,
            "avgEmitRate": 2632,
            "idleTime": 273,
            "eruptionTime": 311,
            "dormancyCycles": 56.9,
            "activeCycles": 89.6
          },
          {
            "id": "OilWell",
            "x": 102,
            "y": 343,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 49,
            "y": 345,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 20,
            "y": 323,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "filthy_water",
            "x": 215,
            "y": 288,
            "emitRate": 8405,
            "avgEmitRate": 2762,
            "idleTime": 312,
            "eruptionTime": 372,
            "dormancyCycles": 42.2,
            "activeCycles": 64.3
          },
          {
            "id": "small_volcano",
            "x": 163,
            "y": 230,
            "emitRate": 116120,
            "avgEmitRate": 537,
            "idleTime": 8884,
            "eruptionTime": 74,
            "dormancyCycles": 55.7,
            "activeCycles": 71.2
          },
          {
            "id": "molten_cobalt",
            "x": 172,
            "y": 146,
            "emitRate": 7892,
            "avgEmitRate": 313,
            "idleTime": 780,
            "eruptionTime": 59,
            "dormancyCycles": 51.5,
            "activeCycles": 65.3
          },
          {
            "id": "molten_gold",
            "x": 222,
            "y": 133,
            "emitRate": 7375,
            "avgEmitRate": 336,
            "idleTime": 673,
            "eruptionTime": 53,
            "dormancyCycles": 56.8,
            "activeCycles": 93.9
          },
          {
            "id": "chlorine_gas",
            "x": 35,
            "y": 132,
            "emitRate": 284,
            "avgEmitRate": 113,
            "idleTime": 243,
            "eruptionTime": 423,
            "dormancyCycles": 34.6,
            "activeCycles": 58.2
          },
          {
            "id": "small_volcano",
            "x": 156,
            "y": 156,
            "emitRate": 122238,
            "avgEmitRate": 572,
            "idleTime": 8027,
            "eruptionTime": 63,
            "dormancyCycles": 60.7,
            "activeCycles": 90.4
          },
          {
            "id": "slush_water",
            "x": 59,
            "y": 266,
            "emitRate": 2722,
            "avgEmitRate": 1109,
            "idleTime": 91,
            "eruptionTime": 473,
            "dormancyCycles": 69.4,
            "activeCycles": 65.6
          },
          {
            "id": "hot_po2",
            "x": 27,
            "y": 218,
            "emitRate": 441,
            "avgEmitRate": 99,
            "idleTime": 240,
            "eruptionTime": 159,
            "dormancyCycles": 34.9,
            "activeCycles": 45.2
          },
          {
            "id": "slush_water",
            "x": 126,
            "y": 333,
            "emitRate": 2722,
            "avgEmitRate": 1109,
            "idleTime": 91,
            "eruptionTime": 473,
            "dormancyCycles": 69.4,
            "activeCycles": 65.6
          },
          {
            "id": "big_volcano",
            "x": 140,
            "y": 138,
            "emitRate": 296858,
            "avgEmitRate": 1132,
            "idleTime": 9419,
            "eruptionTime": 62,
            "dormancyCycles": 44.5,
            "activeCycles": 62.8
          },
          {
            "id": "filthy_water",
            "x": 170,
            "y": 130,
            "emitRate": 8582,
            "avgEmitRate": 2809,
            "idleTime": 356,
            "eruptionTime": 418,
            "dormancyCycles": 45.7,
            "activeCycles": 70.3
          },
          {
            "id": "hot_po2",
            "x": 90,
            "y": 258,
            "emitRate": 411,
            "avgEmitRate": 109,
            "idleTime": 428,
            "eruptionTime": 322,
            "dormancyCycles": 69.3,
            "activeCycles": 112.5
          }
        ]
      },
      {
        "id": "MediumSwampy",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "RadioactiveCrust",
          "MetalCaves"
        ],
        "biomePaths": "Swamp:92,99 77,84 76,84 63,98 71,112 83,112;97,99 107,83 94,69 77,84 92,99;106,124 111,111 97,99 92,99 83,112 92,127;76,84 65,71 50,73 45,84 52,97 63,98;52,97 45,84 27,85 23,94 27,106 44,108;88,134 92,127 83,112 71,112 62,125 70,137;71,112 63,98 52,97 44,108 49,124 62,125;118,108 125,93 115,82 107,83 97,99 111,111\nBoggyMarsh:115,82 124,68 113,54 94,67 94,69 107,83;94,69 94,67 83,54 69,57 65,71 76,84 77,84;135,68 142,59 137,44 131,40 113,52 113,54 124,68;160,82 143,83 137,92 145,107 160,108;160,33 137,44 142,59 160,60\nToxicJungle:113,54 113,52 102,38 87,43 83,54 94,67;65,71 69,57 59,46 47,48 42,61 50,73;42,61 47,48 40,39 24,41 19,50 26,63;21,73 26,63 19,50 0,51 0,74;49,124 44,108 27,106 22,115 25,129 44,131\nFrozenWastes:137,92 143,83 135,68 124,68 115,82 125,93;23,94 27,85 21,73 0,74 0,94;27,106 23,94 0,94 0,114 22,115;25,129 22,115 0,114 0,135 21,135\nWasteland:135,129 134,119 118,108 111,111 106,124 115,135;145,107 137,92 125,93 118,108 134,119;160,131 160,108 145,107 134,119 135,129 136,131;160,152 160,131 136,131 138,153 139,153;112,149 115,135 106,124 92,127 88,134 95,152 95,152;95,152 88,134 70,137 66,149 77,158;45,84 50,73 42,61 26,63 21,73 27,85;66,149 70,137 62,125 49,124 44,131 46,148 57,152;46,148 44,131 25,129 21,135 24,151 37,154\nBarren:160,82 160,60 142,59 135,68 143,83;138,153 136,131 135,129 115,135 112,149 119,155;24,151 21,135 0,135 0,154 18,156\nMagmaCore:119,155 112,149 95,152 99,176 116,176;139,153 138,153 119,155 116,176 140,176;160,152 139,153 140,176 160,176;77,158 66,149 57,152 56,176 75,176;95,152 95,152 77,158 75,176 99,176;57,152 46,148 37,154 38,176 56,176;18,156 0,154 0,176 20,176;37,154 24,151 18,156 20,176 38,176",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 15,
            "y": 79
          },
          {
            "id": "WarpConduitReceiver",
            "x": 35,
            "y": 106
          },
          {
            "id": "WarpConduitSender",
            "x": 65,
            "y": 129
          },
          {
            "id": "WarpReceiver",
            "x": 88,
            "y": 96
          },
          {
            "id": "WarpPortal",
            "x": 66,
            "y": 96
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 25,
            "y": 119,
            "emitRate": 4137,
            "avgEmitRate": 1174,
            "idleTime": 331,
            "eruptionTime": 347,
            "dormancyCycles": 70,
            "activeCycles": 87.3
          },
          {
            "id": "steam",
            "x": 147,
            "y": 95,
            "emitRate": 4571,
            "avgEmitRate": 1284,
            "idleTime": 292,
            "eruptionTime": 324,
            "dormancyCycles": 33.7,
            "activeCycles": 38.7
          },
          {
            "id": "methane",
            "x": 126,
            "y": 67,
            "emitRate": 435,
            "avgEmitRate": 123,
            "idleTime": 320,
            "eruptionTime": 316,
            "dormancyCycles": 54.8,
            "activeCycles": 72.3
          },
          {
            "id": "filthy_water",
            "x": 57,
            "y": 168,
            "emitRate": 8405,
            "avgEmitRate": 2762,
            "idleTime": 312,
            "eruptionTime": 372,
            "dormancyCycles": 42.2,
            "activeCycles": 64.3
          },
          {
            "id": "molten_copper",
            "x": 110,
            "y": 124,
            "emitRate": 9566,
            "avgEmitRate": 315,
            "idleTime": 664,
            "eruptionTime": 40,
            "dormancyCycles": 73.8,
            "activeCycles": 100
          },
          {
            "id": "hot_water",
            "x": 147,
            "y": 166,
            "emitRate": 6486,
            "avgEmitRate": 2398,
            "idleTime": 190,
            "eruptionTime": 492,
            "dormancyCycles": 77.3,
            "activeCycles": 81.3
          },
          {
            "id": "molten_copper",
            "x": 55,
            "y": 108,
            "emitRate": 10182,
            "avgEmitRate": 314,
            "idleTime": 790,
            "eruptionTime": 41,
            "dormancyCycles": 49.4,
            "activeCycles": 84.2
          },
          {
            "id": "slimy_po2",
            "x": 144,
            "y": 115,
            "emitRate": 332,
            "avgEmitRate": 106,
            "idleTime": 357,
            "eruptionTime": 365,
            "dormancyCycles": 34.2,
            "activeCycles": 59.3
          },
          {
            "id": "hot_co2",
            "x": 118,
            "y": 144,
            "emitRate": 533,
            "avgEmitRate": 120,
            "idleTime": 439,
            "eruptionTime": 220,
            "dormancyCycles": 38.3,
            "activeCycles": 79.3
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "FrozenWastes:52,48 47,37 37,35 31,46 42,59 42,59;21,48 15,36 0,37 0,52 17,54;64,69 64,50 52,48 42,59 51,70;42,59 31,46 21,48 17,54 19,60 31,66;46,81 51,70 42,59 42,59 31,66 31,78 35,82;31,78 31,66 19,60 11,72 18,81;64,69 51,70 46,81 53,91 64,92;64,92 53,91 43,104 44,110 64,112;32,95 35,82 31,78 18,81 14,91 21,99;14,91 18,81 11,72 0,72 0,93;31,46 37,35 34,30 19,28 15,36 21,48;53,91 46,81 35,82 32,95 43,104;42,112 44,110 43,104 32,95 21,99 19,110 22,114;64,112 44,110 42,112 43,128 64,128;22,114 19,110 0,111 0,128 20,128;19,110 21,99 14,91 0,93 0,111;42,112 22,114 20,128 43,128;19,60 17,54 0,52 0,72 11,72",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 36,
            "y": 48
          },
          {
            "id": "GravitasPedestal",
            "x": 37,
            "y": 112
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 31,
            "y": 119,
            "emitRate": 7673,
            "avgEmitRate": 357,
            "idleTime": 754,
            "eruptionTime": 61,
            "dormancyCycles": 45.4,
            "activeCycles": 73.3
          },
          {
            "id": "molten_iron",
            "x": 15,
            "y": 71,
            "emitRate": 9944,
            "avgEmitRate": 268,
            "idleTime": 611,
            "eruptionTime": 34,
            "dormancyCycles": 51,
            "activeCycles": 54.6
          },
          {
            "id": "molten_iron",
            "x": 13,
            "y": 98,
            "emitRate": 15653,
            "avgEmitRate": 366,
            "idleTime": 765,
            "eruptionTime": 25,
            "dormancyCycles": 20.5,
            "activeCycles": 55.2
          },
          {
            "id": "molten_iron",
            "x": 33,
            "y": 78,
            "emitRate": 5809,
            "avgEmitRate": 244,
            "idleTime": 673,
            "eruptionTime": 62,
            "dormancyCycles": 65.7,
            "activeCycles": 65
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "CrashedSatellites"
        ],
        "biomePaths": "BoggyMarsh:33,57 36,41 30,35 28,36 17,49 19,56 30,59;64,35 51,35 46,41 51,51 64,52;51,51 46,41 36,41 33,57 45,60;64,52 51,51 45,60 48,67 64,67;28,36 17,29 3,41 17,49;29,76 30,59 19,56 12,62 17,78;19,56 17,49 3,41 0,41 0,62 12,62\nToxicJungle:64,81 64,67 48,67 44,76 49,82;44,76 48,67 45,60 33,57 30,59 29,76 32,78;16,79 17,78 12,62 0,62 0,80\nMagmaCore:49,82 44,76 32,78 33,96 46,96;32,78 29,76 17,78 16,79 17,96 33,96;64,81 49,82 46,96 64,96;16,79 0,80 0,96 17,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 35,
            "y": 50
          },
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 50
          },
          {
            "id": "SapTree",
            "x": 28,
            "y": 50
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 31,
            "y": 78
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 45,
            "y": 51
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 24,
            "y": 37
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 13,
            "y": 74,
            "emitRate": 8967,
            "avgEmitRate": 240,
            "idleTime": 710,
            "eruptionTime": 39,
            "dormancyCycles": 42.1,
            "activeCycles": 44.1
          },
          {
            "id": "molten_tungsten",
            "x": 29,
            "y": 87,
            "emitRate": 9779,
            "avgEmitRate": 319,
            "idleTime": 680,
            "eruptionTime": 40,
            "dormancyCycles": 58.1,
            "activeCycles": 83.2
          },
          {
            "id": "molten_tungsten",
            "x": 57,
            "y": 90,
            "emitRate": 7771,
            "avgEmitRate": 348,
            "idleTime": 736,
            "eruptionTime": 57,
            "dormancyCycles": 29.9,
            "activeCycles": 50.7
          },
          {
            "id": "chlorine_gas",
            "x": 48,
            "y": 70,
            "emitRate": 349,
            "avgEmitRate": 112,
            "idleTime": 349,
            "eruptionTime": 371,
            "dormancyCycles": 44.8,
            "activeCycles": 74.2
          },
          {
            "id": "hot_hydrogen",
            "x": 36,
            "y": 63,
            "emitRate": 416,
            "avgEmitRate": 79,
            "idleTime": 363,
            "eruptionTime": 172,
            "dormancyCycles": 47,
            "activeCycles": 67.3
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:64,31 48,33 48,45 64,51;48,45 48,33 46,30 33,31 29,38 35,50 40,51;35,50 29,38 18,39 14,50 22,59;14,50 18,39 13,33 0,33 0,52;52,78 47,65 44,63 33,71 33,79 44,85;33,79 33,71 22,64 16,69 15,81 23,87;44,85 33,79 23,87 23,96 45,96;15,81 16,69 0,66 0,85;23,87 15,81 0,85 0,96 23,96;64,58 47,65 52,78 64,80;64,80 52,78 44,85 45,96 64,96\nOilField:64,58 64,51 48,45 40,51 44,63 47,65;44,63 40,51 35,50 22,59 22,64 33,71;22,64 22,59 14,50 0,52 0,66 16,69",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 35,
            "y": 58
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 11,
            "y": 65,
            "emitRate": 267671,
            "avgEmitRate": 1145,
            "idleTime": 9183,
            "eruptionTime": 69,
            "dormancyCycles": 70.1,
            "activeCycles": 95.3
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:71,39 71,39 55,30 47,42 50,48 60,51;19,46 15,33 0,33 0,49 14,50;36,39 33,29 19,28 15,33 19,46 29,47;96,44 96,34 77,30 71,39 71,39 80,49;50,48 47,42 36,39 29,47 34,60 41,61;61,65 64,62 60,51 50,48 41,61 44,66;78,62 80,49 71,39 60,51 64,62;34,60 29,47 19,46 14,50 16,63 23,68;16,63 14,50 0,49 0,69;96,44 80,49 78,62 79,63 96,63\nFrozenWastes:44,66 41,61 34,60 23,68 24,80 42,80;79,63 78,62 64,62 61,65 63,80 79,80;61,65 44,66 42,80 63,80;23,68 16,63 0,69 0,80 24,80;96,63 79,63 79,80 96,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 78,
            "y": 37
          },
          {
            "id": "GravitasPedestal",
            "x": 71,
            "y": 37
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 15,
            "y": 46,
            "emitRate": 309,
            "avgEmitRate": 103,
            "idleTime": 273,
            "eruptionTime": 348,
            "dormancyCycles": 48.5,
            "activeCycles": 70.5
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:61,82 66,68 61,62 48,62 47,78 60,83;80,67 66,68 61,82 80,86;80,98 80,86 61,82 60,83 58,99 63,102;27,71 36,59 27,48 21,48 14,61 22,72;37,92 40,82 27,71 22,72 16,81 20,92 34,94;45,59 49,42 44,37 36,37 27,48 36,59;58,99 60,83 47,78 40,82 37,92 52,101;61,62 66,50 60,42 49,42 45,59 48,62;47,78 48,62 45,59 36,59 27,71 40,82;22,72 14,61 0,61 0,80 16,81;14,61 21,48 14,40 0,41 0,61;80,67 80,50 66,50 61,62 66,68;80,98 63,102 66,117 80,118;66,117 63,102 58,99 52,101 47,113 53,122 61,122;80,118 66,117 61,122 66,136 80,137;66,136 61,122 53,122 45,133 46,139 60,144;20,92 16,81 0,80 0,99 14,100;47,113 52,101 37,92 34,94 31,112 34,114;46,139 45,133 33,128 24,134 24,141 38,149;53,122 47,113 34,114 33,128 45,133;34,94 20,92 14,100 20,112 31,112;24,141 24,134 17,130 0,137 0,142 17,149;33,128 34,114 31,112 20,112 15,118 17,130 24,134;66,156 61,153 51,159 51,174 66,174;51,159 39,154 34,159 36,174 51,174;34,159 18,159 18,174 36,174;17,130 15,118 0,117 0,137;20,112 14,100 0,99 0,117 15,118;80,154 66,156 66,174 80,174;18,159 18,159 0,161 0,174 18,174;80,50 80,32 65,33 60,42 66,50\nSwamp:80,154 80,137 66,136 60,144 61,153 66,156;61,153 60,144 46,139 38,149 39,154 51,159;39,154 38,149 24,141 17,149 18,159 18,159 34,159;18,159 17,149 0,142 0,161",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 49,
            "y": 156
          },
          {
            "id": "GravitasPedestal",
            "x": 51,
            "y": 156
          }
        ],
        "geysers": [
          {
            "id": "filthy_water",
            "x": 29,
            "y": 120,
            "emitRate": 9326,
            "avgEmitRate": 2406,
            "idleTime": 224,
            "eruptionTime": 267,
            "dormancyCycles": 44.9,
            "activeCycles": 40.4
          },
          {
            "id": "salt_water",
            "x": 65,
            "y": 80,
            "emitRate": 8078,
            "avgEmitRate": 2731,
            "idleTime": 253,
            "eruptionTime": 357,
            "dormancyCycles": 47.1,
            "activeCycles": 64.2
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:96,78 96,65 77,63 75,76 80,80;21,59 20,57 18,56 0,58 0,75 15,76;75,76 77,63 76,61 62,58 55,65 56,75 64,79;35,77 35,66 21,59 15,76 17,78 31,80;96,48 80,47 76,61 77,63 96,65\nFrozenWastes:64,79 56,75 48,81 49,96 64,96;80,80 75,76 64,79 64,96 79,96;96,78 80,80 79,96 96,96;31,80 17,78 14,96 32,96;17,78 15,76 0,75 0,96 14,96;48,81 35,77 31,80 32,96 49,96\nSandstone:56,75 55,65 43,60 35,66 35,77 48,81",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 20,
            "y": 80
          },
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 80
          },
          {
            "id": "GeneShuffler",
            "x": 22,
            "y": 74
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 56,
            "y": 82,
            "emitRate": 4695,
            "avgEmitRate": 1327,
            "idleTime": 313,
            "eruptionTime": 322,
            "dormancyCycles": 66.2,
            "activeCycles": 83.3
          },
          {
            "id": "hot_steam",
            "x": 36,
            "y": 83,
            "emitRate": 1955,
            "avgEmitRate": 760,
            "idleTime": 223,
            "eruptionTime": 353,
            "dormancyCycles": 41.5,
            "activeCycles": 71.9
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaSandstoneFrozen",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumSwampy",
        "q": 3,
        "r": -1
      },
      {
        "id": "TundraMoonlet",
        "q": -5,
        "r": 3
      },
      {
        "id": "MarshyMoonlet",
        "q": 0,
        "r": -5
      },
      {
        "id": "NiobiumMoonlet",
        "q": 5,
        "r": 1
      },
      {
        "id": "MooMoonlet",
        "q": -3,
        "r": 6
      },
      {
        "id": "WaterMoonlet",
        "q": -6,
        "r": -1
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": -8,
        "r": 8
      },
      {
        "id": "TemporalTear",
        "q": 4,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -3,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 5,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -2,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 9,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 8,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -11,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -10,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 11,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -8,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -7,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 1,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 2,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 1,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 1,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 3,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -4,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": -4,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": 0,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 1,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -7,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 8,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 10,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -11,
        "r": 4
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation4",
        "q": -2,
        "r": 3
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": 6,
        "r": -2
      }
    ]
  },
  {
    "coordinate": "SNDST-C-1607940615-0-0-0",
    "cluster": "SNDST-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "TerraMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 274,
        "worldTraits": [],
        "biomePaths": "Sandstone:104,175 109,168 108,145 94,130 75,128 55,142 51,152 57,176 82,186;131,168 136,155 126,138 108,145 109,168;75,128 69,113 53,111 44,126 55,142;112,194 104,175 82,186 82,198 101,206;51,152 55,142 44,126 30,126 22,139 30,155\nBoggyMarsh:126,138 129,129 108,113 102,115 94,130 108,145;160,152 160,133 132,127 129,129 126,138 136,155;160,104 160,77 140,78 131,95 139,105;160,152 136,155 131,168 137,179 160,180;82,198 82,186 57,176 54,180 53,200 72,206;103,219 101,206 82,198 72,206 73,222 85,229;46,224 46,205 27,200 18,218 29,231\nToxicJungle:160,104 139,105 132,127 160,133;160,226 160,204 133,204 130,219 138,228;130,219 133,204 129,196 112,194 101,206 103,219 111,224;53,200 54,180 31,177 23,192 27,200 46,205;57,176 51,152 30,155 26,165 31,177 54,180;137,179 131,168 109,168 104,175 112,194 129,196;31,177 26,165 0,166 0,189 23,192;22,139 30,126 21,112 0,112 0,140\nFrozenWastes:132,127 139,105 131,95 118,95 108,113 129,129;102,115 85,99 80,99 69,113 75,128 94,130;44,126 53,111 46,97 30,97 21,112 30,126\nBarren:131,95 140,78 126,59 120,60 106,81 118,95;106,81 120,60 102,46 81,61 81,61 99,81;160,77 160,50 133,50 126,59 140,78;85,99 99,81 81,61 63,82 80,99;57,82 44,65 31,66 21,83 30,97 46,97;63,82 81,61 81,61 70,49 52,51 44,65 57,82;31,66 20,53 0,53 0,83 21,83;133,250 138,228 130,219 111,224 112,246 131,251;86,246 85,229 73,222 57,231 57,246 76,254;112,246 111,224 103,219 85,229 86,246 103,252;57,246 57,231 46,224 29,231 26,246 49,253;26,246 29,231 18,218 0,219 0,246 25,247;160,226 138,228 133,250 160,254\nWasteland:108,113 118,95 106,81 99,81 85,99 102,115;80,99 63,82 57,82 46,97 53,111 69,113;160,180 137,179 129,196 133,204 160,204;30,97 21,83 0,83 0,112 21,112;73,222 72,206 53,200 46,205 46,224 57,231;26,165 30,155 22,139 0,140 0,166;18,218 27,200 23,192 0,189 0,219\nMagmaCore:103,252 86,246 76,254 76,274 103,274;160,254 133,250 131,251 130,274 160,274;131,251 112,246 103,252 103,274 130,274;76,254 57,246 49,253 49,274 76,274;25,247 0,246 0,274 22,274;49,253 26,246 25,247 22,274 49,274",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 82,
            "y": 157
          },
          {
            "id": "WarpConduitReceiver",
            "x": 126,
            "y": 192
          },
          {
            "id": "WarpConduitSender",
            "x": 61,
            "y": 188
          },
          {
            "id": "GravitasPedestal",
            "x": 40,
            "y": 112
          },
          {
            "id": "WarpPortal",
            "x": 79,
            "y": 208
          },
          {
            "id": "WarpReceiver",
            "x": 80,
            "y": 214
          },
          {
            "id": "GeneShuffler",
            "x": 114,
            "y": 131
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 105,
            "y": 214,
            "emitRate": 2923,
            "avgEmitRate": 1277,
            "idleTime": 278,
            "eruptionTime": 830,
            "dormancyCycles": 53.7,
            "activeCycles": 75.1
          },
          {
            "id": "slush_water",
            "x": 131,
            "y": 116,
            "emitRate": 5107,
            "avgEmitRate": 1608,
            "idleTime": 255,
            "eruptionTime": 302,
            "dormancyCycles": 27.1,
            "activeCycles": 37.5
          },
          {
            "id": "slush_salt_water",
            "x": 27,
            "y": 115,
            "emitRate": 3931,
            "avgEmitRate": 1408,
            "idleTime": 207,
            "eruptionTime": 246,
            "dormancyCycles": 24.8,
            "activeCycles": 47.8
          },
          {
            "id": "methane",
            "x": 37,
            "y": 213,
            "emitRate": 399,
            "avgEmitRate": 82,
            "idleTime": 333,
            "eruptionTime": 207,
            "dormancyCycles": 49.8,
            "activeCycles": 57.5
          },
          {
            "id": "small_volcano",
            "x": 47,
            "y": 237,
            "emitRate": 147217,
            "avgEmitRate": 692,
            "idleTime": 8344,
            "eruptionTime": 61,
            "dormancyCycles": 38.3,
            "activeCycles": 70.1
          },
          {
            "id": "small_volcano",
            "x": 76,
            "y": 235,
            "emitRate": 196590,
            "avgEmitRate": 772,
            "idleTime": 8591,
            "eruptionTime": 53,
            "dormancyCycles": 56.1,
            "activeCycles": 102.5
          },
          {
            "id": "small_volcano",
            "x": 105,
            "y": 238,
            "emitRate": 146007,
            "avgEmitRate": 613,
            "idleTime": 10450,
            "eruptionTime": 73,
            "dormancyCycles": 47.6,
            "activeCycles": 73
          },
          {
            "id": "big_volcano",
            "x": 143,
            "y": 207,
            "emitRate": 276971,
            "avgEmitRate": 1283,
            "idleTime": 8259,
            "eruptionTime": 61,
            "dormancyCycles": 36.5,
            "activeCycles": 61.3
          },
          {
            "id": "steam",
            "x": 129,
            "y": 99,
            "emitRate": 6924,
            "avgEmitRate": 1411,
            "idleTime": 444,
            "eruptionTime": 271,
            "dormancyCycles": 48.3,
            "activeCycles": 56.2
          },
          {
            "id": "hot_hydrogen",
            "x": 19,
            "y": 199,
            "emitRate": 269,
            "avgEmitRate": 71,
            "idleTime": 247,
            "eruptionTime": 209,
            "dormancyCycles": 50.3,
            "activeCycles": 68.1
          }
        ]
      },
      {
        "id": "IdealLandingSite",
        "offsetX": 244,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "Forest:111,63 108,44 106,42 87,49 94,67 103,69;128,41 108,44 111,63 128,64;94,67 87,49 85,48 71,59 74,73 79,76;74,73 71,59 61,56 53,65 63,78;37,89 36,70 22,68 15,79 23,93;20,98 23,93 15,79 0,79 0,101;61,56 58,42 43,42 37,48 43,64 53,65;43,64 37,48 23,48 18,59 22,68 36,70;15,79 22,68 18,59 0,58 0,79;23,48 18,39 0,38 0,58 18,59\nOcean:107,91 108,90 103,69 94,67 79,76 83,95 84,95;128,88 128,64 111,63 103,69 108,90;112,111 107,91 84,95 86,113 102,121;59,90 63,78 53,65 43,64 36,70 37,89 44,94\nMetallic:83,95 79,76 74,73 63,78 59,90 66,98;128,88 108,90 107,91 112,111 128,113;74,129 75,121 63,111 50,116 49,129 64,136;63,111 66,98 59,90 44,94 43,109 50,116;43,109 44,94 37,89 23,93 20,98 26,114;25,116 26,114 20,98 0,101 0,118\nRadioactive:86,113 84,95 83,95 66,98 63,111 75,121;103,129 102,121 86,113 75,121 74,129 85,138;85,138 74,129 64,136 64,153 85,153;64,136 49,129 43,133 42,153 64,153;43,133 27,129 21,136 22,153 42,153;49,129 50,116 43,109 26,114 25,116 27,129 43,133;128,131 128,113 112,111 102,121 103,129 107,133;128,131 107,133 108,153 128,153;27,129 25,116 0,118 0,133 21,136;107,133 103,129 85,138 85,153 108,153;21,136 0,133 0,153 22,153",
        "pointsOfInterest": [
          {
            "id": "PropSurfaceSatellite3",
            "x": 65,
            "y": 59
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 86,
            "y": 80,
            "emitRate": 10811,
            "avgEmitRate": 3212,
            "idleTime": 405,
            "eruptionTime": 339,
            "dormancyCycles": 40.1,
            "activeCycles": 75.1
          },
          {
            "id": "liquid_co2",
            "x": 81,
            "y": 105,
            "emitRate": 573,
            "avgEmitRate": 151,
            "idleTime": 158,
            "eruptionTime": 137,
            "dormancyCycles": 75.1,
            "activeCycles": 99.2
          },
          {
            "id": "liquid_co2",
            "x": 80,
            "y": 138,
            "emitRate": 609,
            "avgEmitRate": 221,
            "idleTime": 163,
            "eruptionTime": 182,
            "dormancyCycles": 43.4,
            "activeCycles": 95.3
          },
          {
            "id": "molten_aluminum",
            "x": 59,
            "y": 118,
            "emitRate": 6741,
            "avgEmitRate": 297,
            "idleTime": 776,
            "eruptionTime": 62,
            "dormancyCycles": 49.8,
            "activeCycles": 74
          },
          {
            "id": "molten_gold",
            "x": 65,
            "y": 93,
            "emitRate": 8607,
            "avgEmitRate": 310,
            "idleTime": 816,
            "eruptionTime": 52,
            "dormancyCycles": 35.7,
            "activeCycles": 52.9
          },
          {
            "id": "molten_aluminum",
            "x": 39,
            "y": 96,
            "emitRate": 9556,
            "avgEmitRate": 293,
            "idleTime": 709,
            "eruptionTime": 39,
            "dormancyCycles": 67.7,
            "activeCycles": 95.3
          },
          {
            "id": "molten_gold",
            "x": 114,
            "y": 104,
            "emitRate": 11043,
            "avgEmitRate": 311,
            "idleTime": 519,
            "eruptionTime": 25,
            "dormancyCycles": 39.3,
            "activeCycles": 63.7
          },
          {
            "id": "small_volcano",
            "x": 111,
            "y": 136,
            "emitRate": 120029,
            "avgEmitRate": 611,
            "idleTime": 9437,
            "eruptionTime": 74,
            "dormancyCycles": 49.4,
            "activeCycles": 92.9
          },
          {
            "id": "filthy_water",
            "x": 27,
            "y": 107,
            "emitRate": 11966,
            "avgEmitRate": 2062,
            "idleTime": 370,
            "eruptionTime": 266,
            "dormancyCycles": 15,
            "activeCycles": 10.5
          },
          {
            "id": "hot_hydrogen",
            "x": 113,
            "y": 47,
            "emitRate": 429,
            "avgEmitRate": 127,
            "idleTime": 325,
            "eruptionTime": 252,
            "dormancyCycles": 31.4,
            "activeCycles": 66.5
          }
        ]
      },
      {
        "id": "WarpOilySwamp",
        "offsetX": 374,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalCaves",
          "MetalPoor"
        ],
        "biomePaths": "Swamp:91,73 75,63 66,81 83,90;66,81 75,63 74,60 58,56 49,70 62,82;109,85 103,69 91,73 83,90 85,94 101,97;78,104 85,94 83,90 66,81 62,82 56,95 62,105;103,69 103,68 102,56 81,48 74,60 75,63 91,73\nFrozenWastes:62,82 49,70 44,71 35,85 41,95 56,95;109,46 105,36 87,33 80,43 81,48 102,56;49,70 58,56 52,45 41,44 32,59 44,71;23,84 17,73 0,72 0,94 16,94;44,71 32,59 24,60 17,73 23,84 35,85;128,65 128,48 109,46 102,56 103,68;18,115 23,109 16,94 0,94 0,115;128,65 103,68 103,69 109,85 128,88;16,51 20,38 0,25 0,25 0,53\nRust:81,48 80,43 60,34 52,45 58,56 74,60;35,108 41,95 35,85 23,84 16,94 23,109;58,115 62,105 56,95 41,95 35,108 43,118;128,104 128,88 109,85 101,97 106,107;32,59 41,44 31,32 20,38 16,51 24,60;24,60 16,51 0,53 0,72 17,73\nOilField:84,124 85,121 78,104 62,105 58,115 67,129;104,113 106,107 101,97 85,94 78,104 85,121;66,133 67,129 58,115 43,118 40,130 47,138;23,133 18,115 0,115 0,136;128,127 128,104 106,107 104,113 112,128;112,128 104,113 85,121 84,124 90,135 107,136;40,130 43,118 35,108 23,109 18,115 23,133 23,133\nBarren:90,135 84,124 67,129 66,133 72,153 85,153;47,138 40,130 23,133 24,153 45,153;23,133 23,133 0,136 0,153 24,153;66,133 47,138 45,153 72,153;107,136 90,135 85,153 111,153;128,127 112,128 107,136 111,153 128,153",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 55,
            "y": 93
          },
          {
            "id": "WarpConduitSender",
            "x": 35,
            "y": 62
          },
          {
            "id": "WarpConduitReceiver",
            "x": 74,
            "y": 57
          },
          {
            "id": "WarpReceiver",
            "x": 88,
            "y": 80
          },
          {
            "id": "WarpPortal",
            "x": 66,
            "y": 80
          },
          {
            "id": "GeneShuffler",
            "x": 25,
            "y": 133
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 87,
            "y": 125,
            "emitRate": 4971,
            "avgEmitRate": 1505,
            "idleTime": 187,
            "eruptionTime": 178,
            "dormancyCycles": 53,
            "activeCycles": 87
          },
          {
            "id": "OilWell",
            "x": 86,
            "y": 117,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 45,
            "y": 134,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 100,
            "y": 118,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 69,
            "y": 127,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 36,
            "y": 128,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_co2",
            "x": 100,
            "y": 41,
            "emitRate": 335,
            "avgEmitRate": 85,
            "idleTime": 349,
            "eruptionTime": 261,
            "dormancyCycles": 48.5,
            "activeCycles": 70.9
          },
          {
            "id": "hot_co2",
            "x": 13,
            "y": 70,
            "emitRate": 436,
            "avgEmitRate": 89,
            "idleTime": 361,
            "eruptionTime": 236,
            "dormancyCycles": 65.3,
            "activeCycles": 70.4
          },
          {
            "id": "liquid_sulfur",
            "x": 70,
            "y": 44,
            "emitRate": 6678,
            "avgEmitRate": 1631,
            "idleTime": 449,
            "eruptionTime": 303,
            "dormancyCycles": 45.7,
            "activeCycles": 70.6
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 162,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "FrozenWastes:44,67 49,59 42,45 32,49 28,60 35,68;42,45 44,41 43,35 29,28 20,34 20,41 32,49;64,58 64,40 44,41 42,45 49,59;35,68 28,60 17,61 13,69 18,78 29,79;64,58 49,59 44,67 50,77 64,77;28,60 32,49 20,41 12,50 17,61;64,95 64,77 50,77 44,88 49,95;20,41 20,34 15,31 0,33 0,49 12,50;18,78 13,69 0,69 0,88 11,89;45,107 49,95 44,88 34,88 28,96 33,109;50,77 44,67 35,68 29,79 34,88 44,88;33,110 18,115 17,128 36,128;50,112 45,107 33,109 33,110 36,128 47,128;12,108 17,96 11,89 0,88 0,109;64,111 50,112 47,128 64,128;18,115 12,108 0,109 0,128 17,128;34,88 29,79 18,78 11,89 17,96 28,96;64,111 64,95 49,95 45,107 50,112;33,110 33,109 28,96 17,96 12,108 18,115;13,69 17,61 12,50 0,49 0,69",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 36,
            "y": 36
          },
          {
            "id": "GravitasPedestal",
            "x": 34,
            "y": 92
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 16,
            "y": 105,
            "emitRate": 11617,
            "avgEmitRate": 287,
            "idleTime": 679,
            "eruptionTime": 34,
            "dormancyCycles": 70.8,
            "activeCycles": 77.9
          },
          {
            "id": "molten_iron",
            "x": 33,
            "y": 55,
            "emitRate": 5822,
            "avgEmitRate": 317,
            "idleTime": 789,
            "eruptionTime": 82,
            "dormancyCycles": 38,
            "activeCycles": 52
          },
          {
            "id": "molten_iron",
            "x": 51,
            "y": 109,
            "emitRate": 5992,
            "avgEmitRate": 217,
            "idleTime": 687,
            "eruptionTime": 51,
            "dormancyCycles": 57.3,
            "activeCycles": 62.6
          },
          {
            "id": "molten_iron",
            "x": 24,
            "y": 75,
            "emitRate": 8046,
            "avgEmitRate": 295,
            "idleTime": 701,
            "eruptionTime": 49,
            "dormancyCycles": 72,
            "activeCycles": 92.7
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 228,
        "offsetY": 176,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "BoggyMarsh:44,53 44,39 31,34 28,36 28,51 43,56;64,48 64,38 50,34 44,39 44,53;41,66 43,62 43,56 28,51 20,56 18,63 23,70;45,80 41,66 23,70 22,80 42,84;18,63 20,56 9,45 0,45 0,66;22,80 23,70 18,63 0,66 0,80 20,82;28,51 28,36 18,34 9,45 20,56;64,48 44,53 43,56 43,62 64,67\nToxicJungle:64,79 64,67 43,62 41,66 45,80\nMagmaCore:64,79 45,80 42,84 42,96 64,96;42,84 22,80 20,82 20,96 42,96;20,82 0,80 0,96 20,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 45,
            "y": 42
          },
          {
            "id": "GravitasPedestal",
            "x": 31,
            "y": 42
          },
          {
            "id": "SapTree",
            "x": 38,
            "y": 42
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 11,
            "y": 76,
            "emitRate": 7022,
            "avgEmitRate": 257,
            "idleTime": 671,
            "eruptionTime": 46,
            "dormancyCycles": 43.5,
            "activeCycles": 57.2
          },
          {
            "id": "molten_tungsten",
            "x": 56,
            "y": 90,
            "emitRate": 8802,
            "avgEmitRate": 274,
            "idleTime": 701,
            "eruptionTime": 41,
            "dormancyCycles": 64.1,
            "activeCycles": 84.1
          },
          {
            "id": "molten_tungsten",
            "x": 13,
            "y": 91,
            "emitRate": 7844,
            "avgEmitRate": 284,
            "idleTime": 714,
            "eruptionTime": 47,
            "dormancyCycles": 49.1,
            "activeCycles": 70
          },
          {
            "id": "hot_hydrogen",
            "x": 34,
            "y": 59,
            "emitRate": 268,
            "avgEmitRate": 84,
            "idleTime": 399,
            "eruptionTime": 479,
            "dormancyCycles": 59.6,
            "activeCycles": 81.4
          },
          {
            "id": "slimy_po2",
            "x": 48,
            "y": 66,
            "emitRate": 356,
            "avgEmitRate": 110,
            "idleTime": 108,
            "eruptionTime": 115,
            "dormancyCycles": 41.6,
            "activeCycles": 62.3
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 504,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:80,50 80,33 79,33 66,33 61,40 67,51 77,53;79,65 77,53 67,51 58,61 70,75;58,61 67,51 61,40 50,40 46,50 56,61;96,50 80,50 77,53 79,65 96,68;46,50 50,40 46,34 32,33 31,34 30,49 35,53;56,61 46,50 35,53 34,63 45,72;34,63 35,53 30,49 18,51 14,61 22,70;30,49 31,34 17,32 11,42 18,51;96,33 80,33 80,50 96,50;14,61 18,51 11,42 0,42 0,63\nFrozenWastes:96,68 79,65 70,75 70,80 96,80;70,75 58,61 56,61 45,72 45,80 70,80;45,72 34,63 22,70 21,80 45,80;22,70 14,61 0,63 0,80 21,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 57,
            "y": 51
          },
          {
            "id": "GravitasPedestal",
            "x": 50,
            "y": 51
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 14,
            "y": 52,
            "emitRate": 356,
            "avgEmitRate": 92,
            "idleTime": 402,
            "eruptionTime": 318,
            "dormancyCycles": 65.4,
            "activeCycles": 92.3
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 162,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,87 80,64 71,64 61,76 72,87;80,110 80,87 72,87 62,98 70,110;72,87 61,76 57,76 49,87 56,98 62,98;61,76 71,64 62,54 54,54 48,66 57,76;62,119 70,110 62,98 56,98 47,109 57,120;48,66 54,54 46,46 36,47 32,61 39,67;62,54 68,42 60,32 51,35 46,46 54,54;57,76 48,66 39,67 34,79 39,87 49,87;34,79 39,67 32,61 20,62 16,71 21,80;32,61 36,47 31,43 18,45 15,53 20,62;31,43 32,30 19,25 12,35 18,45;46,46 51,35 40,24 32,30 31,43 36,47;80,110 70,110 62,119 72,133 80,134;72,133 62,119 57,120 49,131 61,141;47,109 56,98 49,87 39,87 34,98 41,109;16,71 20,62 15,53 0,54 0,72;80,64 80,41 68,42 62,54 71,64;34,98 39,87 34,79 21,80 17,89 22,99;17,89 21,80 16,71 0,72 0,89;18,105 22,99 17,89 0,89 0,106;39,136 42,133 36,116 22,118 19,122 20,127 35,137;22,118 18,105 0,106 0,120 19,122;36,116 41,109 34,98 22,99 18,105 22,118;35,137 20,127 13,138 16,145 28,147;15,53 18,45 12,35 0,35 0,54;18,164 10,156 0,156 0,174 17,174;49,131 57,120 47,109 41,109 36,116 42,133;49,154 47,154 35,159 37,174 54,174;35,159 31,157 18,164 17,174 37,174;67,155 60,150 49,154 54,174 63,174;20,127 19,122 0,120 0,136 13,138;80,154 67,155 63,174 80,174\nSwamp:60,150 61,141 49,131 42,133 39,136 47,154 49,154;80,154 80,134 72,133 61,141 60,150 67,155;47,154 39,136 35,137 28,147 31,157 35,159;31,157 28,147 16,145 10,156 18,164;10,156 16,145 13,138 0,136 0,156",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 25,
            "y": 158
          },
          {
            "id": "GravitasPedestal",
            "x": 27,
            "y": 158
          }
        ],
        "geysers": [
          {
            "id": "filthy_water",
            "x": 36,
            "y": 78,
            "emitRate": 12968,
            "avgEmitRate": 3071,
            "idleTime": 465,
            "eruptionTime": 290,
            "dormancyCycles": 33.4,
            "activeCycles": 53.6
          },
          {
            "id": "hot_water",
            "x": 58,
            "y": 73,
            "emitRate": 20273,
            "avgEmitRate": 3304,
            "idleTime": 531,
            "eruptionTime": 179,
            "dormancyCycles": 54.4,
            "activeCycles": 99.8
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 294,
        "offsetY": 155,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:64,44 64,30 49,28 43,34 48,44;30,58 34,55 29,39 19,39 14,47 18,58;19,39 15,31 0,29 0,47 14,47;43,55 48,44 43,34 35,34 29,39 34,55;31,77 32,76 30,58 18,58 14,64 19,77;14,64 18,58 14,47 0,47 0,64;19,77 14,64 0,64 0,80 16,80;31,77 19,77 16,80 18,96 31,96;16,80 0,80 0,96 18,96;46,77 45,77 32,76 31,77 31,96 45,96;64,61 49,62 45,77 46,77 64,79\nOilField:64,61 64,44 48,44 43,55 49,62;49,62 43,55 34,55 30,58 32,76 45,77;64,79 46,77 45,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 45,
            "y": 71
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 49,
            "y": 48,
            "emitRate": 252067,
            "avgEmitRate": 1053,
            "idleTime": 9594,
            "eruptionTime": 69,
            "dormancyCycles": 65.4,
            "activeCycles": 92.3
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 360,
        "offsetY": 155,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [
          "DistressSignal"
        ],
        "biomePaths": "Barren:160,74 160,54 140,56 145,75;124,74 126,59 117,53 106,59 106,73 113,77;67,75 69,64 59,55 48,58 51,76 60,79;51,76 48,58 48,58 33,63 33,77 41,81;17,79 15,66 0,62 0,83;24,58 23,52 17,48 0,52 0,62 15,66\nSandstone:144,76 145,75 140,56 140,56 126,59 124,74 130,78;106,73 106,59 97,54 86,60 89,75 96,78;33,77 33,63 24,58 15,66 17,79 21,82;89,75 86,60 81,58 69,64 67,75 79,81\nFrozenWastes:160,74 145,75 144,76 147,96 160,96;144,76 130,78 128,96 147,96;130,78 124,74 113,77 113,96 128,96;113,77 106,73 96,78 98,96 113,96;60,79 51,76 41,81 42,96 60,96;96,78 89,75 79,81 79,96 98,96;41,81 33,77 21,82 22,96 42,96;21,82 17,79 0,83 0,96 22,96;79,81 67,75 60,79 60,96 79,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 14,
            "y": 62
          },
          {
            "id": "GravitasPedestal",
            "x": 18,
            "y": 62
          },
          {
            "id": "GeneShuffler",
            "x": 16,
            "y": 56
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 137,
            "y": 83,
            "emitRate": 4845,
            "avgEmitRate": 1274,
            "idleTime": 345,
            "eruptionTime": 335,
            "dormancyCycles": 50.9,
            "activeCycles": 58.3
          },
          {
            "id": "steam",
            "x": 122,
            "y": 82,
            "emitRate": 5708,
            "avgEmitRate": 2034,
            "idleTime": 285,
            "eruptionTime": 351,
            "dormancyCycles": 39.1,
            "activeCycles": 71.1
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "TerraMoonlet",
        "q": 0,
        "r": 0
      },
      {
        "id": "IdealLandingSite",
        "q": -2,
        "r": 3
      },
      {
        "id": "WarpOilySwamp",
        "q": -5,
        "r": 1
      },
      {
        "id": "TundraMoonlet",
        "q": -4,
        "r": 8
      },
      {
        "id": "MarshyMoonlet",
        "q": 2,
        "r": 4
      },
      {
        "id": "MooMoonlet",
        "q": 7,
        "r": -1
      },
      {
        "id": "WaterMoonlet",
        "q": 4,
        "r": -8
      },
      {
        "id": "NiobiumMoonlet",
        "q": -9,
        "r": 10
      },
      {
        "id": "RegolithMoonlet",
        "q": 9,
        "r": -7
      },
      {
        "id": "TemporalTear",
        "q": 7,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 1,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -7,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -8,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -7,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 1,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 1,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 0,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -4,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 11,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -6,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 3,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 4,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 5,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 5,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -11,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 1,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 10,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 8,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 8,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 7,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 7,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": -11,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -10,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 3,
        "r": 6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation6",
        "q": -2,
        "r": -1
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -2,
        "r": 9
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": -6,
        "r": 11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": -1,
        "r": -5
      }
    ]
  },
  {
    "coordinate": "V-OCAN-C-1381507755-0-0-0",
    "cluster": "V-OCAN-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaOceania",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "GlaciersLarge",
          "DeepOil",
          "GeoDormant",
          "Volcanoes"
        ],
        "biomePaths": "Sandstone:132,196 135,193 143,173 141,161 134,153 113,148 100,154 93,171 96,186 109,197;96,186 93,171 76,168 69,187 74,194;100,154 90,146 72,158 76,168 93,171;113,148 113,135 100,127 90,132 90,146 100,154;132,213 132,196 109,197 108,215 119,221;137,131 132,127 113,135 113,148 134,153;166,194 165,180 143,173 135,193 166,195;172,167 168,156 141,161 143,173 165,180\nMagmaCore:30,354 23,345 0,345 0,380 28,380;60,352 53,347 30,354 28,380 61,380;92,298 92,287 75,280 64,293 72,305;91,353 80,345 60,352 61,380 90,380;121,354 107,346 91,353 90,380 121,380;151,354 134,346 121,354 121,380 152,380;147,301 144,284 134,280 121,290 121,296 140,307;181,351 163,343 151,354 152,380 180,380;210,352 189,346 181,351 180,380 212,380;240,345 218,345 210,352 212,380 240,380;240,252 220,255 223,275 240,278\nFrozenWastes:34,327 26,311 0,309 0,345 23,345;25,233 37,211 34,204 0,197 0,234;185,252 178,229 165,223 155,228 150,244 167,263 176,263;240,252 240,214 212,219 212,250 220,255;240,214 240,189 206,187 200,197 204,213 212,219\nSwamp:37,291 30,278 0,277 0,309 26,311;42,186 35,169 0,178 0,197 34,204;72,214 74,194 69,187 42,186 34,204 37,211 62,220;106,327 105,308 92,298 72,305 70,324 80,334;100,127 101,103 83,93 65,108 66,119 90,132;148,93 148,76 134,64 115,75 116,95 130,102;165,223 166,195 135,193 132,196 132,213 155,228;206,325 213,311 204,294 191,292 175,311 177,320 191,330;208,159 197,137 173,140 168,156 172,167 201,170;240,157 208,159 201,170 206,187 240,189\nOcean:42,252 25,233 0,234 0,277 30,278;34,128 27,107 26,106 0,110 0,140 23,141;70,324 72,305 64,293 37,291 26,311 34,327 52,335;75,280 71,256 59,248 42,252 30,278 37,291 64,293;59,248 62,220 37,211 25,233 42,252;121,249 119,221 108,215 90,223 90,247 104,255;116,95 115,75 98,65 81,80 83,93 101,103;138,327 140,307 121,296 105,308 106,327 107,329 134,331;150,244 155,228 132,213 119,221 121,249 127,251;161,126 164,104 148,93 130,102 132,127 137,131;167,263 150,244 127,251 134,280 144,284;191,292 176,263 167,263 144,284 147,301 175,311;168,156 173,140 161,126 137,131 134,153 141,161;223,275 220,255 212,250 185,252 176,263 191,292 204,294;206,187 201,170 172,167 165,180 166,194 200,197;197,137 207,118 197,99 182,95 164,104 161,126 173,140;240,345 240,310 213,311 206,325 218,345;240,157 240,118 207,118 197,137 208,159;240,118 240,81 208,77 197,99 207,118\nToxicJungle:35,169 35,168 23,141 0,140 0,178;76,168 72,158 60,153 35,168 35,169 42,186 69,187;60,153 54,131 34,128 23,141 35,168;90,247 90,223 72,214 62,220 59,248 71,256;83,93 81,80 62,70 47,83 48,95 65,108;177,320 175,311 147,301 140,307 138,327 163,337;182,95 182,75 172,65 148,76 148,93 164,104;204,213 200,197 166,194 166,195 165,223 178,229\nRadioactive:26,106 21,90 0,88 0,110;48,95 47,83 32,78 21,90 26,106 27,107;121,296 121,290 102,281 92,287 92,298 105,308;208,77 205,73 182,75 182,95 197,99\nBarren:32,78 27,58 0,54 0,88 21,90;62,70 61,57 38,44 27,58 32,78 47,83;98,65 97,55 78,42 61,57 62,70 81,80;134,64 134,53 116,38 97,55 98,65 115,75;172,65 172,55 154,38 134,53 134,64 148,76;205,73 208,54 196,38 172,55 172,65 182,75;240,46 208,54 205,73 208,77 240,81\nOilField:53,347 52,335 34,327 23,345 30,354;80,345 80,334 70,324 52,335 53,347 60,352;107,346 107,329 106,327 80,334 80,345 91,353;134,346 134,331 107,329 107,346 121,354;163,343 163,337 138,327 134,331 134,346 151,354;189,346 191,330 177,320 163,337 163,343 181,351;218,345 206,325 191,330 189,346 210,352\nBoggyMarsh:66,119 65,108 48,95 27,107 34,128 54,131;90,146 90,132 66,119 54,131 60,153 72,158;102,281 104,255 90,247 71,256 75,280 92,287;108,215 109,197 96,186 74,194 72,214 90,223;134,280 127,251 121,249 104,255 102,281 121,290;132,127 130,102 116,95 101,103 100,127 113,135;212,250 212,219 204,213 178,229 185,252;240,310 240,278 223,275 204,294 213,311",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 118,
            "y": 176
          },
          {
            "id": "MassiveHeatSink",
            "x": 20,
            "y": 318
          },
          {
            "id": "MassiveHeatSink",
            "x": 214,
            "y": 215
          },
          {
            "id": "MassiveHeatSink",
            "x": 167,
            "y": 244
          },
          {
            "id": "WarpConduitSender",
            "x": 154,
            "y": 266
          },
          {
            "id": "WarpConduitReceiver",
            "x": 38,
            "y": 281
          },
          {
            "id": "GravitasPedestal",
            "x": 36,
            "y": 90
          },
          {
            "id": "WarpReceiver",
            "x": 176,
            "y": 112
          },
          {
            "id": "WarpPortal",
            "x": 175,
            "y": 106
          },
          {
            "id": "GeneShuffler",
            "x": 177,
            "y": 201
          },
          {
            "id": "GeneShuffler",
            "x": 80,
            "y": 123
          },
          {
            "id": "GeneShuffler",
            "x": 118,
            "y": 258
          },
          {
            "id": "GeneShuffler",
            "x": 33,
            "y": 208
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 32,
            "y": 156,
            "emitRate": 6141,
            "avgEmitRate": 1366,
            "idleTime": 423,
            "eruptionTime": 232,
            "dormancyCycles": 47.6,
            "activeCycles": 80.6
          },
          {
            "id": "chlorine_gas",
            "x": 169,
            "y": 320,
            "emitRate": 505,
            "avgEmitRate": 127,
            "idleTime": 400,
            "eruptionTime": 288,
            "dormancyCycles": 58.3,
            "activeCycles": 88.1
          },
          {
            "id": "steam",
            "x": 118,
            "y": 105,
            "emitRate": 4177,
            "avgEmitRate": 1574,
            "idleTime": 286,
            "eruptionTime": 467,
            "dormancyCycles": 57.6,
            "activeCycles": 89.2
          },
          {
            "id": "methane",
            "x": 208,
            "y": 297,
            "emitRate": 329,
            "avgEmitRate": 102,
            "idleTime": 394,
            "eruptionTime": 421,
            "dormancyCycles": 24.1,
            "activeCycles": 36.3
          },
          {
            "id": "salt_water",
            "x": 143,
            "y": 135,
            "emitRate": 8990,
            "avgEmitRate": 2382,
            "idleTime": 298,
            "eruptionTime": 258,
            "dormancyCycles": 60.3,
            "activeCycles": 80.5
          },
          {
            "id": "OilWell",
            "x": 148,
            "y": 338,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 53,
            "y": 339,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 194,
            "y": 338,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "big_volcano",
            "x": 55,
            "y": 301,
            "emitRate": 237431,
            "avgEmitRate": 1191,
            "idleTime": 9786,
            "eruptionTime": 80,
            "dormancyCycles": 48.1,
            "activeCycles": 77.4
          },
          {
            "id": "hot_water",
            "x": 97,
            "y": 265,
            "emitRate": 9607,
            "avgEmitRate": 2874,
            "idleTime": 376,
            "eruptionTime": 316,
            "dormancyCycles": 33.5,
            "activeCycles": 63.5
          },
          {
            "id": "chlorine_gas",
            "x": 189,
            "y": 88,
            "emitRate": 388,
            "avgEmitRate": 103,
            "idleTime": 354,
            "eruptionTime": 308,
            "dormancyCycles": 55.8,
            "activeCycles": 74
          },
          {
            "id": "slush_salt_water",
            "x": 63,
            "y": 181,
            "emitRate": 5266,
            "avgEmitRate": 1368,
            "idleTime": 317,
            "eruptionTime": 250,
            "dormancyCycles": 62.2,
            "activeCycles": 88.9
          },
          {
            "id": "hot_water",
            "x": 67,
            "y": 101,
            "emitRate": 22852,
            "avgEmitRate": 2545,
            "idleTime": 282,
            "eruptionTime": 67,
            "dormancyCycles": 60.7,
            "activeCycles": 83.1
          },
          {
            "id": "hot_hydrogen",
            "x": 69,
            "y": 330,
            "emitRate": 386,
            "avgEmitRate": 115,
            "idleTime": 317,
            "eruptionTime": 265,
            "dormancyCycles": 38.1,
            "activeCycles": 73.3
          },
          {
            "id": "small_volcano",
            "x": 168,
            "y": 303,
            "emitRate": 131028,
            "avgEmitRate": 611,
            "idleTime": 8776,
            "eruptionTime": 65,
            "dormancyCycles": 37.3,
            "activeCycles": 64.7
          },
          {
            "id": "filthy_water",
            "x": 159,
            "y": 279,
            "emitRate": 7130,
            "avgEmitRate": 2749,
            "idleTime": 264,
            "eruptionTime": 472,
            "dormancyCycles": 47.2,
            "activeCycles": 71.2
          },
          {
            "id": "hot_steam",
            "x": 184,
            "y": 264,
            "emitRate": 1741,
            "avgEmitRate": 654,
            "idleTime": 238,
            "eruptionTime": 383,
            "dormancyCycles": 66.4,
            "activeCycles": 103.5
          },
          {
            "id": "big_volcano",
            "x": 78,
            "y": 293,
            "emitRate": 269828,
            "avgEmitRate": 1244,
            "idleTime": 10345,
            "eruptionTime": 78,
            "dormancyCycles": 35.6,
            "activeCycles": 57.1
          },
          {
            "id": "big_volcano",
            "x": 134,
            "y": 294,
            "emitRate": 220361,
            "avgEmitRate": 1124,
            "idleTime": 7760,
            "eruptionTime": 67,
            "dormancyCycles": 63.2,
            "activeCycles": 92.3
          },
          {
            "id": "OilWell",
            "x": 43,
            "y": 338,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 39,
            "y": 344,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 58,
            "y": 347,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 59,
            "y": 350,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 155,
            "y": 347,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 138,
            "y": 346,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 167,
            "y": 339,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 179,
            "y": 336,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 201,
            "y": 332,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 210,
            "y": 346,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "MediumForestyWasteland",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "MetalCaves",
          "Volcanoes"
        ],
        "biomePaths": "Forest:97,101 95,86 80,76 66,80 60,90 64,107 80,112;128,113 118,97 100,102 105,121 117,124;118,97 122,89 116,79 107,77 95,86 97,101 100,102;107,77 99,58 84,62 80,76 95,86;80,76 84,62 71,50 70,50 57,66 66,80;105,121 100,102 97,101 80,112 83,127 98,129;83,127 80,112 64,107 58,112 59,131 78,131;64,107 60,90 42,89 38,95 40,109 58,112;66,80 57,66 46,65 39,83 42,89 60,90\nWasteland:146,50 137,39 124,42 119,53 127,64 137,65;160,50 146,50 137,65 144,75 160,75;99,58 101,55 97,42 85,37 71,50 84,62;146,101 137,88 122,89 118,97 128,113 137,113;35,129 37,111 24,106 13,113 13,125 21,131;24,106 24,96 21,90 0,91 0,109 13,113;46,65 43,60 24,60 19,69 25,82 39,83;160,75 144,75 137,88 146,101 160,101;46,44 39,37 23,40 19,47 24,60 43,60;19,69 24,60 19,47 0,48 0,70\nRust:127,64 119,53 101,55 99,58 107,77 116,79;137,88 144,75 137,65 127,64 116,79 122,89;119,53 124,42 112,27 97,42 101,55;160,125 160,101 146,101 137,113 144,126;70,50 61,41 46,44 43,60 46,65 57,66;21,90 25,82 19,69 0,70 0,91;59,131 59,131 58,112 40,109 37,111 35,129 39,133\nMagmaCore:118,132 117,124 105,121 98,129 101,135;40,109 38,95 24,96 24,106 37,111;38,95 42,89 39,83 25,82 21,90 24,96;13,125 13,113 0,109 0,127\nRadioactive:138,136 144,126 137,113 128,113 117,124 118,132 123,138;160,125 144,126 138,136 148,152 160,153;98,151 101,135 98,129 83,127 78,131 81,151 88,154;120,154 123,138 118,132 101,135 98,151 110,159;81,151 78,131 59,131 59,131 60,152 66,157;88,154 81,151 66,157 67,176 88,176;160,153 148,152 136,163 137,176 160,176;60,152 59,131 39,133 38,153 44,157;44,157 38,153 21,156 20,176 44,176;38,153 39,133 35,129 21,131 16,152 21,156;16,152 21,131 13,125 0,127 0,153\nFrozenWastes:148,152 138,136 123,138 120,154 136,163;110,159 98,151 88,154 88,176 109,176;21,156 16,152 0,153 0,176 20,176;66,157 60,152 44,157 44,176 67,176;136,163 120,154 110,159 109,176 137,176",
        "pointsOfInterest": [
          {
            "id": "WarpConduitReceiver",
            "x": 52,
            "y": 104
          },
          {
            "id": "WarpConduitSender",
            "x": 103,
            "y": 108
          },
          {
            "id": "WarpReceiver",
            "x": 88,
            "y": 97
          },
          {
            "id": "WarpPortal",
            "x": 66,
            "y": 97
          },
          {
            "id": "GeneShuffler",
            "x": 50,
            "y": 114
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 86,
            "y": 130,
            "emitRate": 506,
            "avgEmitRate": 191,
            "idleTime": 245,
            "eruptionTime": 267,
            "dormancyCycles": 38.9,
            "activeCycles": 102.9
          },
          {
            "id": "liquid_co2",
            "x": 84,
            "y": 167,
            "emitRate": 538,
            "avgEmitRate": 141,
            "idleTime": 408,
            "eruptionTime": 333,
            "dormancyCycles": 49.6,
            "activeCycles": 69.3
          },
          {
            "id": "molten_cobalt",
            "x": 124,
            "y": 96,
            "emitRate": 6525,
            "avgEmitRate": 289,
            "idleTime": 703,
            "eruptionTime": 56,
            "dormancyCycles": 35.8,
            "activeCycles": 53.3
          },
          {
            "id": "molten_iron",
            "x": 138,
            "y": 81,
            "emitRate": 10466,
            "avgEmitRate": 305,
            "idleTime": 850,
            "eruptionTime": 43,
            "dormancyCycles": 42.9,
            "activeCycles": 67.3
          },
          {
            "id": "steam",
            "x": 40,
            "y": 129,
            "emitRate": 6269,
            "avgEmitRate": 1448,
            "idleTime": 231,
            "eruptionTime": 136,
            "dormancyCycles": 35.3,
            "activeCycles": 58.1
          },
          {
            "id": "slush_salt_water",
            "x": 55,
            "y": 74,
            "emitRate": 8612,
            "avgEmitRate": 1521,
            "idleTime": 440,
            "eruptionTime": 199,
            "dormancyCycles": 35.7,
            "activeCycles": 46.7
          },
          {
            "id": "slimy_po2",
            "x": 26,
            "y": 166,
            "emitRate": 361,
            "avgEmitRate": 110,
            "idleTime": 206,
            "eruptionTime": 201,
            "dormancyCycles": 40.5,
            "activeCycles": 65.7
          },
          {
            "id": "liquid_co2",
            "x": 147,
            "y": 129,
            "emitRate": 339,
            "avgEmitRate": 159,
            "idleTime": 201,
            "eruptionTime": 386,
            "dormancyCycles": 51.2,
            "activeCycles": 128.9
          },
          {
            "id": "big_volcano",
            "x": 107,
            "y": 129,
            "emitRate": 212455,
            "avgEmitRate": 1262,
            "idleTime": 9404,
            "eruptionTime": 83,
            "dormancyCycles": 38.8,
            "activeCycles": 81.5
          },
          {
            "id": "big_volcano",
            "x": 31,
            "y": 90,
            "emitRate": 340375,
            "avgEmitRate": 1242,
            "idleTime": 9880,
            "eruptionTime": 65,
            "dormancyCycles": 56.5,
            "activeCycles": 70.6
          },
          {
            "id": "big_volcano",
            "x": 32,
            "y": 103,
            "emitRate": 261534,
            "avgEmitRate": 1324,
            "idleTime": 8623,
            "eruptionTime": 68,
            "dormancyCycles": 64.8,
            "activeCycles": 117.2
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalPoor",
          "LushCore"
        ],
        "biomePaths": "FrozenWastes:50,41 46,34 35,33 28,41 34,52 44,52;64,60 64,42 50,41 44,52 50,60;44,71 50,60 44,52 34,52 27,62 35,71;28,83 35,71 27,62 19,62 13,73 20,83;27,62 34,52 28,41 19,40 13,54 19,62;13,73 19,62 13,54 0,54 0,73;13,54 19,40 15,35 0,35 0,54;35,91 28,83 20,83 14,92 20,104 29,105;64,78 64,60 50,60 44,71 50,79;49,96 45,91 35,91 29,105 33,110 46,110;20,104 14,92 0,92 0,110 14,111;45,91 50,79 44,71 35,71 28,83 35,91;14,92 20,83 13,73 0,73 0,92;64,96 49,96 46,110 47,112 64,112;64,96 64,78 50,79 45,91 49,96\nForest:33,110 29,105 20,104 14,111 19,128 28,128;14,111 0,110 0,128 19,128;64,112 47,112 47,128 64,128;47,112 46,110 33,110 28,128 47,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 32,
            "y": 55
          },
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 113
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 21,
            "y": 89,
            "emitRate": 6570,
            "avgEmitRate": 382,
            "idleTime": 699,
            "eruptionTime": 68,
            "dormancyCycles": 52.8,
            "activeCycles": 102.4
          },
          {
            "id": "molten_iron",
            "x": 12,
            "y": 99,
            "emitRate": 6145,
            "avgEmitRate": 225,
            "idleTime": 814,
            "eruptionTime": 56,
            "dormancyCycles": 64,
            "activeCycles": 84.5
          },
          {
            "id": "molten_iron",
            "x": 40,
            "y": 109,
            "emitRate": 9427,
            "avgEmitRate": 289,
            "idleTime": 654,
            "eruptionTime": 36,
            "dormancyCycles": 55.7,
            "activeCycles": 79.4
          },
          {
            "id": "molten_iron",
            "x": 26,
            "y": 101,
            "emitRate": 11075,
            "avgEmitRate": 298,
            "idleTime": 723,
            "eruptionTime": 32,
            "dormancyCycles": 43.1,
            "activeCycles": 72.8
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:44,48 44,40 28,36 24,38 26,56 31,58;54,56 44,48 31,58 36,68 47,70;64,55 64,34 48,34 44,40 44,48 54,56;14,74 18,60 1,50 0,50 0,75;26,56 24,38 17,37 1,50 18,60\nToxicJungle:30,78 36,68 31,58 26,56 18,60 14,74 20,79;64,55 54,56 47,70 52,77 64,79\nMagmaCore:20,79 14,74 0,75 0,96 17,96;52,77 47,70 36,68 30,78 41,93;41,93 30,78 20,79 17,96 41,96;64,79 52,77 41,93 41,96 64,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 40,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 26,
            "y": 49
          },
          {
            "id": "SapTree",
            "x": 33,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 21,
            "y": 72,
            "emitRate": 7337,
            "avgEmitRate": 258,
            "idleTime": 665,
            "eruptionTime": 39,
            "dormancyCycles": 42.5,
            "activeCycles": 76.2
          },
          {
            "id": "molten_tungsten",
            "x": 15,
            "y": 89,
            "emitRate": 7229,
            "avgEmitRate": 222,
            "idleTime": 726,
            "eruptionTime": 40,
            "dormancyCycles": 51.7,
            "activeCycles": 73.2
          },
          {
            "id": "molten_tungsten",
            "x": 30,
            "y": 88,
            "emitRate": 8398,
            "avgEmitRate": 299,
            "idleTime": 644,
            "eruptionTime": 39,
            "dormancyCycles": 62.8,
            "activeCycles": 100.9
          },
          {
            "id": "hot_co2",
            "x": 56,
            "y": 67,
            "emitRate": 438,
            "avgEmitRate": 130,
            "idleTime": 443,
            "eruptionTime": 368,
            "dormancyCycles": 59.2,
            "activeCycles": 112.3
          },
          {
            "id": "hot_co2",
            "x": 48,
            "y": 60,
            "emitRate": 319,
            "avgEmitRate": 100,
            "idleTime": 299,
            "eruptionTime": 345,
            "dormancyCycles": 58.8,
            "activeCycles": 82.8
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "MagmaCore:64,56 64,37 48,36 44,39 43,43 54,57;64,56 54,57 45,67 48,76 64,79;43,43 44,39 32,30 21,35 21,47 32,51;33,63 32,51 21,47 17,50 16,64 24,70;21,47 21,35 16,31 0,34 0,46 17,50;48,76 45,67 33,63 24,70 24,77 42,84;24,77 24,70 16,64 0,69 0,79 20,83;20,83 0,79 0,96 20,96;42,84 24,77 20,83 20,96 42,96\nOilField:54,57 43,43 32,51 33,63 45,67;64,79 48,76 42,84 42,96 64,96;16,64 17,50 0,46 0,69",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 38,
            "y": 60
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 44,
            "y": 87,
            "emitRate": 304872,
            "avgEmitRate": 1279,
            "idleTime": 9469,
            "eruptionTime": 67,
            "dormancyCycles": 48.8,
            "activeCycles": 73.1
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:63,50 67,36 66,35 49,33 46,44 51,51;46,44 49,33 49,31 33,27 29,31 30,46 31,46;96,32 84,31 78,37 83,48 96,49;83,48 78,37 67,36 63,50 69,58 76,58;69,58 63,50 51,51 47,63 48,65 63,67;30,46 29,31 17,29 10,39 18,49;34,61 31,46 30,46 18,49 14,60 16,63 29,65;51,51 46,44 31,46 34,61 47,63;18,49 10,39 0,39 0,59 14,60;96,64 96,49 83,48 76,58 81,65\nFrozenWastes:96,64 81,65 78,80 96,80;81,65 76,58 69,58 63,67 66,80 78,80;63,67 48,65 46,80 66,80;29,65 16,63 12,80 31,80;16,63 14,60 0,59 0,80 12,80;48,65 47,63 34,61 29,65 31,80 46,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 39,
            "y": 38
          },
          {
            "id": "GravitasPedestal",
            "x": 32,
            "y": 38
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 82,
            "y": 42,
            "emitRate": 319,
            "avgEmitRate": 96,
            "idleTime": 269,
            "eruptionTime": 249,
            "dormancyCycles": 53.2,
            "activeCycles": 90
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:59,74 59,61 59,61 40,60 37,70 48,79;37,70 40,60 38,54 23,51 19,56 20,68 32,73;59,104 60,96 47,89 39,93 38,104 46,110;47,89 48,79 37,70 32,73 30,87 39,93;80,48 63,45 60,48 59,61 59,61 80,62;60,48 44,42 38,54 40,60 59,61;44,42 43,38 36,33 21,39 23,51 38,54;63,45 64,32 57,26 43,38 44,42 60,48;80,110 80,96 64,93 60,96 59,104 66,112;63,122 66,112 59,104 46,110 47,124 50,126;47,124 46,110 38,104 31,108 29,120 35,126;64,93 65,78 59,74 48,79 47,89 60,96;38,104 39,93 30,87 18,91 19,102 31,108;29,120 31,108 19,102 11,109 18,123;52,141 50,126 47,124 35,126 32,139 35,143 49,143;30,87 32,73 20,68 14,73 17,90 18,91;35,126 29,120 18,123 15,127 19,139 32,139;70,132 63,122 50,126 52,141 63,141;80,75 65,78 64,93 80,96;80,28 64,32 63,45 80,48;19,139 15,127 0,129 0,144 16,144;15,127 18,123 11,109 0,109 0,129;20,68 19,56 0,54 0,72 14,73;80,75 80,62 59,61 59,74 65,78;21,160 19,158 0,162 0,174 21,174;41,163 32,156 21,160 21,174 41,174;80,131 80,110 66,112 63,122 70,132;23,51 21,39 18,37 0,39 0,54 19,56;61,161 51,157 41,163 41,174 63,174;80,154 70,153 61,161 63,174 80,174;19,102 18,91 17,90 0,92 0,109 11,109;17,90 14,73 0,72 0,92\nSwamp:70,153 63,141 52,141 49,143 51,157 61,161;32,156 35,143 32,139 19,139 16,144 19,158 21,160;51,157 49,143 35,143 32,156 41,163;19,158 16,144 0,144 0,162;80,131 70,132 63,141 70,153 80,154",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 42,
            "y": 160
          },
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 160
          }
        ],
        "geysers": [
          {
            "id": "hot_water",
            "x": 38,
            "y": 124,
            "emitRate": 12434,
            "avgEmitRate": 3155,
            "idleTime": 364,
            "eruptionTime": 269,
            "dormancyCycles": 45.8,
            "activeCycles": 67.9
          },
          {
            "id": "filthy_water",
            "x": 22,
            "y": 66,
            "emitRate": 9874,
            "avgEmitRate": 3083,
            "idleTime": 383,
            "eruptionTime": 427,
            "dormancyCycles": 45.2,
            "activeCycles": 65.7
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:96,62 96,45 81,44 74,54 80,62;80,62 74,54 69,54 58,64 61,75 75,77;36,73 38,66 30,55 20,55 15,64 21,76;96,62 80,62 75,77 77,79 96,80;19,80 21,76 15,64 0,64 0,83\nSandstone:61,75 58,64 50,60 38,66 36,73 40,78 56,81\nFrozenWastes:40,78 36,73 21,76 19,80 23,96 35,96;77,79 75,77 61,75 56,81 57,96 75,96;56,81 40,78 35,96 57,96;96,80 77,79 75,96 96,96;19,80 0,83 0,96 23,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 69,
            "y": 66
          },
          {
            "id": "GravitasPedestal",
            "x": 73,
            "y": 66
          },
          {
            "id": "GeneShuffler",
            "x": 71,
            "y": 60
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 30,
            "y": 80,
            "emitRate": 1964,
            "avgEmitRate": 730,
            "idleTime": 228,
            "eruptionTime": 353,
            "dormancyCycles": 61.3,
            "activeCycles": 96.2
          },
          {
            "id": "steam",
            "x": 63,
            "y": 81,
            "emitRate": 4926,
            "avgEmitRate": 982,
            "idleTime": 440,
            "eruptionTime": 306,
            "dormancyCycles": 71.7,
            "activeCycles": 67.9
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaOceania",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumForestyWasteland",
        "q": 2,
        "r": 1
      },
      {
        "id": "TundraMoonlet",
        "q": -5,
        "r": 5
      },
      {
        "id": "MarshyMoonlet",
        "q": -6,
        "r": 0
      },
      {
        "id": "NiobiumMoonlet",
        "q": -1,
        "r": -5
      },
      {
        "id": "MooMoonlet",
        "q": 4,
        "r": 3
      },
      {
        "id": "WaterMoonlet",
        "q": 6,
        "r": -6
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": -3,
        "r": 8
      },
      {
        "id": "TemporalTear",
        "q": 0,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 2,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 6,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -10,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -4,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -5,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 2,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 1,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 11,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 5,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -1,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -6,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 7,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 10,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -7,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -3,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -2,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": -2,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -11,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -11,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 10,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 6,
        "r": 5
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation1",
        "q": -2,
        "r": -1
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -9,
        "r": -1
      }
    ]
  },
  {
    "coordinate": "V-OASIS-C-908596465-0-0-0",
    "cluster": "V-OASIS-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaOasis",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "GlaciersLarge",
          "Volcanoes",
          "SlimeSplats"
        ],
        "biomePaths": "Forest:134,240 148,221 147,202 137,190 111,189 94,209 94,209 102,232 133,240;99,236 102,232 94,209 75,218 73,229 78,237;94,209 94,209 81,190 66,193 63,206 75,218;111,189 108,182 86,179 81,190 94,209;133,240 102,232 99,236 101,250 123,256;138,179 121,164 120,164 108,182 111,189 137,190;165,239 162,226 148,221 134,240 155,248;175,211 170,200 147,202 148,221 162,226\nMagmaCore:29,345 26,342 0,341 0,380 24,380;74,252 78,237 73,229 55,232 51,240 64,255;73,229 75,218 63,206 50,214 55,232;58,349 29,345 24,380 59,380;81,190 86,179 80,168 65,168 58,180 66,193;77,136 77,116 57,118 60,139;89,354 65,344 58,349 59,380 89,380;101,250 99,236 78,237 74,252 89,260;121,350 103,344 89,354 89,380 122,380;126,123 130,107 118,99 105,112 113,126;151,349 132,344 121,350 122,380 154,380;150,167 141,152 121,164 138,179;183,348 178,342 165,340 151,349 154,380 177,380;176,261 181,249 165,239 155,248 160,263;211,351 183,348 177,380 215,380;184,278 176,261 160,263 156,272 176,286;219,237 218,228 203,217 200,218 195,244 205,249;240,341 220,342 211,351 215,380 240,380;240,218 218,228 219,237 240,243\nBoggyMarsh:34,315 23,305 0,305 0,341 26,342;103,344 99,318 89,314 66,328 65,344 89,354;194,314 174,298 155,309 165,340 178,342;240,341 240,304 212,306 207,313 220,342;240,304 240,272 210,272 206,277 212,306\nOcean:23,305 33,277 25,268 0,268 0,305;89,314 82,290 61,287 50,314 66,328;165,340 155,309 146,306 132,314 132,344 151,349;220,342 207,313 194,314 178,342 183,348 211,351;207,313 212,306 206,277 184,278 176,286 174,298 194,314;215,135 204,117 189,116 175,138 180,150 204,154\nSandstone:25,268 33,245 15,227 0,228 0,268;51,240 55,232 50,214 31,207 15,227 33,245;64,255 51,240 33,245 25,268 33,277 55,278;63,206 66,193 58,180 33,182 26,194 31,207 50,214;127,274 123,256 101,250 89,260 92,281 111,287;120,164 103,144 89,147 80,168 86,179 108,182;103,144 113,126 105,112 85,108 77,116 77,136 89,147;121,164 141,152 144,141 126,123 113,126 103,144 120,164;156,272 160,263 155,248 134,240 133,240 123,256 127,274 143,280;161,132 163,112 151,100 130,107 126,123 144,141;180,150 175,138 161,132 144,141 141,152 150,167 169,170;170,200 177,184 169,170 150,167 138,179 137,190 147,202;195,244 200,218 175,211 162,226 165,239 181,249;203,217 210,199 203,184 177,184 170,200 175,211 200,218;203,184 211,168 204,154 180,150 169,170 177,184;240,272 240,243 219,237 205,249 210,272;240,101 213,101 204,117 215,135 240,135\nOilField:15,227 31,207 26,194 0,192 0,228;33,182 25,164 0,164 0,192 26,194;114,308 111,287 92,281 82,290 89,314 99,318;80,168 89,147 77,136 60,139 54,147 65,168;174,298 176,286 156,272 143,280 146,306 155,309;206,277 210,272 205,249 195,244 181,249 176,261 184,278;240,218 240,200 210,199 203,217 218,228\nWasteland:25,164 35,148 22,127 0,130 0,164;58,180 65,168 54,147 35,148 25,164 33,182;92,281 89,260 74,252 64,255 55,278 61,287 82,290;146,306 143,280 127,274 111,287 114,308 132,314;240,169 211,168 203,184 210,199 240,200\nRadioactive:22,127 26,117 20,106 0,105 0,130;48,111 46,95 33,91 20,106 26,117;189,116 184,110 163,112 161,132 175,138;213,101 206,91 190,93 184,110 189,116 204,117\nBarren:33,91 23,69 0,68 0,105 20,106;62,81 58,63 36,55 23,69 33,91 46,95;77,116 85,108 80,88 62,81 46,95 48,111 57,118;118,99 116,83 97,73 80,88 85,108 105,112;151,100 154,82 137,67 116,83 118,99 130,107;184,110 190,93 175,75 154,82 151,100 163,112;206,91 215,67 203,54 178,62 175,75 190,93;240,65 215,67 206,91 213,101 240,101\nToxicJungle:65,344 66,328 50,314 34,315 26,342 29,345 58,349;50,314 61,287 55,278 33,277 23,305 34,315;54,147 60,139 57,118 48,111 26,117 22,127 35,148;132,344 132,314 114,308 99,318 103,344 121,350;240,135 215,135 204,154 211,168 240,169",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 122,
            "y": 215
          },
          {
            "id": "WarpConduitReceiver",
            "x": 52,
            "y": 276
          },
          {
            "id": "WarpConduitSender",
            "x": 59,
            "y": 318
          },
          {
            "id": "GravitasPedestal",
            "x": 33,
            "y": 239
          },
          {
            "id": "WarpReceiver",
            "x": 34,
            "y": 140
          },
          {
            "id": "WarpPortal",
            "x": 33,
            "y": 134
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 149,
            "y": 300,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 25,
            "y": 202,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 64,
            "y": 162,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "salt_water",
            "x": 201,
            "y": 141,
            "emitRate": 11977,
            "avgEmitRate": 3174,
            "idleTime": 345,
            "eruptionTime": 324,
            "dormancyCycles": 45,
            "activeCycles": 54.5
          },
          {
            "id": "steam",
            "x": 47,
            "y": 292,
            "emitRate": 5997,
            "avgEmitRate": 1525,
            "idleTime": 270,
            "eruptionTime": 201,
            "dormancyCycles": 33.9,
            "activeCycles": 50
          },
          {
            "id": "steam",
            "x": 222,
            "y": 289,
            "emitRate": 4675,
            "avgEmitRate": 1668,
            "idleTime": 259,
            "eruptionTime": 312,
            "dormancyCycles": 28.6,
            "activeCycles": 53.6
          },
          {
            "id": "methane",
            "x": 107,
            "y": 321,
            "emitRate": 286,
            "avgEmitRate": 112,
            "idleTime": 176,
            "eruptionTime": 342,
            "dormancyCycles": 56.1,
            "activeCycles": 82.4
          },
          {
            "id": "methane",
            "x": 172,
            "y": 309,
            "emitRate": 250,
            "avgEmitRate": 80,
            "idleTime": 335,
            "eruptionTime": 443,
            "dormancyCycles": 41.1,
            "activeCycles": 52.2
          },
          {
            "id": "chlorine_gas",
            "x": 178,
            "y": 176,
            "emitRate": 281,
            "avgEmitRate": 132,
            "idleTime": 235,
            "eruptionTime": 426,
            "dormancyCycles": 39.8,
            "activeCycles": 107.8
          },
          {
            "id": "molten_iron",
            "x": 160,
            "y": 175,
            "emitRate": 17155,
            "avgEmitRate": 305,
            "idleTime": 791,
            "eruptionTime": 25,
            "dormancyCycles": 42.1,
            "activeCycles": 56.5
          },
          {
            "id": "molten_aluminum",
            "x": 21,
            "y": 189,
            "emitRate": 13364,
            "avgEmitRate": 305,
            "idleTime": 809,
            "eruptionTime": 34,
            "dormancyCycles": 55.3,
            "activeCycles": 70.4
          },
          {
            "id": "hot_hydrogen",
            "x": 89,
            "y": 294,
            "emitRate": 425,
            "avgEmitRate": 106,
            "idleTime": 486,
            "eruptionTime": 340,
            "dormancyCycles": 52.6,
            "activeCycles": 80.1
          },
          {
            "id": "hot_steam",
            "x": 84,
            "y": 131,
            "emitRate": 1938,
            "avgEmitRate": 692,
            "idleTime": 238,
            "eruptionTime": 281,
            "dormancyCycles": 38.7,
            "activeCycles": 75.1
          },
          {
            "id": "big_volcano",
            "x": 137,
            "y": 258,
            "emitRate": 228683,
            "avgEmitRate": 1157,
            "idleTime": 9071,
            "eruptionTime": 78,
            "dormancyCycles": 52,
            "activeCycles": 75.9
          },
          {
            "id": "hot_co2",
            "x": 188,
            "y": 284,
            "emitRate": 366,
            "avgEmitRate": 110,
            "idleTime": 425,
            "eruptionTime": 381,
            "dormancyCycles": 57.7,
            "activeCycles": 100.1
          },
          {
            "id": "molten_gold",
            "x": 200,
            "y": 257,
            "emitRate": 8630,
            "avgEmitRate": 314,
            "idleTime": 703,
            "eruptionTime": 47,
            "dormancyCycles": 43.1,
            "activeCycles": 59.3
          },
          {
            "id": "molten_gold",
            "x": 109,
            "y": 300,
            "emitRate": 5867,
            "avgEmitRate": 356,
            "idleTime": 560,
            "eruptionTime": 57,
            "dormancyCycles": 45.3,
            "activeCycles": 86.6
          },
          {
            "id": "steam",
            "x": 176,
            "y": 202,
            "emitRate": 4373,
            "avgEmitRate": 1144,
            "idleTime": 318,
            "eruptionTime": 263,
            "dormancyCycles": 63.3,
            "activeCycles": 86.8
          },
          {
            "id": "oil_drip",
            "x": 187,
            "y": 240,
            "emitRate": 290,
            "avgEmitRate": 158,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.2
          },
          {
            "id": "hot_water",
            "x": 136,
            "y": 336,
            "emitRate": 7978,
            "avgEmitRate": 2857,
            "idleTime": 248,
            "eruptionTime": 313,
            "dormancyCycles": 49.2,
            "activeCycles": 88.4
          },
          {
            "id": "big_volcano",
            "x": 206,
            "y": 234,
            "emitRate": 280179,
            "avgEmitRate": 1420,
            "idleTime": 9824,
            "eruptionTime": 85,
            "dormancyCycles": 48.1,
            "activeCycles": 69.7
          },
          {
            "id": "big_volcano",
            "x": 118,
            "y": 114,
            "emitRate": 292554,
            "avgEmitRate": 1395,
            "idleTime": 8398,
            "eruptionTime": 60,
            "dormancyCycles": 34.7,
            "activeCycles": 69.7
          },
          {
            "id": "big_volcano",
            "x": 167,
            "y": 252,
            "emitRate": 282359,
            "avgEmitRate": 1160,
            "idleTime": 8036,
            "eruptionTime": 53,
            "dormancyCycles": 38.9,
            "activeCycles": 65.8
          },
          {
            "id": "big_volcano",
            "x": 170,
            "y": 273,
            "emitRate": 286126,
            "avgEmitRate": 1311,
            "idleTime": 9809,
            "eruptionTime": 74,
            "dormancyCycles": 45.7,
            "activeCycles": 71.5
          },
          {
            "id": "big_volcano",
            "x": 65,
            "y": 242,
            "emitRate": 223605,
            "avgEmitRate": 981,
            "idleTime": 8046,
            "eruptionTime": 65,
            "dormancyCycles": 59.4,
            "activeCycles": 71.7
          },
          {
            "id": "big_volcano",
            "x": 67,
            "y": 128,
            "emitRate": 255748,
            "avgEmitRate": 1279,
            "idleTime": 8177,
            "eruptionTime": 68,
            "dormancyCycles": 45.3,
            "activeCycles": 69.4
          },
          {
            "id": "big_volcano",
            "x": 87,
            "y": 247,
            "emitRate": 199866,
            "avgEmitRate": 1019,
            "idleTime": 7999,
            "eruptionTime": 63,
            "dormancyCycles": 35.8,
            "activeCycles": 68.1
          },
          {
            "id": "big_volcano",
            "x": 72,
            "y": 180,
            "emitRate": 321118,
            "avgEmitRate": 1054,
            "idleTime": 8725,
            "eruptionTime": 50,
            "dormancyCycles": 47.8,
            "activeCycles": 64.6
          },
          {
            "id": "OilWell",
            "x": 21,
            "y": 214,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 24,
            "y": 181,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 96,
            "y": 292,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 78,
            "y": 152,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 159,
            "y": 301,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 205,
            "y": 272,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 214,
            "y": 207,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "MetalCaves",
          "CrashedSatellites"
        ],
        "biomePaths": "Swamp:97,89 89,78 70,83 70,97 85,104;115,82 116,69 115,67 99,63 91,68 89,78 97,89 105,90;89,78 91,68 77,58 68,62 65,78 70,83;111,108 105,90 97,89 85,104 87,112 96,116;87,112 85,104 70,97 61,103 63,121 73,124;142,107 133,98 116,109 121,121 137,124;99,132 96,116 87,112 73,124 77,134 92,139;70,97 70,83 65,78 47,80 44,85 48,100 61,103\nBoggyMarsh:160,62 160,41 139,41 135,46 141,62;121,47 113,40 99,44 99,63 115,67;77,58 80,43 67,35 57,41 57,55 68,62;20,131 20,110 0,109 0,131\nFrozenWastes:137,69 141,62 135,46 121,47 115,67 116,69;99,63 99,44 91,39 80,43 77,58 91,68;39,60 35,41 22,39 15,46 22,65;62,151 55,147 42,150 38,155 45,176 56,176;79,159 66,150 62,151 56,176 78,176;21,67 22,65 15,46 0,46 0,70;57,55 57,41 43,34 35,41 39,60 43,62\nRust:160,62 141,62 137,69 141,84 160,85;133,98 133,93 115,82 105,90 111,108 116,109;160,129 160,106 142,107 137,124 141,129;48,100 44,85 26,84 22,90 24,106 38,109;24,106 22,90 0,89 0,109 20,110;26,84 21,67 0,70 0,89 22,90\nForest:141,84 137,69 116,69 115,82 133,93;65,78 68,62 57,55 43,62 47,80;160,106 160,85 141,84 133,93 133,98 142,107;63,121 61,103 48,100 38,109 43,123 54,126;121,121 116,109 111,108 96,116 99,132 113,134;137,139 141,129 137,124 121,121 113,134 118,144;47,80 43,62 39,60 22,65 21,67 26,84 44,85;43,123 38,109 24,106 20,110 20,131 22,133 32,133\nRadioactive:66,150 77,134 73,124 63,121 54,126 55,147 62,151;160,152 160,129 141,129 137,139 144,152;116,152 118,144 113,134 99,132 92,139 94,153 99,156;94,153 92,139 77,134 66,150 79,159;55,147 54,126 43,123 32,133 42,150;38,155 42,150 32,133 22,133 17,153 21,158;17,153 22,133 20,131 0,131 0,153;99,156 94,153 79,159 78,176 100,176;139,159 121,158 118,176 142,176;160,152 144,152 139,159 142,176 160,176;21,158 17,153 0,153 0,176 19,176;144,152 137,139 118,144 116,152 121,158 139,159;38,155 21,158 19,176 45,176;121,158 116,152 99,156 100,176 118,176",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 95,
            "y": 51
          },
          {
            "id": "WarpConduitSender",
            "x": 34,
            "y": 108
          },
          {
            "id": "WarpConduitReceiver",
            "x": 36,
            "y": 90
          },
          {
            "id": "WarpReceiver",
            "x": 91,
            "y": 92
          },
          {
            "id": "WarpPortal",
            "x": 69,
            "y": 92
          },
          {
            "id": "GeneShuffler",
            "x": 144,
            "y": 66
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 28,
            "y": 72
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 61,
            "y": 64
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 73,
            "y": 44
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 128,
            "y": 145,
            "emitRate": 636,
            "avgEmitRate": 155,
            "idleTime": 298,
            "eruptionTime": 199,
            "dormancyCycles": 60.3,
            "activeCycles": 93.6
          },
          {
            "id": "liquid_co2",
            "x": 144,
            "y": 158,
            "emitRate": 516,
            "avgEmitRate": 153,
            "idleTime": 238,
            "eruptionTime": 184,
            "dormancyCycles": 40,
            "activeCycles": 84.5
          },
          {
            "id": "slush_water",
            "x": 17,
            "y": 48,
            "emitRate": 4995,
            "avgEmitRate": 1324,
            "idleTime": 228,
            "eruptionTime": 204,
            "dormancyCycles": 68.2,
            "activeCycles": 87.4
          },
          {
            "id": "slush_salt_water",
            "x": 93,
            "y": 65,
            "emitRate": 4045,
            "avgEmitRate": 1444,
            "idleTime": 269,
            "eruptionTime": 311,
            "dormancyCycles": 40.9,
            "activeCycles": 81.2
          },
          {
            "id": "slush_water",
            "x": 76,
            "y": 62,
            "emitRate": 4341,
            "avgEmitRate": 1620,
            "idleTime": 208,
            "eruptionTime": 331,
            "dormancyCycles": 48.2,
            "activeCycles": 74.7
          },
          {
            "id": "molten_aluminum",
            "x": 150,
            "y": 115,
            "emitRate": 8950,
            "avgEmitRate": 262,
            "idleTime": 733,
            "eruptionTime": 46,
            "dormancyCycles": 87.8,
            "activeCycles": 85.9
          },
          {
            "id": "hot_water",
            "x": 18,
            "y": 93,
            "emitRate": 8794,
            "avgEmitRate": 2758,
            "idleTime": 225,
            "eruptionTime": 262,
            "dormancyCycles": 51.4,
            "activeCycles": 71.8
          },
          {
            "id": "molten_cobalt",
            "x": 140,
            "y": 122,
            "emitRate": 8804,
            "avgEmitRate": 320,
            "idleTime": 733,
            "eruptionTime": 49,
            "dormancyCycles": 47.2,
            "activeCycles": 65.5
          },
          {
            "id": "hot_hydrogen",
            "x": 109,
            "y": 82,
            "emitRate": 262,
            "avgEmitRate": 105,
            "idleTime": 144,
            "eruptionTime": 301,
            "dormancyCycles": 43.5,
            "activeCycles": 62.7
          },
          {
            "id": "hot_po2",
            "x": 120,
            "y": 90,
            "emitRate": 560,
            "avgEmitRate": 101,
            "idleTime": 213,
            "eruptionTime": 82,
            "dormancyCycles": 56.7,
            "activeCycles": 101.8
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "FrozenWastes:45,54 51,42 45,35 35,34 27,42 36,55;64,42 51,42 45,54 54,64 64,64;54,64 45,54 36,55 32,62 39,76 43,77;21,42 14,34 0,34 0,53 13,54;39,76 32,62 19,64 16,71 23,83;64,97 64,84 46,82 43,95 48,100;43,95 46,82 43,77 39,76 23,83 22,89 32,98;64,64 54,64 43,77 46,82 64,84;41,118 29,110 20,113 18,128 40,128;47,113 48,100 43,95 32,98 29,110 41,118;22,89 23,83 16,71 0,74 0,88 17,91;13,108 17,91 0,88 0,110;64,97 48,100 47,113 64,118;32,62 36,55 27,42 21,42 13,54 19,64;29,110 32,98 22,89 17,91 13,108 20,113;64,118 47,113 41,118 40,128 64,128;16,71 19,64 13,54 0,53 0,74;20,113 13,108 0,110 0,128 18,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 52,
            "y": 57
          },
          {
            "id": "GravitasPedestal",
            "x": 46,
            "y": 92
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 18,
            "y": 99,
            "emitRate": 9004,
            "avgEmitRate": 326,
            "idleTime": 764,
            "eruptionTime": 51,
            "dormancyCycles": 50.4,
            "activeCycles": 70.7
          },
          {
            "id": "molten_iron",
            "x": 19,
            "y": 111,
            "emitRate": 5833,
            "avgEmitRate": 266,
            "idleTime": 708,
            "eruptionTime": 59,
            "dormancyCycles": 36,
            "activeCycles": 51.5
          },
          {
            "id": "molten_iron",
            "x": 46,
            "y": 68,
            "emitRate": 7353,
            "avgEmitRate": 248,
            "idleTime": 894,
            "eruptionTime": 69,
            "dormancyCycles": 37.3,
            "activeCycles": 33.4
          },
          {
            "id": "molten_iron",
            "x": 23,
            "y": 75,
            "emitRate": 5654,
            "avgEmitRate": 248,
            "idleTime": 651,
            "eruptionTime": 52,
            "dormancyCycles": 54,
            "activeCycles": 79.9
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "BoggyMarsh:45,46 43,40 35,36 22,41 20,54 22,56 40,58;22,41 15,35 0,36 0,54 20,54;64,64 64,50 45,46 40,58 43,65;41,73 43,65 40,58 22,56 19,73 29,78;19,73 22,56 20,54 0,54 0,72 16,75\nMagmaCore:29,78 19,73 16,75 12,96 30,96;16,75 0,72 0,96 12,96;47,81 41,73 29,78 30,96 45,96;64,79 47,81 45,96 64,96\nToxicJungle:64,79 64,64 43,65 41,73 47,81",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 39,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 25,
            "y": 49
          },
          {
            "id": "SapTree",
            "x": 32,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 51,
            "y": 90,
            "emitRate": 7033,
            "avgEmitRate": 250,
            "idleTime": 785,
            "eruptionTime": 48,
            "dormancyCycles": 43.8,
            "activeCycles": 69
          },
          {
            "id": "molten_tungsten",
            "x": 36,
            "y": 91,
            "emitRate": 9878,
            "avgEmitRate": 303,
            "idleTime": 601,
            "eruptionTime": 33,
            "dormancyCycles": 68.1,
            "activeCycles": 94.7
          },
          {
            "id": "molten_tungsten",
            "x": 28,
            "y": 89,
            "emitRate": 11453,
            "avgEmitRate": 313,
            "idleTime": 703,
            "eruptionTime": 32,
            "dormancyCycles": 50.6,
            "activeCycles": 83.1
          },
          {
            "id": "slimy_po2",
            "x": 39,
            "y": 66,
            "emitRate": 265,
            "avgEmitRate": 74,
            "idleTime": 188,
            "eruptionTime": 320,
            "dormancyCycles": 88.2,
            "activeCycles": 70.4
          },
          {
            "id": "hot_hydrogen",
            "x": 29,
            "y": 69,
            "emitRate": 298,
            "avgEmitRate": 95,
            "idleTime": 240,
            "eruptionTime": 342,
            "dormancyCycles": 41.7,
            "activeCycles": 48.8
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "MetalRich"
        ],
        "biomePaths": "MagmaCore:64,47 64,30 48,32 46,47 47,47;31,78 36,67 32,62 20,61 16,66 19,80 20,81;31,43 30,33 20,27 13,33 16,47;49,65 47,47 46,47 36,48 32,62 36,67 47,67;36,48 31,43 16,47 16,48 20,61 32,62;50,80 47,67 36,67 31,78 42,88;42,88 31,78 20,81 20,96 42,96;19,80 16,66 0,65 0,82;64,83 50,80 42,88 42,96 64,96;16,48 16,47 13,33 0,31 0,50;64,65 49,65 47,67 50,80 64,83\nOilField:64,65 64,47 47,47 49,65;20,61 16,48 0,50 0,65 16,66;20,81 19,80 0,82 0,96 20,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 19,
            "y": 83
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 57,
            "y": 57,
            "emitRate": 411230,
            "avgEmitRate": 1366,
            "idleTime": 8765,
            "eruptionTime": 51,
            "dormancyCycles": 83.4,
            "activeCycles": 111.5
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:96,32 83,32 76,41 80,49 96,51;96,51 80,49 74,62 76,66 96,66;80,49 76,41 68,40 58,49 62,60 74,62;68,40 62,29 49,29 46,34 51,47 58,49;51,47 46,34 34,35 29,42 39,56;62,60 58,49 51,47 39,56 38,59 54,69;38,59 39,56 29,42 20,43 16,50 20,61 35,62;20,61 16,50 0,50 0,65 16,66;16,50 20,43 13,33 0,33 0,50\nFrozenWastes:76,66 74,62 62,60 54,69 54,80 75,80;96,66 76,66 75,80 96,80;54,69 38,59 35,62 34,80 54,80;35,62 20,61 16,66 18,80 34,80;16,66 0,65 0,80 18,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 70,
            "y": 53
          },
          {
            "id": "GravitasPedestal",
            "x": 63,
            "y": 53
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 14,
            "y": 47,
            "emitRate": 489,
            "avgEmitRate": 99,
            "idleTime": 505,
            "eruptionTime": 302,
            "dormancyCycles": 58.4,
            "activeCycles": 68.2
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:62,85 66,76 60,68 47,69 45,71 47,85 52,88;47,85 45,71 32,71 28,85 35,90;73,97 62,85 52,88 53,102 63,105;80,97 80,75 66,76 62,85 73,97;80,75 80,57 64,59 60,68 66,76;66,117 63,105 53,102 46,107 47,118 61,122;53,102 52,88 47,85 35,90 34,102 46,107;60,68 64,59 58,49 44,51 44,51 47,69;80,57 80,45 62,42 58,49 64,59;28,85 32,71 27,65 18,66 12,76 19,86;47,118 46,107 34,102 27,107 30,120 41,123;61,135 61,122 47,118 41,123 43,135 52,139;45,71 47,69 44,51 31,54 27,65 32,71;58,49 62,42 60,32 45,32 41,36 44,51;80,136 80,119 66,117 61,122 61,135 66,138;27,139 23,127 17,126 6,137 18,146;19,105 14,95 0,95 0,114 10,115;30,120 27,107 19,105 10,115 17,126 23,127;27,65 31,54 21,44 20,44 10,56 18,66;44,51 44,51 41,36 31,34 21,44 31,54;80,28 61,32 60,32 62,42 80,45;34,102 35,90 28,85 19,86 14,95 19,105 27,107;12,76 18,66 10,56 0,56 0,77;19,86 12,76 0,77 0,95 14,95;80,97 73,97 63,105 66,117 80,119;47,156 37,154 31,159 32,174 47,174;15,157 0,157 0,174 15,174;20,44 12,36 0,36 0,56 10,56;63,157 51,154 47,156 47,174 63,174;80,157 66,155 63,157 63,174 80,174;31,159 17,155 15,157 15,174 32,174;43,135 41,123 30,120 23,127 27,139 34,141;6,137 17,126 10,115 0,114 0,137\nSwamp:51,154 52,139 43,135 34,141 37,154 47,156;66,155 66,138 61,135 52,139 51,154 63,157;37,154 34,141 27,139 18,146 17,155 31,159;80,136 66,138 66,155 80,157;17,155 18,146 6,137 0,137 0,157 15,157",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 59,
            "y": 154
          },
          {
            "id": "GravitasPedestal",
            "x": 61,
            "y": 154
          }
        ],
        "geysers": [
          {
            "id": "hot_water",
            "x": 56,
            "y": 119,
            "emitRate": 16338,
            "avgEmitRate": 2815,
            "idleTime": 387,
            "eruptionTime": 170,
            "dormancyCycles": 57.4,
            "activeCycles": 74.3
          },
          {
            "id": "filthy_water",
            "x": 19,
            "y": 63,
            "emitRate": 10831,
            "avgEmitRate": 3204,
            "idleTime": 481,
            "eruptionTime": 398,
            "dormancyCycles": 47.1,
            "activeCycles": 88.6
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:83,59 77,48 68,47 60,57 67,69 75,69;48,77 44,69 29,64 26,66 24,78 42,86;96,60 83,59 75,69 81,79 96,81;24,78 26,66 17,62 0,68 0,77 20,82\nSandstone:52,56 46,49 35,50 29,64 44,69;67,69 60,57 52,56 44,69 48,77 59,78\nFrozenWastes:59,78 48,77 42,86 42,96 67,96;20,82 0,77 0,96 19,96;96,81 81,79 71,96 96,96;81,79 75,69 67,69 59,78 67,96 71,96;42,86 24,78 20,82 19,96 42,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 64,
            "y": 62
          },
          {
            "id": "GravitasPedestal",
            "x": 68,
            "y": 62
          },
          {
            "id": "GeneShuffler",
            "x": 66,
            "y": 56
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 64,
            "y": 79,
            "emitRate": 8771,
            "avgEmitRate": 1541,
            "idleTime": 511,
            "eruptionTime": 193,
            "dormancyCycles": 50,
            "activeCycles": 89.3
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaOasis",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "q": 3,
        "r": 0
      },
      {
        "id": "TundraMoonlet",
        "q": -4,
        "r": -1
      },
      {
        "id": "MarshyMoonlet",
        "q": 6,
        "r": -6
      },
      {
        "id": "NiobiumMoonlet",
        "q": 1,
        "r": 4
      },
      {
        "id": "MooMoonlet",
        "q": -6,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": 2,
        "r": -7
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 7,
        "r": 1
      },
      {
        "id": "TemporalTear",
        "q": -1,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 3,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -7,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -8,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -9,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -9,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -4,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -4,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 6,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -9,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 7,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 7,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": 7,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 6,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_RockyAsteroidField",
        "q": -3,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -10,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 2,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 3,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 2,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 11,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 10,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": 10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_CarbonAsteroidField",
        "q": 1,
        "r": 10
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -6,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": -7,
        "r": 11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation1",
        "q": -1,
        "r": -2
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 5,
        "r": 4
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": 11,
        "r": -11
      }
    ]
  },
  {
    "coordinate": "V-FRST-C-92902282-0-0-0",
    "cluster": "V-FRST-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaArboria",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "Volcanoes",
          "BouldersSmall",
          "IrregularOil"
        ],
        "biomePaths": "Forest:130,227 140,209 134,186 118,177 95,184 88,205 97,225 121,231;97,225 88,205 71,206 72,230 92,231;88,205 95,184 91,180 71,180 69,204 71,206;118,177 119,165 106,158 92,164 91,180 95,184;121,231 97,225 92,231 98,250 115,252;145,180 146,169 134,157 119,165 118,177 134,186;160,228 153,210 140,209 130,227 151,237;163,194 145,180 134,186 140,209 153,210\nMagmaCore:29,347 28,346 0,345 0,380 26,380;58,351 29,347 26,380 63,380;91,180 92,164 79,153 75,154 67,175 71,180;89,351 72,339 70,339 58,351 63,380 85,380;106,158 101,136 100,136 79,153 92,164;119,353 101,345 89,351 85,380 120,380;151,350 136,343 119,353 120,380 151,380;182,351 162,344 151,350 151,380 181,380;164,157 149,143 138,146 134,157 146,169;211,348 193,344 182,351 181,380 213,380;240,344 216,345 211,348 213,380 240,380;240,223 217,229 217,243 240,248\nOilField:34,319 28,312 0,313 0,345 28,346;26,126 30,116 23,97 0,96 0,128;70,339 57,318 34,319 28,346 29,347 58,351;97,320 82,297 67,298 57,318 70,339 72,339;98,250 92,231 72,230 65,237 69,260 86,264;101,345 99,321 97,320 72,339 89,351;121,261 115,252 98,250 86,264 93,281 111,282;136,343 137,330 115,314 99,321 101,345 119,353;162,344 159,324 153,320 137,330 136,343 151,350;183,312 182,292 150,294 153,320 159,324;193,344 195,319 183,312 159,324 162,344 182,351;175,191 180,182 168,157 164,157 146,169 145,180 163,194;216,345 206,316 195,319 193,344 211,348;202,214 211,195 202,179 180,182 175,191 193,215;240,344 240,309 210,311 206,316 216,345\nOcean:28,312 34,288 28,279 0,279 0,313;36,263 31,248 0,246 0,279 28,279;79,153 100,136 86,114 65,116 62,142 75,154;115,314 120,295 111,282 93,281 82,297 97,320 99,321;181,146 179,119 174,115 153,119 149,143 164,157 168,157;213,278 204,254 189,253 178,263 182,292 203,292;202,179 209,163 204,152 181,146 168,157 180,182;217,130 205,113 179,119 181,146 204,152;205,113 211,96 207,89 180,83 174,87 174,115 179,119\nToxicJungle:39,233 34,218 0,218 0,246 31,248;57,318 67,298 56,285 34,288 28,312 34,319;69,260 65,237 39,233 31,248 36,263 57,269;72,230 71,206 69,204 39,206 34,218 39,233 65,237;69,204 71,180 67,175 39,174 32,189 39,206;61,113 60,88 34,82 23,97 30,116;240,130 217,130 204,152 209,163 240,165\nFrozenWastes:34,218 39,206 32,189 0,189 0,218;39,174 33,158 0,160 0,189 32,189;153,320 150,294 148,292 120,295 115,314 137,330;148,292 147,263 121,261 111,282 120,295;182,292 182,292 178,263 152,259 147,263 148,292 150,294;240,130 240,97 211,96 205,113 217,130\nRust:33,158 37,148 26,126 0,128 0,160;62,142 65,116 61,113 30,116 26,126 37,148;134,157 138,146 127,129 113,127 101,136 106,158 119,165;152,259 151,237 130,227 121,231 115,252 121,261 147,263;189,253 181,230 160,228 151,237 152,259 178,263;217,243 217,229 202,214 193,215 181,230 189,253 204,254;193,215 175,191 163,194 153,210 160,228 181,230;240,194 240,165 209,163 202,179 211,195\nBarren:34,82 30,65 0,63 0,96 23,97;72,76 65,59 35,56 30,65 34,82 60,88;86,114 95,105 83,78 72,76 60,88 61,113 65,116;121,91 115,70 99,66 83,78 95,105 105,105;152,83 146,62 131,57 115,70 121,91 139,96;174,115 174,87 152,83 139,96 144,114 153,119;216,64 204,50 181,60 180,83 207,89;240,62 216,64 207,89 211,96 240,97\nRadioactive:56,285 57,269 36,263 28,279 34,288;113,127 105,105 95,105 86,114 100,136 101,136;149,143 153,119 144,114 127,129 138,146;206,316 210,311 203,292 182,292 182,292 183,312 195,319\nSwamp:75,154 62,142 37,148 33,158 39,174 67,175;82,297 93,281 86,264 69,260 57,269 56,285 67,298;144,114 139,96 121,91 105,105 113,127 127,129;240,309 240,279 213,278 203,292 210,311;240,248 217,243 204,254 213,278 240,279;240,223 240,194 211,195 202,214 217,229",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 115,
            "y": 206
          },
          {
            "id": "WarpConduitSender",
            "x": 52,
            "y": 315
          },
          {
            "id": "WarpConduitReceiver",
            "x": 221,
            "y": 138
          },
          {
            "id": "MassiveHeatSink",
            "x": 221,
            "y": 108
          },
          {
            "id": "MassiveHeatSink",
            "x": 154,
            "y": 269
          },
          {
            "id": "MassiveHeatSink",
            "x": 30,
            "y": 185
          },
          {
            "id": "GravitasPedestal",
            "x": 130,
            "y": 100
          },
          {
            "id": "WarpReceiver",
            "x": 33,
            "y": 155
          },
          {
            "id": "WarpPortal",
            "x": 32,
            "y": 149
          },
          {
            "id": "GeneShuffler",
            "x": 220,
            "y": 321
          },
          {
            "id": "GeneShuffler",
            "x": 61,
            "y": 127
          },
          {
            "id": "GeneShuffler",
            "x": 55,
            "y": 203
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 44,
            "y": 226,
            "emitRate": 5311,
            "avgEmitRate": 1481,
            "idleTime": 216,
            "eruptionTime": 209,
            "dormancyCycles": 58.2,
            "activeCycles": 76.5
          },
          {
            "id": "chlorine_gas",
            "x": 34,
            "y": 112,
            "emitRate": 352,
            "avgEmitRate": 77,
            "idleTime": 397,
            "eruptionTime": 255,
            "dormancyCycles": 67.1,
            "activeCycles": 85.4
          },
          {
            "id": "salt_water",
            "x": 28,
            "y": 288,
            "emitRate": 8350,
            "avgEmitRate": 2923,
            "idleTime": 155,
            "eruptionTime": 341,
            "dormancyCycles": 99.9,
            "activeCycles": 103.7
          },
          {
            "id": "OilWell",
            "x": 180,
            "y": 339,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 87,
            "y": 325,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 73,
            "y": 255,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "big_volcano",
            "x": 147,
            "y": 156,
            "emitRate": 233556,
            "avgEmitRate": 998,
            "idleTime": 9938,
            "eruptionTime": 73,
            "dormancyCycles": 53.9,
            "activeCycles": 75.7
          },
          {
            "id": "big_volcano",
            "x": 94,
            "y": 152,
            "emitRate": 293983,
            "avgEmitRate": 1122,
            "idleTime": 8954,
            "eruptionTime": 61,
            "dormancyCycles": 39.3,
            "activeCycles": 51.1
          },
          {
            "id": "big_volcano",
            "x": 80,
            "y": 169,
            "emitRate": 253695,
            "avgEmitRate": 1070,
            "idleTime": 8756,
            "eruptionTime": 66,
            "dormancyCycles": 54,
            "activeCycles": 70.5
          },
          {
            "id": "hot_hydrogen",
            "x": 174,
            "y": 320,
            "emitRate": 326,
            "avgEmitRate": 106,
            "idleTime": 311,
            "eruptionTime": 359,
            "dormancyCycles": 44.8,
            "activeCycles": 68.2
          },
          {
            "id": "molten_cobalt",
            "x": 92,
            "y": 264,
            "emitRate": 9868,
            "avgEmitRate": 363,
            "idleTime": 606,
            "eruptionTime": 37,
            "dormancyCycles": 51.9,
            "activeCycles": 93.8
          },
          {
            "id": "liquid_co2",
            "x": 199,
            "y": 171,
            "emitRate": 570,
            "avgEmitRate": 128,
            "idleTime": 397,
            "eruptionTime": 298,
            "dormancyCycles": 58.6,
            "activeCycles": 64.1
          },
          {
            "id": "hot_po2",
            "x": 198,
            "y": 298,
            "emitRate": 301,
            "avgEmitRate": 101,
            "idleTime": 194,
            "eruptionTime": 273,
            "dormancyCycles": 41,
            "activeCycles": 54.6
          },
          {
            "id": "liquid_sulfur",
            "x": 54,
            "y": 335,
            "emitRate": 6826,
            "avgEmitRate": 1804,
            "idleTime": 214,
            "eruptionTime": 163,
            "dormancyCycles": 49,
            "activeCycles": 76.8
          },
          {
            "id": "slush_water",
            "x": 176,
            "y": 158,
            "emitRate": 5283,
            "avgEmitRate": 1392,
            "idleTime": 413,
            "eruptionTime": 315,
            "dormancyCycles": 44.2,
            "activeCycles": 69.1
          },
          {
            "id": "big_volcano",
            "x": 95,
            "y": 113,
            "emitRate": 293854,
            "avgEmitRate": 1114,
            "idleTime": 8383,
            "eruptionTime": 56,
            "dormancyCycles": 57.4,
            "activeCycles": 77.6
          },
          {
            "id": "oil_drip",
            "x": 178,
            "y": 103,
            "emitRate": 309,
            "avgEmitRate": 177,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "small_volcano",
            "x": 21,
            "y": 108,
            "emitRate": 115543,
            "avgEmitRate": 523,
            "idleTime": 11643,
            "eruptionTime": 97,
            "dormancyCycles": 74.8,
            "activeCycles": 90.2
          },
          {
            "id": "liquid_co2",
            "x": 74,
            "y": 312,
            "emitRate": 546,
            "avgEmitRate": 185,
            "idleTime": 369,
            "eruptionTime": 303,
            "dormancyCycles": 34.3,
            "activeCycles": 103.9
          },
          {
            "id": "hot_water",
            "x": 103,
            "y": 267,
            "emitRate": 14332,
            "avgEmitRate": 2561,
            "idleTime": 393,
            "eruptionTime": 172,
            "dormancyCycles": 51.2,
            "activeCycles": 72.7
          },
          {
            "id": "hot_water",
            "x": 43,
            "y": 251,
            "emitRate": 9976,
            "avgEmitRate": 2664,
            "idleTime": 334,
            "eruptionTime": 264,
            "dormancyCycles": 34.5,
            "activeCycles": 52.8
          },
          {
            "id": "OilWell",
            "x": 10,
            "y": 125,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 92,
            "y": 249,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 113,
            "y": 271,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 159,
            "y": 315,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 161,
            "y": 166,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 180,
            "y": 191,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "MediumSandyRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "Geodes",
          "SubsurfaceOcean"
        ],
        "biomePaths": "Sandstone:92,95 91,83 82,78 70,81 66,94 77,106 79,107;66,94 70,81 59,71 47,74 43,87 53,96;107,116 107,103 92,95 79,107 86,118 104,119;127,77 119,61 105,64 102,77 117,86;117,95 117,86 102,77 91,83 92,95 107,103;81,132 86,118 79,107 77,106 60,121 60,126 69,134;77,106 66,94 53,96 49,110 60,121;103,136 104,119 86,118 81,132 91,141;43,87 47,74 37,65 22,68 22,81 33,90\nOcean:102,77 105,64 95,54 83,56 82,78 91,83;82,78 83,56 80,54 63,60 59,71 70,81;119,61 124,53 116,40 101,41 95,54 105,64;80,54 76,38 59,36 52,47 63,60;59,71 63,60 52,47 42,49 37,65 47,74;37,65 42,49 35,42 21,43 18,64 22,68;145,68 137,51 124,53 119,61 127,77 137,78;160,43 143,44 137,51 145,68 160,68;18,64 21,43 16,39 0,40 0,64\nWasteland:49,110 53,96 43,87 33,90 27,106 36,114;125,134 122,120 107,116 104,119 103,136 113,143;134,109 133,104 117,95 107,103 107,116 122,120;143,92 137,78 127,77 117,86 117,95 133,104;60,126 60,121 49,110 36,114 34,130 44,137;160,114 143,116 141,135 160,138\nRadioactive:66,151 69,134 60,126 44,137 46,149 57,154;89,153 91,141 81,132 69,134 66,151 77,157;114,154 113,143 103,136 91,141 89,153 98,160;23,134 10,123 0,124 0,150 19,150;46,149 44,137 34,130 23,134 19,150 20,152 37,156;137,154 137,138 125,134 113,143 114,154 119,158;77,157 66,151 57,154 56,176 76,176;57,154 46,149 37,156 39,176 56,176;20,152 19,150 0,150 0,176 16,176;160,155 160,138 141,135 137,138 137,154 140,157;140,157 137,154 119,158 119,176 140,176\nToxicJungle:27,106 33,90 22,81 3,91 21,107;10,123 21,107 3,91 0,91 0,124;22,81 22,68 18,64 0,64 0,91 3,91;34,130 36,114 27,106 21,107 10,123 23,134;160,68 145,68 137,78 143,92 160,94;160,114 160,94 143,92 133,104 134,109 143,116;141,135 143,116 134,109 122,120 125,134 137,138\nFrozenWastes:37,156 20,152 16,176 39,176;119,158 114,154 98,160 97,176 119,176;160,155 140,157 140,176 160,176;98,160 89,153 77,157 76,176 97,176",
        "pointsOfInterest": [
          {
            "id": "WarpConduitReceiver",
            "x": 118,
            "y": 84
          },
          {
            "id": "WarpConduitSender",
            "x": 22,
            "y": 78
          },
          {
            "id": "WarpPortal",
            "x": 73,
            "y": 92
          },
          {
            "id": "WarpReceiver",
            "x": 92,
            "y": 92
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 122,
            "y": 164,
            "emitRate": 409,
            "avgEmitRate": 161,
            "idleTime": 199,
            "eruptionTime": 347,
            "dormancyCycles": 51.1,
            "activeCycles": 83.1
          },
          {
            "id": "liquid_co2",
            "x": 51,
            "y": 154,
            "emitRate": 633,
            "avgEmitRate": 142,
            "idleTime": 335,
            "eruptionTime": 249,
            "dormancyCycles": 62.8,
            "activeCycles": 70
          },
          {
            "id": "steam",
            "x": 139,
            "y": 71,
            "emitRate": 3875,
            "avgEmitRate": 1291,
            "idleTime": 270,
            "eruptionTime": 376,
            "dormancyCycles": 38.9,
            "activeCycles": 52.1
          },
          {
            "id": "molten_copper",
            "x": 133,
            "y": 144,
            "emitRate": 9240,
            "avgEmitRate": 342,
            "idleTime": 724,
            "eruptionTime": 46,
            "dormancyCycles": 40.8,
            "activeCycles": 66.1
          },
          {
            "id": "molten_cobalt",
            "x": 48,
            "y": 167,
            "emitRate": 12369,
            "avgEmitRate": 329,
            "idleTime": 803,
            "eruptionTime": 33,
            "dormancyCycles": 34.2,
            "activeCycles": 67.8
          },
          {
            "id": "hot_steam",
            "x": 59,
            "y": 77,
            "emitRate": 2132,
            "avgEmitRate": 693,
            "idleTime": 256,
            "eruptionTime": 306,
            "dormancyCycles": 58,
            "activeCycles": 85.9
          },
          {
            "id": "slush_water",
            "x": 23,
            "y": 103,
            "emitRate": 6012,
            "avgEmitRate": 1623,
            "idleTime": 262,
            "eruptionTime": 215,
            "dormancyCycles": 50.9,
            "activeCycles": 75.7
          },
          {
            "id": "liquid_sulfur",
            "x": 89,
            "y": 103,
            "emitRate": 4882,
            "avgEmitRate": 1358,
            "idleTime": 319,
            "eruptionTime": 305,
            "dormancyCycles": 57.1,
            "activeCycles": 75.4
          },
          {
            "id": "slimy_po2",
            "x": 118,
            "y": 65,
            "emitRate": 379,
            "avgEmitRate": 101,
            "idleTime": 274,
            "eruptionTime": 216,
            "dormancyCycles": 64.8,
            "activeCycles": 98.8
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "DistressSignal",
          "LushCore"
        ],
        "biomePaths": "FrozenWastes:62,47 46,34 39,38 37,45 43,55;37,45 39,38 29,29 18,33 16,39 21,49;64,66 64,47 62,47 43,55 42,60 47,67;64,25 50,26 46,34 62,47 64,47;42,60 43,55 37,45 21,49 20,55 24,65;20,55 21,49 16,39 0,41 0,58;23,69 24,65 20,55 0,58 0,68 17,73;47,94 52,85 45,75 33,80 31,89 36,95;64,84 64,66 47,67 45,75 52,85;45,75 47,67 42,60 24,65 23,69 33,80;14,86 17,73 0,68 0,87;33,109 36,95 31,89 19,92 17,100 22,109;17,100 19,92 14,86 0,87 0,103;48,112 54,106 47,94 36,95 33,109 36,112;64,105 64,84 52,85 47,94 54,106;31,89 33,80 23,69 17,73 14,86 19,92;22,109 17,100 0,103 0,114 17,116\nForest:64,105 54,106 48,112 54,128 64,128;36,112 33,109 22,109 17,116 20,128 34,128;48,112 36,112 34,128 54,128;17,116 0,114 0,128 20,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 27,
            "y": 47
          },
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 87
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 47,
            "y": 109,
            "emitRate": 14312,
            "avgEmitRate": 351,
            "idleTime": 785,
            "eruptionTime": 35,
            "dormancyCycles": 56.4,
            "activeCycles": 76.5
          },
          {
            "id": "molten_iron",
            "x": 16,
            "y": 112,
            "emitRate": 8723,
            "avgEmitRate": 334,
            "idleTime": 781,
            "eruptionTime": 44,
            "dormancyCycles": 32.5,
            "activeCycles": 82.9
          },
          {
            "id": "molten_iron",
            "x": 46,
            "y": 62,
            "emitRate": 7673,
            "avgEmitRate": 204,
            "idleTime": 671,
            "eruptionTime": 36,
            "dormancyCycles": 57.3,
            "activeCycles": 63.3
          },
          {
            "id": "molten_iron",
            "x": 27,
            "y": 70,
            "emitRate": 10293,
            "avgEmitRate": 380,
            "idleTime": 689,
            "eruptionTime": 40,
            "dormancyCycles": 44.7,
            "activeCycles": 92.2
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:45,41 44,35 32,31 23,40 31,51 34,51;50,61 53,46 45,41 34,51 44,63;64,76 64,66 50,61 44,63 42,68 44,76 49,80;64,44 53,46 50,61 64,66;64,44 64,30 48,29 44,35 45,41 53,46;19,40 13,31 0,31 0,48 11,49;31,51 23,40 19,40 11,49 17,62 21,63\nToxicJungle:44,63 34,51 31,51 21,63 23,67 42,68;21,77 23,67 21,63 17,62 0,68 0,76 16,81;44,76 42,68 23,67 21,77 32,84;17,62 11,49 0,48 0,68\nMagmaCore:64,76 49,80 50,96 64,96;49,80 44,76 32,84 33,96 50,96;16,81 0,76 0,96 15,96;32,84 21,77 16,81 15,96 33,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 41,
            "y": 43
          },
          {
            "id": "GravitasPedestal",
            "x": 27,
            "y": 43
          },
          {
            "id": "SapTree",
            "x": 34,
            "y": 43
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 27,
            "y": 63,
            "emitRate": 8002,
            "avgEmitRate": 305,
            "idleTime": 610,
            "eruptionTime": 44,
            "dormancyCycles": 36.4,
            "activeCycles": 49
          },
          {
            "id": "molten_tungsten",
            "x": 53,
            "y": 90,
            "emitRate": 11612,
            "avgEmitRate": 309,
            "idleTime": 809,
            "eruptionTime": 34,
            "dormancyCycles": 53.2,
            "activeCycles": 103.1
          },
          {
            "id": "molten_tungsten",
            "x": 41,
            "y": 89,
            "emitRate": 9095,
            "avgEmitRate": 334,
            "idleTime": 747,
            "eruptionTime": 43,
            "dormancyCycles": 41.8,
            "activeCycles": 84.6
          },
          {
            "id": "chlorine_gas",
            "x": 55,
            "y": 43,
            "emitRate": 425,
            "avgEmitRate": 139,
            "idleTime": 247,
            "eruptionTime": 202,
            "dormancyCycles": 30.9,
            "activeCycles": 82.2
          },
          {
            "id": "methane",
            "x": 14,
            "y": 62,
            "emitRate": 374,
            "avgEmitRate": 117,
            "idleTime": 330,
            "eruptionTime": 289,
            "dormancyCycles": 41.8,
            "activeCycles": 84.6
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "MagmaCore:39,47 40,35 23,28 18,37 19,43 35,49;64,42 64,37 45,30 40,35 39,47 44,50;43,72 49,64 44,50 39,47 35,49 30,64 37,72;64,80 64,65 49,64 43,72 49,81;19,43 18,37 0,35 0,55 12,55;31,80 37,72 30,64 19,64 14,76 17,81\nOilField:64,42 44,50 49,64 64,65;30,64 35,49 19,43 12,55 19,64;14,76 19,64 12,55 0,55 0,76\nFrozenWastes:49,81 43,72 37,72 31,80 37,96 44,96;31,80 17,81 15,96 37,96;64,80 49,81 44,96 64,96;17,81 14,76 0,76 0,96 15,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 50,
            "y": 52
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 28,
            "y": 60,
            "emitRate": 241008,
            "avgEmitRate": 1096,
            "idleTime": 8862,
            "eruptionTime": 73,
            "dormancyCycles": 48.2,
            "activeCycles": 60.9
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:78,44 81,33 78,28 65,27 59,40 67,46;50,39 44,23 28,32 35,44;66,60 67,46 59,40 53,41 51,59 60,63;51,59 53,41 50,39 35,44 34,49 41,62;41,63 41,62 34,49 19,53 19,64 22,69;34,49 35,44 28,32 24,31 17,36 16,49 19,53;83,50 78,44 67,46 66,60 78,64;16,49 17,36 0,31 0,51;19,64 19,53 16,49 0,51 0,67;96,50 83,50 78,64 78,65 96,67;96,50 96,33 81,33 78,44 83,50\nFrozenWastes:60,63 51,59 41,62 41,63 44,80 59,80;78,65 78,64 66,60 60,63 59,80 76,80;22,69 19,64 0,67 0,80 22,80;96,67 78,65 76,80 96,80;41,63 22,69 22,80 44,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 40,
            "y": 36
          },
          {
            "id": "GravitasPedestal",
            "x": 33,
            "y": 36
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 9,
            "y": 53,
            "emitRate": 291,
            "avgEmitRate": 94,
            "idleTime": 201,
            "eruptionTime": 230,
            "dormancyCycles": 43.7,
            "activeCycles": 66.9
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,78 80,59 71,58 59,66 58,71 63,79;71,58 62,43 54,44 49,55 59,66;60,88 63,79 58,71 43,75 39,85 46,91;58,71 59,66 49,55 39,57 36,65 43,75;80,99 80,78 63,79 60,88 67,99;24,103 33,87 20,78 13,82 16,99 24,103;67,99 60,88 46,91 45,105 60,108;39,85 43,75 36,65 21,69 20,78 33,87;45,105 46,91 39,85 33,87 24,103 41,108;36,65 39,57 33,49 20,50 17,63 21,69;20,78 21,69 17,63 0,63 0,80 13,82;80,36 68,37 62,43 71,58 80,59;49,55 54,44 47,36 37,37 33,49 39,57;24,103 16,99 0,103 0,116 19,120;63,118 60,108 45,105 41,108 41,120 57,126;41,120 41,108 24,103 24,103 19,120 19,120 36,125;16,99 13,82 0,80 0,103;80,99 67,99 60,108 63,118 80,120;58,135 57,126 41,120 36,125 36,137 44,142;80,135 80,120 63,118 57,126 58,135 62,138;17,63 20,50 16,45 0,46 0,63;36,137 36,125 19,120 15,136 22,143;33,49 37,37 32,31 19,32 16,45 20,50;38,159 22,154 19,156 19,174 38,174;19,156 0,155 0,174 19,174;59,159 44,154 38,159 38,174 59,174;80,157 63,155 59,159 59,174 80,174;15,136 19,120 19,120 0,116 0,138\nSwamp:63,155 62,138 58,135 44,142 44,154 59,159;80,135 62,138 63,155 80,157;44,154 44,142 36,137 22,143 22,154 38,159;22,154 22,143 15,136 0,138 0,155 19,156",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 32,
            "y": 154
          },
          {
            "id": "GravitasPedestal",
            "x": 34,
            "y": 154
          }
        ],
        "geysers": [
          {
            "id": "hot_water",
            "x": 56,
            "y": 106,
            "emitRate": 11003,
            "avgEmitRate": 2957,
            "idleTime": 360,
            "eruptionTime": 294,
            "dormancyCycles": 50,
            "activeCycles": 74.6
          },
          {
            "id": "filthy_water",
            "x": 63,
            "y": 75,
            "emitRate": 7555,
            "avgEmitRate": 2952,
            "idleTime": 267,
            "eruptionTime": 455,
            "dormancyCycles": 50.2,
            "activeCycles": 81.8
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:66,76 67,62 57,56 48,60 50,78;13,77 20,64 17,58 0,57 0,78;34,78 30,66 20,64 13,77 24,88;80,78 85,61 78,57 67,62 66,76 71,81\nSandstone:50,78 48,60 40,57 30,66 34,78 48,81\nFrozenWastes:48,81 34,78 24,88 24,96 49,96;71,81 66,76 50,78 48,81 49,96 70,96;24,88 13,77 0,78 0,96 24,96;96,86 80,78 71,81 70,96 96,96;96,60 85,61 80,78 96,86",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 51,
            "y": 75
          },
          {
            "id": "GravitasPedestal",
            "x": 55,
            "y": 75
          },
          {
            "id": "GeneShuffler",
            "x": 53,
            "y": 69
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 39,
            "y": 84,
            "emitRate": 3199,
            "avgEmitRate": 832,
            "idleTime": 326,
            "eruptionTime": 240,
            "dormancyCycles": 46.3,
            "activeCycles": 73.3
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaArboria",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumSandyRadioactiveVanillaWarpPlanet",
        "q": -2,
        "r": -1
      },
      {
        "id": "TundraMoonlet",
        "q": -3,
        "r": 5
      },
      {
        "id": "MarshyMoonlet",
        "q": 3,
        "r": -6
      },
      {
        "id": "NiobiumMoonlet",
        "q": 5,
        "r": 1
      },
      {
        "id": "MooMoonlet",
        "q": -6,
        "r": 0
      },
      {
        "id": "WaterMoonlet",
        "q": 1,
        "r": 6
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": -8,
        "r": 6
      },
      {
        "id": "TemporalTear",
        "q": -9,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 3,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": -7,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -8,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -9,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -9,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 7,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 11,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 3,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -2,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 1,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": 0,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": -1,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 7,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -6,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 11,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 7,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": -10,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -9,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -9,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -2,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 3,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -11,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -5,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -4,
        "r": -3
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation4",
        "q": -3,
        "r": 2
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 0,
        "r": 11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": 2,
        "r": 2
      }
    ]
  },
  {
    "coordinate": "V-VOLCA-C-1945674763-0-0-0",
    "cluster": "V-VOLCA-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaVolcanic",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "BouldersMedium",
          "GlaciersLarge"
        ],
        "biomePaths": "Sandstone:141,188 145,166 137,154 115,149 105,155 98,177 107,192 130,196;25,112 31,101 24,83 0,83 0,112;98,177 105,155 91,149 79,162 89,177;103,200 107,192 98,177 89,177 80,193 85,201;115,149 115,137 104,129 90,135 91,149 105,155;128,210 130,196 107,192 103,200 112,215;143,142 133,131 115,137 115,149 137,154;156,165 163,151 157,141 143,142 137,154 145,166;186,300 191,280 180,268 168,268 156,291 165,301;165,180 156,165 145,166 141,188 156,192;209,278 216,262 208,250 188,249 180,268 191,280\nMagmaCore:26,354 17,346 0,346 0,380 23,380;23,312 28,302 17,285 0,285 0,313;23,222 30,210 24,199 0,198 0,223;47,349 43,328 30,325 17,346 26,354;55,319 56,313 44,300 28,302 23,312 30,325 43,328;52,352 47,349 26,354 23,380 54,380;68,346 69,331 55,319 43,328 47,349 52,352;80,353 68,346 52,352 54,380 78,380;92,321 92,319 75,303 56,313 55,319 69,331;107,357 92,349 80,353 78,380 108,380;92,349 94,323 92,321 69,331 68,346 80,353;120,273 109,258 93,262 90,280 107,290;135,352 125,347 107,357 108,380 137,380;162,351 151,346 135,352 137,380 161,380;156,255 165,240 152,223 143,224 135,241 145,255;187,354 174,346 162,351 161,380 187,380;174,346 176,328 161,320 152,324 151,346 162,351;187,321 189,304 186,300 165,301 161,320 176,328;180,268 188,249 180,239 165,240 156,255 168,268;217,321 210,304 189,304 187,321 208,331;214,350 209,346 187,354 187,380 215,380;210,304 217,292 209,278 191,280 186,300 189,304;240,347 214,350 215,380 240,380;240,206 218,207 211,221 216,232 240,233;240,152 219,153 211,169 215,176 240,178\nOilField:30,325 23,312 0,313 0,346 17,346;125,347 121,329 94,323 92,349 107,357;132,316 130,308 109,297 92,319 92,321 94,323 121,329;151,346 152,324 132,316 121,329 125,347 135,352;161,320 165,301 156,291 144,290 130,308 132,316 152,324;209,346 208,331 187,321 176,328 174,346 187,354;240,347 240,323 217,321 208,331 209,346 214,350;240,292 217,292 210,304 217,321 240,323\nWasteland:32,266 23,253 0,253 0,285 17,285;25,141 32,124 25,112 0,112 0,141;59,191 54,182 32,180 24,199 30,210 51,210;85,201 80,193 59,191 51,210 59,223 77,224;89,177 79,162 63,160 54,182 59,191 80,193;80,250 84,232 77,224 59,223 49,242 52,249 64,255;109,297 107,290 90,280 77,286 75,303 92,319;109,258 116,243 107,229 84,232 80,250 93,262;168,268 156,255 145,255 133,274 144,290 156,291;152,223 162,208 156,192 141,188 130,196 128,210 143,224;192,190 186,180 165,180 156,192 162,208 183,210;192,169 184,151 163,151 156,165 165,180 186,180;216,232 211,221 188,220 180,239 188,249 208,250\nToxicJungle:32,240 23,222 0,223 0,253 23,253;59,223 51,210 30,210 23,222 32,240 49,242;63,160 57,152 32,154 26,169 32,180 54,182;91,149 90,135 79,129 60,137 57,152 63,160 79,162;157,141 166,121 157,110 137,110 133,131 143,142;184,151 192,138 182,120 166,121 157,141 163,151;219,153 209,137 192,138 184,151 192,169 211,169;240,206 240,178 215,176 208,190 218,207\nOcean:32,180 26,169 0,169 0,198 24,199;44,300 52,278 39,266 32,266 17,285 28,302;51,104 57,75 55,72 31,73 24,83 31,101 51,104;144,290 133,274 120,273 107,290 109,297 130,308;145,255 135,241 116,243 109,258 120,273 133,274;180,239 188,220 183,210 162,208 152,223 165,240;218,207 208,190 192,190 183,210 188,220 211,221;240,262 240,233 216,232 208,250 216,262\nFrozenWastes:32,154 25,141 0,141 0,169 26,169;57,152 60,137 46,122 32,124 25,141 32,154;75,303 77,286 61,276 52,278 44,300 56,313;79,129 78,108 51,104 51,104 46,122 60,137;90,280 93,262 80,250 64,255 61,276 77,286;209,137 217,122 208,106 190,107 182,120 192,138;240,152 240,122 217,122 209,137 219,153;240,122 240,92 216,92 208,106 217,122\nBarren:24,83 31,73 24,54 0,52 0,83;55,72 57,54 36,39 24,54 31,73;84,102 78,77 57,75 51,104 78,108;89,66 89,56 72,45 57,54 55,72 57,75 78,77;110,77 89,66 78,77 84,102 104,102;132,105 135,83 121,72 110,77 104,102 107,106;152,77 156,61 135,49 125,55 121,72 135,83;180,87 185,78 180,61 164,56 156,61 152,77 164,89;182,120 190,107 180,87 164,89 157,110 166,121;216,63 211,54 193,49 180,61 185,78 209,78;240,92 240,64 216,63 209,78 216,92\nRadioactive:52,249 49,242 32,240 23,253 32,266 39,266;46,122 51,104 31,101 25,112 32,124;61,276 64,255 52,249 39,266 52,278;215,176 211,169 192,169 186,180 192,190 208,190\nBoggyMarsh:107,229 112,215 103,200 85,201 77,224 84,232;104,129 107,106 104,102 84,102 78,108 79,129 90,135;135,241 143,224 128,210 112,215 107,229 116,243;133,131 137,110 132,105 107,106 104,129 115,137;157,110 164,89 152,77 135,83 132,105 137,110;208,106 216,92 209,78 185,78 180,87 190,107;240,292 240,262 216,262 209,278 217,292",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 122,
            "y": 173
          },
          {
            "id": "MassiveHeatSink",
            "x": 73,
            "y": 121
          },
          {
            "id": "MassiveHeatSink",
            "x": 205,
            "y": 131
          },
          {
            "id": "MassiveHeatSink",
            "x": 73,
            "y": 297
          },
          {
            "id": "WarpConduitSender",
            "x": 32,
            "y": 120
          },
          {
            "id": "WarpConduitReceiver",
            "x": 120,
            "y": 93
          },
          {
            "id": "GravitasPedestal",
            "x": 72,
            "y": 258
          },
          {
            "id": "WarpReceiver",
            "x": 32,
            "y": 256
          },
          {
            "id": "WarpPortal",
            "x": 31,
            "y": 250
          },
          {
            "id": "GeneShuffler",
            "x": 217,
            "y": 191
          },
          {
            "id": "GeneShuffler",
            "x": 149,
            "y": 298
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 135,
            "y": 309,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 110,
            "y": 318,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 103,
            "y": 340,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "salt_water",
            "x": 44,
            "y": 285,
            "emitRate": 9542,
            "avgEmitRate": 3177,
            "idleTime": 389,
            "eruptionTime": 403,
            "dormancyCycles": 30.8,
            "activeCycles": 58.3
          },
          {
            "id": "steam",
            "x": 35,
            "y": 165,
            "emitRate": 6568,
            "avgEmitRate": 1693,
            "idleTime": 371,
            "eruptionTime": 217,
            "dormancyCycles": 44.3,
            "activeCycles": 103
          },
          {
            "id": "steam",
            "x": 127,
            "y": 130,
            "emitRate": 4102,
            "avgEmitRate": 1497,
            "idleTime": 248,
            "eruptionTime": 440,
            "dormancyCycles": 69.3,
            "activeCycles": 92.2
          },
          {
            "id": "methane",
            "x": 186,
            "y": 134,
            "emitRate": 380,
            "avgEmitRate": 92,
            "idleTime": 418,
            "eruptionTime": 349,
            "dormancyCycles": 62.5,
            "activeCycles": 72.1
          },
          {
            "id": "methane",
            "x": 119,
            "y": 218,
            "emitRate": 302,
            "avgEmitRate": 123,
            "idleTime": 257,
            "eruptionTime": 365,
            "dormancyCycles": 36.1,
            "activeCycles": 82.9
          },
          {
            "id": "salt_water",
            "x": 101,
            "y": 235,
            "emitRate": 10470,
            "avgEmitRate": 2562,
            "idleTime": 286,
            "eruptionTime": 235,
            "dormancyCycles": 78,
            "activeCycles": 92.3
          },
          {
            "id": "hot_water",
            "x": 21,
            "y": 163,
            "emitRate": 7704,
            "avgEmitRate": 2641,
            "idleTime": 369,
            "eruptionTime": 482,
            "dormancyCycles": 41.6,
            "activeCycles": 63.6
          },
          {
            "id": "slush_water",
            "x": 67,
            "y": 274,
            "emitRate": 5057,
            "avgEmitRate": 1237,
            "idleTime": 429,
            "eruptionTime": 342,
            "dormancyCycles": 51.7,
            "activeCycles": 63.7
          },
          {
            "id": "methane",
            "x": 76,
            "y": 169,
            "emitRate": 240,
            "avgEmitRate": 114,
            "idleTime": 127,
            "eruptionTime": 406,
            "dormancyCycles": 52.3,
            "activeCycles": 86
          },
          {
            "id": "hot_po2",
            "x": 219,
            "y": 117,
            "emitRate": 404,
            "avgEmitRate": 111,
            "idleTime": 344,
            "eruptionTime": 270,
            "dormancyCycles": 41.7,
            "activeCycles": 70.3
          },
          {
            "id": "molten_aluminum",
            "x": 85,
            "y": 149,
            "emitRate": 10725,
            "avgEmitRate": 347,
            "idleTime": 815,
            "eruptionTime": 44,
            "dormancyCycles": 57.4,
            "activeCycles": 100.7
          },
          {
            "id": "salt_water",
            "x": 136,
            "y": 337,
            "emitRate": 9483,
            "avgEmitRate": 3173,
            "idleTime": 326,
            "eruptionTime": 323,
            "dormancyCycles": 44.1,
            "activeCycles": 90.4
          },
          {
            "id": "oil_drip",
            "x": 38,
            "y": 186,
            "emitRate": 314,
            "avgEmitRate": 205,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.4
          },
          {
            "id": "oil_drip",
            "x": 109,
            "y": 303,
            "emitRate": 295,
            "avgEmitRate": 150,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.2
          },
          {
            "id": "slush_salt_water",
            "x": 55,
            "y": 258,
            "emitRate": 6638,
            "avgEmitRate": 1781,
            "idleTime": 215,
            "eruptionTime": 116,
            "dormancyCycles": 24.3,
            "activeCycles": 79
          },
          {
            "id": "hot_hydrogen",
            "x": 17,
            "y": 118,
            "emitRate": 373,
            "avgEmitRate": 88,
            "idleTime": 487,
            "eruptionTime": 433,
            "dormancyCycles": 95.3,
            "activeCycles": 95.8
          },
          {
            "id": "filthy_water",
            "x": 184,
            "y": 188,
            "emitRate": 11332,
            "avgEmitRate": 3008,
            "idleTime": 343,
            "eruptionTime": 234,
            "dormancyCycles": 47.6,
            "activeCycles": 90.5
          }
        ]
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "MagmaVents",
          "BouldersSmall"
        ],
        "biomePaths": "Swamp:92,106 94,93 85,85 69,91 67,97 78,110;107,88 111,76 96,66 87,70 85,85 94,93;67,97 69,91 60,78 41,86 43,96 54,103;85,85 87,70 79,65 61,74 60,78 69,91;115,110 117,99 107,88 94,93 92,106 100,114;75,118 78,110 67,97 54,103 53,117 59,123;98,128 100,114 92,106 78,110 75,118 85,132;59,135 59,123 53,117 38,121 35,133 43,142\nFrozenWastes:96,66 101,51 91,42 82,43 76,51 79,65 87,70;118,73 122,60 112,49 101,51 96,66 111,76;79,65 76,51 60,49 53,60 61,74;160,63 160,43 139,43 136,57 143,64;81,162 66,154 58,160 58,176 82,176;101,153 93,152 81,162 82,176 102,176\nBoggyMarsh:53,60 60,49 56,39 41,36 35,41 39,60;36,64 39,60 35,41 22,40 14,50 21,64;14,75 21,64 14,50 0,50 0,76\nRust:39,85 36,64 21,64 14,75 22,86;24,110 16,100 0,101 0,125 13,125;160,63 143,64 137,84 160,87;16,100 22,86 14,75 0,76 0,101;53,117 54,103 43,96 28,110 38,121;84,135 85,132 75,118 59,123 59,135 67,143\nForest:134,93 136,84 118,73 111,76 107,88 117,99;137,84 143,64 136,57 122,60 118,73 136,84;60,78 61,74 53,60 39,60 36,64 39,85 41,86;43,96 41,86 39,85 22,86 16,100 24,110 28,110;138,115 142,107 134,93 117,99 115,110 122,118;160,104 160,87 137,84 136,84 134,93 142,107;141,135 145,127 138,115 122,118 119,133 125,138;119,133 122,118 115,110 100,114 98,128 106,135\nMagmaCore:122,155 125,138 119,133 106,135 103,152 122,155;103,152 106,135 98,128 85,132 84,135 93,152 101,153;35,133 38,121 28,110 24,110 13,125 22,135;160,126 160,104 142,107 138,115 145,127;160,126 145,127 141,135 149,150 160,150\nRadioactive:149,150 141,135 125,138 122,155 141,158;160,150 149,150 141,158 144,176 160,176;141,158 122,155 122,155 120,176 144,176;122,155 103,152 101,153 102,176 120,176;66,154 67,143 59,135 43,142 42,153 58,160;37,157 19,153 15,176 37,176;22,135 13,125 0,125 0,150 17,151;42,153 43,142 35,133 22,135 17,151 19,153 37,157;58,160 42,153 37,157 37,176 58,176;19,153 17,151 0,150 0,176 15,176;93,152 84,135 67,143 66,154 81,162",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 60,
            "y": 61
          },
          {
            "id": "WarpConduitSender",
            "x": 116,
            "y": 81
          },
          {
            "id": "WarpConduitReceiver",
            "x": 28,
            "y": 93
          },
          {
            "id": "WarpReceiver",
            "x": 90,
            "y": 98
          },
          {
            "id": "WarpPortal",
            "x": 68,
            "y": 98
          },
          {
            "id": "GeneShuffler",
            "x": 145,
            "y": 75
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 129,
            "y": 152,
            "emitRate": 449,
            "avgEmitRate": 130,
            "idleTime": 108,
            "eruptionTime": 105,
            "dormancyCycles": 58.6,
            "activeCycles": 84
          },
          {
            "id": "liquid_co2",
            "x": 144,
            "y": 161,
            "emitRate": 429,
            "avgEmitRate": 126,
            "idleTime": 322,
            "eruptionTime": 387,
            "dormancyCycles": 30,
            "activeCycles": 34.7
          },
          {
            "id": "slush_salt_water",
            "x": 85,
            "y": 55,
            "emitRate": 5326,
            "avgEmitRate": 1559,
            "idleTime": 308,
            "eruptionTime": 314,
            "dormancyCycles": 33,
            "activeCycles": 45.4
          },
          {
            "id": "slush_water",
            "x": 111,
            "y": 63,
            "emitRate": 6491,
            "avgEmitRate": 1379,
            "idleTime": 483,
            "eruptionTime": 267,
            "dormancyCycles": 64.9,
            "activeCycles": 96
          },
          {
            "id": "hot_hydrogen",
            "x": 116,
            "y": 93,
            "emitRate": 295,
            "avgEmitRate": 87,
            "idleTime": 281,
            "eruptionTime": 322,
            "dormancyCycles": 57.9,
            "activeCycles": 71.2
          },
          {
            "id": "chlorine_gas",
            "x": 42,
            "y": 101,
            "emitRate": 374,
            "avgEmitRate": 91,
            "idleTime": 321,
            "eruptionTime": 260,
            "dormancyCycles": 36.1,
            "activeCycles": 43.5
          },
          {
            "id": "molten_cobalt",
            "x": 124,
            "y": 101,
            "emitRate": 7278,
            "avgEmitRate": 249,
            "idleTime": 733,
            "eruptionTime": 48,
            "dormancyCycles": 57.9,
            "activeCycles": 71.2
          },
          {
            "id": "molten_aluminum",
            "x": 125,
            "y": 64,
            "emitRate": 13182,
            "avgEmitRate": 368,
            "idleTime": 773,
            "eruptionTime": 39,
            "dormancyCycles": 57.7,
            "activeCycles": 79.2
          },
          {
            "id": "molten_cobalt",
            "x": 89,
            "y": 80,
            "emitRate": 7785,
            "avgEmitRate": 360,
            "idleTime": 707,
            "eruptionTime": 53,
            "dormancyCycles": 43.9,
            "activeCycles": 86
          },
          {
            "id": "filthy_water",
            "x": 74,
            "y": 123,
            "emitRate": 11338,
            "avgEmitRate": 3145,
            "idleTime": 310,
            "eruptionTime": 247,
            "dormancyCycles": 38,
            "activeCycles": 63.4
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "LushCore"
        ],
        "biomePaths": "FrozenWastes:64,44 64,37 48,31 40,38 41,48 46,51;46,71 51,66 46,51 41,48 31,56 36,71;41,48 40,38 31,34 21,37 18,47 22,54 31,56;64,44 46,51 51,66 64,66;21,37 14,30 0,32 0,47 18,47;32,75 36,71 31,56 22,54 15,64 20,75;52,86 46,71 36,71 32,75 35,92 46,92;22,54 18,47 0,47 0,63 15,64;16,81 20,75 15,64 0,63 0,81;35,92 32,75 20,75 16,81 19,94 32,95;64,86 64,66 51,66 46,71 52,86;52,107 46,92 35,92 32,95 33,109 44,113;33,109 32,95 19,94 15,98 17,112 23,116;64,86 52,86 46,92 52,107 64,108;19,94 16,81 0,81 0,97 15,98;17,112 15,98 0,97 0,117\nForest:44,113 33,109 23,116 24,128 46,128;64,108 52,107 44,113 46,128 64,128;23,116 17,112 0,117 0,128 24,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 52,
            "y": 48
          },
          {
            "id": "GravitasPedestal",
            "x": 33,
            "y": 109
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 35,
            "y": 82,
            "emitRate": 6644,
            "avgEmitRate": 274,
            "idleTime": 652,
            "eruptionTime": 51,
            "dormancyCycles": 67,
            "activeCycles": 89.5
          },
          {
            "id": "molten_iron",
            "x": 46,
            "y": 105,
            "emitRate": 7280,
            "avgEmitRate": 282,
            "idleTime": 764,
            "eruptionTime": 47,
            "dormancyCycles": 37.3,
            "activeCycles": 74.4
          },
          {
            "id": "molten_iron",
            "x": 12,
            "y": 86,
            "emitRate": 16464,
            "avgEmitRate": 247,
            "idleTime": 705,
            "eruptionTime": 21,
            "dormancyCycles": 42.3,
            "activeCycles": 47.8
          },
          {
            "id": "molten_iron",
            "x": 40,
            "y": 68,
            "emitRate": 9981,
            "avgEmitRate": 313,
            "idleTime": 795,
            "eruptionTime": 39,
            "dormancyCycles": 55.8,
            "activeCycles": 110.7
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "SubsurfaceOcean"
        ],
        "biomePaths": "BoggyMarsh:47,49 44,38 34,37 26,49 30,56 43,57;64,49 47,49 43,57 47,64 64,65;42,76 47,64 43,57 30,56 25,67 39,76;16,77 20,69 8,57 0,58 0,77 14,79\nOcean:64,49 64,34 49,32 44,38 47,49;34,37 30,31 18,30 12,36 17,48 26,49;8,57 17,48 12,36 0,36 0,58\nToxicJungle:64,78 64,65 47,64 42,76 48,81;25,67 30,56 26,49 17,48 8,57 20,69\nMagmaCore:48,81 42,76 39,76 30,88 31,96 48,96;39,76 25,67 20,69 16,77 30,88;30,88 16,77 14,79 12,96 31,96;64,78 48,81 48,96 64,96;14,79 0,77 0,96 12,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 30,
            "y": 49
          },
          {
            "id": "SapTree",
            "x": 37,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 36,
            "y": 88,
            "emitRate": 7770,
            "avgEmitRate": 322,
            "idleTime": 716,
            "eruptionTime": 56,
            "dormancyCycles": 60.5,
            "activeCycles": 80.2
          },
          {
            "id": "molten_tungsten",
            "x": 22,
            "y": 85,
            "emitRate": 9338,
            "avgEmitRate": 262,
            "idleTime": 686,
            "eruptionTime": 35,
            "dormancyCycles": 49.4,
            "activeCycles": 66.9
          },
          {
            "id": "molten_tungsten",
            "x": 15,
            "y": 90,
            "emitRate": 8176,
            "avgEmitRate": 377,
            "idleTime": 769,
            "eruptionTime": 56,
            "dormancyCycles": 48.7,
            "activeCycles": 100.4
          },
          {
            "id": "hot_co2",
            "x": 54,
            "y": 72,
            "emitRate": 336,
            "avgEmitRate": 113,
            "idleTime": 264,
            "eruptionTime": 313,
            "dormancyCycles": 71,
            "activeCycles": 115.2
          },
          {
            "id": "methane",
            "x": 14,
            "y": 53,
            "emitRate": 248,
            "avgEmitRate": 100,
            "idleTime": 133,
            "eruptionTime": 380,
            "dormancyCycles": 56.8,
            "activeCycles": 67.9
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:45,47 51,36 40,24 29,37 36,48;64,34 51,36 45,47 52,56 64,57;30,57 36,48 29,37 21,36 15,46 21,58;64,76 64,57 52,56 43,71 51,77;43,71 52,56 45,47 36,48 30,57 41,71;51,77 43,71 41,71 33,79 37,96 47,96;64,76 51,77 47,96 64,96;20,78 17,64 0,64 0,79 19,79;33,79 20,78 19,79 19,96 37,96;21,36 17,29 0,30 0,46 15,46\nOilField:41,71 30,57 21,58 17,64 20,78 33,79;17,64 21,58 15,46 0,46 0,64;19,79 0,79 0,96 19,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 29,
            "y": 60
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 9,
            "y": 55,
            "emitRate": 283235,
            "avgEmitRate": 1082,
            "idleTime": 8822,
            "eruptionTime": 64,
            "dormancyCycles": 53.9,
            "activeCycles": 60.2
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:79,43 86,31 78,23 66,31 74,44;96,31 86,31 79,43 96,54;74,44 66,31 62,30 51,39 57,49 71,50;57,49 51,39 50,39 36,46 38,59 51,61;73,61 71,50 57,49 51,61 53,64 67,66;38,59 36,46 27,41 24,41 18,51 20,61 34,63;24,41 16,33 0,36 0,49 18,51;96,55 96,54 79,43 74,44 71,50 73,61 82,63;20,61 18,51 0,49 0,65 17,65\nFrozenWastes:53,64 51,61 38,59 34,63 36,80 50,80;96,55 82,63 85,80 96,80;82,63 73,61 67,66 68,80 85,80;34,63 20,61 17,65 18,80 36,80;17,65 0,65 0,80 18,80;67,66 53,64 50,80 68,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 19,
            "y": 40
          },
          {
            "id": "GravitasPedestal",
            "x": 12,
            "y": 40
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 46,
            "y": 51,
            "emitRate": 552,
            "avgEmitRate": 129,
            "idleTime": 393,
            "eruptionTime": 266,
            "dormancyCycles": 57.7,
            "activeCycles": 79.2
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,61 80,44 64,43 59,56 65,62;62,75 65,62 59,56 49,57 46,76 47,76;59,56 64,43 61,38 52,35 42,40 42,52 49,57;80,80 80,61 65,62 62,75 66,81;42,52 42,40 33,34 23,38 20,49 33,56;46,76 49,57 42,52 33,56 30,70 37,76;66,81 62,75 47,76 49,93 62,94;37,76 30,70 19,71 15,83 19,90 30,90;49,93 47,76 46,76 37,76 30,90 36,98 44,98;49,111 44,98 36,98 28,110 35,119 42,119;36,98 30,90 19,90 14,102 19,110 28,110;66,99 62,94 49,93 44,98 49,111 61,111;80,117 80,99 66,99 61,111 65,117;27,131 35,119 28,110 19,110 13,120 20,131;45,138 48,129 42,119 35,119 27,131 34,139;61,127 65,117 61,111 49,111 42,119 48,129;20,49 23,38 16,31 0,34 0,46 18,50;80,135 80,117 65,117 61,127 67,135;80,80 66,81 62,94 66,99 80,99;46,158 31,155 28,174 45,174;63,143 67,135 61,127 48,129 45,138 50,145;15,83 19,71 13,65 0,66 0,83;19,90 15,83 0,83 0,101 14,102;63,161 47,157 46,158 45,174 64,174;13,65 18,50 0,46 0,66;80,155 69,155 63,161 64,174 80,174;13,120 19,110 14,102 0,101 0,120;20,131 13,120 0,120 0,139 14,139;31,155 29,153 19,152 14,157 17,174 28,174;30,70 33,56 20,49 18,50 13,65 19,71;14,157 0,156 0,174 17,174\nSwamp:34,139 27,131 20,131 14,139 19,152 29,153;47,157 50,145 45,138 34,139 29,153 31,155 46,158;69,155 63,143 50,145 47,157 63,161;80,135 67,135 63,143 69,155 80,155;19,152 14,139 0,139 0,156 14,157",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 145
          },
          {
            "id": "GravitasPedestal",
            "x": 23,
            "y": 145
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 22,
            "y": 63,
            "emitRate": 7897,
            "avgEmitRate": 3298,
            "idleTime": 190,
            "eruptionTime": 362,
            "dormancyCycles": 44.6,
            "activeCycles": 78.2
          },
          {
            "id": "hot_water",
            "x": 62,
            "y": 65,
            "emitRate": 10149,
            "avgEmitRate": 3388,
            "idleTime": 308,
            "eruptionTime": 300,
            "dormancyCycles": 42.1,
            "activeCycles": 88.1
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Sandstone:66,64 66,56 56,49 47,54 45,62 52,73 57,73;45,62 47,54 35,45 29,48 28,64 30,66;52,73 45,62 30,66 30,79 45,81\nBarren:78,81 80,78 78,69 66,64 57,73 63,82;88,59 77,48 66,56 66,64 78,69;30,79 30,79 30,66 28,64 15,64 7,72 16,81;29,48 18,45 11,50 15,64 28,64;96,59 88,59 78,69 80,78 96,80;7,72 15,64 11,50 0,49 0,72\nFrozenWastes:63,82 57,73 52,73 45,81 48,96 59,96;30,79 16,81 12,96 31,96;45,81 30,79 30,79 31,96 48,96;96,80 80,78 78,81 79,96 96,96;78,81 63,82 59,96 79,96;16,81 7,72 0,72 0,96 12,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 14,
            "y": 59
          },
          {
            "id": "GravitasPedestal",
            "x": 18,
            "y": 59
          },
          {
            "id": "GeneShuffler",
            "x": 16,
            "y": 53
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 42,
            "y": 86,
            "emitRate": 6442,
            "avgEmitRate": 1546,
            "idleTime": 411,
            "eruptionTime": 305,
            "dormancyCycles": 60.2,
            "activeCycles": 77.7
          },
          {
            "id": "hot_steam",
            "x": 10,
            "y": 84,
            "emitRate": 2627,
            "avgEmitRate": 721,
            "idleTime": 536,
            "eruptionTime": 415,
            "dormancyCycles": 40.6,
            "activeCycles": 68.9
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaVolcanic",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "q": -3,
        "r": 1
      },
      {
        "id": "TundraMoonlet",
        "q": 5,
        "r": -2
      },
      {
        "id": "MarshyMoonlet",
        "q": -1,
        "r": -5
      },
      {
        "id": "NiobiumMoonlet",
        "q": 3,
        "r": 3
      },
      {
        "id": "MooMoonlet",
        "q": -7,
        "r": 4
      },
      {
        "id": "WaterMoonlet",
        "q": 5,
        "r": -7
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": -7,
        "r": -1
      },
      {
        "id": "TemporalTear",
        "q": 2,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": 2,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 7,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": 6,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -8,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -2,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -2,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 9,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -11,
        "r": 1
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -8,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": -4,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": -3,
        "r": -2
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": -5,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 2,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 2,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 9,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -4,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": 1,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 11,
        "r": -7
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -9,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 5,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": -8,
        "r": 2
      },
      {
        "id": "HarvestableSpacePOI_ChlorineCloud",
        "q": 11,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -11,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": -10,
        "r": 6
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation1",
        "q": 2,
        "r": 1
      }
    ]
  },
  {
    "coordinate": "V-BAD-C-524086935-0-0-0",
    "cluster": "V-BAD-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "VanillaBadlands",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 240,
        "sizeY": 380,
        "worldTraits": [
          "SlimeSplats",
          "FrozenCore",
          "IrregularOil"
        ],
        "biomePaths": "Sandstone:136,233 139,213 120,192 95,199 87,216 94,236 123,243;63,216 58,207 35,208 31,216 36,226 58,227;83,183 87,169 78,160 63,162 59,175 65,185;89,264 85,250 65,249 59,258 64,270 82,273;148,177 160,159 150,133 127,130 112,141 109,164 123,181;150,258 148,239 136,233 123,243 125,257 146,261;164,225 151,210 139,213 136,233 148,239;180,208 174,196 160,196 151,210 164,225 170,225;240,320 240,282 212,274 209,275 194,294 199,320 217,327\nFrozenWastes:29,354 20,345 0,345 0,380 27,380;36,226 31,216 0,216 0,244 28,245;60,349 29,354 27,380 63,380;91,355 80,344 60,348 60,349 63,380 89,380;78,160 87,133 74,124 59,126 51,147 63,162;122,352 117,348 91,355 89,380 124,380;153,349 141,344 122,352 124,380 151,380;182,354 159,346 153,349 151,380 183,380;211,350 194,345 182,354 183,380 212,380;217,99 207,83 185,82 177,95 186,114 209,114;240,348 217,345 211,350 212,380 240,380;240,255 240,221 211,223 206,234 221,257;240,161 214,160 205,178 213,192 240,192\nOilField:20,345 31,321 23,310 0,309 0,345;33,289 25,276 0,276 0,309 23,310;28,186 31,179 24,160 0,161 0,188;24,160 30,148 21,132 0,132 0,161;60,349 60,348 51,322 31,321 20,345 29,354;63,308 51,290 33,289 23,310 31,321 51,322;60,100 54,88 32,88 25,98 33,114 51,116;80,344 86,326 74,309 63,308 51,322 60,348;87,291 82,273 64,270 51,290 63,308 74,309;111,321 112,297 87,291 74,309 86,326;117,348 117,326 111,321 86,326 80,344 91,355;141,344 137,326 117,326 117,348 122,352;137,326 143,320 139,294 119,291 112,297 111,321 117,326;159,346 161,323 143,320 137,326 141,344 153,349;165,320 167,292 147,289 139,294 143,320 161,323;184,145 177,129 155,129 150,133 160,159 175,159;194,345 194,324 165,320 161,323 159,346 182,354;170,290 172,263 150,258 146,261 147,289 167,292;217,345 217,327 199,320 194,324 194,345 211,350;199,320 194,294 170,290 167,292 165,320 194,324;240,320 217,327 217,345 240,348;240,99 217,99 209,114 216,127 240,128\nRust:34,256 28,245 0,244 0,276 25,276;65,249 58,227 36,226 28,245 34,256 59,258;120,192 123,181 109,164 87,169 83,183 95,199;119,291 116,268 89,264 82,273 87,291 112,297;109,164 112,141 98,131 87,133 78,160 87,169;125,257 123,243 94,236 85,250 89,264 116,268;209,275 181,257 172,263 170,290 194,294;214,160 206,146 184,145 175,159 185,177 205,178;216,127 209,114 186,114 177,129 184,145 206,146;240,221 240,192 213,192 204,210 211,223\nToxicJungle:31,216 35,208 28,186 0,188 0,216;33,114 25,98 0,98 0,132 21,132;64,270 59,258 34,256 25,276 33,289 51,290;58,207 65,185 59,175 31,179 28,186 35,208;51,147 59,126 51,116 33,114 21,132 30,148;94,236 87,216 63,216 58,227 65,249 85,250;95,199 83,183 65,185 58,207 63,216 87,216;98,131 108,114 101,99 89,95 75,103 74,124 87,133;147,289 146,261 125,257 116,268 119,291 139,294;151,210 160,196 148,177 123,181 120,192 139,213;181,257 184,242 170,225 164,225 148,239 150,258 172,263;185,177 175,159 160,159 148,177 160,196 174,196;206,234 211,223 204,210 180,208 170,225 184,242;213,192 205,178 185,177 174,196 180,208 204,210\nBarren:25,98 32,88 24,66 0,64 0,98;64,66 35,53 24,66 32,88 54,88;89,95 86,72 66,65 64,66 54,88 60,100 75,103;119,90 120,70 101,61 86,72 89,95 101,99;151,97 152,96 150,71 131,63 120,70 119,90 129,100;177,95 185,82 178,66 164,61 150,71 152,96;217,65 203,43 201,43 178,66 185,82 207,83;240,99 240,64 217,65 207,83 217,99\nWasteland:59,175 63,162 51,147 30,148 24,160 31,179;155,129 151,97 129,100 121,116 127,130 150,133;177,129 186,114 177,95 152,96 151,97 155,129;212,274 221,257 206,234 184,242 181,257 209,275;240,128 216,127 206,146 214,160 240,161\nRadioactive:74,124 75,103 60,100 51,116 59,126;127,130 121,116 108,114 98,131 112,141;129,100 119,90 101,99 108,114 121,116;240,255 221,257 212,274 240,282",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 115,
            "y": 220
          },
          {
            "id": "MassiveHeatSink",
            "x": 163,
            "y": 365
          },
          {
            "id": "MassiveHeatSink",
            "x": 187,
            "y": 105
          },
          {
            "id": "MassiveHeatSink",
            "x": 77,
            "y": 134
          },
          {
            "id": "WarpConduitReceiver",
            "x": 222,
            "y": 250
          },
          {
            "id": "WarpConduitSender",
            "x": 205,
            "y": 305
          },
          {
            "id": "GravitasPedestal",
            "x": 213,
            "y": 229
          },
          {
            "id": "WarpReceiver",
            "x": 217,
            "y": 189
          },
          {
            "id": "WarpPortal",
            "x": 216,
            "y": 183
          },
          {
            "id": "GeneShuffler",
            "x": 36,
            "y": 231
          },
          {
            "id": "GeneShuffler",
            "x": 22,
            "y": 332
          },
          {
            "id": "GeneShuffler",
            "x": 66,
            "y": 231
          },
          {
            "id": "GeneShuffler",
            "x": 37,
            "y": 272
          },
          {
            "id": "GeneShuffler",
            "x": 104,
            "y": 141
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 171,
            "y": 178,
            "emitRate": 4342,
            "avgEmitRate": 1639,
            "idleTime": 177,
            "eruptionTime": 339,
            "dormancyCycles": 74.3,
            "activeCycles": 100.6
          },
          {
            "id": "chlorine_gas",
            "x": 160,
            "y": 255,
            "emitRate": 430,
            "avgEmitRate": 125,
            "idleTime": 308,
            "eruptionTime": 278,
            "dormancyCycles": 38.4,
            "activeCycles": 60.6
          },
          {
            "id": "OilWell",
            "x": 165,
            "y": 134,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 211,
            "y": 339,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 156,
            "y": 327,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "methane",
            "x": 100,
            "y": 364,
            "emitRate": 417,
            "avgEmitRate": 104,
            "idleTime": 435,
            "eruptionTime": 336,
            "dormancyCycles": 38.1,
            "activeCycles": 50.5
          },
          {
            "id": "slimy_po2",
            "x": 193,
            "y": 216,
            "emitRate": 314,
            "avgEmitRate": 94,
            "idleTime": 274,
            "eruptionTime": 357,
            "dormancyCycles": 60.5,
            "activeCycles": 67.2
          },
          {
            "id": "hot_po2",
            "x": 130,
            "y": 199,
            "emitRate": 339,
            "avgEmitRate": 102,
            "idleTime": 188,
            "eruptionTime": 205,
            "dormancyCycles": 59.6,
            "activeCycles": 81.1
          },
          {
            "id": "hot_water",
            "x": 186,
            "y": 319,
            "emitRate": 7207,
            "avgEmitRate": 2602,
            "idleTime": 206,
            "eruptionTime": 323,
            "dormancyCycles": 32.7,
            "activeCycles": 47.3
          },
          {
            "id": "molten_copper",
            "x": 181,
            "y": 332,
            "emitRate": 10233,
            "avgEmitRate": 300,
            "idleTime": 577,
            "eruptionTime": 31,
            "dormancyCycles": 44.4,
            "activeCycles": 59.5
          },
          {
            "id": "molten_iron",
            "x": 100,
            "y": 115,
            "emitRate": 9961,
            "avgEmitRate": 334,
            "idleTime": 862,
            "eruptionTime": 48,
            "dormancyCycles": 39.5,
            "activeCycles": 67.1
          },
          {
            "id": "molten_aluminum",
            "x": 152,
            "y": 274,
            "emitRate": 9250,
            "avgEmitRate": 321,
            "idleTime": 741,
            "eruptionTime": 47,
            "dormancyCycles": 49.1,
            "activeCycles": 69.7
          },
          {
            "id": "molten_aluminum",
            "x": 85,
            "y": 364,
            "emitRate": 8053,
            "avgEmitRate": 327,
            "idleTime": 722,
            "eruptionTime": 52,
            "dormancyCycles": 64,
            "activeCycles": 96.9
          },
          {
            "id": "molten_aluminum",
            "x": 184,
            "y": 306,
            "emitRate": 9250,
            "avgEmitRate": 321,
            "idleTime": 741,
            "eruptionTime": 47,
            "dormancyCycles": 49.1,
            "activeCycles": 69.7
          },
          {
            "id": "slimy_po2",
            "x": 135,
            "y": 336,
            "emitRate": 398,
            "avgEmitRate": 113,
            "idleTime": 303,
            "eruptionTime": 245,
            "dormancyCycles": 47,
            "activeCycles": 81.1
          },
          {
            "id": "molten_cobalt",
            "x": 159,
            "y": 235,
            "emitRate": 8714,
            "avgEmitRate": 304,
            "idleTime": 578,
            "eruptionTime": 39,
            "dormancyCycles": 47.4,
            "activeCycles": 57.3
          },
          {
            "id": "slimy_po2",
            "x": 38,
            "y": 327,
            "emitRate": 303,
            "avgEmitRate": 74,
            "idleTime": 250,
            "eruptionTime": 363,
            "dormancyCycles": 84.3,
            "activeCycles": 60.1
          },
          {
            "id": "OilWell",
            "x": 21,
            "y": 168,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 18,
            "y": 154,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 34,
            "y": 109,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 144,
            "y": 299,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 168,
            "y": 157,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 152,
            "y": 285,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 224,
            "y": 124,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "offsetX": 242,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 176,
        "worldTraits": [
          "BouldersMedium",
          "Volcanoes"
        ],
        "biomePaths": "Swamp:85,108 85,94 72,86 62,90 63,108 77,113;108,107 108,94 97,87 85,94 85,108 96,112;63,108 62,90 56,88 40,96 39,102 46,112 54,114;97,87 97,73 76,68 72,86 85,94;101,127 101,126 96,112 85,108 77,113 77,129 90,134;77,129 77,113 63,108 54,114 59,131 68,134;31,128 46,112 39,102 24,103 21,116 28,128\nFrozenWastes:100,70 97,48 96,47 85,48 75,68 76,68 97,73;121,61 115,47 97,48 100,70 115,70;72,86 76,68 75,68 64,64 51,72 56,88 62,90;50,43 50,43 26,34 21,41 23,56 40,58;137,61 143,50 138,41 120,41 115,47 121,61;160,73 160,50 143,50 137,61 143,74;119,154 114,149 99,154 100,176 118,176;99,154 91,150 80,155 81,176 100,176;20,155 0,155 0,176 22,176;80,155 70,151 60,157 60,176 81,176;160,152 139,153 140,176 160,176\nForest:116,91 120,81 115,70 100,70 97,73 97,87 108,94;56,88 51,72 45,70 28,83 29,87 40,96;45,70 40,58 23,56 19,61 22,79 28,83;29,87 28,83 22,79 0,84 0,96 23,101;59,131 54,114 46,112 31,128 46,140;28,128 21,116 0,119 0,134 22,136\nBoggyMarsh:64,64 59,47 50,43 40,58 45,70 51,72;141,80 143,74 137,61 121,61 115,70 120,81;160,86 143,85 138,100 144,109 160,109;23,56 21,41 0,39 0,60 19,61\nRust:138,100 143,85 141,80 120,81 116,91 122,101;144,109 138,100 122,101 117,110 132,127 134,127;22,79 19,61 0,60 0,84;160,109 144,109 134,127 137,130 160,131;21,116 24,103 23,101 0,96 0,119\nMagmaCore:113,111 108,107 96,112 101,126;117,110 122,101 116,91 108,94 108,107 113,111;160,73 143,74 141,80 143,85 160,86;39,102 40,96 29,87 23,101 24,103\nRadioactive:132,127 117,110 113,111 101,126 101,127 114,138;114,149 114,138 101,127 90,134 91,150 99,154;138,152 137,130 134,127 132,127 114,138 114,149 119,154;91,150 90,134 77,129 68,134 70,151 80,155;70,151 68,134 59,131 46,140 44,151 60,157;44,151 46,140 31,128 28,128 22,136 23,152 40,154;23,152 22,136 0,134 0,155 20,155;139,153 138,152 119,154 118,176 140,176;40,154 23,152 20,155 22,176 40,176;160,152 160,131 137,130 138,152 139,153;60,157 44,151 40,154 40,176 60,176",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 55,
            "y": 79
          },
          {
            "id": "WarpConduitSender",
            "x": 98,
            "y": 76
          },
          {
            "id": "WarpConduitReceiver",
            "x": 66,
            "y": 132
          },
          {
            "id": "WarpReceiver",
            "x": 84,
            "y": 104
          },
          {
            "id": "WarpPortal",
            "x": 62,
            "y": 104
          },
          {
            "id": "GeneShuffler",
            "x": 32,
            "y": 53
          },
          {
            "id": "GeneShuffler",
            "x": 139,
            "y": 113
          }
        ],
        "geysers": [
          {
            "id": "liquid_co2",
            "x": 51,
            "y": 158,
            "emitRate": 484,
            "avgEmitRate": 145,
            "idleTime": 188,
            "eruptionTime": 205,
            "dormancyCycles": 59.6,
            "activeCycles": 81.1
          },
          {
            "id": "liquid_co2",
            "x": 133,
            "y": 165,
            "emitRate": 1996,
            "avgEmitRate": 145,
            "idleTime": 347,
            "eruptionTime": 53,
            "dormancyCycles": 63.4,
            "activeCycles": 78.1
          },
          {
            "id": "slush_water",
            "x": 147,
            "y": 62,
            "emitRate": 4669,
            "avgEmitRate": 1264,
            "idleTime": 238,
            "eruptionTime": 335,
            "dormancyCycles": 74.8,
            "activeCycles": 64.4
          },
          {
            "id": "slush_salt_water",
            "x": 107,
            "y": 57,
            "emitRate": 5633,
            "avgEmitRate": 1409,
            "idleTime": 247,
            "eruptionTime": 202,
            "dormancyCycles": 46.6,
            "activeCycles": 58.5
          },
          {
            "id": "filthy_water",
            "x": 35,
            "y": 164,
            "emitRate": 10192,
            "avgEmitRate": 2437,
            "idleTime": 384,
            "eruptionTime": 265,
            "dormancyCycles": 27.2,
            "activeCycles": 38.4
          },
          {
            "id": "molten_copper",
            "x": 129,
            "y": 141,
            "emitRate": 5364,
            "avgEmitRate": 254,
            "idleTime": 861,
            "eruptionTime": 80,
            "dormancyCycles": 95.1,
            "activeCycles": 120.1
          },
          {
            "id": "molten_copper",
            "x": 151,
            "y": 167,
            "emitRate": 8981,
            "avgEmitRate": 254,
            "idleTime": 805,
            "eruptionTime": 41,
            "dormancyCycles": 39.3,
            "activeCycles": 56.2
          },
          {
            "id": "molten_copper",
            "x": 104,
            "y": 166,
            "emitRate": 11882,
            "avgEmitRate": 259,
            "idleTime": 710,
            "eruptionTime": 26,
            "dormancyCycles": 42.9,
            "activeCycles": 70
          },
          {
            "id": "liquid_co2",
            "x": 80,
            "y": 73,
            "emitRate": 507,
            "avgEmitRate": 141,
            "idleTime": 349,
            "eruptionTime": 265,
            "dormancyCycles": 27.6,
            "activeCycles": 50.2
          },
          {
            "id": "hot_po2",
            "x": 76,
            "y": 166,
            "emitRate": 351,
            "avgEmitRate": 74,
            "idleTime": 194,
            "eruptionTime": 186,
            "dormancyCycles": 65.4,
            "activeCycles": 49.3
          },
          {
            "id": "big_volcano",
            "x": 31,
            "y": 97,
            "emitRate": 333376,
            "avgEmitRate": 1531,
            "idleTime": 7953,
            "eruptionTime": 56,
            "dormancyCycles": 42.5,
            "activeCycles": 80.6
          },
          {
            "id": "big_volcano",
            "x": 103,
            "y": 115,
            "emitRate": 200394,
            "avgEmitRate": 1017,
            "idleTime": 10514,
            "eruptionTime": 97,
            "dormancyCycles": 95.1,
            "activeCycles": 120.1
          },
          {
            "id": "big_volcano",
            "x": 151,
            "y": 80,
            "emitRate": 207721,
            "avgEmitRate": 1262,
            "idleTime": 8536,
            "eruptionTime": 84,
            "dormancyCycles": 52.3,
            "activeCycles": 87.9
          },
          {
            "id": "big_volcano",
            "x": 114,
            "y": 102,
            "emitRate": 263061,
            "avgEmitRate": 1062,
            "idleTime": 6563,
            "eruptionTime": 53,
            "dormancyCycles": 45.5,
            "activeCycles": 46.7
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 324,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "SlimeSplats"
        ],
        "biomePaths": "FrozenWastes:47,48 53,38 47,31 33,31 31,33 38,49;56,60 47,48 38,49 33,55 36,64 46,68;38,49 31,33 26,33 18,42 18,52 33,55;64,38 53,38 47,48 56,60 64,60;16,55 18,52 18,42 0,35 0,55;36,64 33,55 18,52 16,55 17,70 28,72;64,81 64,60 56,60 46,68 48,80 50,82;50,97 50,82 48,80 32,85 35,98 42,102;64,81 50,82 50,97 64,102;48,80 46,68 36,64 28,72 32,85 32,85;17,70 16,55 0,55 0,71 14,72;32,85 28,72 17,70 14,72 14,88 16,89;35,98 32,85 32,85 16,89 19,106;19,106 19,106 16,89 14,88 0,90 0,107 16,108;64,110 64,102 50,97 42,102 42,111 48,115;64,110 48,115 48,128 64,128;30,117 19,106 16,108 12,128 29,128;16,108 0,107 0,128 12,128;14,88 14,72 0,71 0,90;42,111 42,102 35,98 19,106 19,106 30,117;48,115 42,111 30,117 29,128 48,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 29,
            "y": 38
          },
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 116
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 43,
            "y": 61,
            "emitRate": 6492,
            "avgEmitRate": 300,
            "idleTime": 699,
            "eruptionTime": 63,
            "dormancyCycles": 79.5,
            "activeCycles": 100.8
          },
          {
            "id": "molten_iron",
            "x": 38,
            "y": 105,
            "emitRate": 7080,
            "avgEmitRate": 243,
            "idleTime": 571,
            "eruptionTime": 40,
            "dormancyCycles": 59.2,
            "activeCycles": 66.4
          },
          {
            "id": "molten_iron",
            "x": 37,
            "y": 117,
            "emitRate": 7856,
            "avgEmitRate": 274,
            "idleTime": 671,
            "eruptionTime": 45,
            "dormancyCycles": 62.3,
            "activeCycles": 79.5
          },
          {
            "id": "molten_iron",
            "x": 25,
            "y": 82,
            "emitRate": 7590,
            "avgEmitRate": 302,
            "idleTime": 881,
            "eruptionTime": 61,
            "dormancyCycles": 51.8,
            "activeCycles": 83
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 324,
        "offsetY": 308,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "DistressSignal"
        ],
        "biomePaths": "BoggyMarsh:39,49 39,35 32,32 22,39 31,52;55,44 47,31 39,35 39,49 47,52;51,79 47,66 34,68 32,72 34,81 44,86;64,61 64,44 55,44 47,52 50,62;32,72 34,68 29,56 16,58 13,63 18,73;19,39 13,30 0,30 0,46 12,46;29,56 31,52 22,39 19,39 12,46 16,58;14,80 18,73 13,63 0,64 0,82;64,26 48,29 47,31 55,44 64,44;47,66 50,62 47,52 39,49 31,52 29,56 34,68\nMagmaCore:64,80 51,79 44,86 45,96 64,96;44,86 34,81 22,92 22,96 45,96;22,92 14,80 0,82 0,96 22,96;34,81 32,72 18,73 14,80 22,92\nToxicJungle:64,61 50,62 47,66 51,79 64,80;13,63 16,58 12,46 0,46 0,64",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 38,
            "y": 43
          },
          {
            "id": "GravitasPedestal",
            "x": 24,
            "y": 43
          },
          {
            "id": "SapTree",
            "x": 31,
            "y": 43
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 21,
            "y": 88,
            "emitRate": 9797,
            "avgEmitRate": 282,
            "idleTime": 803,
            "eruptionTime": 42,
            "dormancyCycles": 50.5,
            "activeCycles": 69.5
          },
          {
            "id": "molten_tungsten",
            "x": 54,
            "y": 91,
            "emitRate": 8752,
            "avgEmitRate": 284,
            "idleTime": 759,
            "eruptionTime": 39,
            "dormancyCycles": 49.9,
            "activeCycles": 101.3
          },
          {
            "id": "molten_tungsten",
            "x": 20,
            "y": 82,
            "emitRate": 10063,
            "avgEmitRate": 400,
            "idleTime": 679,
            "eruptionTime": 47,
            "dormancyCycles": 48,
            "activeCycles": 76.5
          },
          {
            "id": "methane",
            "x": 22,
            "y": 61,
            "emitRate": 229,
            "avgEmitRate": 101,
            "idleTime": 41,
            "eruptionTime": 59,
            "dormancyCycles": 30.1,
            "activeCycles": 88.3
          },
          {
            "id": "slimy_po2",
            "x": 11,
            "y": 51,
            "emitRate": 496,
            "avgEmitRate": 106,
            "idleTime": 440,
            "eruptionTime": 239,
            "dormancyCycles": 39.8,
            "activeCycles": 61
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 390,
        "offsetY": 178,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "MagmaCore:64,56 64,37 50,37 45,47 50,56;45,64 50,56 45,47 34,47 27,57 34,65;45,47 50,37 46,29 33,28 27,36 34,47;27,57 34,47 27,36 18,36 12,43 19,58;51,77 45,64 34,65 30,77 43,85;30,77 34,65 27,57 19,58 14,63 18,79 21,80;21,80 18,79 0,82 0,96 21,96;18,79 14,63 0,62 0,82;19,58 12,43 0,43 0,62 14,63\nOilField:64,56 50,56 45,64 51,77 64,78;64,78 51,77 43,85 43,96 64,96;43,85 30,77 21,80 21,96 43,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 25,
            "y": 84
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 49,
            "y": 64,
            "emitRate": 217519,
            "avgEmitRate": 1218,
            "idleTime": 8423,
            "eruptionTime": 69,
            "dormancyCycles": 49,
            "activeCycles": 106.7
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 404,
        "offsetY": 0,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:44,58 48,43 39,36 35,37 28,45 36,60 37,61;76,64 80,51 75,42 62,45 62,60 76,64;96,49 96,36 78,33 75,42 80,51;62,60 62,45 57,41 48,43 44,58 56,64;28,45 35,37 23,23 14,33 18,46;36,60 28,45 18,46 15,49 17,64 19,65;18,46 14,33 0,31 0,49 15,49;96,49 80,51 76,64 96,67;17,64 15,49 0,49 0,66\nFrozenWastes:37,61 36,60 19,65 20,80 38,80;56,64 44,58 37,61 38,80 55,80;19,65 17,64 0,66 0,80 20,80;96,67 76,64 76,64 74,80 96,80;76,64 62,60 56,64 55,80 74,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 39,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 32,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 70,
            "y": 53,
            "emitRate": 401,
            "avgEmitRate": 100,
            "idleTime": 397,
            "eruptionTime": 350,
            "dormancyCycles": 51.1,
            "activeCycles": 57.9
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 242,
        "offsetY": 178,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:67,68 61,60 53,59 45,70 51,80 60,82;53,59 47,51 36,50 30,58 35,69 45,70;80,108 80,89 64,88 59,101 65,108;69,47 62,39 54,38 47,51 53,59 61,60;51,80 45,70 35,69 27,80 38,92 40,92;80,69 67,68 60,82 64,88 80,89;59,101 64,88 60,82 51,80 40,92 48,102;60,121 65,108 59,101 48,102 43,115 50,122;80,128 80,108 65,108 60,121 67,129;54,38 48,32 34,33 31,37 36,50 47,51;43,115 48,102 40,92 38,92 23,104 34,116;36,50 31,37 18,38 17,56 18,58 30,58;38,92 27,80 20,80 14,94 22,104 23,104;45,136 50,122 43,115 34,116 27,129 36,137;14,94 20,80 15,74 0,75 0,95;27,80 35,69 30,58 18,58 15,74 20,80;27,129 34,116 23,104 22,104 12,116 20,129;22,104 14,94 0,95 0,115 12,116;15,74 18,58 17,56 0,55 0,75;14,137 20,129 12,116 0,115 0,137;18,38 16,37 0,38 0,55 17,56;62,140 67,129 60,121 50,122 45,136 52,142;80,47 69,47 61,60 67,68 80,69;80,152 73,152 60,162 61,174 80,174;19,156 0,157 0,174 20,174;60,162 50,155 40,159 38,174 61,174;40,159 30,151 21,153 19,156 20,174 38,174\nSwamp:30,151 36,137 27,129 20,129 14,137 21,153;80,128 67,129 62,140 73,152 80,152;50,155 52,142 45,136 36,137 30,151 40,159;73,152 62,140 52,142 50,155 60,162;19,156 21,153 14,137 0,137 0,157",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 44,
            "y": 155
          },
          {
            "id": "GravitasPedestal",
            "x": 46,
            "y": 155
          }
        ],
        "geysers": [
          {
            "id": "slush_water",
            "x": 59,
            "y": 95,
            "emitRate": 6068,
            "avgEmitRate": 1668,
            "idleTime": 362,
            "eruptionTime": 261,
            "dormancyCycles": 41.6,
            "activeCycles": 79.5
          },
          {
            "id": "hot_water",
            "x": 17,
            "y": 93,
            "emitRate": 12016,
            "avgEmitRate": 3349,
            "idleTime": 419,
            "eruptionTime": 321,
            "dormancyCycles": 57.8,
            "activeCycles": 104.1
          }
        ]
      },
      {
        "id": "MiniRegolithMoonlet",
        "offsetX": 390,
        "offsetY": 276,
        "sizeX": 96,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:77,55 74,47 58,43 53,53 57,62 65,64;84,75 80,57 77,55 65,64 69,77 77,79;96,54 80,57 84,75 96,76;36,64 38,52 37,50 19,50 18,63 23,69;18,63 19,50 18,48 0,48 0,66;46,75 46,74 36,64 23,69 22,77 37,84;22,77 23,69 18,63 0,66 0,78 18,81\nSandstone:57,62 53,53 38,52 36,64 46,74;69,77 65,64 57,62 46,74 46,75 58,84\nFrozenWastes:96,76 84,75 77,79 79,96 96,96;77,79 69,77 58,84 58,96 79,96;37,84 22,77 18,81 17,96 37,96;18,81 0,78 0,96 17,96;58,84 46,75 37,84 37,96 58,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 68,
            "y": 58
          },
          {
            "id": "GravitasPedestal",
            "x": 72,
            "y": 58
          },
          {
            "id": "GeneShuffler",
            "x": 70,
            "y": 52
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 47,
            "y": 82,
            "emitRate": 5984,
            "avgEmitRate": 1494,
            "idleTime": 240,
            "eruptionTime": 206,
            "dormancyCycles": 57.7,
            "activeCycles": 68.2
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "VanillaBadlands",
        "q": 0,
        "r": 0
      },
      {
        "id": "MediumRadioactiveVanillaWarpPlanet",
        "q": 1,
        "r": -3
      },
      {
        "id": "TundraMoonlet",
        "q": -5,
        "r": 5
      },
      {
        "id": "MarshyMoonlet",
        "q": 6,
        "r": -4
      },
      {
        "id": "NiobiumMoonlet",
        "q": 0,
        "r": -6
      },
      {
        "id": "MooMoonlet",
        "q": -1,
        "r": 7
      },
      {
        "id": "WaterMoonlet",
        "q": -6,
        "r": 0
      },
      {
        "id": "MiniRegolithMoonlet",
        "q": 6,
        "r": 1
      },
      {
        "id": "TemporalTear",
        "q": 7,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SandyOreField",
        "q": -3,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 4,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 3,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": -11,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -11,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": 9,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -4,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 10,
        "r": -8
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 1,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": 2,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 3,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 2,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_InterstellarIceField",
        "q": -3,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -4,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -9,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_MetallicAsteroidField",
        "q": 0,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": -1,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": -2,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -4,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": -5,
        "r": 11
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 9,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 8,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_OxidizedAsteroidField",
        "q": 8,
        "r": -10
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation6",
        "q": 3,
        "r": -2
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": 4,
        "r": -11
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": -8,
        "r": 10
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation2",
        "q": 11,
        "r": -1
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": 0,
        "r": 11
      }
    ]
  },
  {
    "coordinate": "M-RAD-C-328466368-0-0-0",
    "cluster": "M-RAD-C",
    "dlcs": [
      "FrostyPlanet",
      "SpacedOut"
    ],
    "asteroids": [
      {
        "id": "MiniRadioactiveOceanStart",
        "offsetX": 212,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "LushCore",
          "Volcanoes"
        ],
        "biomePaths": "Forest:72,110 82,96 82,87 72,67 65,64 42,67 32,82 31,92 38,108 64,113;101,95 102,92 96,84 82,87 82,96 90,101;31,92 32,82 17,77 12,86 18,96;38,109 38,108 31,92 18,96 16,101 22,109;91,112 90,101 82,96 72,110 85,117;64,113 38,108 38,109 40,119 61,123;128,135 108,133 102,137 102,153 128,153;102,137 85,131 77,137 77,153 102,153;22,135 14,128 0,128 0,153 20,153;77,137 63,131 49,146 49,153 77,153;49,146 35,132 22,135 20,153 49,153\nOcean:104,72 113,54 103,43 90,48 87,60 102,72;128,77 128,53 113,54 104,72 113,80;65,64 61,46 48,42 37,57 42,67;96,84 102,72 87,60 72,67 82,87;42,67 37,57 24,54 15,73 17,77 32,82;24,54 19,49 0,48 0,71 15,73;128,110 128,92 112,86 102,92 101,95 106,112 107,113\nMagmaCore:128,77 113,80 112,86 128,92;112,86 113,80 104,72 102,72 96,84 102,92;12,86 17,77 15,73 0,71 0,87;16,101 18,96 12,86 0,87 0,102;101,95 90,101 91,112 106,112\nRadioactive:128,110 107,113 108,133 128,135;108,133 107,113 106,112 91,112 85,117 85,131 102,137;63,131 61,123 40,119 35,132 49,146;85,131 85,117 72,110 64,113 61,123 63,131 77,137;14,128 22,109 16,101 0,102 0,128;35,132 40,119 38,109 22,109 14,128 22,135",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 58,
            "y": 91
          },
          {
            "id": "WarpConduitSender",
            "x": 73,
            "y": 119
          },
          {
            "id": "WarpConduitReceiver",
            "x": 47,
            "y": 120
          },
          {
            "id": "GravitasPedestal",
            "x": 106,
            "y": 133
          },
          {
            "id": "WarpReceiver",
            "x": 31,
            "y": 101
          },
          {
            "id": "WarpPortal",
            "x": 31,
            "y": 96
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 30,
            "y": 72,
            "emitRate": 15903,
            "avgEmitRate": 2893,
            "idleTime": 316,
            "eruptionTime": 129,
            "dormancyCycles": 55.9,
            "activeCycles": 94.1
          },
          {
            "id": "slush_water",
            "x": 50,
            "y": 136,
            "emitRate": 5285,
            "avgEmitRate": 1512,
            "idleTime": 279,
            "eruptionTime": 218,
            "dormancyCycles": 41.8,
            "activeCycles": 78.4
          },
          {
            "id": "hot_steam",
            "x": 82,
            "y": 128,
            "emitRate": 2600,
            "avgEmitRate": 738,
            "idleTime": 531,
            "eruptionTime": 392,
            "dormancyCycles": 59.4,
            "activeCycles": 120
          },
          {
            "id": "hot_water",
            "x": 110,
            "y": 107,
            "emitRate": 10521,
            "avgEmitRate": 2409,
            "idleTime": 274,
            "eruptionTime": 272,
            "dormancyCycles": 64.8,
            "activeCycles": 55.1
          },
          {
            "id": "big_volcano",
            "x": 104,
            "y": 83,
            "emitRate": 273656,
            "avgEmitRate": 955,
            "idleTime": 9214,
            "eruptionTime": 60,
            "dormancyCycles": 49.8,
            "activeCycles": 58.4
          },
          {
            "id": "big_volcano",
            "x": 97,
            "y": 106,
            "emitRate": 297986,
            "avgEmitRate": 1208,
            "idleTime": 9729,
            "eruptionTime": 65,
            "dormancyCycles": 60.2,
            "activeCycles": 95.2
          }
        ]
      },
      {
        "id": "MiniBadlands",
        "offsetX": 82,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MetalPoor"
        ],
        "biomePaths": "ToxicJungle:98,64 86,47 74,48 69,54 71,68 85,73;107,59 110,41 106,37 93,37 86,47 98,64 100,65;71,68 69,54 50,52 44,62 48,73 62,77;85,92 85,73 71,68 62,77 65,91 83,94;50,52 47,43 28,38 25,41 23,57 25,61 44,62;65,91 62,77 48,73 40,85 44,95 58,98;35,107 44,95 40,85 26,83 19,96 25,108;38,133 24,131 19,135 20,153 39,153;23,57 25,41 0,37 0,57;128,41 110,41 107,59 128,63\nSandstone:82,110 83,94 65,91 58,98 61,111 70,115;48,73 44,62 25,61 21,76 26,83 40,85;105,111 109,105 103,93 85,92 83,94 82,110 91,116;128,103 109,105 105,111 115,129 128,129;90,131 91,116 82,110 70,115 70,130 81,136;70,130 70,115 61,111 46,121 45,127 60,136;61,111 58,98 44,95 35,107 46,121;81,136 70,130 60,136 59,153 81,153;25,108 19,96 0,96 0,114 21,114;24,131 21,114 0,114 0,134 19,135;128,103 128,81 109,82 103,93 109,105;25,61 23,57 0,57 0,75 21,76;19,135 0,134 0,153 20,153\nOilField:128,81 128,63 107,59 100,65 109,82;109,82 100,65 98,64 85,73 85,92 103,93;115,129 105,111 91,116 90,131 105,138;26,83 21,76 0,75 0,96 19,96;60,136 45,127 38,133 39,153 59,153;45,127 46,121 35,107 25,108 21,114 24,131 38,133;128,129 115,129 105,138 106,153 128,153;105,138 90,131 81,136 81,153 106,153",
        "pointsOfInterest": [
          {
            "id": "GeneShuffler",
            "x": 33,
            "y": 57
          }
        ],
        "geysers": [
          {
            "id": "OilWell",
            "x": 89,
            "y": 82,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 43,
            "y": 134,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 27,
            "y": 120,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 99,
            "y": 135,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 95,
            "y": 120,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "steam",
            "x": 89,
            "y": 62,
            "emitRate": 5074,
            "avgEmitRate": 1306,
            "idleTime": 264,
            "eruptionTime": 238,
            "dormancyCycles": 67.5,
            "activeCycles": 80.4
          },
          {
            "id": "chlorine_gas",
            "x": 37,
            "y": 98,
            "emitRate": 265,
            "avgEmitRate": 100,
            "idleTime": 208,
            "eruptionTime": 367,
            "dormancyCycles": 24.2,
            "activeCycles": 34.8
          },
          {
            "id": "oil_drip",
            "x": 72,
            "y": 122,
            "emitRate": 135,
            "avgEmitRate": 79,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "big_volcano",
            "x": 65,
            "y": 70,
            "emitRate": 334922,
            "avgEmitRate": 1290,
            "idleTime": 9845,
            "eruptionTime": 61,
            "dormancyCycles": 67.2,
            "activeCycles": 114
          },
          {
            "id": "hot_co2",
            "x": 58,
            "y": 93,
            "emitRate": 623,
            "avgEmitRate": 110,
            "idleTime": 240,
            "eruptionTime": 114,
            "dormancyCycles": 58.8,
            "activeCycles": 70.6
          }
        ]
      },
      {
        "id": "MiniFlippedWarp",
        "offsetX": 342,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "BouldersMedium"
        ],
        "biomePaths": "Sandstone:76,130 67,115 53,119 52,132 64,139;83,105 86,96 82,88 69,88 64,97 70,108;52,132 53,119 43,112 43,112 32,129 42,137;42,137 32,129 25,130 21,135 22,153 41,153;86,132 85,131 76,130 64,139 65,153 85,153;128,136 107,134 107,135 106,153 128,153;64,139 52,132 42,137 41,153 65,153;107,135 86,132 85,153 106,153;21,135 0,134 0,153 22,153\nMagmaCore:90,52 83,41 71,40 62,58 67,67 82,67;110,63 101,52 90,52 82,67 87,76 106,78;71,40 68,35 48,31 41,41 48,57 62,58;48,57 41,41 28,40 20,56 24,66 42,67;28,40 24,33 0,32 0,55 20,56;128,61 128,39 108,36 101,52 110,63\nWasteland:63,76 67,67 62,58 48,57 42,67 46,78;82,88 87,76 82,67 67,67 63,76 69,88;44,84 46,78 42,67 24,66 20,75 22,80 35,87;22,80 20,75 0,72 0,94 11,94;24,66 20,56 0,55 0,72 20,75;128,61 110,63 106,78 106,80 128,82;104,94 106,80 106,78 87,76 82,88 86,96;128,116 128,98 108,99 105,113 108,117;43,112 53,97 44,84 35,87 31,104 43,112;67,115 70,108 64,97 53,97 43,112 53,119\nFrozenWastes:64,97 69,88 63,76 46,78 44,84 53,97;31,104 35,87 22,80 11,94 22,107;17,115 22,107 11,94 0,94 0,117;32,129 43,112 31,104 22,107 17,115 25,130;25,130 17,115 0,117 0,134 21,135;128,98 128,82 106,80 104,94 108,99;105,113 108,99 104,94 86,96 83,105 91,115;128,116 108,117 107,134 128,136;107,134 108,117 105,113 91,115 85,131 86,132 107,135;91,115 83,105 70,108 67,115 76,130 85,131",
        "pointsOfInterest": [
          {
            "id": "MassiveHeatSink",
            "x": 112,
            "y": 131
          },
          {
            "id": "MassiveHeatSink",
            "x": 62,
            "y": 85
          },
          {
            "id": "MassiveHeatSink",
            "x": 38,
            "y": 118
          },
          {
            "id": "WarpConduitReceiver",
            "x": 101,
            "y": 93
          },
          {
            "id": "WarpConduitSender",
            "x": 91,
            "y": 112
          },
          {
            "id": "WarpPortal",
            "x": 52,
            "y": 130
          },
          {
            "id": "WarpReceiver",
            "x": 74,
            "y": 130
          }
        ],
        "geysers": [
          {
            "id": "liquid_sulfur",
            "x": 14,
            "y": 47,
            "emitRate": 6636,
            "avgEmitRate": 1605,
            "idleTime": 495,
            "eruptionTime": 341,
            "dormancyCycles": 50.5,
            "activeCycles": 73.7
          },
          {
            "id": "hot_hydrogen",
            "x": 28,
            "y": 89,
            "emitRate": 301,
            "avgEmitRate": 104,
            "idleTime": 318,
            "eruptionTime": 365,
            "dormancyCycles": 44.3,
            "activeCycles": 81.5
          },
          {
            "id": "hot_co2",
            "x": 65,
            "y": 110,
            "emitRate": 296,
            "avgEmitRate": 87,
            "idleTime": 230,
            "eruptionTime": 339,
            "dormancyCycles": 84.9,
            "activeCycles": 81.8
          },
          {
            "id": "hot_po2",
            "x": 90,
            "y": 128,
            "emitRate": 290,
            "avgEmitRate": 89,
            "idleTime": 260,
            "eruptionTime": 328,
            "dormancyCycles": 53.5,
            "activeCycles": 66.2
          }
        ]
      },
      {
        "id": "MiniMetallicSwampy",
        "offsetX": 472,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "MagmaVents",
          "MetalPoor"
        ],
        "biomePaths": "Swamp:86,61 84,44 69,41 61,48 64,61 76,66;75,86 76,66 64,61 52,71 63,89;95,84 92,64 86,61 76,66 75,86 84,90;117,72 105,59 92,64 95,84 105,87;46,71 39,60 23,61 18,69 24,83 36,84;60,95 63,89 52,71 46,71 36,84 44,97;24,83 18,69 0,69 0,91 18,91;128,72 117,72 105,87 109,96 128,97;128,72 128,47 110,47 105,59 117,72\nBoggyMarsh:64,61 61,48 45,44 39,60 46,71 52,71;105,59 110,47 106,42 90,39 84,44 86,61 92,64;39,60 45,44 44,42 24,38 18,46 23,61;18,69 23,61 18,46 0,46 0,69;44,97 36,84 24,83 18,91 24,107 38,108;86,129 83,110 66,110 61,117 64,131 78,136;66,110 60,95 44,97 38,108 43,116 61,117;24,107 18,91 0,91 0,113 18,114;128,117 106,116 101,132 103,135 128,136\nMetallic:83,110 85,108 84,90 75,86 63,89 60,95 66,110;109,96 105,87 95,84 84,90 85,108 103,109;38,130 43,116 38,108 24,107 18,114 21,131 25,134;128,97 109,96 103,109 106,116 128,117;64,131 61,117 43,116 38,130 51,142\nMagmaCore:106,116 103,109 85,108 83,110 86,129 101,132;51,142 38,130 25,134 24,153 51,153;25,134 21,131 0,134 0,153 24,153;21,131 18,114 0,113 0,134;78,136 64,131 51,142 51,153 79,153;103,135 101,132 86,129 78,136 79,153 101,153;128,136 103,135 101,153 128,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 96,
            "y": 91
          }
        ],
        "geysers": [
          {
            "id": "methane",
            "x": 52,
            "y": 63,
            "emitRate": 336,
            "avgEmitRate": 84,
            "idleTime": 315,
            "eruptionTime": 297,
            "dormancyCycles": 80.8,
            "activeCycles": 87
          },
          {
            "id": "molten_cobalt",
            "x": 48,
            "y": 122,
            "emitRate": 8570,
            "avgEmitRate": 295,
            "idleTime": 598,
            "eruptionTime": 35,
            "dormancyCycles": 47.3,
            "activeCycles": 76.6
          },
          {
            "id": "molten_gold",
            "x": 115,
            "y": 115,
            "emitRate": 13069,
            "avgEmitRate": 296,
            "idleTime": 800,
            "eruptionTime": 38,
            "dormancyCycles": 54.7,
            "activeCycles": 54.5
          },
          {
            "id": "molten_gold",
            "x": 89,
            "y": 105,
            "emitRate": 6538,
            "avgEmitRate": 333,
            "idleTime": 599,
            "eruptionTime": 51,
            "dormancyCycles": 54.4,
            "activeCycles": 101.2
          },
          {
            "id": "molten_aluminum",
            "x": 30,
            "y": 87,
            "emitRate": 8819,
            "avgEmitRate": 264,
            "idleTime": 587,
            "eruptionTime": 32,
            "dormancyCycles": 38.8,
            "activeCycles": 52.1
          },
          {
            "id": "molten_cobalt",
            "x": 27,
            "y": 111,
            "emitRate": 7759,
            "avgEmitRate": 232,
            "idleTime": 641,
            "eruptionTime": 38,
            "dormancyCycles": 53.5,
            "activeCycles": 61.3
          },
          {
            "id": "small_volcano",
            "x": 48,
            "y": 102,
            "emitRate": 145420,
            "avgEmitRate": 662,
            "idleTime": 10113,
            "eruptionTime": 74,
            "dormancyCycles": 54.2,
            "activeCycles": 89.3
          },
          {
            "id": "methane",
            "x": 90,
            "y": 60,
            "emitRate": 506,
            "avgEmitRate": 111,
            "idleTime": 401,
            "eruptionTime": 222,
            "dormancyCycles": 53.4,
            "activeCycles": 84.2
          }
        ]
      },
      {
        "id": "MiniForestFrozen",
        "offsetX": 602,
        "offsetY": 0,
        "sizeX": 128,
        "sizeY": 153,
        "worldTraits": [
          "CrashedSatellites"
        ],
        "biomePaths": "ToxicJungle:104,84 111,68 104,60 84,62 83,64 84,74 98,85;128,91 128,68 111,68 104,84 113,92;62,85 58,64 41,68 38,84 48,92;17,78 21,63 0,55 0,80;66,111 73,95 70,88 62,85 48,92 47,106 64,112;26,107 22,99 0,99 0,118 20,118;47,106 48,92 38,84 26,87 22,99 26,107 43,110;22,136 25,131 20,118 0,118 0,136;42,130 43,110 26,107 20,118 25,131;128,91 113,92 107,112 128,115;128,133 128,115 107,112 107,112 105,131 108,134\nRust:98,85 84,74 70,88 73,95 89,98;107,112 113,92 104,84 98,85 89,98 93,109 107,112;84,74 83,64 58,63 58,64 62,85 70,88;38,84 41,68 29,58 21,63 17,78 26,87;93,109 89,98 73,95 66,111 83,118;22,99 26,87 17,78 0,80 0,99;60,131 64,112 47,106 43,110 42,130 45,133;85,131 83,118 66,111 64,112 60,131 67,136;105,131 107,112 93,109 83,118 85,131 87,133\nForest:128,68 128,45 108,44 104,60 111,68;84,62 82,44 67,40 57,49 58,63 83,64;104,60 108,44 107,42 88,39 82,44 84,62;58,64 58,63 57,49 44,42 31,49 29,58 41,68;29,58 31,49 21,39 0,44 0,55 21,63\nMagmaCore:45,133 42,130 25,131 22,136 24,153 44,153;87,133 85,131 67,136 66,153 88,153;22,136 0,136 0,153 24,153;67,136 60,131 45,133 44,153 66,153;128,133 108,134 107,153 128,153;108,134 105,131 87,133 88,153 107,153",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 26,
            "y": 76
          },
          {
            "id": "PropSurfaceSatellite3",
            "x": 55,
            "y": 65
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 109,
            "y": 84
          },
          {
            "id": "PropSurfaceSatellite2",
            "x": 88,
            "y": 69
          },
          {
            "id": "PropSurfaceSatellite1",
            "x": 73,
            "y": 68
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 41,
            "y": 85,
            "emitRate": 460,
            "avgEmitRate": 130,
            "idleTime": 601,
            "eruptionTime": 446,
            "dormancyCycles": 45.2,
            "activeCycles": 89
          },
          {
            "id": "slush_water",
            "x": 84,
            "y": 103,
            "emitRate": 5202,
            "avgEmitRate": 1596,
            "idleTime": 235,
            "eruptionTime": 302,
            "dormancyCycles": 83,
            "activeCycles": 99.4
          },
          {
            "id": "liquid_sulfur",
            "x": 66,
            "y": 83,
            "emitRate": 4857,
            "avgEmitRate": 912,
            "idleTime": 515,
            "eruptionTime": 300,
            "dormancyCycles": 57.8,
            "activeCycles": 60.3
          },
          {
            "id": "hot_hydrogen",
            "x": 79,
            "y": 125,
            "emitRate": 313,
            "avgEmitRate": 137,
            "idleTime": 219,
            "eruptionTime": 348,
            "dormancyCycles": 29.6,
            "activeCycles": 73.3
          },
          {
            "id": "slimy_po2",
            "x": 86,
            "y": 81,
            "emitRate": 350,
            "avgEmitRate": 133,
            "idleTime": 277,
            "eruptionTime": 276,
            "dormancyCycles": 20.9,
            "activeCycles": 66.7
          }
        ]
      },
      {
        "id": "TundraMoonlet",
        "offsetX": 732,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 128,
        "worldTraits": [
          "SubsurfaceOcean",
          "LushCore"
        ],
        "biomePaths": "Ocean:64,50 64,33 46,33 41,39 51,51;39,39 23,29 15,39 19,50 29,52;19,50 15,39 0,37 0,59 11,60\nFrozenWastes:64,50 51,51 45,62 50,70 64,70;51,51 41,39 39,39 29,52 34,61 45,62;34,61 29,52 19,50 11,60 19,71 27,71;45,80 50,70 45,62 34,61 27,71 33,80;64,88 64,70 50,70 45,80 50,89;11,82 19,71 11,60 0,59 0,82;28,91 33,80 27,71 19,71 11,82 19,92;64,88 50,89 46,96 53,108 64,109;46,96 50,89 45,80 33,80 28,91 34,98;31,111 34,98 28,91 19,92 12,104 16,110 30,112;19,92 11,82 0,82 0,104 12,104;53,108 46,96 34,98 31,111 47,115\nForest:16,110 12,104 0,104 0,128 10,128;64,109 53,108 47,115 48,128 64,128;30,112 16,110 10,128 29,128;47,115 31,111 30,112 29,128 48,128",
        "pointsOfInterest": [
          {
            "id": "TemporalTearOpener",
            "x": 24,
            "y": 40
          },
          {
            "id": "GravitasPedestal",
            "x": 53,
            "y": 111
          }
        ],
        "geysers": [
          {
            "id": "molten_iron",
            "x": 53,
            "y": 75,
            "emitRate": 14980,
            "avgEmitRate": 261,
            "idleTime": 705,
            "eruptionTime": 23,
            "dormancyCycles": 63.3,
            "activeCycles": 79.3
          },
          {
            "id": "molten_iron",
            "x": 9,
            "y": 88,
            "emitRate": 10416,
            "avgEmitRate": 296,
            "idleTime": 749,
            "eruptionTime": 38,
            "dormancyCycles": 52.5,
            "activeCycles": 77.6
          },
          {
            "id": "molten_iron",
            "x": 35,
            "y": 67,
            "emitRate": 11605,
            "avgEmitRate": 249,
            "idleTime": 718,
            "eruptionTime": 25,
            "dormancyCycles": 49.3,
            "activeCycles": 83
          },
          {
            "id": "molten_iron",
            "x": 33,
            "y": 94,
            "emitRate": 8351,
            "avgEmitRate": 339,
            "idleTime": 798,
            "eruptionTime": 57,
            "dormancyCycles": 46.2,
            "activeCycles": 73.4
          }
        ]
      },
      {
        "id": "MarshyMoonlet",
        "offsetX": 798,
        "offsetY": 0,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "BoggyMarsh:41,50 40,40 23,36 19,40 20,57 21,59 30,59;64,53 64,37 44,35 40,40 41,50 50,56;50,56 41,50 30,59 39,74 47,75;20,57 19,40 0,38 0,58\nToxicJungle:17,77 21,59 20,57 0,58 0,74 16,77;64,53 50,56 47,75 48,75 64,75;39,74 30,59 21,59 17,77 31,81\nMagmaCore:64,75 48,75 48,96 64,96;48,75 47,75 39,74 31,81 33,96 48,96;16,77 0,74 0,96 13,96;31,81 17,77 16,77 13,96 33,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 35,
            "y": 49
          },
          {
            "id": "GravitasPedestal",
            "x": 21,
            "y": 49
          },
          {
            "id": "SapTree",
            "x": 28,
            "y": 49
          }
        ],
        "geysers": [
          {
            "id": "molten_tungsten",
            "x": 55,
            "y": 65,
            "emitRate": 9979,
            "avgEmitRate": 343,
            "idleTime": 797,
            "eruptionTime": 40,
            "dormancyCycles": 35.6,
            "activeCycles": 93.5
          },
          {
            "id": "molten_tungsten",
            "x": 12,
            "y": 91,
            "emitRate": 6038,
            "avgEmitRate": 304,
            "idleTime": 722,
            "eruptionTime": 60,
            "dormancyCycles": 52.8,
            "activeCycles": 98.8
          },
          {
            "id": "molten_tungsten",
            "x": 50,
            "y": 87,
            "emitRate": 8710,
            "avgEmitRate": 294,
            "idleTime": 815,
            "eruptionTime": 45,
            "dormancyCycles": 52.6,
            "activeCycles": 95.1
          },
          {
            "id": "hot_po2",
            "x": 37,
            "y": 89,
            "emitRate": 282,
            "avgEmitRate": 79,
            "idleTime": 232,
            "eruptionTime": 344,
            "dormancyCycles": 69.9,
            "activeCycles": 61.6
          },
          {
            "id": "chlorine_gas",
            "x": 16,
            "y": 71,
            "emitRate": 332,
            "avgEmitRate": 116,
            "idleTime": 271,
            "eruptionTime": 348,
            "dormancyCycles": 66.2,
            "activeCycles": 109.1
          }
        ]
      },
      {
        "id": "MooMoonlet",
        "offsetX": 864,
        "offsetY": 98,
        "sizeX": 96,
        "sizeY": 80,
        "worldTraits": [],
        "biomePaths": "Moo:65,42 56,30 46,34 43,47 58,52;79,60 81,57 75,41 65,42 58,52 60,60 66,64;60,60 58,52 43,47 39,49 38,62 46,68;96,55 96,37 78,37 75,41 81,57;38,62 39,49 29,45 19,51 17,61 24,68;17,61 19,51 8,41 0,41 0,64\nFrozenWastes:96,55 81,57 79,60 86,80 96,80;66,64 60,60 46,68 47,80 66,80;46,68 38,62 24,68 23,80 47,80;24,68 17,61 0,64 0,80 23,80;79,60 66,64 66,80 86,80",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 54,
            "y": 43
          },
          {
            "id": "GravitasPedestal",
            "x": 47,
            "y": 43
          }
        ],
        "geysers": [
          {
            "id": "chlorine_gas",
            "x": 13,
            "y": 51,
            "emitRate": 678,
            "avgEmitRate": 113,
            "idleTime": 623,
            "eruptionTime": 275,
            "dormancyCycles": 45.2,
            "activeCycles": 54
          }
        ]
      },
      {
        "id": "WaterMoonlet",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 80,
        "sizeY": 174,
        "worldTraits": [],
        "biomePaths": "Barren:80,35 61,38 62,53 80,55;80,55 62,53 58,57 60,68 80,72;62,53 61,38 57,34 43,38 40,53 41,54 58,57;80,81 80,72 60,68 55,76 57,86 60,87;43,38 36,31 20,37 22,50 40,53;60,68 58,57 41,54 37,70 38,72 55,76;41,54 40,53 22,50 17,57 21,68 37,70;61,105 64,100 60,87 57,86 41,94 41,101 49,107;22,50 20,37 19,37 0,39 0,56 17,57;15,77 21,68 17,57 0,56 0,77;34,87 38,72 37,70 21,68 15,77 21,89;47,122 49,107 41,101 30,109 32,121 41,125;80,100 64,100 61,105 66,118 80,118;57,86 55,76 38,72 34,87 41,94;21,89 15,77 0,77 0,96 17,97;20,105 17,97 0,96 0,117 11,117;80,135 80,118 66,118 60,126 63,136;80,100 80,81 60,87 64,100;66,118 61,105 49,107 47,122 60,126;32,121 30,109 20,105 11,117 20,128;19,133 20,128 11,117 0,117 0,138 11,139;41,101 41,94 34,87 21,89 17,97 20,105 30,109;61,139 63,136 60,126 47,122 41,125 41,138 48,143;41,138 41,125 32,121 20,128 19,133 31,142;19,157 17,156 0,160 0,174 20,174;40,161 28,154 19,157 20,174 39,174;80,155 67,154 60,161 61,174 80,174;60,161 47,156 40,161 39,174 61,174\nSwamp:28,154 31,142 19,133 11,139 17,156 19,157;47,156 48,143 41,138 31,142 28,154 40,161;67,154 61,139 48,143 47,156 60,161;80,135 63,136 61,139 67,154 80,155;17,156 11,139 0,138 0,160",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 34,
            "y": 153
          },
          {
            "id": "GravitasPedestal",
            "x": 36,
            "y": 153
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 20,
            "y": 80,
            "emitRate": 5454,
            "avgEmitRate": 2184,
            "idleTime": 226,
            "eruptionTime": 512,
            "dormancyCycles": 43.6,
            "activeCycles": 59.5
          },
          {
            "id": "slush_water",
            "x": 62,
            "y": 61,
            "emitRate": 5292,
            "avgEmitRate": 1842,
            "idleTime": 327,
            "eruptionTime": 359,
            "dormancyCycles": 35.2,
            "activeCycles": 69.7
          }
        ]
      },
      {
        "id": "NiobiumMoonlet",
        "offsetX": 798,
        "offsetY": 98,
        "sizeX": 64,
        "sizeY": 96,
        "worldTraits": [
          "FrozenCore"
        ],
        "biomePaths": "MagmaCore:64,47 64,33 47,31 40,41 44,48;64,63 46,64 44,76 47,80 64,80;42,59 44,48 40,41 34,40 21,50 21,56 24,61;21,56 21,50 12,39 0,40 0,61\nOilField:64,63 64,47 44,48 42,59 46,64;23,74 24,61 21,56 0,61 0,73 14,78;44,76 46,64 42,59 24,61 23,74 30,78\nFrozenWastes:47,80 44,76 30,78 29,96 45,96;64,80 47,80 45,96 64,96;14,78 0,73 0,96 13,96;30,78 23,74 14,78 13,96 29,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 48,
            "y": 53
          }
        ],
        "geysers": [
          {
            "id": "molten_niobium",
            "x": 27,
            "y": 64,
            "emitRate": 240467,
            "avgEmitRate": 953,
            "idleTime": 9106,
            "eruptionTime": 74,
            "dormancyCycles": 71.4,
            "activeCycles": 68.9
          }
        ]
      },
      {
        "id": "RegolithMoonlet",
        "offsetX": 864,
        "offsetY": 0,
        "sizeX": 160,
        "sizeY": 96,
        "worldTraits": [],
        "biomePaths": "Barren:129,61 122,50 107,52 104,62 112,71;112,76 112,71 104,62 92,66 89,75 101,85;69,59 70,58 65,46 48,45 45,55 52,64;156,69 141,57 130,61 135,77 142,79;51,75 52,64 45,55 36,57 31,70 34,76 42,79;12,77 19,69 14,58 0,55 0,77\nSandstone:135,77 130,61 129,61 112,71 112,76 124,85;89,75 92,66 84,56 70,58 69,59 71,75 80,80;36,57 24,47 14,58 19,69 31,70;71,75 69,59 52,64 51,75 61,81\nFrozenWastes:124,85 112,76 101,85 100,96 124,96;160,69 156,69 142,79 146,96 160,96;142,79 135,77 124,85 124,96 146,96;101,85 89,75 80,80 79,96 100,96;80,80 71,75 61,81 61,96 79,96;42,79 34,76 22,92 21,96 44,96;34,76 31,70 19,69 12,77 22,92;22,92 12,77 0,77 0,96 21,96;61,81 51,75 42,79 44,96 61,96",
        "pointsOfInterest": [
          {
            "id": "GravitasPedestal",
            "x": 103,
            "y": 80
          },
          {
            "id": "GravitasPedestal",
            "x": 107,
            "y": 80
          },
          {
            "id": "GeneShuffler",
            "x": 105,
            "y": 74
          }
        ],
        "geysers": [
          {
            "id": "hot_steam",
            "x": 72,
            "y": 83,
            "emitRate": 2212,
            "avgEmitRate": 673,
            "idleTime": 350,
            "eruptionTime": 392,
            "dormancyCycles": 51.2,
            "activeCycles": 69.5
          },
          {
            "id": "steam",
            "x": 47,
            "y": 88,
            "emitRate": 4721,
            "avgEmitRate": 1627,
            "idleTime": 131,
            "eruptionTime": 152,
            "dormancyCycles": 24.7,
            "activeCycles": 44.3
          }
        ]
      }
    ],
    "starMapEntriesVanilla": null,
    "starMapEntriesSpacedOut": [
      {
        "id": "MiniBadlands",
        "q": -1,
        "r": 0
      },
      {
        "id": "MiniRadioactiveOceanStart",
        "q": -4,
        "r": 0
      },
      {
        "id": "MiniFlippedWarp",
        "q": 2,
        "r": 1
      },
      {
        "id": "MiniMetallicSwampy",
        "q": 2,
        "r": -3
      },
      {
        "id": "MiniForestFrozen",
        "q": -2,
        "r": 3
      },
      {
        "id": "TundraMoonlet",
        "q": 7,
        "r": -7
      },
      {
        "id": "MarshyMoonlet",
        "q": 0,
        "r": 6
      },
      {
        "id": "MooMoonlet",
        "q": 7,
        "r": -1
      },
      {
        "id": "WaterMoonlet",
        "q": -8,
        "r": 8
      },
      {
        "id": "NiobiumMoonlet",
        "q": -8,
        "r": 1
      },
      {
        "id": "RegolithMoonlet",
        "q": -7,
        "r": -4
      },
      {
        "id": "TemporalTear",
        "q": 5,
        "r": -9
      },
      {
        "id": "HarvestableSpacePOI_SwampyOreField",
        "q": 0,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_OrganicMassField",
        "q": 3,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_FrozenOreField",
        "q": 8,
        "r": 4
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 8,
        "r": 3
      },
      {
        "id": "HarvestableSpacePOI_GildedAsteroidField",
        "q": -1,
        "r": 12
      },
      {
        "id": "HarvestableSpacePOI_HeliumCloud",
        "q": -4,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": -4,
        "r": -6
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveAsteroidField",
        "q": -11,
        "r": 5
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 1,
        "r": -12
      },
      {
        "id": "HarvestableSpacePOI_ForestyOreField",
        "q": -4,
        "r": 6
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 6,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 7,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 8,
        "r": -5
      },
      {
        "id": "HarvestableSpacePOI_InterstellarOcean",
        "q": 6,
        "r": -4
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": -10,
        "r": 0
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": -9,
        "r": -1
      },
      {
        "id": "HarvestableSpacePOI_RadioactiveGasCloud",
        "q": 4,
        "r": 7
      },
      {
        "id": "HarvestableSpacePOI_SaltyAsteroidField",
        "q": 4,
        "r": 8
      },
      {
        "id": "HarvestableSpacePOI_OxygenRichAsteroidField",
        "q": 3,
        "r": 9
      },
      {
        "id": "HarvestableSpacePOI_IceAsteroidField",
        "q": 10,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GlimmeringAsteroidField",
        "q": 9,
        "r": -10
      },
      {
        "id": "HarvestableSpacePOI_GasGiantCloud",
        "q": 11,
        "r": -11
      },
      {
        "id": "HarvestableSpacePOI_OilyAsteroidField",
        "q": 12,
        "r": -3
      },
      {
        "id": "HarvestableSpacePOI_SatelliteField",
        "q": -10,
        "r": 12
      },
      {
        "id": "ArtifactSpacePOI_RussellsTeapot",
        "q": -3,
        "r": 10
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation7",
        "q": 12,
        "r": -8
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation3",
        "q": 0,
        "r": -8
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation8",
        "q": -11,
        "r": 9
      },
      {
        "id": "ArtifactSpacePOI_GravitasSpaceStation5",
        "q": -6,
        "r": 4
      }
    ]
  },
  {
    "coordinate": "CER-A-487061744-0-0-0",
    "cluster": "CER-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "CeresBaseGameAsteroid",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "GeoActive",
          "Geodes",
          "GlaciersLarge"
        ],
        "biomePaths": "IceCaves:146,170 154,159 153,140 152,138 132,130 119,136 112,151 121,169 128,173;175,145 153,140 154,159 172,160;151,183 146,170 128,173 129,187 137,192;129,187 128,173 121,169 108,176 108,190 113,193;121,169 112,151 104,152 96,166 108,176;132,130 133,120 122,112 110,118 109,127 119,136;156,118 150,113 133,120 132,130 152,138;112,151 119,136 109,127 94,137 104,152\nCarrotQuarry:177,179 179,170 172,160 154,159 146,170 151,183 160,187;191,196 177,179 160,187 164,209 180,212;226,156 232,147 225,128 212,127 200,145 205,156;202,195 210,184 200,165 179,170 177,179 191,196;195,87 197,74 180,63 166,71 165,87 177,95;159,237 156,217 140,213 132,219 133,241 153,243;133,241 132,219 115,215 107,221 106,241 129,244;182,250 177,239 159,237 153,243 156,264 172,267;106,241 107,221 86,214 79,221 81,241 104,243;150,113 150,94 136,87 124,93 122,112 133,120;136,87 137,70 121,60 106,74 107,84 124,93;109,127 110,118 90,101 78,117 86,136 94,137;107,84 106,74 91,65 79,72 77,89 90,101;203,297 205,292 199,276 177,276 172,290 182,302;256,93 256,76 230,71 225,75 224,95 228,98;86,136 78,117 60,116 52,131 56,140 75,145;60,116 53,102 31,105 27,119 30,127 52,131;53,102 57,95 47,77 31,75 25,94 31,105;224,95 225,75 206,69 197,74 195,87 207,99;86,214 86,197 80,192 60,197 56,208 60,217 79,221;60,197 51,181 30,185 26,195 31,207 56,208;77,89 79,72 60,61 47,77 57,95;30,230 26,220 0,219 0,243 23,244\nRust:226,183 233,173 226,156 205,156 200,165 210,184;256,173 256,146 232,147 226,156 233,173;212,211 202,195 191,196 180,212 186,224 203,226;202,115 207,99 195,87 177,95 177,115 181,118;256,225 234,225 227,240 232,250 256,251;209,238 203,226 186,224 177,239 182,250 201,253;226,289 231,274 226,266 206,265 199,276 205,292;129,265 129,244 106,241 104,243 103,265 122,271;172,290 177,276 172,267 156,264 148,270 150,289 156,293;123,291 122,271 103,265 97,270 97,292 104,297;30,127 27,119 0,118 0,144 23,144;77,167 75,145 56,140 46,156 55,171;56,244 51,233 30,230 23,244 29,256 48,258;90,101 90,101 77,89 57,95 53,102 60,116 78,117;60,217 56,208 31,207 26,220 30,230 51,233;29,256 23,244 0,243 0,270 21,270;26,195 30,185 22,170 0,171 0,196;21,297 29,284 21,270 0,270 0,298;78,266 76,246 56,244 48,258 53,269 71,272;31,207 26,195 0,196 0,219 26,220\nFrozenWastes:200,165 205,156 200,145 181,140 175,145 172,160 179,170;212,127 202,115 181,118 181,140 200,145;256,146 256,122 231,121 225,128 232,147;164,209 160,187 151,183 137,192 140,213 156,217;256,199 256,173 233,173 226,183 234,199;181,140 181,118 177,115 156,118 152,138 153,140 175,145;231,121 228,98 224,95 207,99 202,115 212,127 225,128;140,213 137,192 129,187 113,193 115,215 132,219;115,215 113,193 108,190 86,197 86,214 107,221;108,190 108,176 96,166 80,169 80,192 86,197;177,115 177,95 165,87 150,94 150,113 156,118;122,112 124,93 107,84 90,101 90,101 110,118;56,140 52,131 30,127 23,144 30,156 46,156;96,166 104,152 94,137 86,136 75,145 77,167 80,169;227,211 234,199 226,183 210,184 202,195 212,211;22,170 30,156 23,144 0,144 0,171\nOcean:234,225 227,211 212,211 203,226 209,238 227,240;186,224 180,212 164,209 156,217 159,237 177,239;256,93 228,98 231,121 256,122;256,199 234,199 227,211 234,225 256,225;256,251 232,250 226,266 231,274 256,275;206,265 201,253 182,250 172,267 177,276 199,276;156,264 153,243 133,241 129,244 129,265 148,270;256,298 256,275 231,274 226,289 233,299;103,265 104,243 81,241 76,246 78,266 97,270;81,241 79,221 60,217 51,233 56,244 76,246;31,105 25,94 0,94 0,118 27,119;232,250 227,240 209,238 201,253 206,265 226,266;80,192 80,169 77,167 55,171 51,181 60,197;51,181 55,171 46,156 30,156 22,170 30,185;150,289 148,270 129,265 122,271 123,291 130,296\nForest:155,315 156,293 150,289 130,296 131,315 141,321;131,315 130,296 123,291 104,297 106,317 115,322;178,318 182,302 172,290 156,293 155,315 168,322;233,299 226,289 205,292 203,297 210,317 226,320;256,298 233,299 226,320 227,322 256,324;210,317 203,297 182,302 178,318 197,330;106,317 104,297 97,292 79,298 81,320 87,324;81,320 79,298 74,295 53,300 51,307 60,326;97,292 97,270 78,266 71,272 74,295 79,298;74,295 71,272 53,269 43,284 53,300;43,284 53,269 48,258 29,256 21,270 29,284;51,307 53,300 43,284 29,284 21,297 29,312;29,312 21,297 0,298 0,323 25,324\nOilField:141,341 141,321 131,315 115,322 115,342 126,347;256,345 256,324 227,322 225,342 231,347;198,341 197,330 178,318 168,322 167,343 178,348;225,342 227,322 226,320 210,317 197,330 198,341 205,345;167,343 168,322 155,315 141,321 141,341 155,347;115,342 115,322 106,317 87,324 88,341 102,348;88,341 87,324 81,320 60,326 57,332 63,348 70,351;60,326 51,307 29,312 25,324 27,330 57,332;20,349 27,330 25,324 0,323 0,352\nSugarWoods:165,87 166,71 152,62 137,70 136,87 150,94;152,62 152,48 138,38 121,50 121,60 137,70;121,60 121,50 107,41 91,50 91,65 106,74;206,69 208,46 200,40 181,47 180,63 197,74;180,63 181,47 169,38 152,48 152,62 166,71;91,65 91,50 78,41 59,54 60,61 79,72;256,45 232,47 230,71 256,76;230,71 232,47 228,43 208,46 206,69 225,75;25,94 31,75 28,71 0,70 0,94;31,52 24,45 0,48 0,70 28,71;60,61 59,54 50,47 31,52 28,71 31,75 47,77\nMagmaCore:178,348 167,343 155,347 151,384 181,384;126,347 115,342 102,348 99,384 129,384;155,347 141,341 126,347 129,384 151,384;102,348 88,341 70,351 77,384 99,384;231,347 225,342 205,345 204,384 229,384;205,345 198,341 178,348 181,384 204,384;63,348 57,332 27,330 20,349 37,367;256,345 231,347 229,384 256,384;37,367 20,349 0,352 0,384 37,384;70,351 63,348 37,367 37,384 77,384",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 133,
            "y": 153
          },
          {
            "id": "MassiveHeatSink",
            "x": 83,
            "y": 166
          },
          {
            "id": "GeneShuffler",
            "x": 199,
            "y": 230
          },
          {
            "id": "GeneShuffler",
            "x": 165,
            "y": 133
          }
        ],
        "geysers": [
          {
            "id": "salt_water",
            "x": 23,
            "y": 107,
            "emitRate": 11483,
            "avgEmitRate": 2596,
            "idleTime": 360,
            "eruptionTime": 221,
            "dormancyCycles": 53.7,
            "activeCycles": 78.5
          },
          {
            "id": "OilWell",
            "x": 200,
            "y": 330,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 130,
            "y": 322,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 170,
            "y": 339,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "filthy_water",
            "x": 79,
            "y": 291,
            "emitRate": 8504,
            "avgEmitRate": 2897,
            "idleTime": 356,
            "eruptionTime": 431,
            "dormancyCycles": 41.5,
            "activeCycles": 68.4
          },
          {
            "id": "oil_drip",
            "x": 80,
            "y": 214,
            "emitRate": 328,
            "avgEmitRate": 218,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.4
          },
          {
            "id": "slush_salt_water",
            "x": 125,
            "y": 224,
            "emitRate": 4333,
            "avgEmitRate": 1471,
            "idleTime": 153,
            "eruptionTime": 181,
            "dormancyCycles": 44.5,
            "activeCycles": 74.1
          },
          {
            "id": "steam",
            "x": 50,
            "y": 226,
            "emitRate": 3336,
            "avgEmitRate": 1375,
            "idleTime": 229,
            "eruptionTime": 354,
            "dormancyCycles": 58.3,
            "activeCycles": 123.2
          },
          {
            "id": "small_volcano",
            "x": 158,
            "y": 251,
            "emitRate": 118681,
            "avgEmitRate": 571,
            "idleTime": 9633,
            "eruptionTime": 80,
            "dormancyCycles": 57.2,
            "activeCycles": 80.7
          },
          {
            "id": "filthy_water",
            "x": 212,
            "y": 334,
            "emitRate": 8980,
            "avgEmitRate": 2675,
            "idleTime": 251,
            "eruptionTime": 273,
            "dormancyCycles": 53.5,
            "activeCycles": 71.3
          },
          {
            "id": "molten_copper",
            "x": 98,
            "y": 238,
            "emitRate": 5670,
            "avgEmitRate": 298,
            "idleTime": 528,
            "eruptionTime": 56,
            "dormancyCycles": 61.3,
            "activeCycles": 74.2
          },
          {
            "id": "filthy_water",
            "x": 148,
            "y": 71,
            "emitRate": 8412,
            "avgEmitRate": 2499,
            "idleTime": 302,
            "eruptionTime": 369,
            "dormancyCycles": 83.6,
            "activeCycles": 98.4
          },
          {
            "id": "molten_gold",
            "x": 171,
            "y": 284,
            "emitRate": 8065,
            "avgEmitRate": 335,
            "idleTime": 850,
            "eruptionTime": 65,
            "dormancyCycles": 50,
            "activeCycles": 69.4
          },
          {
            "id": "big_volcano",
            "x": 55,
            "y": 79,
            "emitRate": 303542,
            "avgEmitRate": 1195,
            "idleTime": 7536,
            "eruptionTime": 49,
            "dormancyCycles": 47,
            "activeCycles": 72.2
          },
          {
            "id": "slimy_po2",
            "x": 236,
            "y": 116,
            "emitRate": 320,
            "avgEmitRate": 110,
            "idleTime": 228,
            "eruptionTime": 299,
            "dormancyCycles": 37.7,
            "activeCycles": 58.4
          },
          {
            "id": "liquid_co2",
            "x": 51,
            "y": 318,
            "emitRate": 596,
            "avgEmitRate": 163,
            "idleTime": 407,
            "eruptionTime": 293,
            "dormancyCycles": 44.5,
            "activeCycles": 83.6
          },
          {
            "id": "chlorine_gas",
            "x": 161,
            "y": 261,
            "emitRate": 262,
            "avgEmitRate": 103,
            "idleTime": 207,
            "eruptionTime": 493,
            "dormancyCycles": 44.5,
            "activeCycles": 56.6
          },
          {
            "id": "big_volcano",
            "x": 203,
            "y": 49,
            "emitRate": 262462,
            "avgEmitRate": 1329,
            "idleTime": 8471,
            "eruptionTime": 64,
            "dormancyCycles": 38.6,
            "activeCycles": 80.1
          },
          {
            "id": "chlorine_gas",
            "x": 212,
            "y": 201,
            "emitRate": 360,
            "avgEmitRate": 107,
            "idleTime": 260,
            "eruptionTime": 273,
            "dormancyCycles": 66.8,
            "activeCycles": 91.7
          },
          {
            "id": "liquid_co2",
            "x": 68,
            "y": 268,
            "emitRate": 785,
            "avgEmitRate": 130,
            "idleTime": 388,
            "eruptionTime": 181,
            "dormancyCycles": 73.6,
            "activeCycles": 79.7
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "RockyAsteroid",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 4
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "IcyDwarf",
        "distance": 5
      },
      {
        "id": "DustyMoon",
        "distance": 6
      },
      {
        "id": "TerraPlanet",
        "distance": 7
      },
      {
        "id": "GasGiant",
        "distance": 8
      },
      {
        "id": "GasGiant",
        "distance": 9
      },
      {
        "id": "HeliumGiant",
        "distance": 9
      },
      {
        "id": "RustPlanet",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "RustPlanet",
        "distance": 10
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "OrganicDwarf",
        "distance": 12
      },
      {
        "id": "ForestPlanet",
        "distance": 12
      },
      {
        "id": "GasGiant",
        "distance": 14
      },
      {
        "id": "OilyAsteriod",
        "distance": 15
      },
      {
        "id": "GoldAsteroid",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "BAD-A-769743522-0-0-0",
    "cluster": "BAD-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "Badlands",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "SubsurfaceOcean",
          "GeoActive"
        ],
        "biomePaths": "Sandstone:156,187 154,179 130,167 110,182 120,212 138,212;38,326 42,306 26,289 0,295 0,321 25,330;34,264 39,240 28,225 0,225 0,263 29,268;40,208 34,187 27,183 0,187 0,225 28,225;41,88 28,69 0,70 0,107 28,109;52,359 47,331 38,326 25,330 12,357 31,374;65,302 67,271 62,267 34,264 29,268 26,289 42,306;67,152 69,135 49,119 34,120 26,131 29,155 56,161;84,358 85,333 67,321 47,331 52,359 66,367;100,213 88,194 69,194 55,211 63,236 65,237 84,239;88,194 99,181 87,153 67,152 56,161 55,178 69,194;91,144 75,133 69,135 67,152 87,153;110,322 112,316 97,291 66,303 67,321 85,333;125,271 126,256 113,244 90,245 81,270 97,289;140,256 149,249 155,233 138,212 120,212 117,215 113,244 126,256;130,167 128,147 111,137 91,144 87,153 99,181 110,182;111,137 117,124 105,100 84,100 80,104 75,133 91,144;118,360 124,343 110,322 85,333 84,358 97,368;143,304 140,278 125,271 97,289 97,291 112,316;134,139 126,123 117,124 111,137 128,147;156,329 151,308 143,304 112,316 110,322 124,343 145,344;140,277 140,256 126,256 125,271 140,278;189,357 185,335 156,329 145,344 148,359 163,370;172,251 166,234 155,233 149,249 166,256;167,169 171,147 157,134 134,139 128,147 130,167 154,179;193,329 193,303 167,295 151,308 156,329 185,335;185,213 175,191 156,187 138,212 155,233 166,234;210,242 208,222 189,212 185,213 166,234 172,251 195,257;188,166 179,146 171,147 167,169 179,175;214,217 204,195 200,196 189,212 208,222;189,212 200,196 179,187 175,191 185,213;179,187 179,175 167,169 154,179 156,187 175,191\nMagmaCore:31,374 12,357 0,359 0,384 31,384;66,367 52,359 31,374 31,384 67,384;97,368 84,358 66,367 67,384 96,384;130,373 118,360 97,368 96,384 130,384;163,370 148,359 130,373 130,384 164,384;191,358 189,357 163,370 164,384 194,384;210,355 191,358 194,384 216,384;234,358 228,351 214,351 210,355 216,384 229,384;256,357 234,358 229,384 256,384\nOilField:12,357 25,330 0,321 0,359;67,321 66,303 65,302 42,306 38,326 47,331;148,359 145,344 124,343 118,360 130,373;210,355 214,351 207,331 193,329 185,335 189,357 191,358;228,351 235,331 228,321 219,320 207,331 214,351;219,320 210,302 196,301 193,303 193,329 207,331;256,357 256,332 235,331 228,351 234,358;240,303 231,291 221,290 210,302 219,320 228,321;256,304 240,303 228,321 235,331 256,332\nToxicJungle:26,289 29,268 0,263 0,295;27,183 26,157 0,157 0,187;34,120 28,109 0,107 0,130 26,131;97,291 97,289 81,270 67,271 65,302 66,303;120,212 110,182 99,181 88,194 100,213 117,215;75,133 80,104 59,101 49,119 69,135;110,94 108,77 91,71 81,79 84,100 105,100;179,146 186,138 181,117 168,113 160,119 157,134 171,147;210,158 203,139 186,138 179,146 188,166 203,167;204,195 210,188 203,167 188,166 179,175 179,187 200,196;228,187 232,182 228,158 210,158 203,167 210,188;256,270 246,270 231,291 240,303 256,304;256,270 256,239 236,239 227,248 229,258 246,270;256,182 256,155 232,155 228,158 232,182\nFrozenWastes:29,155 26,131 0,130 0,157 26,157;65,237 63,236 39,240 34,264 62,267;63,236 55,211 40,208 28,225 39,240;69,194 55,178 34,187 40,208 55,211;49,119 59,101 53,89 41,88 28,109 34,120;81,270 90,245 84,239 65,237 62,267 67,271;84,100 81,79 64,76 53,89 59,101 80,104;126,123 138,108 137,100 110,94 105,100 117,124;167,295 166,277 140,277 140,278 143,304 151,308;166,277 166,277 166,256 149,249 140,256 140,277;193,276 198,270 195,257 172,251 166,256 166,277;196,107 189,86 175,85 166,94 168,113 181,117;221,290 213,273 198,270 193,276 196,301 210,302;212,129 203,109 196,107 181,117 186,138 203,139;223,96 220,84 200,77 189,86 196,107 203,109;246,270 229,258 213,273 221,290 231,291;236,239 229,216 214,217 208,222 210,242 227,248;229,216 234,210 228,187 210,188 204,195 214,217;232,155 229,130 212,129 203,139 210,158 228,158;256,210 234,210 229,216 236,239 256,239;256,210 256,182 232,182 228,187 234,210;256,98 231,101 231,128 256,128\nOcean:28,69 37,50 32,40 0,40 0,70;64,76 56,51 37,50 28,69 41,88 53,89;91,71 89,47 65,41 56,51 64,76 81,79;124,63 123,46 95,40 89,47 91,71 108,77;145,89 144,74 124,63 108,77 110,94 137,100;163,60 164,58 153,40 126,42 123,46 124,63 144,74;200,77 198,55 182,48 164,58 163,60 175,85 189,86;235,65 229,51 211,45 198,55 200,77 220,84;256,98 256,68 235,65 220,84 223,96 231,101\nRust:55,178 56,161 29,155 26,157 27,183 34,187;113,244 117,215 100,213 84,239 90,245;157,134 160,119 138,108 126,123 134,139;168,113 166,94 145,89 137,100 138,108 160,119;175,85 163,60 144,74 145,89 166,94;196,301 193,276 166,277 166,277 167,295 193,303;229,258 227,248 210,242 195,257 198,270 213,273;231,128 231,101 223,96 203,109 212,129 229,130;256,128 231,128 229,130 232,155 256,155",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 130,
            "y": 190
          },
          {
            "id": "MassiveHeatSink",
            "x": 148,
            "y": 292
          },
          {
            "id": "MassiveHeatSink",
            "x": 218,
            "y": 242
          },
          {
            "id": "MassiveHeatSink",
            "x": 186,
            "y": 109
          },
          {
            "id": "GeneShuffler",
            "x": 75,
            "y": 130
          },
          {
            "id": "GeneShuffler",
            "x": 225,
            "y": 271
          },
          {
            "id": "GeneShuffler",
            "x": 52,
            "y": 250
          },
          {
            "id": "GeneShuffler",
            "x": 49,
            "y": 331
          },
          {
            "id": "GeneShuffler",
            "x": 165,
            "y": 107
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 188,
            "y": 167,
            "emitRate": 5196,
            "avgEmitRate": 1552,
            "idleTime": 359,
            "eruptionTime": 353,
            "dormancyCycles": 37.6,
            "activeCycles": 57
          },
          {
            "id": "methane",
            "x": 70,
            "y": 294,
            "emitRate": 429,
            "avgEmitRate": 122,
            "idleTime": 455,
            "eruptionTime": 330,
            "dormancyCycles": 49.9,
            "activeCycles": 103
          },
          {
            "id": "OilWell",
            "x": 204,
            "y": 321,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 214,
            "y": 341,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 125,
            "y": 348,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_co2",
            "x": 204,
            "y": 254,
            "emitRate": 286,
            "avgEmitRate": 105,
            "idleTime": 325,
            "eruptionTime": 525,
            "dormancyCycles": 77.1,
            "activeCycles": 113.2
          },
          {
            "id": "molten_gold",
            "x": 123,
            "y": 90,
            "emitRate": 7849,
            "avgEmitRate": 386,
            "idleTime": 684,
            "eruptionTime": 53,
            "dormancyCycles": 43.4,
            "activeCycles": 95.6
          },
          {
            "id": "molten_copper",
            "x": 80,
            "y": 72,
            "emitRate": 9852,
            "avgEmitRate": 339,
            "idleTime": 886,
            "eruptionTime": 52,
            "dormancyCycles": 43.9,
            "activeCycles": 71.7
          },
          {
            "id": "slush_salt_water",
            "x": 96,
            "y": 313,
            "emitRate": 6412,
            "avgEmitRate": 1219,
            "idleTime": 533,
            "eruptionTime": 315,
            "dormancyCycles": 55.6,
            "activeCycles": 58.3
          },
          {
            "id": "methane",
            "x": 183,
            "y": 290,
            "emitRate": 826,
            "avgEmitRate": 118,
            "idleTime": 376,
            "eruptionTime": 110,
            "dormancyCycles": 55,
            "activeCycles": 94.7
          },
          {
            "id": "hot_water",
            "x": 160,
            "y": 339,
            "emitRate": 8190,
            "avgEmitRate": 2523,
            "idleTime": 178,
            "eruptionTime": 219,
            "dormancyCycles": 53.7,
            "activeCycles": 67.9
          },
          {
            "id": "chlorine_gas",
            "x": 197,
            "y": 239,
            "emitRate": 379,
            "avgEmitRate": 95,
            "idleTime": 267,
            "eruptionTime": 253,
            "dormancyCycles": 66.8,
            "activeCycles": 71.5
          },
          {
            "id": "salt_water",
            "x": 55,
            "y": 71,
            "emitRate": 8955,
            "avgEmitRate": 3095,
            "idleTime": 381,
            "eruptionTime": 435,
            "dormancyCycles": 37.2,
            "activeCycles": 68.7
          },
          {
            "id": "big_volcano",
            "x": 232,
            "y": 309,
            "emitRate": 215732,
            "avgEmitRate": 1119,
            "idleTime": 7068,
            "eruptionTime": 65,
            "dormancyCycles": 45,
            "activeCycles": 59
          },
          {
            "id": "liquid_co2",
            "x": 222,
            "y": 119,
            "emitRate": 420,
            "avgEmitRate": 150,
            "idleTime": 304,
            "eruptionTime": 442,
            "dormancyCycles": 53.1,
            "activeCycles": 81.4
          },
          {
            "id": "slimy_po2",
            "x": 148,
            "y": 81,
            "emitRate": 338,
            "avgEmitRate": 103,
            "idleTime": 237,
            "eruptionTime": 275,
            "dormancyCycles": 75.5,
            "activeCycles": 99.9
          },
          {
            "id": "hot_steam",
            "x": 236,
            "y": 107,
            "emitRate": 1433,
            "avgEmitRate": 601,
            "idleTime": 154,
            "eruptionTime": 433,
            "dormancyCycles": 43.6,
            "activeCycles": 57.4
          },
          {
            "id": "molten_iron",
            "x": 78,
            "y": 313,
            "emitRate": 9382,
            "avgEmitRate": 385,
            "idleTime": 765,
            "eruptionTime": 48,
            "dormancyCycles": 38.8,
            "activeCycles": 85.9
          },
          {
            "id": "hot_co2",
            "x": 136,
            "y": 119,
            "emitRate": 320,
            "avgEmitRate": 111,
            "idleTime": 285,
            "eruptionTime": 352,
            "dormancyCycles": 40.6,
            "activeCycles": 69.1
          },
          {
            "id": "chlorine_gas",
            "x": 143,
            "y": 319,
            "emitRate": 314,
            "avgEmitRate": 112,
            "idleTime": 379,
            "eruptionTime": 535,
            "dormancyCycles": 33.8,
            "activeCycles": 52.9
          },
          {
            "id": "slimy_po2",
            "x": 44,
            "y": 310,
            "emitRate": 291,
            "avgEmitRate": 127,
            "idleTime": 232,
            "eruptionTime": 392,
            "dormancyCycles": 48.7,
            "activeCycles": 109.1
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "RockyAsteroid",
        "distance": 3
      },
      {
        "id": "ForestPlanet",
        "distance": 3
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "SaltDwarf",
        "distance": 4
      },
      {
        "id": "MetallicAsteroid",
        "distance": 5
      },
      {
        "id": "RockyAsteroid",
        "distance": 5
      },
      {
        "id": "RustPlanet",
        "distance": 8
      },
      {
        "id": "HeliumGiant",
        "distance": 9
      },
      {
        "id": "VolcanoPlanet",
        "distance": 10
      },
      {
        "id": "TerraPlanet",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 11
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "GasGiant",
        "distance": 14
      },
      {
        "id": "HeliumGiant",
        "distance": 15
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "SNDST-A-2131635252-0-0-0",
    "cluster": "SNDST-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "SandstoneDefault",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [],
        "biomePaths": "Sandstone:151,199 154,191 152,175 134,161 122,161 104,175 102,191 105,199 128,212;105,199 102,191 76,189 70,202 71,206 97,213;104,175 87,167 74,175 76,189 102,191;128,212 105,199 97,213 100,224 128,226;134,161 141,141 128,129 115,141 122,161;156,224 159,213 151,199 128,212 128,226 128,226;185,206 186,202 180,189 154,191 151,199 159,213;180,189 182,175 169,167 152,175 154,191\nMagmaCore:30,361 15,347 0,347 0,384 28,384;62,353 56,350 30,361 28,384 64,384;95,360 77,348 62,353 64,384 94,384;128,355 115,349 95,360 94,384 128,384;161,360 141,349 128,355 128,384 162,384;194,353 179,348 161,360 162,384 192,384;226,361 200,350 194,353 192,384 228,384;256,347 241,347 226,361 228,384 256,384\nOilField:15,347 32,322 24,308 0,307 0,347;56,350 48,326 32,322 15,347 30,361;77,348 83,326 72,312 64,311 48,326 56,350 62,353;110,323 114,318 110,297 88,292 72,312 83,326;115,349 110,323 83,326 77,348 95,360;141,349 146,323 142,318 114,318 110,323 115,349 128,355;179,348 173,326 146,323 141,349 161,360;184,312 168,292 146,297 142,318 146,323 173,326;200,350 208,326 192,311 184,312 173,326 179,348 194,353;241,347 224,322 208,326 200,350 226,361;256,307 232,308 224,322 241,347 256,347\nToxicJungle:34,290 24,274 0,274 0,307 24,308;36,226 31,214 0,212 0,241 29,243;35,183 36,179 23,154 0,154 0,187;64,270 59,259 34,255 24,274 34,290 50,290;71,206 70,202 39,194 31,214 36,226 62,227;76,189 74,175 58,168 36,179 35,183 39,194 70,202;91,137 92,115 64,103 60,108 61,138 87,140;87,75 87,73 73,57 42,66 41,68 65,96;122,161 115,141 91,137 87,140 87,167 104,175;106,103 107,97 87,75 65,96 64,103 92,115;128,247 128,226 128,226 100,224 91,241 99,258 109,260;150,103 149,97 128,82 107,97 106,103 128,123;165,137 164,115 150,103 128,123 128,129 141,141;192,311 206,290 192,270 174,275 168,292 184,312;198,168 200,141 195,138 169,140 169,167 182,175;222,290 232,274 222,255 197,259 192,270 206,290;217,194 221,183 220,179 198,168 182,175 180,189 186,202;256,274 256,241 227,243 222,255 232,274;256,212 256,187 221,183 217,194 225,214;256,121 256,90 230,89 222,108 230,121\nFrozenWastes:34,255 29,243 0,241 0,274 24,274;39,194 35,183 0,187 0,212 31,214;34,108 26,89 0,90 0,121 26,121;142,318 146,297 136,286 120,286 110,297 114,318;168,292 174,275 157,258 147,260 136,286 146,297;233,154 222,139 200,141 198,168 220,179;256,154 233,154 220,179 221,183 256,187;256,121 230,121 222,139 233,154 256,154\nBoggyMarsh:23,154 34,139 26,121 0,121 0,154;64,311 50,290 34,290 24,308 32,322 48,326;60,108 64,103 65,96 41,68 39,69 26,89 34,108;88,292 82,275 64,270 50,290 64,311 72,312;100,224 97,213 71,206 62,227 68,240 91,241;87,167 87,140 61,138 56,141 58,168 74,175;99,258 91,241 68,240 59,259 64,270 82,275;120,286 109,260 99,258 82,275 88,292 110,297;128,129 128,123 106,103 92,115 91,137 115,141;136,286 147,260 128,247 109,260 120,286;157,258 165,241 156,224 128,226 128,247 147,260;169,167 169,140 165,137 141,141 134,161 152,175;188,240 194,227 185,206 159,213 156,224 165,241;195,138 196,108 192,103 164,115 165,137 169,140;220,226 225,214 217,194 186,202 185,206 194,227;222,139 230,121 222,108 196,108 195,138 200,141\nOcean:68,240 62,227 36,226 29,243 34,255 59,259;58,168 56,141 34,139 23,154 36,179;61,138 60,108 34,108 26,121 34,139 56,141;192,270 197,259 188,240 165,241 157,258 174,275;192,103 191,96 169,75 149,97 150,103 164,115;215,68 214,66 183,57 169,73 169,75 191,96;224,322 232,308 222,290 206,290 192,311 208,326;222,255 227,243 220,226 194,227 188,240 197,259;230,89 217,69 215,68 191,96 192,103 196,108 222,108;256,307 256,274 232,274 222,290 232,308;256,241 256,212 225,214 220,226 227,243",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 127,
            "y": 187
          },
          {
            "id": "MassiveHeatSink",
            "x": 231,
            "y": 181
          },
          {
            "id": "MassiveHeatSink",
            "x": 231,
            "y": 146
          },
          {
            "id": "MassiveHeatSink",
            "x": 24,
            "y": 103
          },
          {
            "id": "GeneShuffler",
            "x": 133,
            "y": 247
          },
          {
            "id": "GeneShuffler",
            "x": 181,
            "y": 164
          },
          {
            "id": "GeneShuffler",
            "x": 229,
            "y": 109
          },
          {
            "id": "GeneShuffler",
            "x": 93,
            "y": 345
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 58,
            "y": 173,
            "emitRate": 9841,
            "avgEmitRate": 1442,
            "idleTime": 473,
            "eruptionTime": 162,
            "dormancyCycles": 37.8,
            "activeCycles": 51
          },
          {
            "id": "chlorine_gas",
            "x": 236,
            "y": 210,
            "emitRate": 308,
            "avgEmitRate": 115,
            "idleTime": 430,
            "eruptionTime": 468,
            "dormancyCycles": 28.6,
            "activeCycles": 73
          },
          {
            "id": "steam",
            "x": 139,
            "y": 276,
            "emitRate": 3742,
            "avgEmitRate": 1179,
            "idleTime": 202,
            "eruptionTime": 236,
            "dormancyCycles": 49.3,
            "activeCycles": 69.3
          },
          {
            "id": "methane",
            "x": 195,
            "y": 222,
            "emitRate": 344,
            "avgEmitRate": 87,
            "idleTime": 280,
            "eruptionTime": 290,
            "dormancyCycles": 79.2,
            "activeCycles": 79.3
          },
          {
            "id": "salt_water",
            "x": 203,
            "y": 300,
            "emitRate": 10775,
            "avgEmitRate": 3370,
            "idleTime": 366,
            "eruptionTime": 409,
            "dormancyCycles": 69.7,
            "activeCycles": 101.2
          },
          {
            "id": "OilWell",
            "x": 116,
            "y": 335,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 209,
            "y": 334,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 55,
            "y": 335,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "molten_gold",
            "x": 201,
            "y": 275,
            "emitRate": 9057,
            "avgEmitRate": 375,
            "idleTime": 754,
            "eruptionTime": 51,
            "dormancyCycles": 53.6,
            "activeCycles": 100.3
          },
          {
            "id": "oil_drip",
            "x": 30,
            "y": 192,
            "emitRate": 324,
            "avgEmitRate": 170,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.2
          },
          {
            "id": "hot_hydrogen",
            "x": 226,
            "y": 228,
            "emitRate": 899,
            "avgEmitRate": 102,
            "idleTime": 561,
            "eruptionTime": 138,
            "dormancyCycles": 44,
            "activeCycles": 60
          },
          {
            "id": "hot_steam",
            "x": 36,
            "y": 328,
            "emitRate": 2367,
            "avgEmitRate": 617,
            "idleTime": 366,
            "eruptionTime": 293,
            "dormancyCycles": 46.5,
            "activeCycles": 66.1
          },
          {
            "id": "hot_po2",
            "x": 205,
            "y": 115,
            "emitRate": 401,
            "avgEmitRate": 119,
            "idleTime": 384,
            "eruptionTime": 312,
            "dormancyCycles": 52.1,
            "activeCycles": 102.4
          },
          {
            "id": "filthy_water",
            "x": 171,
            "y": 321,
            "emitRate": 9073,
            "avgEmitRate": 2814,
            "idleTime": 259,
            "eruptionTime": 276,
            "dormancyCycles": 53.3,
            "activeCycles": 80.5
          },
          {
            "id": "liquid_co2",
            "x": 76,
            "y": 103,
            "emitRate": 491,
            "avgEmitRate": 125,
            "idleTime": 280,
            "eruptionTime": 290,
            "dormancyCycles": 79.2,
            "activeCycles": 79.3
          },
          {
            "id": "hot_co2",
            "x": 22,
            "y": 315,
            "emitRate": 334,
            "avgEmitRate": 123,
            "idleTime": 260,
            "eruptionTime": 291,
            "dormancyCycles": 27.3,
            "activeCycles": 62.8
          },
          {
            "id": "big_volcano",
            "x": 17,
            "y": 159,
            "emitRate": 289356,
            "avgEmitRate": 1051,
            "idleTime": 9295,
            "eruptionTime": 63,
            "dormancyCycles": 52.8,
            "activeCycles": 61
          },
          {
            "id": "hot_hydrogen",
            "x": 77,
            "y": 257,
            "emitRate": 339,
            "avgEmitRate": 126,
            "idleTime": 290,
            "eruptionTime": 318,
            "dormancyCycles": 30.2,
            "activeCycles": 74.5
          },
          {
            "id": "small_volcano",
            "x": 184,
            "y": 83,
            "emitRate": 179804,
            "avgEmitRate": 634,
            "idleTime": 10699,
            "eruptionTime": 58,
            "dormancyCycles": 35.5,
            "activeCycles": 67
          },
          {
            "id": "hot_co2",
            "x": 204,
            "y": 162,
            "emitRate": 306,
            "avgEmitRate": 98,
            "idleTime": 279,
            "eruptionTime": 381,
            "dormancyCycles": 59.1,
            "activeCycles": 73.4
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 3
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 3
      },
      {
        "id": "RockyAsteroid",
        "distance": 3
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "MetallicAsteroid",
        "distance": 4
      },
      {
        "id": "SaltDwarf",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "IcyDwarf",
        "distance": 6
      },
      {
        "id": "TerraPlanet",
        "distance": 7
      },
      {
        "id": "VolcanoPlanet",
        "distance": 7
      },
      {
        "id": "VolcanoPlanet",
        "distance": 7
      },
      {
        "id": "GasGiant",
        "distance": 8
      },
      {
        "id": "HeliumGiant",
        "distance": 9
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "ForestPlanet",
        "distance": 12
      },
      {
        "id": "IceGiant",
        "distance": 13
      },
      {
        "id": "RedDwarf",
        "distance": 14
      },
      {
        "id": "GasGiant",
        "distance": 14
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "VolcanoPlanet",
        "distance": 16
      },
      {
        "id": "VolcanoPlanet",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "FRST-A-615805514-0-0-0",
    "cluster": "FRST-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "ForestDefault",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "Geodes",
          "GlaciersLarge",
          "BouldersMixed",
          "DeepOil"
        ],
        "biomePaths": "Forest:132,213 138,200 135,183 123,171 103,170 92,178 86,196 93,216 97,219 118,222;93,216 86,196 69,196 63,213 66,218;86,196 92,178 70,171 62,182 69,196;118,242 118,222 97,219 98,248 99,249;127,151 121,145 103,151 103,170 123,171;163,218 157,201 138,200 132,213 155,226;165,186 157,176 135,183 138,200 157,201;228,325 221,305 197,302 185,320 203,341\nMagmaCore:31,357 20,348 0,351 0,384 30,384;63,357 49,348 31,357 30,384 63,384;95,357 80,347 63,357 63,384 95,384;128,358 113,346 95,357 95,384 127,384;162,360 146,348 128,358 127,384 162,384;193,352 178,348 162,360 162,384 194,384;225,356 203,345 193,352 194,384 225,384;256,351 236,348 225,356 225,384 256,384\nOilField:20,348 26,327 25,327 0,322 0,351;49,348 49,338 26,327 20,348 31,357;80,347 80,339 63,328 49,338 49,348 63,357;113,346 114,343 98,327 80,339 80,347 95,357;146,348 147,340 136,329 114,343 113,346 128,358;178,348 173,332 147,340 146,348 162,360;203,345 203,341 185,320 178,322 173,332 178,348 193,352;236,348 231,327 228,325 203,341 203,345 225,356;256,323 231,327 236,348 256,351\nToxicJungle:37,297 35,294 0,291 0,322 25,327;29,260 36,246 26,228 0,227 0,263;65,272 68,251 62,244 36,246 29,260 40,280;98,327 99,313 79,297 63,309 63,328 80,339;102,273 99,249 98,248 68,251 65,272 80,287;120,301 120,283 102,273 80,287 79,297 99,313;189,247 194,236 186,221 163,218 155,226 154,243 168,253;196,202 188,186 165,186 157,201 163,218 186,221;188,186 196,170 186,153 161,155 157,176 165,186;232,289 221,268 203,269 191,287 197,302 221,305;221,268 231,253 220,233 194,236 189,247 203,269;256,323 256,289 232,289 221,305 228,325 231,327;256,253 256,218 227,219 220,233 231,253\nFrozenWastes:40,280 29,260 0,263 0,291 35,294;36,211 29,195 0,194 0,227 26,228;38,180 31,162 0,164 0,194 29,195;29,97 33,90 25,65 0,64 0,98;63,328 63,309 37,297 25,327 26,327 49,338;65,147 57,119 36,116 26,130 35,153;79,297 80,287 65,272 40,280 35,294 37,297 63,309;89,141 93,115 69,107 57,119 65,147 66,148;63,84 59,60 36,53 25,65 33,90;161,87 162,65 150,56 129,64 127,84 139,94;165,282 168,253 154,243 135,253 134,274 160,286;169,120 171,94 161,87 139,94 136,114 158,127;185,320 197,302 191,287 165,282 160,286 157,301 178,322;220,233 227,219 219,203 196,202 186,221 194,236;219,169 226,155 220,141 194,136 186,153 196,170;220,102 228,91 219,64 198,69 192,89 206,103;256,253 231,253 221,268 232,289 256,289\nRust:31,162 35,153 26,130 0,130 0,164;62,244 66,218 63,213 36,211 26,228 36,246;69,196 62,182 38,180 29,195 36,211 63,213;70,171 66,148 65,147 35,153 31,162 38,180 62,182;69,107 66,87 63,84 33,90 29,97 36,116 57,119;98,248 97,219 93,216 66,218 62,244 68,251;103,170 103,151 89,141 66,148 70,171 92,178;101,110 105,91 92,78 66,87 69,107 93,115;121,145 123,121 101,110 93,115 89,141 103,151;134,274 135,253 118,242 99,249 102,273 120,283;157,301 160,286 134,274 120,283 120,301 136,313;154,243 155,226 132,213 118,222 118,242 135,253;155,147 158,127 136,114 123,121 121,145 127,151;157,176 161,155 155,147 127,151 123,171 135,183;186,153 194,136 191,129 169,120 158,127 155,147 161,155;228,187 219,169 196,170 188,186 196,202 219,203;256,218 256,187 228,187 219,203 227,219;256,90 228,91 220,102 231,123 256,123\nOcean:36,116 29,97 0,98 0,130 26,130;136,329 136,313 120,301 99,313 98,327 114,343;136,114 139,94 127,84 105,91 101,110 123,121;173,332 178,322 157,301 136,313 136,329 147,340;203,269 189,247 168,253 165,282 191,287;206,103 192,89 171,94 169,120 191,129;192,89 198,69 184,56 162,65 161,87 171,94;231,123 220,102 206,103 191,129 194,136 220,141;256,187 256,156 226,155 219,169 228,187;256,123 231,123 220,141 226,155 256,156",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 111,
            "y": 196
          },
          {
            "id": "MassiveHeatSink",
            "x": 206,
            "y": 88
          },
          {
            "id": "MassiveHeatSink",
            "x": 29,
            "y": 177
          },
          {
            "id": "MassiveHeatSink",
            "x": 219,
            "y": 215
          },
          {
            "id": "GeneShuffler",
            "x": 79,
            "y": 298
          },
          {
            "id": "GeneShuffler",
            "x": 153,
            "y": 94
          },
          {
            "id": "GeneShuffler",
            "x": 154,
            "y": 298
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 161,
            "y": 160,
            "emitRate": 5811,
            "avgEmitRate": 1602,
            "idleTime": 262,
            "eruptionTime": 304,
            "dormancyCycles": 54.4,
            "activeCycles": 57.5
          },
          {
            "id": "methane",
            "x": 73,
            "y": 260,
            "emitRate": 447,
            "avgEmitRate": 103,
            "idleTime": 290,
            "eruptionTime": 208,
            "dormancyCycles": 45,
            "activeCycles": 55.4
          },
          {
            "id": "salt_water",
            "x": 234,
            "y": 178,
            "emitRate": 15591,
            "avgEmitRate": 3541,
            "idleTime": 408,
            "eruptionTime": 208,
            "dormancyCycles": 25.3,
            "activeCycles": 52
          },
          {
            "id": "OilWell",
            "x": 171,
            "y": 343,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 135,
            "y": 336,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 235,
            "y": 333,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "small_volcano",
            "x": 99,
            "y": 304,
            "emitRate": 125381,
            "avgEmitRate": 496,
            "idleTime": 10497,
            "eruptionTime": 82,
            "dormancyCycles": 55.9,
            "activeCycles": 58.3
          },
          {
            "id": "salt_water",
            "x": 21,
            "y": 71,
            "emitRate": 6247,
            "avgEmitRate": 2402,
            "idleTime": 132,
            "eruptionTime": 447,
            "dormancyCycles": 60.3,
            "activeCycles": 59.8
          },
          {
            "id": "methane",
            "x": 193,
            "y": 336,
            "emitRate": 248,
            "avgEmitRate": 94,
            "idleTime": 111,
            "eruptionTime": 336,
            "dormancyCycles": 64.3,
            "activeCycles": 65.8
          },
          {
            "id": "molten_iron",
            "x": 167,
            "y": 304,
            "emitRate": 11752,
            "avgEmitRate": 357,
            "idleTime": 854,
            "eruptionTime": 43,
            "dormancyCycles": 55.9,
            "activeCycles": 98.8
          },
          {
            "id": "hot_water",
            "x": 149,
            "y": 253,
            "emitRate": 10597,
            "avgEmitRate": 2795,
            "idleTime": 366,
            "eruptionTime": 269,
            "dormancyCycles": 61,
            "activeCycles": 100.5
          },
          {
            "id": "big_volcano",
            "x": 179,
            "y": 292,
            "emitRate": 226405,
            "avgEmitRate": 1209,
            "idleTime": 10421,
            "eruptionTime": 92,
            "dormancyCycles": 72.7,
            "activeCycles": 115.3
          },
          {
            "id": "steam",
            "x": 201,
            "y": 131,
            "emitRate": 3310,
            "avgEmitRate": 1150,
            "idleTime": 248,
            "eruptionTime": 408,
            "dormancyCycles": 32.3,
            "activeCycles": 40.8
          },
          {
            "id": "oil_drip",
            "x": 74,
            "y": 140,
            "emitRate": 356,
            "avgEmitRate": 231,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.1,
            "activeCycles": 0.2
          },
          {
            "id": "slimy_po2",
            "x": 214,
            "y": 338,
            "emitRate": 424,
            "avgEmitRate": 113,
            "idleTime": 392,
            "eruptionTime": 297,
            "dormancyCycles": 51.2,
            "activeCycles": 82.8
          },
          {
            "id": "salt_water",
            "x": 215,
            "y": 311,
            "emitRate": 7897,
            "avgEmitRate": 2717,
            "idleTime": 335,
            "eruptionTime": 516,
            "dormancyCycles": 57.2,
            "activeCycles": 75.1
          },
          {
            "id": "salt_water",
            "x": 142,
            "y": 282,
            "emitRate": 14281,
            "avgEmitRate": 2823,
            "idleTime": 202,
            "eruptionTime": 102,
            "dormancyCycles": 42.8,
            "activeCycles": 61
          },
          {
            "id": "salt_water",
            "x": 73,
            "y": 236,
            "emitRate": 45018,
            "avgEmitRate": 2296,
            "idleTime": 463,
            "eruptionTime": 55,
            "dormancyCycles": 58,
            "activeCycles": 53.6
          },
          {
            "id": "OilWell",
            "x": 61,
            "y": 354,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 56,
            "y": 345,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 103,
            "y": 349,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 106,
            "y": 342,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 221,
            "y": 346,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 230,
            "y": 350,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 247,
            "y": 348,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "Satellite",
        "distance": 3
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "MetallicAsteroid",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "SaltDwarf",
        "distance": 4
      },
      {
        "id": "RockyAsteroid",
        "distance": 5
      },
      {
        "id": "MetallicAsteroid",
        "distance": 5
      },
      {
        "id": "TerraPlanet",
        "distance": 7
      },
      {
        "id": "IceGiant",
        "distance": 8
      },
      {
        "id": "HeliumGiant",
        "distance": 9
      },
      {
        "id": "GasGiant",
        "distance": 9
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "ShinyPlanet",
        "distance": 11
      },
      {
        "id": "ChlorinePlanet",
        "distance": 12
      },
      {
        "id": "ForestPlanet",
        "distance": 12
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "DustyMoon",
        "distance": 13
      },
      {
        "id": "MetallicAsteroid",
        "distance": 13
      },
      {
        "id": "ShinyPlanet",
        "distance": 14
      },
      {
        "id": "ShinyPlanet",
        "distance": 14
      },
      {
        "id": "OilyAsteriod",
        "distance": 15
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "SaltDwarf",
        "distance": 16
      },
      {
        "id": "SaltDwarf",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "LUSH-A-1847667149-0-0-0",
    "cluster": "LUSH-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "ForestLush",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "Geodes",
          "FrozenCore",
          "MagmaVents"
        ],
        "biomePaths": "Forest:147,212 154,195 149,179 133,170 113,175 106,189 109,206 118,214 142,215;17,352 30,330 22,319 0,319 0,353;31,275 23,259 0,258 0,290 18,291;26,175 33,161 24,145 0,146 0,175;76,351 74,329 57,323 45,334 49,354 58,359;109,206 106,189 89,187 83,208 88,212;113,175 108,166 90,166 84,178 89,187 106,189;142,215 118,214 118,232 136,236;133,170 132,153 117,149 108,166 113,175;176,208 168,195 154,195 147,212 166,220;175,178 165,169 149,179 154,195 168,195;256,209 256,182 228,182 222,194 230,210\nFrozenWastes:28,362 17,352 0,353 0,384 26,384;58,359 49,354 28,362 26,384 59,384;87,357 76,351 58,359 59,384 86,384;116,361 100,352 87,357 86,384 115,384;146,359 133,352 116,361 115,384 146,384;174,359 162,350 146,359 146,384 174,384;201,353 197,350 174,359 174,384 204,384;229,355 223,349 201,353 204,384 226,384;256,353 229,355 226,384 256,384\nOilField:22,319 27,307 18,291 0,290 0,319;26,228 31,218 26,204 0,204 0,228;49,354 45,334 30,330 17,352 28,362;50,301 43,278 31,275 18,291 27,307;50,129 55,120 45,100 33,99 23,115 32,130;80,264 80,240 62,237 53,247 58,265 73,269;109,133 111,128 99,109 81,111 77,116 86,138;107,326 114,300 101,293 85,300 87,320 105,327;131,118 124,96 108,96 99,109 111,128;110,62 93,47 78,58 82,77 99,79;177,91 181,86 175,64 159,64 150,86 156,92;201,85 206,78 201,61 182,58 175,64 181,86;211,256 197,236 181,242 183,266 202,268;202,138 196,120 183,118 173,126 174,145 193,149;222,194 228,182 222,168 198,168 194,175 203,194;228,76 233,66 227,55 209,52 201,61 206,78;256,66 233,66 228,76 237,93 256,94\nToxicJungle:32,245 26,228 0,228 0,258 23,259;57,323 53,303 50,301 27,307 22,319 30,330 45,334;62,237 56,218 31,218 26,228 32,245 53,247;57,86 48,66 34,63 23,85 33,99 45,100;77,116 81,111 73,87 57,86 45,100 55,120;121,299 132,281 124,266 111,263 101,270 101,293 114,300;174,325 171,308 153,303 142,312 140,329 161,338;223,349 226,327 223,323 206,319 193,332 197,350 201,353;224,222 230,210 222,194 203,194 193,210 201,225;232,125 224,109 210,107 196,120 202,138 223,140;256,240 256,209 230,210 224,222 233,240;256,126 232,125 223,140 229,154 256,155\nBoggyMarsh:33,190 26,175 0,175 0,204 26,204;88,235 88,212 83,208 62,208 56,218 62,237 80,240;83,208 89,187 84,178 62,178 55,190 62,208;81,147 86,138 77,116 55,120 50,129 60,150;118,232 118,214 109,206 88,212 88,235 107,240;99,109 108,96 99,79 82,77 73,87 81,111;100,352 105,327 87,320 74,329 76,351 87,357;111,263 107,240 88,235 80,240 80,264 101,270;117,149 109,133 86,138 81,147 90,166 108,166;133,352 133,334 107,326 105,327 100,352 116,361;134,86 123,60 110,62 99,79 108,96 124,96;150,118 156,92 150,86 134,86 124,96 131,118 143,122;159,64 151,54 126,55 123,60 134,86 150,86;170,237 166,220 147,212 142,215 136,236 143,247 151,249;174,145 173,126 150,118 143,122 143,144 165,152;183,118 177,91 156,92 150,118 173,126;210,288 202,268 183,266 175,273 182,298 199,300;197,236 201,225 193,210 176,208 166,220 170,237 181,242;194,175 198,168 193,149 174,145 165,152 165,169 175,178;210,107 201,85 181,86 177,91 183,118 196,120;256,182 256,155 229,154 222,168 228,182\nRust:24,145 32,130 23,115 0,115 0,146;33,99 23,85 0,85 0,115 23,115;58,265 53,247 32,245 23,259 31,275 43,278;56,218 62,208 55,190 33,190 26,204 31,218;82,77 78,58 62,52 48,66 57,86 73,87;101,293 101,270 80,264 73,269 74,295 85,300;140,329 142,312 121,299 114,300 107,326 133,334;161,272 151,249 143,247 124,266 132,281 149,284;197,350 193,332 174,325 161,338 162,350 174,359;256,353 256,328 226,327 223,349 229,355;256,299 233,297 223,323 226,327 256,328;256,272 256,240 233,240 224,254 237,272;256,94 237,93 224,109 232,125 256,126\nOcean:62,178 54,160 33,161 26,175 33,190 55,190;54,160 60,150 50,129 32,130 24,145 33,161;74,295 73,269 58,265 43,278 50,301 53,303;90,166 81,147 60,150 54,160 62,178 84,178;87,320 85,300 74,295 53,303 57,323 74,329;143,144 143,122 131,118 111,128 109,133 117,149 132,153;143,247 136,236 118,232 107,240 111,263 124,266;182,298 175,273 161,272 149,284 153,303 171,308;183,266 181,242 170,237 151,249 161,272 175,273;165,169 165,152 143,144 132,153 133,170 149,179;206,319 199,300 182,298 171,308 174,325 193,332;203,194 194,175 175,178 168,195 176,208 193,210;233,297 228,290 210,288 199,300 206,319 223,323;237,272 224,254 211,256 202,268 210,288 228,290;224,254 233,240 224,222 201,225 197,236 211,256;222,168 229,154 223,140 202,138 193,149 198,168;237,93 228,76 206,78 201,85 210,107 224,109\nMagmaCore:162,350 161,338 140,329 133,334 133,352 146,359;153,303 149,284 132,281 121,299 142,312;256,272 237,272 228,290 233,297 256,299",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 130,
            "y": 195
          },
          {
            "id": "GeneShuffler",
            "x": 93,
            "y": 328
          },
          {
            "id": "GeneShuffler",
            "x": 170,
            "y": 158
          },
          {
            "id": "GeneShuffler",
            "x": 203,
            "y": 330
          },
          {
            "id": "GeneShuffler",
            "x": 98,
            "y": 272
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 227,
            "y": 215,
            "emitRate": 6717,
            "avgEmitRate": 1513,
            "idleTime": 412,
            "eruptionTime": 239,
            "dormancyCycles": 35,
            "activeCycles": 55.6
          },
          {
            "id": "methane",
            "x": 226,
            "y": 122,
            "emitRate": 365,
            "avgEmitRate": 94,
            "idleTime": 337,
            "eruptionTime": 311,
            "dormancyCycles": 65.9,
            "activeCycles": 77.1
          },
          {
            "id": "steam",
            "x": 156,
            "y": 124,
            "emitRate": 7880,
            "avgEmitRate": 1721,
            "idleTime": 389,
            "eruptionTime": 213,
            "dormancyCycles": 44.7,
            "activeCycles": 72.2
          },
          {
            "id": "methane",
            "x": 18,
            "y": 200,
            "emitRate": 303,
            "avgEmitRate": 70,
            "idleTime": 338,
            "eruptionTime": 344,
            "dormancyCycles": 75.1,
            "activeCycles": 64.2
          },
          {
            "id": "salt_water",
            "x": 169,
            "y": 299,
            "emitRate": 7575,
            "avgEmitRate": 2817,
            "idleTime": 345,
            "eruptionTime": 566,
            "dormancyCycles": 59.4,
            "activeCycles": 88.7
          },
          {
            "id": "OilWell",
            "x": 124,
            "y": 108,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 155,
            "y": 85,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 184,
            "y": 133,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_hydrogen",
            "x": 214,
            "y": 173,
            "emitRate": 380,
            "avgEmitRate": 112,
            "idleTime": 235,
            "eruptionTime": 208,
            "dormancyCycles": 41.4,
            "activeCycles": 69.6
          },
          {
            "id": "hot_steam",
            "x": 75,
            "y": 257,
            "emitRate": 2164,
            "avgEmitRate": 503,
            "idleTime": 338,
            "eruptionTime": 344,
            "dormancyCycles": 75.1,
            "activeCycles": 64.2
          },
          {
            "id": "molten_copper",
            "x": 31,
            "y": 292,
            "emitRate": 10352,
            "avgEmitRate": 313,
            "idleTime": 746,
            "eruptionTime": 41,
            "dormancyCycles": 64.2,
            "activeCycles": 88.3
          },
          {
            "id": "slimy_po2",
            "x": 87,
            "y": 287,
            "emitRate": 271,
            "avgEmitRate": 122,
            "idleTime": 188,
            "eruptionTime": 387,
            "dormancyCycles": 51.5,
            "activeCycles": 103.6
          },
          {
            "id": "steam",
            "x": 204,
            "y": 256,
            "emitRate": 3875,
            "avgEmitRate": 1142,
            "idleTime": 405,
            "eruptionTime": 356,
            "dormancyCycles": 45.1,
            "activeCycles": 76.5
          },
          {
            "id": "hot_hydrogen",
            "x": 240,
            "y": 88,
            "emitRate": 240,
            "avgEmitRate": 115,
            "idleTime": 176,
            "eruptionTime": 507,
            "dormancyCycles": 71.1,
            "activeCycles": 130.7
          },
          {
            "id": "hot_steam",
            "x": 52,
            "y": 167,
            "emitRate": 2386,
            "avgEmitRate": 618,
            "idleTime": 300,
            "eruptionTime": 254,
            "dormancyCycles": 17,
            "activeCycles": 22.2
          },
          {
            "id": "filthy_water",
            "x": 168,
            "y": 367,
            "emitRate": 8644,
            "avgEmitRate": 2675,
            "idleTime": 319,
            "eruptionTime": 368,
            "dormancyCycles": 35.2,
            "activeCycles": 48.2
          },
          {
            "id": "molten_iron",
            "x": 151,
            "y": 142,
            "emitRate": 17021,
            "avgEmitRate": 311,
            "idleTime": 809,
            "eruptionTime": 27,
            "dormancyCycles": 53.8,
            "activeCycles": 69.3
          },
          {
            "id": "big_volcano",
            "x": 24,
            "y": 347,
            "emitRate": 224295,
            "avgEmitRate": 1141,
            "idleTime": 8598,
            "eruptionTime": 76,
            "dormancyCycles": 55.5,
            "activeCycles": 76.7
          },
          {
            "id": "steam",
            "x": 193,
            "y": 222,
            "emitRate": 4044,
            "avgEmitRate": 1036,
            "idleTime": 326,
            "eruptionTime": 313,
            "dormancyCycles": 55,
            "activeCycles": 60.4
          },
          {
            "id": "hot_water",
            "x": 195,
            "y": 130,
            "emitRate": 11395,
            "avgEmitRate": 2701,
            "idleTime": 285,
            "eruptionTime": 184,
            "dormancyCycles": 47.5,
            "activeCycles": 72.4
          },
          {
            "id": "OilWell",
            "x": 12,
            "y": 299,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 16,
            "y": 214,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 37,
            "y": 356,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 45,
            "y": 300,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 49,
            "y": 109,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 66,
            "y": 246,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 96,
            "y": 124,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 99,
            "y": 323,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 125,
            "y": 118,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 94,
            "y": 76,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 174,
            "y": 80,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 202,
            "y": 82,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 195,
            "y": 265,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 191,
            "y": 126,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 207,
            "y": 191,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 216,
            "y": 74,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 237,
            "y": 72,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "IcyDwarf",
        "distance": 5
      },
      {
        "id": "OrganicDwarf",
        "distance": 5
      },
      {
        "id": "IcyDwarf",
        "distance": 5
      },
      {
        "id": "OrganicDwarf",
        "distance": 6
      },
      {
        "id": "DustyMoon",
        "distance": 6
      },
      {
        "id": "DustyMoon",
        "distance": 7
      },
      {
        "id": "VolcanoPlanet",
        "distance": 7
      },
      {
        "id": "IceGiant",
        "distance": 9
      },
      {
        "id": "HeliumGiant",
        "distance": 9
      },
      {
        "id": "IceGiant",
        "distance": 9
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "TerraPlanet",
        "distance": 10
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "OrganicDwarf",
        "distance": 12
      },
      {
        "id": "GasGiant",
        "distance": 14
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "TerraPlanet",
        "distance": 16
      },
      {
        "id": "GoldAsteroid",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "CERS-A-1481554262-0-0-0",
    "cluster": "CERS-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "CeresBaseGameShatteredAsteroid",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "BouldersSmall",
          "BouldersMedium",
          "BouldersLarge"
        ],
        "biomePaths": "IceCaves:134,169 150,151 146,135 125,125 109,132 105,141 107,157 121,169;121,169 107,157 96,164 95,175 114,181;140,181 134,169 121,169 114,181 119,191 128,194;119,191 114,181 95,175 87,181 88,203 103,208;107,157 105,141 83,145 82,149 96,164;203,130 198,120 182,115 168,131 171,140 191,148;195,158 191,148 171,140 159,153 168,172 176,173;186,193 176,173 168,172 153,184 161,205 174,206;168,172 159,153 150,151 134,169 140,181 153,184;95,175 96,164 82,149 62,160 67,176 87,181;161,205 153,184 140,181 128,194 137,213 151,214;137,213 128,194 119,191 103,208 109,222 127,224;171,140 168,131 155,127 146,135 150,151 159,153;155,127 151,104 136,102 124,114 125,125 146,135;105,141 109,132 102,116 81,117 75,126 83,145;125,125 124,114 107,109 102,116 109,132;118,80 107,63 83,74 83,87 104,95;102,116 107,109 104,95 83,87 74,97 81,117;182,115 177,101 159,98 151,104 155,127 168,131;82,149 83,145 75,126 58,127 51,137 58,157 62,160;136,102 128,81 118,80 104,95 107,109 124,114;159,98 156,76 137,73 128,81 136,102 151,104\nFrozenWastes:224,151 227,140 224,135 203,130 191,148 195,158 201,161;199,224 207,212 199,194 186,193 174,206 184,224;174,236 184,224 174,206 161,205 151,214 159,237;109,222 103,208 88,203 81,208 78,228 84,235 99,235;153,243 159,237 151,214 137,213 127,224 133,243;256,195 232,194 225,212 231,221 256,221;88,203 87,181 67,176 56,191 57,199 81,208;208,183 201,161 195,158 176,173 186,193 199,194;210,104 202,89 188,87 177,101 182,115 198,120;75,126 81,117 74,97 59,95 49,109 58,127;58,127 49,109 31,108 24,129 30,137 51,137;32,181 34,168 24,156 0,159 0,180 30,183\nRust:234,166 224,151 201,161 208,183 226,184;256,247 256,221 231,221 224,243 228,247;256,165 256,140 227,140 224,151 234,166;26,208 30,183 0,180 0,208;225,212 232,194 226,184 208,183 199,194 207,212\nCarrotQuarry:231,221 225,212 207,212 199,224 210,243 224,243;256,140 256,115 232,114 224,135 227,140;232,114 226,105 210,104 198,120 203,130 224,135;67,176 62,160 58,157 34,168 32,181 56,191;59,95 52,81 31,82 26,102 31,108 49,109;256,87 235,87 226,105 232,114 256,115;188,87 181,73 164,70 156,76 159,98 177,101;58,157 51,137 30,137 24,156 34,168;133,243 127,224 109,222 99,235 108,251 126,251;76,256 84,235 78,228 56,230 49,244 58,256\nOcean:256,165 234,166 226,184 232,194 256,195;78,228 81,208 57,199 47,214 56,230;57,199 56,191 32,181 30,183 26,208 31,214 47,214;24,156 30,137 24,129 0,128 0,159;31,108 26,102 0,102 0,128 24,129;24,235 31,214 26,208 0,208 0,235;49,244 56,230 47,214 31,214 24,235 32,245\nMagmaCore:256,297 256,274 227,273 225,275 229,298;226,303 229,298 225,275 208,274 197,291 206,304;208,274 199,258 184,258 176,268 185,290 197,291;185,290 176,268 160,268 153,275 158,296 176,298;132,302 127,283 107,283 99,298 104,308 125,310;107,283 97,267 85,266 75,285 81,296 99,298;158,296 153,275 133,275 127,283 132,302 150,304;73,310 81,296 75,285 53,282 48,305 52,311;53,282 51,278 29,276 21,296 28,304 48,305;29,276 23,267 0,266 0,295 21,296\nSugarWoods:137,73 135,53 123,48 108,56 107,63 118,80 128,81;107,63 108,56 94,41 77,49 75,67 83,74;164,70 162,50 152,44 135,53 137,73 156,76;83,87 83,74 75,67 57,70 52,81 59,95 74,97;31,82 25,75 0,76 0,102 26,102;256,58 236,57 227,74 235,87 256,87;195,55 181,41 162,50 164,70 181,73;214,74 201,55 195,55 181,73 188,87 202,89;25,75 30,52 28,50 0,50 0,76;52,81 57,70 47,54 30,52 25,75 31,82;236,57 229,47 210,45 201,55 214,74 227,74;235,87 227,74 214,74 202,89 210,104 226,105\nOilField:256,323 256,297 229,298 226,303 232,325;199,320 206,304 197,291 185,290 176,298 180,321 186,323;228,330 232,325 226,303 206,304 199,320 213,333;256,355 238,354 230,362 231,384 256,384;180,321 176,298 158,296 150,304 155,326 160,328;155,326 150,304 132,302 125,310 129,330 137,334;166,352 160,328 155,326 137,334 139,354 153,360;129,330 125,310 104,308 96,327 110,339;139,354 137,334 129,330 110,339 109,353 127,362;153,360 139,354 127,362 126,384 154,384;109,353 110,339 96,327 82,329 81,332 85,354 100,359;74,363 53,355 47,359 46,384 74,384;53,355 55,338 48,330 28,332 23,353 47,359;48,330 52,311 48,305 28,304 21,324 28,332;23,354 0,352 0,384 20,384;23,353 28,332 21,324 0,325 0,352 23,354;21,324 28,304 21,296 0,295 0,325\nForest:204,356 213,333 199,320 186,323 186,352 204,356;238,354 228,330 213,333 204,356 230,362;186,352 186,323 180,321 160,328 166,352 177,356;96,327 104,308 99,298 81,296 73,310 82,329;177,356 166,352 153,360 154,384 178,384;127,362 109,353 100,359 100,384 126,384;204,356 186,352 177,356 178,384 200,384;47,359 23,353 23,354 20,384 46,384\nSandstone:256,323 232,325 228,330 238,354 256,355;230,362 204,356 204,356 200,384 231,384;85,354 81,332 55,338 53,355 74,363;100,359 85,354 74,363 74,384 100,384;81,332 82,329 73,310 52,311 48,330 55,338",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 126,
            "y": 147
          },
          {
            "id": "MassiveHeatSink",
            "x": 28,
            "y": 171
          },
          {
            "id": "MassiveHeatSink",
            "x": 44,
            "y": 134
          },
          {
            "id": "MassiveHeatSink",
            "x": 89,
            "y": 223
          },
          {
            "id": "GeneShuffler",
            "x": 236,
            "y": 203
          },
          {
            "id": "GeneShuffler",
            "x": 195,
            "y": 175
          }
        ],
        "geysers": [
          {
            "id": "slush_salt_water",
            "x": 232,
            "y": 179,
            "emitRate": 5165,
            "avgEmitRate": 1632,
            "idleTime": 280,
            "eruptionTime": 313,
            "dormancyCycles": 45.7,
            "activeCycles": 68
          },
          {
            "id": "OilWell",
            "x": 123,
            "y": 328,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 204,
            "y": 317,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 218,
            "y": 321,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "oil_drip",
            "x": 231,
            "y": 149,
            "emitRate": 361,
            "avgEmitRate": 220,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "big_volcano",
            "x": 27,
            "y": 327,
            "emitRate": 238101,
            "avgEmitRate": 1046,
            "idleTime": 10033,
            "eruptionTime": 84,
            "dormancyCycles": 92.7,
            "activeCycles": 105.3
          },
          {
            "id": "methane",
            "x": 49,
            "y": 155,
            "emitRate": 381,
            "avgEmitRate": 117,
            "idleTime": 377,
            "eruptionTime": 364,
            "dormancyCycles": 43.9,
            "activeCycles": 74.2
          },
          {
            "id": "big_volcano",
            "x": 152,
            "y": 50,
            "emitRate": 239588,
            "avgEmitRate": 1213,
            "idleTime": 9817,
            "eruptionTime": 81,
            "dormancyCycles": 56.8,
            "activeCycles": 90.6
          },
          {
            "id": "methane",
            "x": 234,
            "y": 229,
            "emitRate": 599,
            "avgEmitRate": 121,
            "idleTime": 388,
            "eruptionTime": 175,
            "dormancyCycles": 30.5,
            "activeCycles": 56.4
          },
          {
            "id": "molten_copper",
            "x": 72,
            "y": 109,
            "emitRate": 8464,
            "avgEmitRate": 357,
            "idleTime": 613,
            "eruptionTime": 42,
            "dormancyCycles": 31.8,
            "activeCycles": 61.2
          },
          {
            "id": "hot_co2",
            "x": 162,
            "y": 324,
            "emitRate": 390,
            "avgEmitRate": 103,
            "idleTime": 309,
            "eruptionTime": 254,
            "dormancyCycles": 49.9,
            "activeCycles": 71.5
          },
          {
            "id": "molten_gold",
            "x": 125,
            "y": 357,
            "emitRate": 10476,
            "avgEmitRate": 376,
            "idleTime": 821,
            "eruptionTime": 48,
            "dormancyCycles": 44,
            "activeCycles": 81.3
          },
          {
            "id": "big_volcano",
            "x": 72,
            "y": 194,
            "emitRate": 275883,
            "avgEmitRate": 1171,
            "idleTime": 8016,
            "eruptionTime": 57,
            "dormancyCycles": 76.7,
            "activeCycles": 113.6
          },
          {
            "id": "slimy_po2",
            "x": 68,
            "y": 224,
            "emitRate": 359,
            "avgEmitRate": 97,
            "idleTime": 223,
            "eruptionTime": 227,
            "dormancyCycles": 65.5,
            "activeCycles": 75.7
          },
          {
            "id": "small_volcano",
            "x": 188,
            "y": 312,
            "emitRate": 116422,
            "avgEmitRate": 592,
            "idleTime": 9018,
            "eruptionTime": 76,
            "dormancyCycles": 50.2,
            "activeCycles": 78.5
          },
          {
            "id": "chlorine_gas",
            "x": 199,
            "y": 62,
            "emitRate": 410,
            "avgEmitRate": 108,
            "idleTime": 236,
            "eruptionTime": 193,
            "dormancyCycles": 44.5,
            "activeCycles": 63.6
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "ForestPlanet",
        "distance": 3
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 3
      },
      {
        "id": "ForestPlanet",
        "distance": 3
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "MetallicAsteroid",
        "distance": 5
      },
      {
        "id": "TerraPlanet",
        "distance": 7
      },
      {
        "id": "VolcanoPlanet",
        "distance": 7
      },
      {
        "id": "TerraPlanet",
        "distance": 8
      },
      {
        "id": "TerraPlanet",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "ChlorinePlanet",
        "distance": 12
      },
      {
        "id": "MetallicAsteroid",
        "distance": 13
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "VolcanoPlanet",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "VOLCA-1234889416-0-0-0",
    "cluster": "VOLCA",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "Volcanic",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "BouldersMixed",
          "BouldersSmall",
          "GlaciersLarge",
          "MetalRich"
        ],
        "biomePaths": "Sandstone:136,202 149,180 146,162 125,147 100,159 94,176 98,195 100,197 131,204;94,176 73,174 67,186 70,194 98,195;100,159 88,151 71,159 73,174 94,176;131,204 100,197 102,214 122,222;143,121 127,106 124,106 113,120 126,140;167,192 163,184 149,180 136,202 155,209;192,186 185,168 174,167 163,184 167,192 187,193;174,167 162,155 146,162 149,180 163,184\nMagmaCore:31,354 16,342 0,343 0,384 29,384;16,342 29,319 18,304 0,303 0,343;32,281 25,270 0,268 0,303 18,304;28,198 35,181 31,172 0,173 0,198;55,345 47,321 29,319 16,342 31,354;64,261 59,253 37,249 25,270 32,281 49,283;63,348 55,345 31,354 29,384 64,384;96,355 78,344 63,348 64,384 95,384;96,249 86,231 71,229 59,253 64,261 81,265;129,350 115,344 96,355 95,384 129,384;115,344 108,321 86,321 78,344 96,355;118,274 105,250 96,249 81,265 90,284 108,285;124,232 122,222 102,214 86,231 96,249 105,250;135,308 144,290 133,274 118,274 108,285 119,308;161,354 141,344 129,350 129,384 163,384;179,342 167,318 147,322 141,344 161,354;194,349 190,344 179,342 161,354 163,384 191,384;167,318 173,308 162,290 144,290 135,308 147,322;225,350 194,349 191,384 228,384;202,319 194,307 173,308 167,318 179,342 190,344;230,344 220,319 202,319 190,344 194,349 225,350;231,305 218,284 204,286 194,307 202,319 220,319;222,211 224,207 217,183 192,186 187,193 196,213;217,183 223,173 218,162 198,156 185,168 192,186;256,344 230,344 225,350 228,384 256,384;256,203 224,207 222,211 229,232 256,233\nFrozenWastes:37,249 29,233 0,233 0,268 25,270;71,229 62,216 37,216 29,233 37,249 59,253;113,95 116,79 96,64 84,66 76,94 94,106;229,232 222,211 196,213 187,228 196,247 218,247;220,121 228,103 219,90 200,88 188,102 191,125 192,125;256,268 256,233 229,232 218,247 228,269;256,103 228,103 220,121 232,139 256,139\nToxicJungle:37,216 28,198 0,198 0,233 29,233;31,172 37,157 19,135 0,136 0,173;19,135 37,112 36,107 18,96 0,100 0,136;56,150 58,121 37,112 19,135 37,157;88,151 88,122 66,116 58,121 56,150 71,159;151,245 124,232 105,250 118,274 133,274;162,155 166,131 151,119 143,121 126,140 125,147 146,162;198,156 192,125 191,125 166,131 162,155 174,167 185,168;218,284 228,269 218,247 196,247 185,265 204,286;256,203 256,176 223,173 217,183 224,207\nOilField:62,304 49,283 32,281 18,304 29,319 47,321;78,344 86,321 75,305 62,304 47,321 55,345 63,348;90,284 81,265 64,261 49,283 62,304 75,305;119,308 108,285 90,284 75,305 86,321 108,321;141,344 147,322 135,308 119,308 108,321 115,344 129,350;176,267 151,245 151,245 133,274 144,290 162,290;194,307 204,286 185,265 176,267 162,290 173,308;256,344 256,305 231,305 220,319 230,344;256,305 256,268 228,269 218,284 231,305\nBoggyMarsh:62,216 70,194 67,186 35,181 28,198 37,216;73,174 71,159 56,150 37,157 31,172 35,181 67,186;102,214 100,197 98,195 70,194 62,216 71,229 86,231;125,147 126,140 113,120 94,117 88,122 88,151 100,159;151,245 162,228 155,209 136,202 131,204 122,222 124,232 151,245;185,265 196,247 187,228 162,228 151,245 176,267;196,213 187,193 167,192 155,209 162,228 187,228\nOcean:53,92 46,65 25,64 18,69 18,96 36,107;76,94 84,66 75,57 50,60 46,65 53,92 67,98;191,125 188,102 169,91 155,99 151,119 166,131;232,139 220,121 192,125 198,156 218,162;232,65 230,61 207,50 193,60 191,74 200,88 219,90;256,139 232,139 218,162 223,173 256,176;256,103 256,69 232,65 219,90 228,103",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 121,
            "y": 180
          },
          {
            "id": "MassiveHeatSink",
            "x": 228,
            "y": 124
          },
          {
            "id": "MassiveHeatSink",
            "x": 98,
            "y": 81
          },
          {
            "id": "MassiveHeatSink",
            "x": 199,
            "y": 124
          },
          {
            "id": "GeneShuffler",
            "x": 63,
            "y": 167
          },
          {
            "id": "GeneShuffler",
            "x": 169,
            "y": 151
          },
          {
            "id": "GeneShuffler",
            "x": 239,
            "y": 332
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 132,
            "y": 137,
            "emitRate": 9658,
            "avgEmitRate": 1801,
            "idleTime": 387,
            "eruptionTime": 155,
            "dormancyCycles": 39.9,
            "activeCycles": 75
          },
          {
            "id": "chlorine_gas",
            "x": 24,
            "y": 155,
            "emitRate": 203,
            "avgEmitRate": 101,
            "idleTime": 158,
            "eruptionTime": 395,
            "dormancyCycles": 37.6,
            "activeCycles": 84.7
          },
          {
            "id": "steam",
            "x": 178,
            "y": 248,
            "emitRate": 5344,
            "avgEmitRate": 1685,
            "idleTime": 241,
            "eruptionTime": 270,
            "dormancyCycles": 49.7,
            "activeCycles": 73.9
          },
          {
            "id": "methane",
            "x": 39,
            "y": 213,
            "emitRate": 274,
            "avgEmitRate": 108,
            "idleTime": 245,
            "eruptionTime": 456,
            "dormancyCycles": 43.4,
            "activeCycles": 66.1
          },
          {
            "id": "salt_water",
            "x": 168,
            "y": 119,
            "emitRate": 7401,
            "avgEmitRate": 2406,
            "idleTime": 195,
            "eruptionTime": 274,
            "dormancyCycles": 70,
            "activeCycles": 87.7
          },
          {
            "id": "OilWell",
            "x": 67,
            "y": 310,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 64,
            "y": 321,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 187,
            "y": 277,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "molten_copper",
            "x": 236,
            "y": 310,
            "emitRate": 7951,
            "avgEmitRate": 339,
            "idleTime": 624,
            "eruptionTime": 46,
            "dormancyCycles": 50.1,
            "activeCycles": 83.4
          },
          {
            "id": "hot_co2",
            "x": 64,
            "y": 196,
            "emitRate": 407,
            "avgEmitRate": 105,
            "idleTime": 216,
            "eruptionTime": 164,
            "dormancyCycles": 42.7,
            "activeCycles": 63.7
          },
          {
            "id": "molten_iron",
            "x": 82,
            "y": 131,
            "emitRate": 10710,
            "avgEmitRate": 393,
            "idleTime": 727,
            "eruptionTime": 40,
            "dormancyCycles": 51.8,
            "activeCycles": 125.8
          },
          {
            "id": "hot_steam",
            "x": 83,
            "y": 302,
            "emitRate": 2198,
            "avgEmitRate": 596,
            "idleTime": 317,
            "eruptionTime": 301,
            "dormancyCycles": 60.8,
            "activeCycles": 76.4
          },
          {
            "id": "hot_hydrogen",
            "x": 21,
            "y": 239,
            "emitRate": 462,
            "avgEmitRate": 111,
            "idleTime": 461,
            "eruptionTime": 290,
            "dormancyCycles": 60.2,
            "activeCycles": 99.3
          },
          {
            "id": "hot_water",
            "x": 229,
            "y": 155,
            "emitRate": 10477,
            "avgEmitRate": 2698,
            "idleTime": 347,
            "eruptionTime": 261,
            "dormancyCycles": 41.5,
            "activeCycles": 62.1
          },
          {
            "id": "oil_drip",
            "x": 154,
            "y": 222,
            "emitRate": 313,
            "avgEmitRate": 183,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.3,
            "activeCycles": 0.4
          },
          {
            "id": "filthy_water",
            "x": 177,
            "y": 210,
            "emitRate": 9543,
            "avgEmitRate": 2583,
            "idleTime": 218,
            "eruptionTime": 210,
            "dormancyCycles": 52.3,
            "activeCycles": 64.4
          },
          {
            "id": "methane",
            "x": 164,
            "y": 263,
            "emitRate": 410,
            "avgEmitRate": 109,
            "idleTime": 385,
            "eruptionTime": 319,
            "dormancyCycles": 45.7,
            "activeCycles": 64.4
          },
          {
            "id": "slush_salt_water",
            "x": 62,
            "y": 294,
            "emitRate": 5108,
            "avgEmitRate": 1370,
            "idleTime": 371,
            "eruptionTime": 321,
            "dormancyCycles": 68.1,
            "activeCycles": 93.4
          },
          {
            "id": "big_volcano",
            "x": 39,
            "y": 85,
            "emitRate": 271100,
            "avgEmitRate": 1137,
            "idleTime": 8544,
            "eruptionTime": 63,
            "dormancyCycles": 56,
            "activeCycles": 75.8
          },
          {
            "id": "hot_co2",
            "x": 108,
            "y": 129,
            "emitRate": 285,
            "avgEmitRate": 108,
            "idleTime": 251,
            "eruptionTime": 403,
            "dormancyCycles": 65.4,
            "activeCycles": 104.2
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "Satellite",
        "distance": 3
      },
      {
        "id": "RockyAsteroid",
        "distance": 3
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "SaltDwarf",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "IcyDwarf",
        "distance": 5
      },
      {
        "id": "IcyDwarf",
        "distance": 6
      },
      {
        "id": "DustyMoon",
        "distance": 7
      },
      {
        "id": "IceGiant",
        "distance": 8
      },
      {
        "id": "GasGiant",
        "distance": 9
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "RustPlanet",
        "distance": 10
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "ForestPlanet",
        "distance": 12
      },
      {
        "id": "IceGiant",
        "distance": 13
      },
      {
        "id": "RedDwarf",
        "distance": 14
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "OilyAsteriod",
        "distance": 15
      },
      {
        "id": "OilyAsteriod",
        "distance": 15
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "OASIS-A-1126779281-0-0-0",
    "cluster": "OASIS-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "Oasis",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "MetalRich",
          "BouldersMedium",
          "Geodes"
        ],
        "biomePaths": "Forest:145,211 152,196 149,181 134,169 111,175 104,188 107,205 121,216;107,205 104,188 89,187 82,205 89,212;111,175 107,166 90,164 83,176 89,187 104,188;149,218 145,211 121,216 121,227 139,235;134,169 134,158 118,150 107,166 111,175;174,208 168,198 152,196 145,211 149,218 167,220;175,180 167,173 149,181 152,196 168,198\nMagmaCore:28,363 17,353 0,353 0,384 26,384;58,358 53,355 28,363 26,384 60,384;87,354 80,351 58,358 60,384 85,384;115,364 97,351 87,354 85,384 115,384;147,361 133,353 115,364 115,384 147,384;175,358 165,351 147,361 147,384 176,384;202,352 198,350 175,358 176,384 204,384;229,356 222,349 202,352 204,384 226,384;256,354 229,356 226,384 256,384\nOcean:17,353 29,329 23,319 0,318 0,353;32,272 23,257 0,257 0,288 22,288;47,131 56,118 46,98 32,97 21,114 32,132;80,351 75,325 60,322 48,333 53,355 58,358;76,291 81,265 77,261 58,259 48,273 59,294;86,317 85,298 76,291 59,294 53,303 60,322 75,325;133,353 132,337 107,328 97,351 115,364;143,327 142,312 119,303 106,324 107,328 132,337;211,286 200,266 185,265 176,274 182,297 199,299;256,354 256,329 227,328 222,349 229,356;233,300 224,286 211,286 199,299 206,319 223,321;235,269 224,251 210,253 200,266 211,286 224,286;256,300 233,300 223,321 227,328 256,329;256,269 235,269 224,286 233,300 256,300;256,238 256,209 228,210 224,219 232,239;256,128 232,127 223,142 229,156 256,156;256,96 236,96 224,113 232,127 256,128\nBoggyMarsh:31,304 22,288 0,288 0,318 23,319;34,216 29,202 0,202 0,228 28,229;29,202 34,191 27,174 0,175 0,202;27,174 32,163 23,145 0,146 0,175;53,355 48,333 29,329 17,353 28,363;53,303 59,294 48,273 32,272 22,288 31,304;108,101 104,83 84,78 75,87 80,110 95,112;97,351 107,328 106,324 86,317 75,325 80,351 87,354;165,351 164,335 143,327 132,337 133,353 147,361;152,301 148,283 132,278 116,294 119,303 142,312;171,86 169,67 150,60 137,80 141,91 152,96;198,350 194,331 175,325 164,335 165,351 175,358;182,297 176,274 161,272 148,283 152,301 172,306;222,349 227,328 223,321 206,319 194,331 198,350 202,352;206,319 199,299 182,297 172,306 175,325 194,331;256,209 256,183 228,183 222,195 228,210;256,156 229,156 222,169 228,183 256,183\nSandstone:33,240 28,229 0,228 0,257 23,257;23,145 32,132 21,114 0,114 0,146;32,97 23,84 0,83 0,114 21,114;58,259 52,243 33,240 23,257 32,272 48,273;54,191 63,175 55,161 32,163 27,174 34,191;55,161 59,150 47,131 32,132 23,145 32,163;57,85 50,65 33,63 23,84 32,97 46,98;88,232 89,212 82,205 62,206 56,217 60,232 81,237;82,205 89,187 83,176 63,175 54,191 62,206;90,164 82,144 79,143 59,150 55,161 63,175 83,176;80,110 75,87 57,85 46,98 56,118 71,118;84,78 81,59 62,53 50,65 57,85 75,87;121,227 121,216 107,205 89,212 88,232 107,239;110,257 107,239 88,232 81,237 77,261 81,265 98,267;118,150 114,137 100,132 82,144 90,164 107,166;129,123 124,107 108,101 95,112 100,132 114,137;114,73 113,57 92,49 81,59 84,78 104,83;132,278 127,262 110,257 98,267 104,289 116,294;149,146 146,128 129,123 114,137 118,150 134,158;141,91 137,80 114,73 104,83 108,101 124,107;142,247 139,235 121,227 107,239 110,257 127,262;157,120 152,96 141,91 124,107 129,123 146,128;150,60 149,56 124,46 113,57 114,73 137,80;161,272 156,252 142,247 127,262 132,278 148,283;175,240 167,220 149,218 139,235 142,247 156,252;178,147 177,123 157,120 146,128 149,146 167,154;167,173 167,154 149,146 134,158 134,169 149,181;180,121 182,93 171,86 152,96 157,120 177,123;199,88 202,82 196,62 181,59 169,67 171,86 182,93;197,234 202,222 195,209 174,208 167,220 175,240 177,241;203,195 194,178 175,180 168,198 174,208 195,209;194,178 200,169 194,151 178,147 167,154 167,173 175,180;211,112 199,88 182,93 180,121 200,124;224,251 232,239 224,219 202,222 197,234 210,253;229,156 223,142 205,139 194,151 200,169 222,169;225,79 232,64 227,55 208,51 196,62 202,82;256,65 232,64 225,79 236,96 256,96\nToxicJungle:60,322 53,303 31,304 23,319 29,329 48,333;119,303 116,294 104,289 85,298 86,317 106,324;175,325 172,306 152,301 142,312 143,327 164,335;236,96 225,79 202,82 199,88 211,112 224,113;256,238 232,239 224,251 235,269 256,269\nOilField:60,232 56,217 34,216 28,229 33,240 52,243;62,206 54,191 34,191 29,202 34,216 56,217;81,237 60,232 52,243 58,259 77,261;79,143 71,118 56,118 47,131 59,150;104,289 98,267 81,265 76,291 85,298;100,132 95,112 80,110 71,118 79,143 82,144;185,265 177,241 175,240 156,252 161,272 176,274;210,253 197,234 177,241 185,265 200,266;205,139 200,124 180,121 177,123 178,147 194,151;224,219 228,210 222,195 203,195 195,209 202,222;222,195 228,183 222,169 200,169 194,178 203,195;232,127 224,113 211,112 200,124 205,139 223,142",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 129,
            "y": 196
          },
          {
            "id": "GeneShuffler",
            "x": 36,
            "y": 280
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 163,
            "y": 320,
            "emitRate": 3986,
            "avgEmitRate": 1588,
            "idleTime": 233,
            "eruptionTime": 325,
            "dormancyCycles": 49.4,
            "activeCycles": 106.9
          },
          {
            "id": "methane",
            "x": 215,
            "y": 83,
            "emitRate": 276,
            "avgEmitRate": 95,
            "idleTime": 193,
            "eruptionTime": 272,
            "dormancyCycles": 53,
            "activeCycles": 75.6
          },
          {
            "id": "steam",
            "x": 130,
            "y": 302,
            "emitRate": 5512,
            "avgEmitRate": 1323,
            "idleTime": 531,
            "eruptionTime": 419,
            "dormancyCycles": 62.3,
            "activeCycles": 74.3
          },
          {
            "id": "methane",
            "x": 81,
            "y": 327,
            "emitRate": 356,
            "avgEmitRate": 117,
            "idleTime": 246,
            "eruptionTime": 264,
            "dormancyCycles": 57.1,
            "activeCycles": 99.7
          },
          {
            "id": "salt_water",
            "x": 188,
            "y": 267,
            "emitRate": 13312,
            "avgEmitRate": 3190,
            "idleTime": 285,
            "eruptionTime": 226,
            "dormancyCycles": 57.9,
            "activeCycles": 68.3
          },
          {
            "id": "OilWell",
            "x": 183,
            "y": 244,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 222,
            "y": 216,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 198,
            "y": 139,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "slimy_po2",
            "x": 229,
            "y": 320,
            "emitRate": 281,
            "avgEmitRate": 116,
            "idleTime": 243,
            "eruptionTime": 455,
            "dormancyCycles": 64.6,
            "activeCycles": 111.1
          },
          {
            "id": "hot_hydrogen",
            "x": 156,
            "y": 140,
            "emitRate": 321,
            "avgEmitRate": 88,
            "idleTime": 196,
            "eruptionTime": 246,
            "dormancyCycles": 74.9,
            "activeCycles": 72.5
          },
          {
            "id": "molten_copper",
            "x": 185,
            "y": 115,
            "emitRate": 9485,
            "avgEmitRate": 317,
            "idleTime": 940,
            "eruptionTime": 57,
            "dormancyCycles": 46.3,
            "activeCycles": 66.3
          },
          {
            "id": "hot_water",
            "x": 202,
            "y": 206,
            "emitRate": 8016,
            "avgEmitRate": 1978,
            "idleTime": 225,
            "eruptionTime": 289,
            "dormancyCycles": 71.2,
            "activeCycles": 55.5
          },
          {
            "id": "salt_water",
            "x": 48,
            "y": 88,
            "emitRate": 9342,
            "avgEmitRate": 3066,
            "idleTime": 330,
            "eruptionTime": 351,
            "dormancyCycles": 55.5,
            "activeCycles": 97.2
          },
          {
            "id": "molten_iron",
            "x": 232,
            "y": 114,
            "emitRate": 5796,
            "avgEmitRate": 290,
            "idleTime": 592,
            "eruptionTime": 63,
            "dormancyCycles": 61.8,
            "activeCycles": 67.6
          },
          {
            "id": "methane",
            "x": 52,
            "y": 183,
            "emitRate": 272,
            "avgEmitRate": 117,
            "idleTime": 68,
            "eruptionTime": 154,
            "dormancyCycles": 45.1,
            "activeCycles": 74.5
          },
          {
            "id": "big_volcano",
            "x": 225,
            "y": 238,
            "emitRate": 267502,
            "avgEmitRate": 1366,
            "idleTime": 9209,
            "eruptionTime": 69,
            "dormancyCycles": 40.7,
            "activeCycles": 91.3
          },
          {
            "id": "oil_drip",
            "x": 166,
            "y": 289,
            "emitRate": 336,
            "avgEmitRate": 191,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.2
          },
          {
            "id": "slush_salt_water",
            "x": 147,
            "y": 101,
            "emitRate": 4314,
            "avgEmitRate": 1442,
            "idleTime": 388,
            "eruptionTime": 469,
            "dormancyCycles": 39.7,
            "activeCycles": 62.2
          },
          {
            "id": "filthy_water",
            "x": 60,
            "y": 284,
            "emitRate": 6765,
            "avgEmitRate": 2898,
            "idleTime": 216,
            "eruptionTime": 471,
            "dormancyCycles": 48.6,
            "activeCycles": 81
          },
          {
            "id": "small_volcano",
            "x": 106,
            "y": 211,
            "emitRate": 145448,
            "avgEmitRate": 572,
            "idleTime": 9227,
            "eruptionTime": 62,
            "dormancyCycles": 74.6,
            "activeCycles": 106.2
          },
          {
            "id": "OilWell",
            "x": 37,
            "y": 229,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 35,
            "y": 212,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 58,
            "y": 254,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 63,
            "y": 134,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 92,
            "y": 281,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 83,
            "y": 118,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 175,
            "y": 258,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 190,
            "y": 261,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 193,
            "y": 130,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 218,
            "y": 210,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 205,
            "y": 194,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 213,
            "y": 132,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 3
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "RockyAsteroid",
        "distance": 5
      },
      {
        "id": "TerraPlanet",
        "distance": 7
      },
      {
        "id": "DustyMoon",
        "distance": 7
      },
      {
        "id": "IceGiant",
        "distance": 8
      },
      {
        "id": "TerraPlanet",
        "distance": 8
      },
      {
        "id": "IceGiant",
        "distance": 9
      },
      {
        "id": "GasGiant",
        "distance": 9
      },
      {
        "id": "RockyAsteroid",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 11
      },
      {
        "id": "RockyAsteroid",
        "distance": 11
      },
      {
        "id": "MetallicAsteroid",
        "distance": 11
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "IcyDwarf",
        "distance": 13
      },
      {
        "id": "RedDwarf",
        "distance": 14
      },
      {
        "id": "OilyAsteriod",
        "distance": 15
      },
      {
        "id": "GoldAsteroid",
        "distance": 16
      },
      {
        "id": "TerraPlanet",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "HTFST-A-2145340721-0-0-0",
    "cluster": "HTFST-A",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "ForestHot",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "Geodes",
          "BouldersSmall",
          "MetalRich",
          "FrozenCore"
        ],
        "biomePaths": "Forest:128,221 136,205 133,187 120,175 95,179 86,191 86,210 97,225;86,210 86,191 64,189 58,197 68,215;96,229 97,225 86,210 68,215 64,228 70,236;120,175 120,163 99,154 91,161 95,179;129,238 132,230 128,221 97,225 96,229 105,243;162,79 167,63 150,41 145,40 128,66 147,83;157,226 160,220 154,207 136,205 128,221 132,230;161,188 154,180 133,187 136,205 154,207\nFrozenWastes:30,360 17,348 0,349 0,384 28,384;63,357 54,352 30,360 28,384 65,384;96,357 83,349 63,357 65,384 96,384;129,358 113,350 96,357 96,384 128,384;162,362 143,351 129,358 128,384 164,384;195,354 192,349 181,348 162,362 164,384 193,384;226,357 195,354 193,384 229,384;256,349 233,349 226,357 229,384 256,384\nOilField:17,348 33,320 28,312 0,310 0,349;54,352 51,326 33,320 17,348 30,360;83,349 82,326 65,317 51,326 54,352 63,357;113,350 114,326 101,316 82,326 83,349 96,357;143,351 145,328 134,319 114,326 113,350 129,358;181,348 166,322 145,328 143,351 162,362;204,325 192,309 172,313 166,322 181,348 192,349;233,349 224,325 204,325 192,349 195,354 226,357;256,313 232,314 224,325 233,349 256,349\nToxicJungle:35,295 23,278 0,278 0,310 28,312;65,317 65,297 56,291 35,295 28,312 33,320 51,326;68,256 70,236 64,228 38,231 32,247 35,255 58,263;58,197 64,189 58,165 37,163 28,186 36,199;88,123 84,99 58,95 58,96 55,129 64,135;100,262 105,243 96,229 70,236 68,256 89,267;125,287 124,277 100,262 89,267 86,289 101,301;134,319 136,297 125,287 101,301 101,316 114,326;155,120 140,102 119,106 113,124 137,141;166,322 172,313 159,291 136,297 134,319 145,328;166,248 157,226 132,230 129,238 139,262 155,264;176,93 162,79 147,83 140,102 155,120 165,120;192,309 199,295 189,278 163,281 159,291 172,313;188,216 193,203 185,187 161,188 154,207 160,220;195,126 204,104 192,92 176,93 165,120 173,127;221,262 228,249 220,232 196,233 188,250 195,263;232,157 222,140 206,139 192,165 193,169 221,177;221,103 229,90 219,67 200,70 192,92 204,104;256,122 233,123 222,140 232,157 256,157\nRust:23,278 35,255 32,247 0,244 0,278;64,228 68,215 58,197 36,199 29,216 38,231;95,179 91,161 67,155 58,165 64,189 86,191;58,95 62,67 35,59 30,63 30,93 31,94 58,96;99,154 100,129 88,123 64,135 67,155 91,161;139,262 129,238 105,243 100,262 124,277;137,148 137,141 113,124 100,129 99,154 120,163;154,180 156,160 137,148 120,163 120,175 133,187;189,278 195,263 188,250 166,248 155,264 163,281;196,233 188,216 160,220 157,226 166,248 188,250;185,187 193,169 192,165 169,154 156,160 154,180 161,188;206,139 195,126 173,127 169,154 192,165;192,92 200,70 188,57 167,63 162,79 176,93;219,201 225,189 221,177 193,169 185,187 193,203;233,123 221,103 204,104 195,126 206,139 222,140\nOcean:38,231 29,216 0,217 0,244 32,247;37,163 32,156 0,154 0,186 28,186;56,291 58,263 35,255 23,278 35,295;67,155 64,135 55,129 37,133 32,156 37,163 58,165;55,129 58,96 31,94 28,125 37,133;86,289 89,267 68,256 58,263 56,291 65,297;93,90 85,66 70,61 62,67 58,95 84,99;124,67 105,52 85,66 93,90 107,92;159,291 163,281 155,264 139,262 124,277 125,287 136,297;220,232 227,219 219,201 193,203 188,216 196,233;256,313 256,280 229,281 222,292 232,314;256,280 256,249 228,249 221,262 229,281;256,218 256,190 225,189 219,201 227,219\nBoggyMarsh:29,216 36,199 28,186 0,186 0,217;37,133 28,125 0,127 0,154 32,156;28,125 31,94 30,93 0,93 0,127;30,63 0,60 0,93 30,93;101,316 101,301 86,289 65,297 65,317 82,326;113,124 119,106 107,92 93,90 84,99 88,123 100,129;140,102 147,83 128,66 124,67 107,92 119,106;169,154 173,127 165,120 155,120 137,141 137,148 156,160;224,325 232,314 222,292 199,295 192,309 204,325;222,292 229,281 221,262 195,263 189,278 199,295;256,249 256,218 227,219 220,232 228,249;256,157 232,157 221,177 225,189 256,190;256,122 256,89 229,90 221,103 233,123",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 111,
            "y": 201
          },
          {
            "id": "GeneShuffler",
            "x": 84,
            "y": 320
          },
          {
            "id": "GeneShuffler",
            "x": 128,
            "y": 88
          },
          {
            "id": "GeneShuffler",
            "x": 222,
            "y": 188
          },
          {
            "id": "GeneShuffler",
            "x": 237,
            "y": 345
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 179,
            "y": 302,
            "emitRate": 4356,
            "avgEmitRate": 1430,
            "idleTime": 108,
            "eruptionTime": 129,
            "dormancyCycles": 61.8,
            "activeCycles": 93.4
          },
          {
            "id": "methane",
            "x": 159,
            "y": 202,
            "emitRate": 291,
            "avgEmitRate": 94,
            "idleTime": 284,
            "eruptionTime": 311,
            "dormancyCycles": 64.7,
            "activeCycles": 104.6
          },
          {
            "id": "steam",
            "x": 111,
            "y": 120,
            "emitRate": 5961,
            "avgEmitRate": 1998,
            "idleTime": 318,
            "eruptionTime": 270,
            "dormancyCycles": 27.3,
            "activeCycles": 74.1
          },
          {
            "id": "methane",
            "x": 16,
            "y": 121,
            "emitRate": 429,
            "avgEmitRate": 113,
            "idleTime": 300,
            "eruptionTime": 223,
            "dormancyCycles": 53.1,
            "activeCycles": 86.2
          },
          {
            "id": "salt_water",
            "x": 131,
            "y": 273,
            "emitRate": 10337,
            "avgEmitRate": 3333,
            "idleTime": 307,
            "eruptionTime": 271,
            "dormancyCycles": 46.4,
            "activeCycles": 102.2
          },
          {
            "id": "OilWell",
            "x": 221,
            "y": 336,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 34,
            "y": 328,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 179,
            "y": 337,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "hot_po2",
            "x": 65,
            "y": 107,
            "emitRate": 409,
            "avgEmitRate": 93,
            "idleTime": 401,
            "eruptionTime": 313,
            "dormancyCycles": 46.1,
            "activeCycles": 50.2
          },
          {
            "id": "hot_water",
            "x": 51,
            "y": 226,
            "emitRate": 9929,
            "avgEmitRate": 2199,
            "idleTime": 386,
            "eruptionTime": 315,
            "dormancyCycles": 73.2,
            "activeCycles": 71.1
          },
          {
            "id": "salt_water",
            "x": 57,
            "y": 178,
            "emitRate": 14901,
            "avgEmitRate": 2833,
            "idleTime": 431,
            "eruptionTime": 206,
            "dormancyCycles": 45.2,
            "activeCycles": 64.8
          },
          {
            "id": "methane",
            "x": 125,
            "y": 337,
            "emitRate": 385,
            "avgEmitRate": 108,
            "idleTime": 408,
            "eruptionTime": 389,
            "dormancyCycles": 65.9,
            "activeCycles": 89.8
          },
          {
            "id": "molten_iron",
            "x": 39,
            "y": 203,
            "emitRate": 21301,
            "avgEmitRate": 234,
            "idleTime": 956,
            "eruptionTime": 25,
            "dormancyCycles": 52.2,
            "activeCycles": 38.3
          },
          {
            "id": "hot_po2",
            "x": 157,
            "y": 153,
            "emitRate": 374,
            "avgEmitRate": 119,
            "idleTime": 266,
            "eruptionTime": 249,
            "dormancyCycles": 61.9,
            "activeCycles": 119
          },
          {
            "id": "molten_iron",
            "x": 77,
            "y": 285,
            "emitRate": 10944,
            "avgEmitRate": 300,
            "idleTime": 622,
            "eruptionTime": 33,
            "dormancyCycles": 64.8,
            "activeCycles": 75.8
          },
          {
            "id": "hot_steam",
            "x": 75,
            "y": 162,
            "emitRate": 1815,
            "avgEmitRate": 702,
            "idleTime": 248,
            "eruptionTime": 380,
            "dormancyCycles": 46.2,
            "activeCycles": 81.9
          },
          {
            "id": "small_volcano",
            "x": 67,
            "y": 343,
            "emitRate": 122665,
            "avgEmitRate": 573,
            "idleTime": 7959,
            "eruptionTime": 63,
            "dormancyCycles": 41.9,
            "activeCycles": 60.6
          },
          {
            "id": "slush_water",
            "x": 24,
            "y": 264,
            "emitRate": 3593,
            "avgEmitRate": 1427,
            "idleTime": 245,
            "eruptionTime": 425,
            "dormancyCycles": 59.5,
            "activeCycles": 99.8
          },
          {
            "id": "small_volcano",
            "x": 182,
            "y": 146,
            "emitRate": 119628,
            "avgEmitRate": 553,
            "idleTime": 9274,
            "eruptionTime": 76,
            "dormancyCycles": 63.3,
            "activeCycles": 84.5
          },
          {
            "id": "slush_salt_water",
            "x": 78,
            "y": 335,
            "emitRate": 19309,
            "avgEmitRate": 1102,
            "idleTime": 592,
            "eruptionTime": 83,
            "dormancyCycles": 57.7,
            "activeCycles": 50
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "Satellite",
        "distance": 2
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 3
      },
      {
        "id": "ForestPlanet",
        "distance": 3
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 4
      },
      {
        "id": "MetallicAsteroid",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "RedDwarf",
        "distance": 6
      },
      {
        "id": "DustyMoon",
        "distance": 7
      },
      {
        "id": "IceGiant",
        "distance": 8
      },
      {
        "id": "RustPlanet",
        "distance": 8
      },
      {
        "id": "HeliumGiant",
        "distance": 9
      },
      {
        "id": "GasGiant",
        "distance": 9
      },
      {
        "id": "TerraPlanet",
        "distance": 10
      },
      {
        "id": "TerraPlanet",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 11
      },
      {
        "id": "GoldAsteroid",
        "distance": 12
      },
      {
        "id": "IcyDwarf",
        "distance": 13
      },
      {
        "id": "ShinyPlanet",
        "distance": 14
      },
      {
        "id": "ShinyPlanet",
        "distance": 14
      },
      {
        "id": "ShinyPlanet",
        "distance": 14
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "HeliumGiant",
        "distance": 15
      },
      {
        "id": "VolcanoPlanet",
        "distance": 16
      },
      {
        "id": "SaltDwarf",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  },
  {
    "coordinate": "S-FRZ-1098424328-0-0-0",
    "cluster": "S-FRZ",
    "dlcs": [
      "FrostyPlanet"
    ],
    "asteroids": [
      {
        "id": "SandstoneFrozen",
        "offsetX": 0,
        "offsetY": 0,
        "sizeX": 256,
        "sizeY": 384,
        "worldTraits": [
          "MagmaVents",
          "GeoDormant",
          "BouldersMixed"
        ],
        "biomePaths": "Sandstone:119,216 130,202 125,178 118,173 94,173 82,190 88,213 92,216;88,213 82,190 72,188 61,202 65,213;94,173 90,163 85,161 66,173 72,188 82,190;85,161 77,140 60,137 52,145 57,169 66,173;123,228 119,216 92,216 93,227 116,236;118,173 119,157 111,150 90,163 94,173;140,174 142,154 140,152 119,157 118,173 125,178;149,122 148,98 137,91 119,106 121,122 139,129;163,276 145,256 133,261 129,283 149,295;146,215 139,203 130,202 119,216 123,228 140,228;150,184 140,174 125,178 130,202 139,203\nMagmaCore:28,359 19,351 0,351 0,384 26,384;48,353 47,332 30,328 19,351 28,359;56,358 48,353 28,359 26,384 57,384;73,351 74,337 58,325 47,332 48,353 56,358;85,359 73,351 56,358 57,384 84,384;116,365 99,353 85,359 84,384 116,384;99,353 101,336 94,327 74,337 73,351 85,359;145,358 134,353 116,365 116,384 148,384;158,349 157,334 134,325 129,332 134,353 145,358;174,356 158,349 145,358 148,384 172,384;202,359 183,351 174,356 172,384 201,384;183,351 185,330 172,322 157,334 158,349 174,356;229,359 214,352 202,359 201,384 232,384;245,348 230,328 215,333 214,352 229,359;256,348 245,348 229,359 232,384 256,384;256,314 237,316 230,328 245,348 256,348\nOilField:19,351 30,328 25,318 0,317 0,351;34,299 24,288 0,288 0,317 25,318;134,353 129,332 101,336 99,353 116,365;214,352 215,333 202,323 185,330 183,351 202,359;256,218 256,188 231,189 223,205 232,219\nBoggyMarsh:24,288 32,261 29,258 0,258 0,288;32,226 31,202 0,199 0,227 30,229;37,197 36,180 27,173 0,178 0,199 31,202;27,173 27,145 0,144 0,178;61,239 56,230 32,226 30,229 29,258 32,261 46,262;57,169 52,145 29,143 27,145 27,173 36,180;113,306 114,289 103,282 82,289 80,303 95,317;111,150 109,133 90,126 77,140 85,161 90,163;129,332 134,325 133,320 113,306 95,317 94,327 101,336;137,91 135,83 114,71 99,86 101,97 119,106;145,256 150,246 140,228 123,228 116,236 116,252 133,261;172,275 182,259 170,242 150,246 145,256 163,276;207,269 197,258 182,259 172,275 187,294 197,294;194,166 201,145 198,140 173,135 165,153 174,166;206,86 197,77 171,84 169,91 180,108 199,109;223,205 231,189 223,176 201,176 195,196 202,206;256,129 233,129 224,148 230,159 256,160;256,98 234,98 225,116 233,129 256,129;256,98 256,68 232,67 224,84 234,98\nRust:30,229 0,227 0,258 29,258;29,143 29,116 0,114 0,144 27,145;33,112 32,93 24,87 0,91 0,114 29,116;116,252 116,236 93,227 80,246 83,253 103,261;140,152 139,129 121,122 109,133 111,150 119,157;169,91 171,84 162,65 151,62 135,83 137,91 148,98;196,226 202,206 195,196 175,196 167,215 176,227;224,268 233,250 224,236 205,237 197,258 207,269;234,98 224,84 206,86 199,109 205,116 225,116\nForest:24,87 26,59 0,56 0,91;65,213 61,202 37,197 31,202 32,226 56,230;60,137 55,117 33,112 29,116 29,143 52,145;54,84 51,61 31,56 26,59 24,87 32,93;93,227 92,216 88,213 65,213 56,230 61,239 80,246;121,122 119,106 101,97 87,112 90,126 109,133;165,153 173,135 169,128 149,122 139,129 140,152 142,154;195,196 201,176 194,166 174,166 166,184 175,196;230,328 237,316 225,300 205,301 202,323 215,333;224,84 232,67 226,58 208,53 198,60 197,77 206,86\nToxicJungle:58,325 60,311 47,298 34,299 25,318 30,328 47,332;47,298 58,276 46,262 32,261 24,288 34,299;72,188 66,173 57,169 36,180 37,197 61,202;80,303 82,289 69,276 58,276 47,298 60,311;69,276 83,253 80,246 61,239 46,262 58,276;67,105 64,89 54,84 32,93 33,112 55,117;94,327 95,317 80,303 60,311 58,325 74,337;101,97 99,86 82,77 64,89 67,105 87,112;170,242 176,227 167,215 146,215 140,228 150,246;166,184 174,166 165,153 142,154 140,174 150,184;202,323 205,301 197,294 187,294 170,312 172,322 185,330;167,215 175,196 166,184 150,184 139,203 146,215;205,116 199,109 180,108 169,128 173,135 198,140;223,176 230,159 224,148 201,145 194,166 201,176;233,129 225,116 205,116 198,140 201,145 224,148;256,314 256,282 234,282 225,300 237,316;256,282 256,249 233,250 224,268 234,282;256,188 256,160 230,159 223,176 231,189\nOcean:82,77 80,60 62,53 51,61 54,84 64,89;103,282 103,261 83,253 69,276 82,289;90,126 87,112 67,105 55,117 60,137 77,140;114,71 113,59 92,51 80,60 82,77 99,86;129,283 133,261 116,252 103,261 103,282 114,289;149,299 149,295 129,283 114,289 113,306 133,320;151,62 147,55 122,49 113,59 114,71 135,83;172,322 170,312 149,299 133,320 134,325 157,334;187,294 172,275 163,276 149,295 149,299 170,312;180,108 169,91 148,98 149,122 169,128;197,77 198,60 179,51 162,65 171,84;197,258 205,237 196,226 176,227 170,242 182,259;225,300 234,282 224,268 207,269 197,294 205,301;224,236 232,219 223,205 202,206 196,226 205,237\nFrozenWastes:256,249 256,218 232,219 224,236 233,250",
        "pointsOfInterest": [
          {
            "id": "Headquarters",
            "x": 105,
            "y": 194
          },
          {
            "id": "GeneShuffler",
            "x": 56,
            "y": 147
          },
          {
            "id": "GeneShuffler",
            "x": 23,
            "y": 316
          }
        ],
        "geysers": [
          {
            "id": "steam",
            "x": 200,
            "y": 149,
            "emitRate": 5041,
            "avgEmitRate": 1744,
            "idleTime": 256,
            "eruptionTime": 270,
            "dormancyCycles": 9.1,
            "activeCycles": 19
          },
          {
            "id": "methane",
            "x": 39,
            "y": 285,
            "emitRate": 252,
            "avgEmitRate": 113,
            "idleTime": 139,
            "eruptionTime": 312,
            "dormancyCycles": 46.4,
            "activeCycles": 85.8
          },
          {
            "id": "steam",
            "x": 222,
            "y": 199,
            "emitRate": 2676,
            "avgEmitRate": 1124,
            "idleTime": 101,
            "eruptionTime": 291,
            "dormancyCycles": 28.2,
            "activeCycles": 36.7
          },
          {
            "id": "methane",
            "x": 233,
            "y": 135,
            "emitRate": 342,
            "avgEmitRate": 87,
            "idleTime": 210,
            "eruptionTime": 189,
            "dormancyCycles": 32.3,
            "activeCycles": 37.3
          },
          {
            "id": "salt_water",
            "x": 219,
            "y": 270,
            "emitRate": 11325,
            "avgEmitRate": 3189,
            "idleTime": 317,
            "eruptionTime": 237,
            "dormancyCycles": 35.8,
            "activeCycles": 69.1
          },
          {
            "id": "OilWell",
            "x": 237,
            "y": 213,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 19,
            "y": 294,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "OilWell",
            "x": 191,
            "y": 332,
            "emitRate": 3333,
            "avgEmitRate": 3333,
            "idleTime": 0,
            "eruptionTime": 1,
            "dormancyCycles": 0,
            "activeCycles": 1
          },
          {
            "id": "big_volcano",
            "x": 233,
            "y": 367,
            "emitRate": 195331,
            "avgEmitRate": 800,
            "idleTime": 7084,
            "eruptionTime": 50,
            "dormancyCycles": 52.4,
            "activeCycles": 73.5
          },
          {
            "id": "big_volcano",
            "x": 184,
            "y": 369,
            "emitRate": 253091,
            "avgEmitRate": 1123,
            "idleTime": 7556,
            "eruptionTime": 58,
            "dormancyCycles": 56.1,
            "activeCycles": 77.6
          },
          {
            "id": "big_volcano",
            "x": 198,
            "y": 369,
            "emitRate": 285384,
            "avgEmitRate": 1298,
            "idleTime": 8134,
            "eruptionTime": 60,
            "dormancyCycles": 49.9,
            "activeCycles": 82.9
          },
          {
            "id": "liquid_co2",
            "x": 99,
            "y": 79,
            "emitRate": 392,
            "avgEmitRate": 167,
            "idleTime": 177,
            "eruptionTime": 303,
            "dormancyCycles": 37.9,
            "activeCycles": 79.2
          },
          {
            "id": "small_volcano",
            "x": 156,
            "y": 295,
            "emitRate": 122885,
            "avgEmitRate": 522,
            "idleTime": 9035,
            "eruptionTime": 72,
            "dormancyCycles": 56.4,
            "activeCycles": 65
          },
          {
            "id": "big_volcano",
            "x": 25,
            "y": 95,
            "emitRate": 220907,
            "avgEmitRate": 1120,
            "idleTime": 9602,
            "eruptionTime": 86,
            "dormancyCycles": 42.9,
            "activeCycles": 56.4
          },
          {
            "id": "slimy_po2",
            "x": 95,
            "y": 266,
            "emitRate": 391,
            "avgEmitRate": 114,
            "idleTime": 243,
            "eruptionTime": 212,
            "dormancyCycles": 49.9,
            "activeCycles": 82.9
          },
          {
            "id": "small_volcano",
            "x": 193,
            "y": 177,
            "emitRate": 148819,
            "avgEmitRate": 513,
            "idleTime": 7310,
            "eruptionTime": 48,
            "dormancyCycles": 59.8,
            "activeCycles": 67.2
          },
          {
            "id": "oil_drip",
            "x": 131,
            "y": 311,
            "emitRate": 326,
            "avgEmitRate": 201,
            "idleTime": 0,
            "eruptionTime": 600,
            "dormancyCycles": 0.2,
            "activeCycles": 0.3
          },
          {
            "id": "big_volcano",
            "x": 141,
            "y": 301,
            "emitRate": 307662,
            "avgEmitRate": 1216,
            "idleTime": 8726,
            "eruptionTime": 56,
            "dormancyCycles": 68,
            "activeCycles": 110.7
          },
          {
            "id": "big_volcano",
            "x": 177,
            "y": 293,
            "emitRate": 241058,
            "avgEmitRate": 1176,
            "idleTime": 8515,
            "eruptionTime": 70,
            "dormancyCycles": 45.9,
            "activeCycles": 69.2
          },
          {
            "id": "hot_steam",
            "x": 145,
            "y": 164,
            "emitRate": 2033,
            "avgEmitRate": 619,
            "idleTime": 330,
            "eruptionTime": 375,
            "dormancyCycles": 34.1,
            "activeCycles": 45.6
          }
        ]
      }
    ],
    "starMapEntriesVanilla": [
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 0
      },
      {
        "id": "MetallicAsteroid",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "OilyAsteriod",
        "distance": 1
      },
      {
        "id": "RockyAsteroid",
        "distance": 2
      },
      {
        "id": "IcyDwarf",
        "distance": 3
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 3
      },
      {
        "id": "CarbonaceousAsteroid",
        "distance": 3
      },
      {
        "id": "OrganicDwarf",
        "distance": 4
      },
      {
        "id": "Earth",
        "distance": 4
      },
      {
        "id": "OrganicDwarf",
        "distance": 5
      },
      {
        "id": "OrganicDwarf",
        "distance": 5
      },
      {
        "id": "DustyMoon",
        "distance": 7
      },
      {
        "id": "GasGiant",
        "distance": 8
      },
      {
        "id": "GasGiant",
        "distance": 9
      },
      {
        "id": "IceGiant",
        "distance": 9
      },
      {
        "id": "MetallicAsteroid",
        "distance": 10
      },
      {
        "id": "MetallicAsteroid",
        "distance": 11
      },
      {
        "id": "ForestPlanet",
        "distance": 12
      },
      {
        "id": "ForestPlanet",
        "distance": 12
      },
      {
        "id": "ShinyPlanet",
        "distance": 14
      },
      {
        "id": "ForestPlanet",
        "distance": 15
      },
      {
        "id": "GoldAsteroid",
        "distance": 16
      },
      {
        "id": "VolcanoPlanet",
        "distance": 16
      },
      {
        "id": "Wormhole",
        "distance": 17
      }
    ],
    "starMapEntriesSpacedOut": null
  }
]
""".trimIndent()