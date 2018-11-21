package mc.cyberplex.LaserTag;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mc.cyberplex.LaserTag.arena.Arena;
import mc.cyberplex.LaserTag.arena.ArenaState;
import mc.cyberplex.LaserTag.arena.PlayerState;
import mc.cyberplex.LaserTag.kits.Shop;

public class Commands implements CommandExecutor{

	Main main = Main.getMain();
	Shop shop = new Shop();
	Arena data = new Arena();
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

		if(cmd.getName().equalsIgnoreCase("lt")) {

			switch (args[0].toLowerCase()) {

			case "help":
				helpCmd(player, args);
				break;

			case "join":
				joinCmd(player, args);
				break;

			case "leave":
				leaveCmd(player, args);
				break;

			case "start":
				startCmd(player, args);
				break;

			case "stop":
				stopCmd(player, args);
				break;

			case "create":				
				createCmd(player, args);
				break;

			case "delete":
				deleteCmd(player, args);
				break;

			case "min":
				minCmd(player, args);
				break;

			case "max":
				maxCmd(player, args);
				break;

			case "reload":
				reloadCmd(player, args);
				break;

			case "list":
				listCmd(player, args);
				break;

			case "shop":
				shopCmd(player, args);
				break;

			case "add":
				addCmd(player, args);
				break;

			case "remove":
				removeCmd(player, args);
				break;

			case "pos1":
				pos1Cmd(player, args);
				break;

			case "pos2":
				pos2Cmd(player, args);
				break;

			default:
				helpCmd(player, args);

			}

		}

		return false;
	}

	private void helpCmd(Player player, String[] args) {

		if(player.hasPermission("lt.help")) {

			if(args.length == 2) {

				if(Integer.parseInt(args[1]) == 2) {

					//Display help messages to user
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
					player.sendMessage(ChatColor.DARK_AQUA +  "Laser Tag Commands " + ChatColor.GREEN + "| pg 2 of 3");
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");					
					player.sendMessage(ChatColor.YELLOW + "/lt min [Arena Name] [Number]: " + ChatColor.GRAY + "Set the minimum number of players for an arena");
					player.sendMessage(ChatColor.YELLOW + "/lt max [Arena Name] [Number]: " + ChatColor.GRAY + "Set the maximum number of players for an arena");
					player.sendMessage(ChatColor.YELLOW + "/lt reload: " + ChatColor.GRAY + "Reload configuration file");
					player.sendMessage(ChatColor.YELLOW + "/lt list: " + ChatColor.GRAY + "Display laser tag arenas");
					player.sendMessage(ChatColor.YELLOW + "/lt list spawns [Arena Name]: " + ChatColor.GRAY + "Display the spawns for an arena");
					player.sendMessage(ChatColor.YELLOW + "/lt shop: " + ChatColor.GRAY + "Open laser tag shop");
					player.sendMessage(ChatColor.YELLOW + "/lt pos1 [Arena Name]: " + ChatColor.GRAY + "Set position 1 of an arena map");
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

				} else if(Integer.parseInt(args[1]) == 3) {

					//Display help messages to user
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
					player.sendMessage(ChatColor.DARK_AQUA +  "Laser Tag Commands " + ChatColor.GREEN + "| pg 3 of 3");
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
					player.sendMessage(ChatColor.YELLOW + "/lt pos2 [Arena Name]: " + ChatColor.GRAY + "Set position 2 of an arena map");
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
					player.sendMessage(ChatColor.YELLOW + "/lt delete [Arena Name]: " + ChatColor.GRAY + "Delete an arena");
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
				player.sendMessage(ChatColor.YELLOW + "/lt delete [Arena Name]: " + ChatColor.GRAY + "Delete an arena");
				player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

			}


		}

	}

	private void joinCmd(Player player, String[] args) {

		if(player.hasPermission("lt.join")) {

			boolean inGame = false;

			if(args.length == 2) {

				String arenaName = args[1].toLowerCase();

				int arenaNum = data.getArenaNum(arenaName);

				for(int count = 0; count < data.getArena(arenaNum).getGameCount(); count++) {

					if(data.getArena(arenaNum).getPlayer(count).equals(player.getUniqueId())) {
						inGame = true;								
					}

				}

			}

			if(inGame == true) {						
				player.sendMessage(ChatColor.RED + "Sorry, you are currently in an arena");
				player.sendMessage(ChatColor.RED + "Please do " + ChatColor.DARK_RED + "/lt leave " + ChatColor.RED + "before joining an arena" );
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

	}

	private void leaveCmd(Player player, String[] args) {

		if(player.hasPermission("lt.leave")) {

			boolean inArena = false;
			String arenaName = null;

			for(String arena : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arena);

				for(int count = 0; count < data.getArena(arenaNum).getGameCount(); count++) {

					if(data.getArena(arenaNum).getPlayer(count).equals(player.getUniqueId())) {
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

	}

	private void startCmd(Player player, String[] args) {

		if(player.hasPermission("lt.start")) {

			if(args.length == 2) {

				//check if the arena exist in the config
				if(main.getConfig().contains("Arenas." + args[1].toLowerCase())) {

					//check if the arena is currently running
					if(data.getState(args[1].toLowerCase()).equalsIgnoreCase("running")) {

						//display error message to user
						player.sendMessage(ChatColor.RED + "Sorry, the arena is currently running");

					} else {

						ArenaState state = new ArenaState();

						//start the arena
						state.start(args[1].toLowerCase());
						player.sendMessage(ChatColor.GREEN + "Arena " + args[1].toLowerCase() + " has been started");

					}

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

	}

	private void stopCmd(Player player, String[] args) {

		//check if player has permission to stop arena
		if(player.hasPermission("lt.stop")) {

			if(args.length == 2) {

				//check if arena name exist in config
				if(main.getConfig().contains("Arenas." + args[1].toLowerCase())) {

					if(data.getState(args[1].toLowerCase()).equalsIgnoreCase("waiting for players")) {

						//display error message to user
						player.sendMessage(ChatColor.RED + "Sorry, the arena is currently not running");

					} else {

						data.setState(args[1].toLowerCase(), "stopping");

						ArenaState state = new ArenaState();

						//stop the arena
						state.stop(args[1].toLowerCase());
						player.sendMessage(ChatColor.GREEN + "Arena " + args[1].toLowerCase() + " has been stopped");
					}

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

	}

	private void createCmd(Player player, String[] args) {

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
					data.addArena(arenaName);

					player.sendMessage(ChatColor.GREEN + "Laser tag arena " + arenaName + " created");

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

	}

	private void deleteCmd(Player player, String[] args) {

		//check if player has permission to delete
		if(player.hasPermission("lt.delete")) {

			if(args.length == 2) {

				String arenaName = args[1].toLowerCase();

				//check if arena name exist in config
				if(main.getConfig().contains("Arenas." + arenaName)) {

					//remove arena from config
					data.removeArena(arenaName);

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

	}

	private void minCmd(Player player, String[] args) {

		//check if player has permission to set min players
		if(player.hasPermission("lt.min")) {

			if(args.length == 3) {

				String arenaName = args[1].toLowerCase();

				//check if arena name exist in config
				if(main.getConfig().contains("Arenas." + arenaName)) {

					int minNum = Integer.parseInt(args[2]);

					//save min players to config
					data.setMinPlayers(arenaName, minNum);

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

	}

	private void maxCmd(Player player, String[] args) {

		//check if player has permission to set max players
		if(player.hasPermission("lt.max")) {

			if(args.length == 3) {

				String arenaName = args[1].toLowerCase();

				//check if arena name exist in config
				if(main.getConfig().contains("Arenas." + arenaName)) {

					int maxNum = Integer.parseInt(args[2]);

					//save max players to config
					data.setMaxPlayers(arenaName, maxNum);

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

	}

	private void reloadCmd(Player player, String[] args) {

		//check if player has permission to reload config
		if(player.hasPermission("lt.reload")) {

			if(args.length == 1) {

				ArenaState state = new ArenaState();

				for(String arenaName: main.getConfig().getConfigurationSection("Arenas").getKeys(false)){

					state.stop(arenaName);

				}

				//reload config
				main.reloadConfig();
				main.saveConfig();

				data.emptyArenaList();
				data.setArenaList();

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

	}

	private void listCmd(Player player, String[] args) {

		//check if player has permission to see arena list
		if(player.hasPermission("lt.list")) {

			if(args.length == 3) {

				if(args[1].equalsIgnoreCase("spawns")) {

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

	}

	private void shopCmd(Player player, String[] args) {

		//check if player has permission to open shop
		if(player.hasPermission("lt.shop")) {

			boolean inArena = false, gameRunning = false;
			int arenaNum = -1;

			for(String arena : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
				arenaNum = data.getArenaNum(arena);
				for(int count = 0; count < data.getArena(arenaNum).getGameCount(); count++) {
					if(data.getArena(arenaNum).getPlayer(count).equals(player.getUniqueId())) {
						inArena = true;

						if(data.getState(arena).equals("running")) {
							gameRunning = true;
						}

					}

				}

			}

			if(inArena == true && gameRunning == true) {
				player.sendMessage(ChatColor.RED + "Sorry, you can't use this command while in a game");
			} else {
				shop.createShop(player);
			}

		} else {

			//display error message to user
			player.sendMessage(noPermission);
			player.sendMessage(systemAdmin);

		}

	}

	private void addCmd(Player player, String[] args) {

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
						data.addSpawn(args[2].toLowerCase(), player);

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
						data.setLobby(args[2].toLowerCase(), player);

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

	}

	private void removeCmd(Player player, String[] args) {

		if(args.length == 2) {

			if(args[1].equalsIgnoreCase("hub")) {

				//check to see if player has permission
				if(player.hasPermission("lt.remove.hub")) {

					//remove the hub
					data.removeHub();

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
						data.removeLobby(args[2].toLowerCase());

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
							data.removeSpawn(args[2].toLowerCase(), Integer.parseInt(args[3]));

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

	}

	private void pos1Cmd(Player player, String[] args) {

		if(player.hasPermission("lt.pos1")) {

			if(args.length == 2) {

				String arenaName = args[1].toLowerCase();

				if(main.getConfig().contains("Arenas." + arenaName)) {
					data.setPos1(arenaName, player);
					player.sendMessage(ChatColor.GREEN + "Position 1 set for " + arenaName + " arena");
				} else {
					player.sendMessage(invalidArena);
				}

			} else {
				player.sendMessage(missingComponent);
				player.sendMessage(helpMsg);
			}

		} else {
			player.sendMessage(noPermission);
			player.sendMessage(systemAdmin);
		}

	}

	private void pos2Cmd(Player player, String[] args) {

		if(player.hasPermission("lt.pos2")) {

			if(args.length == 2) {

				String arenaName = args[1].toLowerCase();

				if(main.getConfig().contains("Arenas." + arenaName)) {
					data.setPos2(arenaName, player);
					player.sendMessage(ChatColor.GREEN + "Position 2 set for " + arenaName + " arena");
				} else {
					player.sendMessage(invalidArena);
				}

			} else {
				player.sendMessage(missingComponent);
				player.sendMessage(helpMsg);
			}

		} else {
			player.sendMessage(noPermission);
			player.sendMessage(systemAdmin);
		}

	}

}
