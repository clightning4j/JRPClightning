/**
 * This is a wrapper for c-lightning RPC interface.
 * Copyright (C) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jrpc.clightning.plugins.rpcmethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractRPCMethod implements ICLightningRPCMethod {

    protected String name;
    protected String usage;
    protected String description;
    @SerializedName("long_description")
    protected String longDescription;
    @Expose
    private RPCMethodType type;

    public AbstractRPCMethod(String name, String usage, String description) {
        this(name, usage, description, description);
    }

    public AbstractRPCMethod(String name, String usage, String description, String longDescription) {
        this(name, usage, description, longDescription, RPCMethodType.RPCMETHOD);
    }

    public AbstractRPCMethod(String name, String usage, String description, String longDescription, RPCMethodType type) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.longDescription = longDescription;
        this.type = type;
    }

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public RPCMethodType getType() {
        return type;
    }


}
