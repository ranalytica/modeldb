# -*- coding: utf-8 -*-
import json

import click

from .registry import registry
from ... import Client
from ...environment import Python
from ..._internal_utils._utils import _multiple_arguments_for_each


@registry.group(name="update")
def update():
    """Update an existing entry.
    """
    pass

@update.command(name="registeredmodel")
@click.argument("model_name", nargs=1, required=True)
@click.option("--label", "-l", multiple=True, help="Label to be associated with the object.")
@click.option("--workspace", "-w", help="Workspace to use.")
@click.option("--description", "-d", help="Description.")
def update_model(model_name, label, workspace, description):
    """Update an existing registeredmodel entry.
    """
    client = Client()
    try:
        registered_model = client.get_registered_model(model_name, workspace=workspace)
    except ValueError:
        raise click.BadParameter("model {} not found".format(model_name))

    if label:
        registered_model.add_labels(label)

    if description:
        registered_model.set_description(description)


@update.command(name="registeredmodelversion")
@click.argument("model_name", nargs=1, required=True)
@click.argument("version_name", nargs=1, required=True)
@click.option("--label", "-l", multiple=True, help="Label to be associated with the object.")
@click.option("--model", help="Path to the model.")
@click.option("--custom-module", type=click.Path(exists=True), multiple=True, help="Path to custom module file or directory.")
@click.option("--no-custom-modules", help="Flag to not upload any custom modules.", is_flag=True)
@click.option("--artifact", type=str, multiple=True, help="Path to the artifact required for the model. The format is --artifact artifact_key=path_to_artifact.")
@click.option("--workspace", "-w", help="Workspace to use.")
@click.option('--overwrite', help="Overwrite model and artifacts if already logged.", is_flag=True)
@click.option("--requirements", type=click.Path(exists=True, dir_okay=False), help="Path to the requirements.txt file.")
@click.option("--description", "-d", help="Description.")
@click.option("--attribute", type=str, multiple=True, help="Attribute to be associated with the model version. The format is --attribute attribute_key=value.")
def update_model_version(model_name, version_name, label, model, custom_module, no_custom_modules,
                         artifact, workspace, overwrite, requirements,
                         description, attribute):
    """Update an existing registeredmodelversion entry.
    """
    if custom_module and no_custom_modules:
        raise click.BadParameter("--custom-module cannot be used alongside --no-custom-modules.")
    elif no_custom_modules:
        custom_module = []
    elif not custom_module:
        custom_module = None

    client = Client()

    try:
        registered_model = client.get_registered_model(model_name, workspace=workspace)
    except ValueError:
        raise click.BadParameter("model {} not found".format(model_name))

    try:
        model_version = registered_model.get_version(name=version_name)
    except ValueError:
        raise click.BadParameter("version {} not found".format(version_name))

    _multiple_arguments_for_each(artifact, "artifact",
                                 lambda key, path: model_version.log_artifact(key, path, overwrite=overwrite),
                                 lambda: model_version.get_artifact_keys(), overwrite)

    if not overwrite and model and model_version.has_model:
        raise click.BadParameter("a model has already been associated with the version; consider using --overwrite flag")

    if label:
        model_version.add_labels(label)

    if model:
        model_version.log_model(model, custom_modules=custom_module, overwrite=overwrite)

    if requirements:
        reqs = Python.read_pip_file(requirements)
        model_version.log_environment(Python(requirements=reqs))

    if description:
        model_version.set_description(description)

    add_attributes(model_version, attribute, overwrite)


def add_attributes(model_version, attribute, overwrite):
    _multiple_arguments_for_each(attribute, "attribute",
                                 lambda key, value: model_version.add_attribute(key, json.loads(value), overwrite=overwrite),
                                 lambda: model_version._get_attribute_keys(), overwrite)
