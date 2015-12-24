package org.topdank.minenet.protocols.v47;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.topdank.bot.auth.AuthenticationException;
import org.topdank.bot.eventbus.EventBus;
import org.topdank.bot.eventbus.EventPriority;
import org.topdank.bot.eventbus.EventTarget;
import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.connection.ConnectedEvent;
import org.topdank.bot.net.event.disconnect.DisconnectedEvent;
import org.topdank.bot.net.event.disconnect.DisconnectingEvent;
import org.topdank.bot.net.event.disconnect.TimeoutEvent;
import org.topdank.bot.net.event.packet.PacketReceivedEvent;
import org.topdank.bot.net.event.packet.PacketSentEvent;
import org.topdank.bot.net.packet.IdentifiablePacket;
import org.topdank.bot.net.packet.ReadablePacket;
import org.topdank.bot.net.packet.encryption.EncryptionProtocol;
import org.topdank.bot.net.packet.header.DefaultPacketHeader;
import org.topdank.bot.net.packet.header.PacketHeader;
import org.topdank.mc.authyggdrasil.YggdrasilSession;
import org.topdank.mc.bot.MCClient;
import org.topdank.mc.bot.MCProtocol;
import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.item.BasicItemStack;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.chunk.Chunk;
import org.topdank.mc.bot.api.world.settings.WorldSettings;
import org.topdank.mc.bot.impl.entity.living.LivingEntity;
import org.topdank.mc.bot.impl.entity.living.player.LocalPlayer;
import org.topdank.mc.bot.impl.entity.living.player.PlayerEntity;
import org.topdank.mc.bot.impl.entity.object.ObjectEntity;
import org.topdank.mc.bot.impl.event.PluginMessageEvent;
import org.topdank.mc.bot.impl.event.internal.player.RequestRespawnEvent;
import org.topdank.mc.bot.impl.event.internal.world.InternalBlockChangeEvent;
import org.topdank.mc.bot.impl.event.internal.world.InternalMultiChunkLoadEvent;
import org.topdank.mc.bot.impl.event.spawn.LivingEntitySpawnEvent;
import org.topdank.mc.bot.impl.event.spawn.ObjectEntitySpawnEvent;
import org.topdank.mc.bot.impl.event.spawn.PlayerSpawnEvent;
import org.topdank.mc.bot.impl.event.world.TimeUpdateEvent;
import org.topdank.minenet.protocols.v47.packets.handshake.client.PacketHC00Handshake;
import org.topdank.minenet.protocols.v47.packets.login.client.PacketLC00LoginStart;
import org.topdank.minenet.protocols.v47.packets.login.client.PacketLC01EncryptionResponse;
import org.topdank.minenet.protocols.v47.packets.login.server.PacketLS00Disconnect;
import org.topdank.minenet.protocols.v47.packets.login.server.PacketLS01EncryptionRequest;
import org.topdank.minenet.protocols.v47.packets.login.server.PacketLS02LoginSuccess;
import org.topdank.minenet.protocols.v47.packets.login.server.PacketLS03SetCompression;
import org.topdank.minenet.protocols.v47.packets.play.PacketPS02C01ChatMessage;
import org.topdank.minenet.protocols.v47.packets.play.PacketPS32C0FConfirmTransaction;
import org.topdank.minenet.protocols.v47.packets.play.PacketPS3FC17PluginMessage;
import org.topdank.minenet.protocols.v47.packets.play.PacketPSC00KeepAlive;
import org.topdank.minenet.protocols.v47.packets.play.client.PacketPC11EnchantItem;
import org.topdank.minenet.protocols.v47.packets.play.client.PacketPC15ClientSettings;
import org.topdank.minenet.protocols.v47.packets.play.client.PacketPC16ClientStatus;
import org.topdank.minenet.protocols.v47.packets.play.client.PacketPC16ClientStatus.ClientStatusAction;
import org.topdank.minenet.protocols.v47.packets.play.client.PacketPC18Spectate;
import org.topdank.minenet.protocols.v47.packets.play.client.PacketPC19ResourcePackStatus;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.PacketPC02UseEntity;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.PacketPC0BEntityAction;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.PacketPC14TabComplete;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC03PlayerUpdate;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC04PlayerMove;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC05PlayerLook;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC06PlayerMoveLook;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC07PlayerDigging;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC08BlockPlace;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC09SwitchItem;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC0ASwingArm;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC0CSteerVehicle;
import org.topdank.minenet.protocols.v47.packets.play.client.entity.player.PacketPC13PlayerAbilities;
import org.topdank.minenet.protocols.v47.packets.play.client.window.PacketPC0DCloseWindow;
import org.topdank.minenet.protocols.v47.packets.play.client.window.PacketPC0EClickWindow;
import org.topdank.minenet.protocols.v47.packets.play.client.window.PacketPC10CreativeInventoryAction;
import org.topdank.minenet.protocols.v47.packets.play.client.world.PacketPC12UpdateSign;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS04EntityEquipment;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS0BAnimation;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS0DCollectItem;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS0ESpawnObject;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS0FSpawnMob;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS13DestroyEntities;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS1AEntityStatus;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS1BEntityAttach;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS1CEntityMetadata;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS1DEntityEffect;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS1ERemoveEntityEffect;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS20EntityPropertyUpdate;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.PacketPS49UpdateEntityNBT;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.movement.PacketPS12EntityVelocity;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.movement.PacketPS15EntityMove;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.movement.PacketPS16EntityLook;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.movement.PacketPS17EntityLookMove;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.movement.PacketPS18EntityTeleport;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.movement.PacketPS19EntityHeadLook;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS01JoinGame;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS06UpdateHealth;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS08PositionRotation;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS0AUseBed;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS0CSpawnPlayer;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS11SpawnExpOrb;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS1FSetExperience;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS38PlayerList;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS38PlayerList.CompletePlayerListEntry;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPS39PlayerAbilities;
import org.topdank.minenet.protocols.v47.packets.play.server.entity.player.PacketPSC09HeldItemChange;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS07Respawn;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS28Effect;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS2BGameStateChange;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS37Statistics;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS3ATabComplete;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS40Disconnect;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS42CombatEvent;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS43Camera;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS45Title;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS46SetCompression;
import org.topdank.minenet.protocols.v47.packets.play.server.general.PacketPS48ResourcePackSend;
import org.topdank.minenet.protocols.v47.packets.play.server.scoreboard.PacketPS3BScoreboardObjective;
import org.topdank.minenet.protocols.v47.packets.play.server.scoreboard.PacketPS3CUpdateScore;
import org.topdank.minenet.protocols.v47.packets.play.server.scoreboard.PacketPS3DDisplayScoreboard;
import org.topdank.minenet.protocols.v47.packets.play.server.scoreboard.PacketPS3ETeams;
import org.topdank.minenet.protocols.v47.packets.play.server.sound.PacketPS29SoundEffect;
import org.topdank.minenet.protocols.v47.packets.play.server.window.PacketPS2DOpenWindow;
import org.topdank.minenet.protocols.v47.packets.play.server.window.PacketPS2EWindowClose;
import org.topdank.minenet.protocols.v47.packets.play.server.window.PacketPS2FSetSlot;
import org.topdank.minenet.protocols.v47.packets.play.server.window.PacketPS30WindowItems;
import org.topdank.minenet.protocols.v47.packets.play.server.window.PacketPS31WindowProperty;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS03TimeUpdate;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS05SpawnLocation;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS21MapChunkData;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS22MultiBlockChange;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS23BlockChange;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS24BlockAction;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS25BlockBreakAnimation;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS26MultiMapChunkData;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS27Explosion;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS2ASpawnParticle;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS2CSpawnGlobalEntity;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS33UpdateSign;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS34MapData;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS35UpdateBlockEntity;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS36SignEditorOpen;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS41ServerDifficulty;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS44WorldBorder;
import org.topdank.minenet.protocols.v47.packets.play.server.world.PacketPS47PlayerListHeaderFooter;

public class Protocol47 extends MCProtocol {

	protected PacketHeader header;
	protected EncryptionProtocol encryption;
	protected SecretKey secretKey;

	protected MCClient client;
	protected EventBus bus;
	protected ClientState state;

	private BotContext context;

	// protected PlayerController playerController = new
	// Protocol47PlayerController();
	// protected WorldController worldController;

	@Override
	public void init(Client<?> client) {
		if (!(client instanceof MCClient))
			throw new UnsupportedOperationException("Protocol47 requires a valid MCClient.");

		this.client = (MCClient) client;

		header = new DefaultPacketHeader();

		setState(ClientState.HANDSHAKE);

		bus = client.getEventBus();
		bus.register(this);
	}

	public void setState(ClientState state) {
		switch (this.state = state) {
			case HANDSHAKE:
				initHandshakePackets();
				break;
			case LOGIN:
				initLoginPackets();
				break;
			case PLAY:
				initPlayPackets();
				break;
		}
	}

	private void initHandshakePackets() {
		registerOutgoing(0x00, PacketHC00Handshake.class);
	}

	private void initLoginPackets() {
		registerOutgoing(0x00, PacketLC00LoginStart.class);
		registerOutgoing(0x01, PacketLC01EncryptionResponse.class);

		registerIncoming(0x00, PacketLS00Disconnect.class);
		registerIncoming(0x01, PacketLS01EncryptionRequest.class);
		registerIncoming(0x02, PacketLS02LoginSuccess.class);
		registerIncoming(0x03, PacketLS03SetCompression.class);
	}

	private void initPlayPackets() {
		registerIncoming(0x00, PacketPSC00KeepAlive.class);
		registerOutgoing(0x00, PacketPSC00KeepAlive.class);
		registerIncoming(0x01, PacketPS01JoinGame.class);
		registerIncoming(0x02, PacketPS02C01ChatMessage.class);
		registerIncoming(0x03, PacketPS03TimeUpdate.class);
		registerIncoming(0x04, PacketPS04EntityEquipment.class);
		registerIncoming(0x05, PacketPS05SpawnLocation.class);
		registerIncoming(0x06, PacketPS06UpdateHealth.class);
		registerIncoming(0x07, PacketPS07Respawn.class);
		registerIncoming(0x08, PacketPS08PositionRotation.class);
		registerIncoming(0x09, PacketPSC09HeldItemChange.class);
		registerIncoming(0x0A, PacketPS0AUseBed.class);
		registerIncoming(0x0B, PacketPS0BAnimation.class);
		registerIncoming(0x0C, PacketPS0CSpawnPlayer.class);
		registerIncoming(0x0D, PacketPS0DCollectItem.class);
		registerIncoming(0x0E, PacketPS0ESpawnObject.class);
		registerIncoming(0x0F, PacketPS0FSpawnMob.class);
		registerIncoming(0x11, PacketPS11SpawnExpOrb.class);
		registerIncoming(0x12, PacketPS12EntityVelocity.class);
		registerIncoming(0x13, PacketPS13DestroyEntities.class);
		// registerIncoming(0x14, entitymovebase); never received
		registerIncoming(0x15, PacketPS15EntityMove.class);
		registerIncoming(0x16, PacketPS16EntityLook.class);
		registerIncoming(0x17, PacketPS17EntityLookMove.class);
		registerIncoming(0x18, PacketPS18EntityTeleport.class);
		registerIncoming(0x19, PacketPS19EntityHeadLook.class);
		registerIncoming(0x1A, PacketPS1AEntityStatus.class);
		registerIncoming(0x1B, PacketPS1BEntityAttach.class);
		registerIncoming(0x1C, PacketPS1CEntityMetadata.class);
		registerIncoming(0x1D, PacketPS1DEntityEffect.class);
		registerIncoming(0x1E, PacketPS1ERemoveEntityEffect.class);
		registerIncoming(0x1F, PacketPS1FSetExperience.class);
		registerIncoming(0x20, PacketPS20EntityPropertyUpdate.class);
		registerIncoming(0x21, PacketPS21MapChunkData.class);
		registerIncoming(0x22, PacketPS22MultiBlockChange.class);
		registerIncoming(0x23, PacketPS23BlockChange.class);
		registerIncoming(0x24, PacketPS24BlockAction.class);
		registerIncoming(0x25, PacketPS25BlockBreakAnimation.class);// TODO:
																	// proper
		registerIncoming(0x26, PacketPS26MultiMapChunkData.class);
		registerIncoming(0x27, PacketPS27Explosion.class);
		registerIncoming(0x28, PacketPS28Effect.class);
		registerIncoming(0x29, PacketPS29SoundEffect.class);
		registerIncoming(0x2A, PacketPS2ASpawnParticle.class);
		registerIncoming(0x2B, PacketPS2BGameStateChange.class);
		registerIncoming(0x2C, PacketPS2CSpawnGlobalEntity.class);
		registerIncoming(0x2D, PacketPS2DOpenWindow.class);
		registerIncoming(0x2E, PacketPS2EWindowClose.class);
		registerIncoming(0x2F, PacketPS2FSetSlot.class);
		registerIncoming(0x30, PacketPS30WindowItems.class);
		registerIncoming(0x31, PacketPS31WindowProperty.class);
		registerIncoming(0x32, PacketPS32C0FConfirmTransaction.class);
		registerIncoming(0x33, PacketPS33UpdateSign.class);
		registerIncoming(0x34, PacketPS34MapData.class);
		registerIncoming(0x35, PacketPS35UpdateBlockEntity.class);
		registerIncoming(0x36, PacketPS36SignEditorOpen.class);
		registerIncoming(0x37, PacketPS37Statistics.class);
		registerIncoming(0x38, PacketPS38PlayerList.class);
		registerIncoming(0x39, PacketPS39PlayerAbilities.class);
		registerIncoming(0x3A, PacketPS3ATabComplete.class);
		registerIncoming(0x3B, PacketPS3BScoreboardObjective.class);
		registerIncoming(0x3C, PacketPS3CUpdateScore.class);
		registerIncoming(0x3D, PacketPS3DDisplayScoreboard.class);
		registerIncoming(0x3E, PacketPS3ETeams.class);
		registerIncoming(0x3F, PacketPS3FC17PluginMessage.class);
		registerIncoming(0x40, PacketPS40Disconnect.class);
		registerIncoming(0x41, PacketPS41ServerDifficulty.class);
		registerIncoming(0x42, PacketPS42CombatEvent.class);
		registerIncoming(0x43, PacketPS43Camera.class);
		registerIncoming(0x44, PacketPS44WorldBorder.class);
		registerIncoming(0x45, PacketPS45Title.class);
		registerIncoming(0x46, PacketPS46SetCompression.class);
		registerIncoming(0x47, PacketPS47PlayerListHeaderFooter.class);
		registerIncoming(0x48, PacketPS48ResourcePackSend.class);
		registerIncoming(0x49, PacketPS49UpdateEntityNBT.class);

		//
		//
		// client to server
		//
		//

		registerOutgoing(0x01, PacketPS02C01ChatMessage.class);
		registerOutgoing(0x02, PacketPC02UseEntity.class);
		registerOutgoing(0x03, PacketPC03PlayerUpdate.class);
		registerOutgoing(0x04, PacketPC04PlayerMove.class);
		registerOutgoing(0x05, PacketPC05PlayerLook.class);
		registerOutgoing(0x06, PacketPC06PlayerMoveLook.class);
		registerOutgoing(0x07, PacketPC07PlayerDigging.class);
		registerOutgoing(0x08, PacketPC08BlockPlace.class);
		registerOutgoing(0x09, PacketPC09SwitchItem.class);
		registerOutgoing(0x0A, PacketPC0ASwingArm.class);
		registerOutgoing(0x0B, PacketPC0BEntityAction.class);
		registerOutgoing(0x0C, PacketPC0CSteerVehicle.class);
		registerOutgoing(0x0D, PacketPC0DCloseWindow.class);
		registerOutgoing(0x0E, PacketPC0EClickWindow.class);
		registerOutgoing(0x0F, PacketPS32C0FConfirmTransaction.class);
		registerOutgoing(0x10, PacketPC10CreativeInventoryAction.class);
		registerOutgoing(0x11, PacketPC11EnchantItem.class);
		registerOutgoing(0x12, PacketPC12UpdateSign.class);
		registerOutgoing(0x13, PacketPC13PlayerAbilities.class);
		registerOutgoing(0x14, PacketPC14TabComplete.class);
		registerOutgoing(0x15, PacketPC15ClientSettings.class);
		registerOutgoing(0x16, PacketPC16ClientStatus.class);
		registerOutgoing(0x17, PacketPS3FC17PluginMessage.class);
		registerOutgoing(0x18, PacketPC18Spectate.class);
		registerOutgoing(0x19, PacketPC19ResourcePackStatus.class);
	}

	@Override
	public void onConnect(ConnectedEvent event) {
		Client<?> client = event.getClient();

		if (!(client.getSession() instanceof YggdrasilSession)) {
			try {
				client.disconnect("Must be logged in with a Yggdrasil session.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		if (state == ClientState.HANDSHAKE) {
			client.send(new PacketHC00Handshake(client.getProtocol().getProtocolVersion(), client.getHost(), client.getPort(), 2));
			setState(ClientState.LOGIN);
			client.send(new PacketLC00LoginStart(client.getSession().getUserID()));
		}
	}

	private Map<String, CompletePlayerListEntry> listEntryCache = new HashMap<String, CompletePlayerListEntry>();

	@Override
	public void onPacketReceived(PacketReceivedEvent event) {
		MCClient client = (MCClient) event.getClient();
		ReadablePacket p = event.getPacket();
		// System.out.println("got " + p.getClass().getSimpleName());
		int id = ((IdentifiablePacket) p).getId();

		// System.out.println("got " + p.getClass().getSimpleName());

		switch (state) {
			case HANDSHAKE: {
				// never called
				throw new IllegalArgumentException("WatReceived: " + event.getPacket().getClass().getSimpleName());
			}
			case LOGIN:
				switch (id) {
					case 0x01: { // PacketLS01EncryptionRequest
						try {
							KeyGenerator gen = KeyGenerator.getInstance("AES");
							gen.init(128);
							secretKey = gen.generateKey();
						} catch (NoSuchAlgorithmException e) {
							throw new Error("Failed to generate shared key.", e);
						}

						PublicKey publicKey = ((PacketLS01EncryptionRequest) p).getPublicKey();
						byte[] verifyToken = ((PacketLS01EncryptionRequest) p).getVerifyToken();

						String serverHash = new BigInteger(Protocol47CryptoHelper.generateServerHash(((PacketLS01EncryptionRequest) p).getServerId(), publicKey, secretKey)).toString(16);

						try {
							YggdrasilSession session = (YggdrasilSession) client.getSession();
							session.joinServer(serverHash);
						} catch (AuthenticationException e) {
							e.printStackTrace();
						}

						client.send(new PacketLC01EncryptionResponse(secretKey, publicKey, verifyToken));
						encryption = Protocol47CryptoHelper.createEncryption(secretKey);
						break;
					}
					case 0x02: { // PacketLS02LoginSuccess
						PacketLS02LoginSuccess pls = (PacketLS02LoginSuccess) p;
						System.out.println("succ " + pls.getName() + " " + pls.getUUID());
						setState(ClientState.PLAY);
						break;
					}
					case 0x03: { // PacketLS03SetCompression
						PacketLS03SetCompression compressionPacket = (PacketLS03SetCompression) event.getPacket();
						client.setCompression(compressionPacket.getThreshold());
						System.out.println("comp[ress: " + compressionPacket.getThreshold());
						break;
					}
				}
				break;
			case PLAY:
				// if (p instanceof PacketPSBaseMove) {
				// if (((PacketPSBaseMove) p).getEntityId() ==
				// context.getWorld().getPlayerByName("Bibl_").getId())
				// System.out.println(p);
				// }
				switch (id) {
					case 0x00: { // PacketPSC00KeepAlive
						PacketPSC00KeepAlive pk = (PacketPSC00KeepAlive) p;
						client.send(pk);
						break;
					}
					case 0x01: { // PacketPS01JoinGame
						PacketPS01JoinGame pj = (PacketPS01JoinGame) p;
						context = new Protocol47BotContext(client, bus);
						client.setBotContext(context);
						WorldSettings settings = new WorldSettings(255, pj.getDifficulty(), pj.isHardcore(), pj.getDimension(), pj.getGameMode(), pj.getWorldType(), pj.getMaxPlayers(), null);
						DefaultMinecraftWorld world = new DefaultMinecraftWorld(context, settings);
						context.setWorld(world);
						LocalPlayer player = new LocalPlayer(context, pj.getEntityId(), client.getSession().getUserID());
						world.spawnEntity(player);
						context.getTaskManager().start();
						joined = true;
						break;
					}
					case 0x02: { // PacketPS02ChatMessage
						PacketPS02C01ChatMessage pm = (PacketPS02C01ChatMessage) p;
						System.out.println(pm.getMessage());
						break;
					}
					case 0x03: { // PacketPS03TimeUpdate
						PacketPS03TimeUpdate pt = (PacketPS03TimeUpdate) p;
						bus.dispatch(new TimeUpdateEvent(pt.getTime(), pt.getWorldAge()));
						break;
					}
					case 0x04: { // PacketPS04EntityEquipment
						PacketPS04EntityEquipment pee = (PacketPS04EntityEquipment) p;
						Entity peEntity = context.getWorld().getEntityById(pee.getEntityId());
						if ((peEntity == null) || !(peEntity instanceof LivingEntity))
							break;
						((LivingEntity) peEntity).setWornItemAt(pee.getSlot(), pee.getItem());
						break;
					}
					case 0x05: { // PacketPS05SpawnLocation
						PacketPS05SpawnLocation pl = (PacketPS05SpawnLocation) p;
						context.getWorld().getSettings().setSpawnLocation(pl.getLocation());
						break;
					}
					case 0x06: { // PacketPS06UpdateHealth
						PacketPS06UpdateHealth puh = (PacketPS06UpdateHealth) p;
						LocalPlayer foodPlayer = context.getWorld().getLocalPlayer();
						foodPlayer.setHealth(puh.getHealth());
						foodPlayer.setHunger(puh.getFood());
						foodPlayer.setFoodSaturation(puh.getFoodSaturation());
						break;
					}
					case 0x07: { // PacketPS07Respawn
						PacketPS07Respawn pr = (PacketPS07Respawn) p;
						WorldSettings settings = context.getWorld().getSettings();
						settings.setDimension(pr.getDimension()).setDifficulty(pr.getDifficulty()).setGameMode(pr.getGameMode()).setWorldType(pr.getWorldType());
						break;
					}
					case 0x08: { // PacketPS08PositionRotation
						fixing = true;

						PacketPS08PositionRotation pp = (PacketPS08PositionRotation) p;
						LocalPlayer player = context.getWorld().getLocalPlayer();

						System.out.println(String.format("Pre fix pos x:%.3f, y:%.3f, z:%.3f, motX:%.3f, motY:%.3f, motZ:%.3f, pitch:%.3f, yaw:%.3f", player.getX(), player.getY(),
								player.getZ(), player.getMotionX(), player.getMotionY(), player.getMotionZ(), player.getPitch(), player.getYaw()));
						System.out.println(String.format("Post fix pos x:%.3f y:%.3f z:%.3f pitch:%.3f yaw:%.3f rr:%s", pp.getX(), pp.getY(), pp.getZ(), pp.getPitch(), pp.getYaw(),
								Arrays.toString(pp.getRelativeStates())));
						// X 0x01
						// Y 0x02
						// Z 0x04
						// Y_ROT 0x08
						// X_ROT 0x10
						boolean[] r = pp.getRelativeStates();
						if (!r[0])
							player.setX(pp.getX());
						else
							player.setX(player.getX() + pp.getX());

						if (!r[1])
							player.setY(pp.getY());
						else
							player.setY(player.getY() + pp.getY());

						if (!r[2])
							player.setZ(pp.getZ());
						else
							player.setZ(player.getZ() + pp.getZ());

						// if (!r[3])
						// player.setPitch(player.getPitch() + pp.getPitch());
						// else
						player.setPitch(player.getPitch());

						// if (!r[4])
						// player.setYaw(player.getYaw() + pp.getYaw());
						// else
						player.setYaw(player.getYaw());

						fixing = false;
						break;
					}
					case 0x09: { // PacketPSC09HeldItemChange
						PacketPSC09HeldItemChange ph = (PacketPSC09HeldItemChange) p;
						context.getWorld().getLocalPlayer().switchHeldItems(ph.getSlot());
						break;
					}
					case 0x0A: { // PacketPS0AUseBed
						PacketPS0AUseBed pub = (PacketPS0AUseBed) p;
						Entity e = context.getWorld().getEntityById(pub.getEntityId());
						if ((e != null) && (e instanceof PlayerEntity)) {
							((PlayerEntity) e).setBedLocation(pub.getLocation());
						} else {
							System.out.println("Bed location for " + e + " ?");
						}
						break;
					}
					case 0x0B: { // PacketPS0BAnimation
						PacketPS0BAnimation pea = (PacketPS0BAnimation) p;
						Entity paEntity = context.getWorld().getEntityById(pea.getEntityId());
						if ((paEntity != null) && (paEntity instanceof LivingEntity))
							((LivingEntity) paEntity).setAnimation(pea.getAnimation());
						else if ((paEntity != null) && !(paEntity instanceof LivingEntity))
							System.out.println("wtf bitch");
						break;
					}
					case 0x0C: { // PacketPS0CSpawnPlayer
						PacketPS0CSpawnPlayer psp = (PacketPS0CSpawnPlayer) p;
						CompletePlayerListEntry listEntry = listEntryCache.get(psp.getUUID().toString());
						String playerName = listEntry.getUsername();
						PlayerEntity playerEntity = new PlayerEntity(context.getWorld(), psp.getEntityId(), playerName, listEntry.getGameMode());
						playerEntity.setLocation(psp.getX(), psp.getY(), psp.getZ());
						playerEntity.setPitch(psp.getPitch());
						playerEntity.setYaw(psp.getYaw());
						playerEntity.setHeldItem(new BasicItemStack(psp.getCurrentItem(), 1, 0));
						playerEntity.updateMetadata(psp.getMetadata());
						context.getWorld().spawnEntity(playerEntity);
						bus.dispatch(new PlayerSpawnEvent(playerEntity));
						break;
					}
					case 0x0D: { // PacketPS0DCollectItem
						PacketPS0DCollectItem pci = (PacketPS0DCollectItem) p;
						EntityHandler handler = context.getWorld();
						Entity collector = handler.getEntityById(pci.getCollectorId());
						if (collector != null) {
							Entity collected = handler.getEntityById(pci.getCollectedId());
							if (collected != null) {
								if (collected instanceof DroppedItemEntity) {

								}
							}
						}
						break;
					}
					case 0x0E: { // PacketPS0ESpawnObject
						PacketPS0ESpawnObject pso = (PacketPS0ESpawnObject) p;
						World world = context.getWorld();
						ObjectEntity objectEntity = world.getObjectEntityFactory().create(pso.getTypeId(), world, pso.getEntityId());
						objectEntity.setLocation(pso.getX(), pso.getY(), pso.getZ());
						objectEntity.setPitch(pso.getPitch());
						objectEntity.setYaw(pso.getYaw());
						objectEntity.setMotion(pso.getMotX(), pso.getMotY(), pso.getMotZ());
						world.spawnEntity(objectEntity);
						bus.dispatch(new ObjectEntitySpawnEvent(objectEntity));
						break;
					}
					case 0x0F: { // PacketPS0FSpawnMob
						PacketPS0FSpawnMob psm = (PacketPS0FSpawnMob) p;
						DefaultMinecraftWorld world = context.getWorld();
						LivingEntity livingEntity = world.getLivingEntityFactory().create(psm.getTypeId(), world, psm.getEntityId());
						livingEntity.setLocation(psm.getX(), psm.getY(), psm.getZ());
						livingEntity.setPitch(psm.getPitch());
						livingEntity.setYaw(psm.getYaw());
						livingEntity.setHeadYaw(psm.getHeadYaw());
						livingEntity.setMotion(psm.getMotionX(), psm.getMotionY(), psm.getMotionZ());
						world.spawnEntity(livingEntity);
						bus.dispatch(new LivingEntitySpawnEvent(livingEntity));
						break;
					}
					case 0x12: { // PacketPS12EntityVelocity
						PacketPS12EntityVelocity pev = (PacketPS12EntityVelocity) p;
						Entity vEntity = context.getWorld().getEntityById(pev.getEntityId());
						if (vEntity != null) {
							vEntity.setMotion(pev.getMotionX(), pev.getMotionY(), pev.getMotionZ());
						}
						break;
					}
					case 0x13: { // PacketPS13DestroyEntities
						PacketPS13DestroyEntities pde = (PacketPS13DestroyEntities) p;
						// dont use event, just
						DefaultMinecraftWorld world = context.getWorld();
						for (int dEntityId : pde.getEntityIds()) {
							world.despawnEntity(dEntityId);
							Entity e1 = world.getEntityById(dEntityId);
							if (e1 != null)
								e1.setDead(true);
						}
						break;
					}
					case 0x15: { // PacketPS15EntityMove
						PacketPS15EntityMove pem = (PacketPS15EntityMove) p;
						DefaultMinecraftWorld world = context.getWorld();
						Entity entity = world.getEntityById(pem.getEntityId());
						if (entity != null) {
							entity.setMotion(pem.getMovementX(), pem.getMovementY(), pem.getMovementZ());
						}
						// bus.dispatch(new EntityMoveEvent(pem.getEntityId(),
						// pem.getMovementX(), pem.getMovementY(),
						// pem.getMovementZ()));
						break;
					}
					case 0x16: { // PacketPS16EntityLook
						PacketPS16EntityLook pel = (PacketPS16EntityLook) p;
						DefaultMinecraftWorld world = context.getWorld();
						Entity entity = world.getEntityById(pel.getEntityId());
						if (entity != null) {
							entity.setPitch(pel.getPitch());
							entity.setYaw(pel.getYaw());
						}
						// bus.dispatch(new EntityRotateEvent(pel.getEntityId(),
						// pel.getYaw(), pel.getPitch()));
						break;
					}
					case 0x17: { // PacketPS17EntityLookMove
						PacketPS17EntityLookMove pelm = (PacketPS17EntityLookMove) p;
						Entity entity = context.getWorld().getEntityById(pelm.getEntityId());
						if (entity == null)
							return;
						entity.setLocation(entity.getX() + pelm.getMovementX(), entity.getY() + pelm.getMovementY(), entity.getZ() + pelm.getMovementZ());
						entity.setPitch(pelm.getPitch());
						entity.setYaw(pelm.getYaw());
						// too slow to do a look and move at the same time
						// bus.dispatch(new EntityMoveRotateEvent(pelm.getId(),
						// pelm.getMovementX(), pelm.getMovementY(),
						// pelm.getMovementZ(), pelm.getYaw(),
						// pelm.getPitch()));
						break;
					}
					case 0x18: { // PacketPS18EntityTeleport
						PacketPS18EntityTeleport pet = (PacketPS18EntityTeleport) p;
						Entity entity = context.getWorld().getEntityById(pet.getEntityId());
						if (entity != null) {
							entity.setLocation(pet.getX(), pet.getY(), pet.getZ());
							entity.setOnGround(pet.isOnGround());
						}
						break;
					}
					case 0x19: { // PacketPS19EntityHeadLook
						PacketPS19EntityHeadLook pehl = (PacketPS19EntityHeadLook) p;
						Entity entity = context.getWorld().getEntityById(pehl.getEntityId());
						if (entity != null) {
							entity.setHeadYaw(pehl.getHeadYaw());
						}
						break;
					}
					case 0x1A: { // PacketPS1AEntityStatus
						// TODO: implement

						PacketPS1AEntityStatus pes = (PacketPS1AEntityStatus) p;
						Entity pesEntity = context.getWorld().getEntityById(pes.getEntityId());
						// if (pesEntity == null)
						// break;
						// switch (pes.getStatus()) {
						//
						// }
						if (pesEntity instanceof PlayerEntity) {
							System.out.println("stat: " + pes.getStatus());
						}
						break;
					}
					case 0x1B: { // PacketPS1BEntityAttack
						PacketPS1BEntityAttach pae = (PacketPS1BEntityAttach) p;
						Entity paeEntity = context.getWorld().getEntityById(pae.getEntityId());
						if (paeEntity == null)
							break;
						if (pae.getVehicleId() == -1) {
							paeEntity.setRiding(null);
						} else {
							Entity vehicleEntity = context.getWorld().getEntityById(pae.getVehicleId());
							if (vehicleEntity != null) {
								vehicleEntity.setRider(paeEntity);
								paeEntity.setRiding(vehicleEntity);
							}
						}
						break;
					}
					case 0x1C: { // PacketPS1CEntityMetadata
						PacketPS1CEntityMetadata pem = (PacketPS1CEntityMetadata) p;
						Entity entity = context.getWorld().getEntityById(pem.getEntityId());
						if (entity != null) {
							entity.updateMetadata(pem.getMetadata());
						}
						break;
					}
					case 0x1F: { // PacketPS1FSetExperience
						PacketPS1FSetExperience pse = (PacketPS1FSetExperience) p;
						LocalPlayer pl = context.getWorld().getLocalPlayer();
						pl.setExperienceLevel(pse.getLevel());
						pl.setExperienceTotal(pse.getTotalXP());
						System.out.println("total xp: " + pse.getTotalXP());
						break;
					}
					case 0x20: { // PacketPS20EntityPropertyUpdate
						PacketPS20EntityPropertyUpdate pepu = (PacketPS20EntityPropertyUpdate) p;
						Entity pepuEntity = context.getWorld().getEntityById(pepu.getEntityId());
						if (pepuEntity != null)
							pepuEntity.updateProperty(pepu.getProperties());
						break;
					}
					case 0x21: { // PacketPS21MapChunkData
						PacketPS21MapChunkData pmc = (PacketPS21MapChunkData) p;

						Chunk[] chunks = pmc.getChunks();
						int length = chunks.length;
						int x = pmc.getX();
						int z = pmc.getZ();
						int[] xs = new int[length];
						int[] ys = new int[length];
						int[] zs = new int[length];

						for (int i = 0; i < length; i++) {
							xs[i] = x;
							ys[i] = i;
							zs[i] = z;
						}
						InternalMultiChunkLoadEvent clEvent = new InternalMultiChunkLoadEvent(xs, ys, zs, chunks, pmc.getBiomeData());
						bus.dispatch(clEvent);

						break;
					}
					case 0x22: {
						PacketPS22MultiBlockChange pmbc = (PacketPS22MultiBlockChange) p;
						for (int i = 0; i < pmbc.getLength(); i++) {
							bus.dispatch(new InternalBlockChangeEvent(pmbc.getBlockData(i), pmbc.getLocation(i)));
						}
						break;
					}
					case 0x23: {
						PacketPS23BlockChange pbc = (PacketPS23BlockChange) p;
						InternalBlockChangeEvent pbcEvent = new InternalBlockChangeEvent(pbc.getData(), pbc.getLocation());
						bus.dispatch(pbcEvent);
						break;
					}
					case 0x26: { // PacketPS26MultiMapChunkData
						PacketPS26MultiMapChunkData pmmc = (PacketPS26MultiMapChunkData) p;
						for (int column = 0; column < pmmc.getColumns(); column++) {
							int x = pmmc.getX(column);
							int z = pmmc.getZ(column);
							Chunk[] chanks = pmmc.getChunks(column);
							byte[] biomes = pmmc.getBiomeData(column);
							int[] xs = new int[chanks.length];
							int[] ys = new int[chanks.length];
							int[] zs = new int[chanks.length];
							for (int i = 0; i < chanks.length; i++) {
								xs[i] = x;
								ys[i] = i;
								zs[i] = z;
							}
							bus.dispatch(new InternalMultiChunkLoadEvent(xs, ys, zs, chanks, biomes));
						}
						break;
					}
					case 0x2B: { // PacketPS2BGameStateChange
						PacketPS2BGameStateChange pgsc = (PacketPS2BGameStateChange) p;
						System.out.println("Notify: " + pgsc.getReason() + " (" + pgsc.getValue() + ")");
						break;
					}
					case 0x2E: { // PacketPS2EWindowClose
					//						PacketPS2EWindowClose pwc = (PacketPS2EWindowClose) p;
						// TODO: IMPLEMENT break;
						break;
					}
					case 0x33: { // PacketPS33UpdateSign
					//						PacketPS33UpdateSign pus = (PacketPS33UpdateSign) p;
						// TODO: IMPLEMENT
						break;
					}
					case 0x35: { // PacketPS35UpdateBlockEntity
					//						PacketPS35UpdateBlockEntity pu = (PacketPS35UpdateBlockEntity) p;
						// TODO: IMPLEMENT
						break;
					}
					case 0x38: { // PacketPS38PlayerList
						PacketPS38PlayerList ppl = (PacketPS38PlayerList) p;
						for (CompletePlayerListEntry entry : ppl.getEntries()) {
							listEntryCache.put(entry.getUUID().toString(), entry);
						}
						break;
					}
					case 0x3F: { // PacketPS3FC17PluginMessage
						PacketPS3FC17PluginMessage msg = (PacketPS3FC17PluginMessage) p;
						bus.dispatch(new PluginMessageEvent(msg.getChannel(), new String(msg.getData()), PluginMessageEvent.Direction.CLIENT));
						break;
					}
					case 0x40: { // PacketPS40Disconnect
						PacketPS40Disconnect pd = (PacketPS40Disconnect) p;
						client.disconnect(pd.getReason());
						break;
					}
					case 0x46: { // PacketPS46SetCompression
						PacketPS46SetCompression pc = (PacketPS46SetCompression) p;
						client.setCompression(pc.getThreshold());
						System.out.println("comp[res11s");
						break;
					}
				}
				break;
		}
	}

	@Override
	public void onTimeout(TimeoutEvent event) {
		System.out.println(event.getType().name() + " timed out.");
	}

	@Override
	public void onDisconnected(DisconnectedEvent event) {
		System.out.println("Disconnected: " + event.getReason());
		System.exit(0);
	}

	@Override
	public void onDisconnecting(DisconnectingEvent event) {
		System.out.println("Disconnecting: " + event.getReason());
	}

	@Override
	public void onPacketSent(PacketSentEvent event) {
	}

	@Override
	public boolean requiresEncryption() {
		return true;
	}

	@Override
	public boolean requiresPacketSizer() {
		return true;
	}

	@Override
	public EncryptionProtocol getPacketEncryptionProtocol() {
		return encryption;
	}

	@Override
	public PacketHeader getPacketHeader() {
		return header;
	}

	@Override
	public String getGameVersion() {
		return "1.8";
	}

	@Override
	public int getProtocolVersion() {
		return 47;
	}

	private boolean joined = false;
	private boolean fixing = false;

	@Override
	public void onTick() {
		if (!joined)
			return;

		LocalPlayer player = context.getWorld().getLocalPlayer();

		double x = player.getX();
		double y = player.getY();
		double z = player.getZ();
		float yaw = player.getYaw();
		float pitch = player.getPitch();

		boolean move = (x != player.getLastX()) || (y != player.getLastY()) || (z != player.getLastZ());
		boolean rotate = (yaw != player.getLastYaw()) || (pitch != player.getLastPitch());
		boolean onGround = player.isOnGround();

		if (!fixing)
			player.updatePosition(move, rotate);

		PacketPC03PlayerUpdate packet = null;
		if (move && rotate) {
			packet = new PacketPC06PlayerMoveLook(onGround, x, y, z, yaw, pitch);
		} else if (move) {
			packet = new PacketPC04PlayerMove(onGround, x, y, z);
		} else if (rotate) {
			packet = new PacketPC05PlayerLook(onGround, yaw, pitch);
		} else {
			packet = new PacketPC03PlayerUpdate(onGround);
		}

		if (!fixing)
			player.update();

		client.send(packet);
	}

	@EventTarget(priority = EventPriority.HIGHEST)
	public void onRequestRespawn(RequestRespawnEvent event) {
		System.out.println("Respawn requested! :o");
		client.send(new PacketPC16ClientStatus(ClientStatusAction.RESPAWN));
	}
}