/**
 * This is a wrapper for c-lightning RPC interface. Copyright (C) 2020 Vincenzo Palazzo
 * vincenzopalazzodev@gmail.com
 *
 * <p>This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import jrpc.clightning.plugins.rpcmethods.ICLightningRPCMethod;
import jrpc.clightning.plugins.rpcmethods.RPCMethodType;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Features;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;

import static java.util.Objects.requireNonNull;

/** @author https://github.com/vincenzopalazzo */
public class ManifestMethodTypeAdapter extends TypeAdapter<ManifestMethod> {

  private final Gson gson;

  public ManifestMethodTypeAdapter(Gson gson) {
      this.gson = requireNonNull(gson);
  }

  @Override
  public void write(JsonWriter out, ManifestMethod value) throws IOException {
    // Stat object getManifest
    out.beginObject();
    // Stat array options
    out.name("options");
    out.beginArray();
    for (Option option : value.getOptions()) {
      out.beginObject();
      out.name("name").value(option.getNameOption());
      out.name("type").value(option.getType());
      out.name("default").value(option.getDefaultPropriety());
      out.name("description").value(option.getDescriptionPlugin());
      out.endObject();
    }
    out.endArray();
    // end options array
    out.name("rpcmethods");
    // Start array rpc method
    out.beginArray();
    for (ICLightningRPCMethod rpcMethod : value.getRpcMethods()) {
      if (!rpcMethod.getName().equals("init")
          && !rpcMethod.getName().equals("getmanifest")
          && rpcMethod.getType().equals(RPCMethodType.RPCMETHOD)) {
        out.beginObject();
        out.name("name").value(rpcMethod.getName());
        out.name("usage").value(rpcMethod.getUsage());
        out.name("description").value(rpcMethod.getDescription());
        if (rpcMethod.getLongDescription() != null && !rpcMethod.getLongDescription().isEmpty()) {
          out.name("long_description").value(rpcMethod.getLongDescription());
        }
        out.endObject();
      }
    }
    out.endArray();
    // end array rpcmethods

    // start array subscriptions
    out.name("subscriptions");
    out.beginArray();
    for (String subscription : value.getSubscriptions()) {
      out.value(subscription);
    }
    out.endArray();
    // end array subscriptions

    out.name("hooks");
    // start hooks array
    out.beginArray();

    for (String hook : value.getHooks()) {
      out.value(hook);
    }

    out.endArray();
    // end hooks array
    out.name("features");
    gson.getAdapter(Features.class).write(out, value.getFeatures());

    out.name("dynamic");
    gson.getAdapter(Boolean.class).write(out, value.getDynamic());

    out.endObject();
    // EndObject get manifest
  }

  @Override
  public ManifestMethod read(JsonReader in) {
    return gson.fromJson(in, ManifestMethod.class);
  }
}
