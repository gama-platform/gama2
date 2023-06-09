/*******************************************************************************************************
 *
 * CommandExecutor.java, in gama.headless, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.headless.listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.enums.ReadyState;

import gama.core.util.IMap;
import gama.core.util.file.json.Jsoner;

public class CommandExecutor {

	private final Map<String, ISocketCommand> COMMANDS;

	public CommandExecutor() {
		final Map<String, ISocketCommand> cmds = new HashMap<>();
		cmds.put("load", new LoadCommand());

		cmds.put("play", new PlayCommand());

		cmds.put("step", new StepCommand());

		cmds.put("stepBack", new StepBackCommand());
		cmds.put("pause", new PauseCommand());
		cmds.put("stop", new StopCommand());
		cmds.put("reload", new ReloadCommand());
		cmds.put("expression", new ExpressionCommand());
		cmds.put("exit", new ExitCommand());
		cmds.put("fetch", new FetchCommand());

		COMMANDS = Collections.unmodifiableMap(cmds);
	}

	public void process(final WebSocket socket, final IMap<String, Object> map) {
		final String cmd_type = map.get("type").toString();
		ISocketCommand command = COMMANDS.get(cmd_type);

		if (command == null) {
			throw new IllegalArgumentException("Invalid command type: " + cmd_type);
		}

		var res = command.execute(socket, map);
		if(res!=null) {			
			if(socket.getReadyState().equals(ReadyState.OPEN))
				socket.send(Jsoner.serialize(res));
		}
	}

}