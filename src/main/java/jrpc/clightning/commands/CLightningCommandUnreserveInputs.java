/**
 * This is a wrapper for c-lightning RPC interface.
 * Copyright (C) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningReserveInputs;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningCommandUnreserveInputs extends AbstractRPCCommand<CLightningReserveInputs>{

    public CLightningCommandUnreserveInputs() {
        super(Command.UNRESERVEINPUTS.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningReserveInputs>>(){}.getType();
    }
}
