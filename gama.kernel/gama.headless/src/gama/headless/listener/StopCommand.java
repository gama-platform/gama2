/*******************************************************************************************************
 *
 * StopCommand.java, in gama.headless, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.headless.listener;

import org.java_websocket.WebSocket;

import gama.core.util.IMap;
import gama.dev.DEBUG;
import gama.headless.core.GamaServerMessageType;

public class StopCommand implements ISocketCommand {
	
	@Override
	public CommandResponse execute(final WebSocket socket, IMap<String, Object> map) {

		final String 	exp_id 		= map.get("exp_id") != null ? map.get("exp_id").toString() : "";
		final String 	socket_id	= map.get("socket_id") != null ? map.get("socket_id").toString() : ("" + socket.hashCode());
		final GamaWebSocketServer gamaWebSocketServer = (GamaWebSocketServer) map.get("server");
		DEBUG.OUT("stop");
		DEBUG.OUT(exp_id);

		if (exp_id == "" ) {
			return new CommandResponse(GamaServerMessageType.MalformedRequest, "For 'stop', mandatory parameter is: 'exp_id'", map, false);
		}
		var gama_exp = gamaWebSocketServer.get_listener().getExperiment(socket_id, exp_id); 
		if (gama_exp != null && gama_exp.getSimulation() != null) {
			gama_exp.controller.directPause();
			gama_exp.controller.dispose();
			return new CommandResponse(GamaServerMessageType.CommandExecutedSuccessfully, "", map, false);
		}
		else {
			return new CommandResponse(GamaServerMessageType.UnableToExecuteRequest, "Unable to find the experiment or simulation", map, false);
		}	
	}
}
