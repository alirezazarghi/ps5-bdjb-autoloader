# PS5 BD-JB Autoloader

**Note : YOU NEED ALREADY JAILBROKEN PS5**

PS5 BD-JB Autoloader is an automated payload loader for jailbroken PS5 consoles. It chains the `poops` kernel exploit with an `autoloader` module that automatically executes ELF and JAR payloads from a specified configuration.

Send `bdj_unpatch.elf` to elfldr to unpatch BD-J.
`bdj_unpatch.elf` will backup existing `bdjstack.jar` to `bdjstack.jar.bak` just in case.

Then burn `ps5-bd-jb-autoloader.iso` and run.

**DO NOT REINSTALL FW, IT WILL WIPE THE PATCH AND LOSE BD-JB**

---

RemoteLogger server is listening on port 18194.  
Use log_client.py to get the log.  
I recommend first running the log_client.py then starting the BD-J app.  

RemoteJarLoader server is listening on port 9025.  
Use jar_client.py to send the jar file.  
You can use any other TCP payload sender too.  
Don't forget to set Main-Class in manifest.txt.  

---

Use john-tornblom's **[bdj-sdk](https://github.com/john-tornblom/bdj-sdk/)** and **[ps5-payload-sdk](https://github.com/ps5-payload-dev/sdk/)** for compiling.  

---

### Credits

* **[TheFlow](https://github.com/theofficialflow)** — BD-JB documentation & native code execution sources.  
* **[hammer-83](https://github.com/hammer-83)** — PS5 Remote JAR Loader reference.  
* **[john-tornblom](https://github.com/john-tornblom)** — [BDJ-SDK](https://github.com/john-tornblom/bdj-sdk) and [ps5-payload-sdk](https://github.com/ps5-payload-dev/sdk/) used for compilation.  
* **[kuba--](https://github.com/kuba--)** — [zip](https://github.com/kuba--/zip) used for bdj_unpatch elf payload.  

---

## License

This project is licensed under the GPL-3.0 License.

The original base code remains under its original MIT License (see LICENSE-MIT).
All unique modifications and additions in this project are licensed under GPL-3.0.

































