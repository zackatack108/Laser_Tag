package mc.cyberplex.us;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mc.cyberplex.us.arena.ArenaData;
import mc.cyberplex.us.arena.ArenaState;
import mc.cyberplex.us.arena.PlayerState;
import mc.cyberplex.us.kits.Shop;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{

	Main main = Main.getMain();
	Shop shop = new Shop();
	ArenaData data = new ArenaData();
	ArenaState state = new ArenaState();
	PlayerState playerState = new PlayerState();

	//create error messages for commands
	String noPermission = ChatColor.RED + "Sorry, you don't have permisson for that command",
			systemAdmin = ChatColor.RED + "Please contact your server administrator if you believe this is wrong",
			invalidCmd = ChatColor.RED + "Sorry, the command was entered wrong",
			helpMsg = ChatColor.RED + "Do /lt help for a list of commands",
			invalidArena = ChatColor.RED + "Sorry, that arena doesn't exist",
			arenaExist = ChatColor.RED + "Sorry, an arena with that name already exists",
			missingName = ChatColor.RED + "Sorry, the arena name was missing",
			missingComponent = ChatColor.RED + "Sorry, a component of the command is missing";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

		Player player = (Player) sender;
		String playerID = player.getUniqueId().toString();

		if(cmd.getName().equalsIgnoreCase("lt")) {

			switch (args[0].toLowerCase()) {

			case "help":

				if(player.hasPermission("lt.help")) {

					if(args.length == 2) {

						if(Integer.parseInt(args[1]) == 2) {

							//Display help messages to user
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.DARK_AQUA +  "Laser Tag Commands " + ChatColor.GREEN + "| pg 2 of 3");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.YELLOW + "/lt delete [Arena Name]: " + ChatColor.GRAY + "Delete an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt min [Arena Name] [Number]: " + ChatColor.GRAY + "Set the minimum number of players for an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt max [Arena Name] [Number]: " + ChatColor.GRAY + "Set the maximum number of players for an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt reload: " + ChatColor.GRAY + "Reload configuration file");
							player.sendMessage(ChatColor.YELLOW + "/lt list: " + ChatColor.GRAY + "Display laser tag arenas");
							player.sendMessage(ChatColor.YELLOW + "/lt list spawns [Arena Name]: " + ChatColor.GRAY + "Display the spawns for an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt shop: " + ChatColor.GRAY + "Open laser tag shop");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

						} else if(Integer.parseInt(args[1]) == 3) {

							//Display help messages to user
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.DARK_AQUA +  "Laser Tag Commands " + ChatColor.GREEN + "| pg 3 of 3");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.YELLOW + "/lt add spawn [Arena Name]: " + ChatColor.GRAY + "Add a spawn point to an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt add lobby [Arena Name]: " + ChatColor.GRAY + "Add a lobby for an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt add hub: " + ChatColor.GRAY + "Add a hub for laser tag");
							player.sendMessage(ChatColor.YELLOW + "/lt remove spawn [Arena Name] [Number]: " + ChatColor.GRAY + "Delete a spawn point for an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt remove lobby [Arena Name]: " + ChatColor.GRAY + "Delete a lobby for an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt remove hub: " + ChatColor.GRAY + "Delete the laser tag hub");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

						} else {

							//Display help messages to user
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.DARK_AQUA +  "Laser Tag Commands " + ChatColor.GREEN + "| pg 1 of 3");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.YELLOW + "/lt help [Page]: " + ChatColor.GRAY + "Display all commands for laser tag plugin");
							player.sendMessage(ChatColor.YELLOW + "/lt join [Arena Name]: " + ChatColor.GRAY + "Join an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt leave: " + ChatColor.GRAY + "Leave an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt start [Arena Name]: " + ChatColor.GRAY + "Start an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt stop [Arena Name]: " + ChatColor.GRAY + "Stop an arena");
							player.sendMessage(ChatColor.YELLOW + "/lt create [Arena Name]: " + ChatColor.GRAY + "Create an arena");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

						}

					} else {

						//Display help messages to user
						player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
						player.sendMessage(ChatColor.DARK_AQUA +  "Laser Tag Commands " + ChatColor.GREEN + "| pg 1 of 3");
						player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
						player.sendMessage(ChatColor.YELLOW + "/lt help [Page]: " + ChatColor.GRAY + "Display all commands for laser tag plugin");
						player.sendMessage(ChatColor.YELLOW + "/lt join [Arena Name]: " + ChatColor.GRAY + "Join an arena");
						player.sendMessage(ChatColor.YELLOW + "/lt leave: " + ChatColor.GRAY + "Leave an arena");
						player.sendMessage(ChatColor.YELLOW + "/lt start [Arena Name]: " + ChatColor.GRAY + "Start an arena");
						player.sendMessage(ChatColor.YELLOW + "/lt stop [Arena Name]: " + ChatColor.GRAY + "Stop an arena");
						player.sendMessage(ChatColor.YELLOW + "/lt create [Arena Name]: " + ChatColor.GRAY + "Create an arena");
						player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

					}


				}

				break;

			case "join":

				if(player.hasPermission("lt.join")) {

					boolean inGame = false;

					if(args.length == 2) {

						String arenaName = args[1].toLowerCase();

						int arenaNum = data.getArenaNum(arenaName);

						for(int count = 0; count < data.getArena(arenaNum).getInGameCount(); count++) {

							if(data.getArena(arenaNum).getInGame(count).equals(player.getUniqueId().toString())) {
								inGame = true;								
							}

						}

					}

					if(inGame == true) {						
						player.sendMessage(ChatColor.RED + "Sorry, you are currently in an arena");
						player.sendMessage(ChatColor.RED + "Please do " + ChatColor.DARK_RED + "/lt leave " + ChatColor.RED + "before joining the arena" );
					} else {

						if(args.length == 2) {

							String arenaName = args[1].toLowerCase();

							if(main.getConfig().contains("Arenas." + arenaName)) {

								playerState.joinGame(player, arenaName);

							}

						} else {

							player.teleport(data.getHub());
							player.sendMessage(ChatColor.YELLOW + "Sending you to the laser tag hub");

						}

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "leave":

				if(player.hasPermission("lt.leave")) {

					boolean inArena = false;
					String arenaName = null;

					for(String arena : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

						int arenaNum = data.getArenaNum(arena);

						for(int count = 0; count < data.getArena(arenaNum).getInGameCount(); count++) {

							if(data.getArena(arenaNum).getInGame(count).equals(playerID)) {
								arenaName = arena;
								inArena = true;
							}

						}

					}

					if(inArena == false) {
						player.sendMessage(ChatColor.RED + "Sorry, you aren't in an arena");
					} else if(inArena == true) {
						playerState.leaveGame(arenaName, player);
					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "start":

				if(player.hasPermission("lt.start")) {

					if(args.length == 2) {

						if(main.getConfig().contains("Arenas." + args[1].toLowerCase())) {
							state.start(args[1].toLowerCase());
						} else {
							player.sendMessage(missingName);
						}

					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "stop":

				//check if player has permission to stop arena
				if(player.hasPermission("lt.stop")) {

					if(args.length == 2) {

						//check if arena name exist in config
						if(main.getConfig().contains("Arenas." + args[1].toLowerCase())) {

							//stop the arena
							state.stop(args[1].toLowerCase());

						} else {

							//display error message to user
							player.sendMessage(missingName);

						}

					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "create":

				//check if player has permission to create arena
				if(player.hasPermission("lt.create")) {

					if(args.length == 2) {

						String arenaName = args[1].toLowerCase();

						//check if the arena exist in the config
						if(main.getConfig().contains("Arenas." + arenaName)) {

							//display error message to user
							player.sendMessage(arenaExist);

						} else {

							//display create message to player
							player.sendMessage(ChatColor.GREEN + "Creating laser tag arena " + arenaName);

							//save arena to the config
							main.getConfig().set("Arenas." + arenaName, arenaName);
							main.getConfig().set("Arenas." + arenaName + ".min", 2);
							main.getConfig().set("Arenas." + arenaName + ".max", 4);
							main.getConfig().set("Arenas." + arenaName + ".state", "waiting for players");
							main.saveConfig();

						}
					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}
				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "delete":

				//check if player has permission to delete
				if(player.hasPermission("lt.delete")) {

					if(args.length == 2) {

						String arenaName = args[1].toLowerCase();

						//check if arena name exist in config
						if(main.getConfig().contains("Arenas." + arenaName)) {

							//remove arena from config
							main.getConfig().set("Arenas." + arenaName, null);
							main.saveConfig();

							//send message to player saying the arena was removed
							player.sendMessage(ChatColor.YELLOW + "Removing laser tag arena " + arenaName);
							player.sendMessage(ChatColor.YELLOW + "Arena removed");

						} else {

							//display error message to user
							player.sendMessage(missingName);

						}

					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "min":

				//check if player has permission to set min players
				if(player.hasPermission("lt.min")) {

					if(args.length == 3) {

						String arenaName = args[1].toLowerCase();

						//check if arena name exist in config
						if(main.getConfig().contains("Arenas." + arenaName)) {

							int minNum = Integer.parseInt(args[2]);

							//save min players to config
							main.getConfig().set("Arenas." + arenaName + ".min", minNum);
							main.saveConfig();

							//send message to player saying minimum players was set
							player.sendMessage(ChatColor.GREEN + "Minimum players set to " + minNum + " for arena " + arenaName);

						} else {

							//display error message to user
							player.sendMessage(missingName);

						}

					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "max":

				//check if player has permission to set max players
				if(player.hasPermission("lt.max")) {

					if(args.length == 3) {

						String arenaName = args[1].toLowerCase();

						//check if arena name exist in config
						if(main.getConfig().contains("Arenas." + arenaName)) {

							int maxNum = Integer.parseInt(args[2]);

							//save max players to config
							main.getConfig().set("Arenas." + arenaName + ".max", maxNum);
							main.saveConfig();

							//send message to player saying maximum players was set
							player.sendMessage(ChatColor.GREEN + "Maximum players set to " + maxNum + " for arena " + arenaName);

						} else {

							//display error message to user
							player.sendMessage(missingName);

						}

					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "reload":

				//check if player has permission to reload config
				if(player.hasPermission("lt.reload")) {

					if(args.length == 1) {

						//reload config
						main.reloadConfig();
						main.saveConfig();

						//display message to player saying config was reloaded
						player.sendMessage(ChatColor.GREEN + "Laser tag config reloaded");

					} else {

						//display error message to user
						player.sendMessage(missingComponent);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "list":

				//check if player has permission to see arena list
				if(player.hasPermission("lt.list")) {

					if(args.length == 3) {

						if(args[1].equalsIgnoreCase("spawn")) {

							if(player.hasPermission("lt.list.spawns")) {

								if(main.getConfig().contains("Arenas." + args[2].toLowerCase())) {

									//get arenas from config and save them to arena list
									Set<String> spawns = main.getConfig().getConfigurationSection("Arenas." + args[2].toLowerCase() + ".spawn").getKeys(false);
									String[] spawnList = new String[spawns.size()];
									spawns.toArray(spawnList);

									//display arena list to user
									String spawnMsg = new String();
									for(String spawn : spawnList) {
										spawnMsg += spawn + ", ";
									}

									player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
									player.sendMessage(ChatColor.DARK_AQUA +  "Laser tag arena spawns");
									player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
									player.sendMessage(ChatColor.YELLOW + spawnMsg);

								} else {

									//display error message to user
									player.sendMessage(invalidArena);

								}

							} else {

								//display error message to user
								player.sendMessage(noPermission);
								player.sendMessage(systemAdmin);

							}

						} 

					} else {

						//get arenas from config and save them to arena list
						Set<String> arenas = main.getConfig().getConfigurationSection("Arenas").getKeys(false);
						String[] arenaList = new String[arenas.size()];
						arenas.toArray(arenaList);

						//display arena list to user
						String arenaMsg = new String();
						for(String arena : arenaList) {
							arenaMsg += arena + "\n";
						}

						player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
						player.sendMessage(ChatColor.DARK_AQUA +  "Laser tag arenas");
						player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
						player.sendMessage(ChatColor.YELLOW + arenaMsg.substring(0,1).toUpperCase() + arenaMsg.substring(1));

					}

				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "shop":

				//check if player has permission to open shop
				if(player.hasPermission("lt.shop")) {
					shop.createShop(player);
				} else {

					//display error message to user
					player.sendMessage(noPermission);
					player.sendMessage(systemAdmin);

				}

				break;

			case "add":

				if(args.length == 2) {

					//check to see if player did /lt add hub for the command
					if(args[1].equalsIgnoreCase("hub")) {

						//check to see if player has permission
						if(player.hasPermission("lt.add.hub")) {

							//save hub to config
							data.setHub(player);

							//send a message to the player about the hub being created
							player.sendMessage(ChatColor.GREEN + "Hub spawn created");

						} else {

							//display error message to user
							player.sendMessage(noPermission);
							player.sendMessage(systemAdmin);

						}

					} else {

						//display error message to user
						player.sendMessage(invalidCmd);
						player.sendMessage(helpMsg);

					}

				} else if(args.length == 3) {

					//check to see if player did /lt add spawn for the command
					if(args[1].equalsIgnoreCase("spawn")) {

						//check to see if player has permission to add spawn
						if(player.hasPermission("lt.add.spawn")) {

							//check if arena exist in the config
							if(main.getConfig().contains("Arenas." + args[2].toLowerCase())) {

								//save the spawn to the config for the arena
								data.setSpawn(player, args[2].toLowerCase());

								//send a message to the player about the spawn being created
								player.sendMessage(ChatColor.GREEN + "Spawn point created");

							} else {

								//display error message to user
								player.sendMessage(invalidArena);

							}

						} else {

							//display error message to user
							player.sendMessage(noPermission);
							player.sendMessage(systemAdmin);

						}

					} else if(args[1].equalsIgnoreCase("lobby")) { //check to see if player did /lt add lobby for the command

						//check to see if player has permission to add lobby
						if(player.hasPermission("lt.add.lobby")) {

							if(main.getConfig().contains("Arenas." + args[2].toLowerCase())) {

								//save the lobby to the config for the arena
								data.setLobby(player, args[2].toLowerCase());

								//send a message to the player about the lobby being created
								player.sendMessage(ChatColor.GREEN + "Lobby spawn created");

							} else {

								//display error message to user
								player.sendMessage(invalidArena);

							}

						} else {

							//display error message to user
							player.sendMessage(noPermission);
							player.sendMessage(systemAdmin);

						}

					} else {

						//display error message to user
						player.sendMessage(invalidCmd);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(missingComponent);
					player.sendMessage(helpMsg);

				}

				break;

			case "remove":

				if(args.length == 2) {

					if(args[1].equalsIgnoreCase("hub")) {

						//check to see if player has permission
						if(player.hasPermission("lt.remove.hub")) {

							//remove the hub
							main.getConfig().set("Hub", null);
							main.saveConfig();

							//display message to user
							player.sendMessage(ChatColor.GREEN + "Hub removed");

						} else {

							//display error message to user
							player.sendMessage(noPermission);
							player.sendMessage(systemAdmin);

						}

					} else {

						//display error message to user
						player.sendMessage(invalidCmd);
						player.sendMessage(helpMsg);

					}

				} else if (args.length == 3) {

					//check to see if player did /lt remove lobby [Arena Name] command
					if(args[1].equalsIgnoreCase("lobby")) {

						//check to see if player has permission
						if(player.hasPermission("lt.remove.lobby")) {

							//check if the arena exist in the config
							if(main.getConfig().contains("Arenas." + args[2].toLowerCase())) {

								//remove the lobby
								main.getConfig().set("Arenas." + args[2].toLowerCase() +".lobby", null);
								main.saveConfig();

								//display message to user
								player.sendMessage(ChatColor.GREEN + "Lobby removed");

							} else {

								//display error message to user
								player.sendMessage(invalidArena);

							}

						} else {

							//display error message to user
							player.sendMessage(noPermission);
							player.sendMessage(systemAdmin);

						}

					} else {

						//display error message to user
						player.sendMessage(invalidCmd);
						player.sendMessage(helpMsg);

					}

				} else if(args.length == 4) {

					//check if player did /lt remove spawn [Arena Name] [number]
					if(args[1].equalsIgnoreCase("spawn")) {

						//check to see if the player has permission for the command
						if(player.hasPermission("lt.remove.spawn")) {

							//check to see if the arena exist in the config
							if(main.getConfig().contains("Arenas." + args[2].toLowerCase())) {

								//check if spawn number exist in the config
								if(main.getConfig().contains("Arenas." + args[2].toLowerCase() +".spawn." + args[3])) {

									//remove the spawn
									main.getConfig().set("Arenas." + args[2].toLowerCase() +".spawn." + args[3], null);
									main.saveConfig();

									//display message to user
									player.sendMessage(ChatColor.RED + "Spawn removed");

								} else {

									//display error message to user
									player.sendMessage(ChatColor.RED + "Sorry, spawn number is invalid");

								}

							} else {

								//display error message to user
								player.sendMessage(invalidArena);

							}

						} else {

							//display error message to user
							player.sendMessage(noPermission);
							player.sendMessage(systemAdmin);

						}

					} else {

						//display error message to user
						player.sendMessage(invalidCmd);
						player.sendMessage(helpMsg);

					}

				} else {

					//display error message to user
					player.sendMessage(missingComponent);
					player.sendMessage(helpMsg);

				}

				break;

			}

		}

		return false;
	}

}
